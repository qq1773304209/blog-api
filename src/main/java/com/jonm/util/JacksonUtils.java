package com.jonm.util;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jonm.respond.Result;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Description: Jackson Object Mapper
 * @Author: Naccl
 * @Date: 2020-11-07
 */
public class JacksonUtils {
	private static ObjectMapper objectMapper = new ObjectMapper();

	public static String writeValueAsString(Object value) {
		try {
			return objectMapper.writeValueAsString(value);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static <T> T readValue(String content, Class<T> valueType) {
		try {
			return objectMapper.readValue(content, valueType);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T> T readValue(String content, TypeReference<T> valueTypeRef) {
		try {
			return objectMapper.readValue(content, valueTypeRef);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T> T readValue(InputStream src, Class<T> valueType) {
		try {
			return objectMapper.readValue(src, valueType);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T> T convertValue(Object fromValue, Class<T> toValueType) {
		return objectMapper.convertValue(fromValue, toValueType);
	}


	public static String object2Json(Result ret){
		return JSON.toJSONString(ret);
	}

	public static Object Json2object(String jsonStr, Class cls){
		return JSON.parseObject(jsonStr, cls);
	}
}
