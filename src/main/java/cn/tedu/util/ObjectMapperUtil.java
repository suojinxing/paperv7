package cn.tedu.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtil {
	private static final ObjectMapper MAPPER = new ObjectMapper();

	public static String toJSON(Object obj) {
		String json = null;
		try {
			json = MAPPER.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return json;
	}

	public static <T> T toObject(String json, Class<T> targetClass) {
		T t = null;
		try {
//			t = MAPPER.readValue(MAPPER.readTree(json).traverse(),
//					MAPPER.getTypeFactory().constructCollectionType(List.class,
//							targetClass));
			t = MAPPER.readValue(json, targetClass);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return t;
	}
}
