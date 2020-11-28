package com.sxt.sys.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	private static final String NULL_EXCEPTION = "系统异常：JsonJacksonConverter.toString返回空值";
	private static final String CONVERTE_EXCEPTION = "Json转换异常";
	private static ObjectMapper objectMapper = new ObjectMapper();
	static {
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}

	public static String toJson(Object obj) {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NULL_EXCEPTION;
	}

	public static <T> T toObject(String obj, Class<T> clazz) {
		try {
			return objectMapper.readValue(obj, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
