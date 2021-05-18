package com.example.demo.reflex;

import lombok.Data;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chengzhe yan
 * @description 测试反射获取父类字段
 * @date 2021/5/10 12:31 下午
 */
public class SuperTest {
	/**
	 * 字段集合
	 */
	public static Map<String, Field> fieldMap = new HashMap<>();
	/**
	 * get方法集合
	 */
	public static Map<String, Method> getMethod = new HashMap<>();
	/**
	 * set方法集合
	 */
	public static Map<String, Method> setMethod = new HashMap<>();

	public static void main(String[] args) {
		Field[] fields = A.class.getDeclaredFields();
		setFieldMep(A.class);

		setMethod.putAll(setMethodMep(A.class, "set"));
		getMethod.putAll(setMethodMep(A.class, "get"));
		//测试结果
		fieldMap.entrySet().forEach(System.out::println);
		getMethod.entrySet().forEach(System.out::println);
		setMethod.entrySet().forEach(System.out::println);
		Assert.isTrue(fieldMap.size() == 2, "字段数量不对");
		Assert.isTrue(getMethod.size() == 2, "get方法数量不对");
		Assert.isTrue(setMethod.size() == 2, "set方法数量不对");
	}

	public static <T> HashMap<String, Method> setMethodMep(Class<T> clazz, String methodType) {
		HashMap<String, Method> methodHashMap = new HashMap<>();
		//遍历field 找到 set方法
		fieldMap.forEach((fieldName, value) -> {
			//拼接 方法名称
			String methodName = methodType + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			Method method;
			if ("set".equals(methodType)) {
				method = findMethod(methodName, clazz, value.getType());
			} else {
				method = findMethod(methodName, clazz);
			}

			if (method != null) {
				methodHashMap.put(fieldName, method);
			}
		});
		return methodHashMap;
	}


	public static <T> Method findMethod(String methodName, Class<T> clazz, Class<?>... parameterTypes) {
		try {
			return clazz.getDeclaredMethod(methodName, parameterTypes);
		} catch (NoSuchMethodException e) {
			if (clazz.getSuperclass() != null) {
				return findMethod(methodName, clazz.getSuperclass(), parameterTypes);
			}
			return null;
		}
	}

	public static <T> void setFieldMep(Class<T> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			fieldMap.put(field.getName(), field);
		}
		if (clazz.getSuperclass() != null) {
			setFieldMep(clazz.getSuperclass());
		}
	}

}

@Data
class A extends B {
	private int aTest;
}

@Data
class B {
	private int bTest;
}