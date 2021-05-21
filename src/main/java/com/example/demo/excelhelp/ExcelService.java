package com.example.demo.excelhelp;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.example.demo.util.Strings;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ExcelService<T> {

	/**
	 * 输出为excel
	 *
	 * @param list           需要转换为excel的数据
	 * @param title          excel文件名
	 * @param sheetName      表单名称
	 * @param pojoClass      需要转换为excel的数据实体类.class
	 * @param fileName       输出文件名
	 * @param isCreateHeader 是否创建文件头
	 */
	public Workbook exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName, boolean isCreateHeader) {
		ExportParams exportParams = new ExportParams(title, sheetName);
		exportParams.setCreateHeadRows(isCreateHeader);
		return defaultExport(list, pojoClass, fileName, exportParams);
	}

	/**
	 * @param path
	 * @return
	 */
	public List<T> readExcel(String path, Class<T> clazz) {
		List<T> list = new ArrayList<>();
		File excel = new File(path);
		try {
			if (excel.isFile() && excel.exists()) {   //判断文件是否存在

				String[] split = excel.getName().split("\\.");  //.是特殊字符，需要转义！！！！！
				Workbook wb;
				//根据文件后缀（xls/xlsx）进行判断
				if ("xls".equals(split[1])) {
					FileInputStream fis = new FileInputStream(excel);   //文件流对象
					wb = new HSSFWorkbook(fis);
				} else if ("xlsx".equals(split[1])) {
					wb = new XSSFWorkbook(excel);
				} else {
					System.out.println("文件类型错误!");
					return null;
				}
				//开始解析
				Sheet sheet = wb.getSheetAt(0);     //读取sheet 0

				int firstRowIndex = sheet.getFirstRowNum() + 1;   //第一行是列名，所以不读
				int lastRowIndex = sheet.getLastRowNum();
				log.debug("firstRowIndex: " + firstRowIndex);
				log.debug("lastRowIndex: " + lastRowIndex);

				//解析类型，得到字段列表
				for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {   //遍历行
					log.debug("rIndex: " + rIndex);
					Row row = sheet.getRow(rIndex);
					if (row != null) {
						//通过反射进行处理
						Field[] fields = getSortFilesByOrderNum(clazz);
						T t = dealRow(row, clazz, fields);
						list.add(t);
					}
				}
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			log.error("read excel error ", e);
		}
		return list;
	}

	private static <T> Field[] getSortFilesByOrderNum(Class<T> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		Field[] sortField = new Field[fields.length];
		for (Field field : fields) {
			field.setAccessible(true);
			Excel excel = field.getAnnotation(Excel.class);
			String number = excel.orderNum();
			//按照 orderid进行排序
			sortField[Integer.parseInt(number)] = field;
		}
		return sortField;
	}

	/**
	 * 处理一行数据
	 *
	 * @param row   一行数据
	 * @param clazz 实体类
	 * @param <T>   类
	 * @return 需要的类
	 */
	@SneakyThrows
	private static <T> T dealRow(Row row, Class<T> clazz, Field[] sortField) {
		T tt = clazz.newInstance();
		int index = row.getFirstCellNum();
		for (Field field : sortField) {
			//遍历设置数值
			field.setAccessible(true);
			Cell cellInfo = row.getCell(index++);
			if (cellInfo != null) {
				field.set(tt, cellInfo.toString());
			}
		}
		return tt;
	}

	/**
	 * 默认输出
	 *
	 * @param list
	 * @param pojoClass
	 * @param fileName
	 * @param exportParams
	 */
	private Workbook defaultExport(List<?> list, Class<?> pojoClass, String fileName, ExportParams exportParams) {
		Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
		if (workbook != null) {
			if (Strings.isNotNullOrEmpty(fileName)) {
				//1。 按照文件名进行下载
				downLoadExcel(fileName, workbook);
			} else {
				//2。 返回 workbook 由业务方自行决定如何下载
				return workbook;
			}
		} else {
			log.error("输出excel表格数据不能为null");
		}
		return null;
	}

	/**
	 * 根据文件名 ， excel表格数据，最终生成excel文件
	 *
	 * @param fileName 写入的本地文件
	 * @param workbook excel元数据
	 */
	private void downLoadExcel(String fileName, Workbook workbook) {
		try {
			//打开文件
			OutputStream var1 = new FileOutputStream(fileName);
			//写入本地
			workbook.write(var1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
