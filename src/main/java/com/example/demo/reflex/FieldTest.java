package com.example.demo.reflex;


import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author chengzhe yan
 * @description
 * @date 2021/4/1 3:00 下午
 */
public class FieldTest<T, K extends FieldTest<T, K>> {

	private String string;
	private Object object;
	private K t;

	private List<K> kList;


	public static void main(String[] args) throws NoSuchFieldException {
		Field file = FieldTest.class.getDeclaredField("kList");
		System.out.println(file.getType());
		System.out.println(file.getGenericType());
		/*
			getGenericType（） 和 getType 一般情况下返回的是一样的 ，
			如果出现泛型，getGenericType会返回携带泛型的信息，而getType只会返回object ，
			场景 ： 如果定一个list ， 通过getType只能获取到list类型，不知道list中的元素类型，此时可以通过file.getGenericType进一步取到元素类型
		 */
		final Type elementType = ((ParameterizedType) file.getGenericType()).getActualTypeArguments()[0];
		System.out.println(elementType.getTypeName());
	}
}
