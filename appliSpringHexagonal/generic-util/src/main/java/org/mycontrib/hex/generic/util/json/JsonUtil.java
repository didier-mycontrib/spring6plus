package org.mycontrib.hex.generic.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	
	private static ObjectMapper jacksonObjectMapper = new ObjectMapper();
	
	public static <T> T parse(String jsonString,Class<T> objClass) {
		T res=null;
		jacksonObjectMapper.configure(
				DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			res =  jacksonObjectMapper.readValue(jsonString, objClass);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return res;
	}
	//exemple: Notification<Devise> notifDevise = JsonUtil.parse(jsonString, new TypeReference<Notification<Devise>>() {});
	public static <T> T parse(String jsonString,TypeReference<T> typeReference) {
		T res=null;
		jacksonObjectMapper.configure(
				DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			res =  jacksonObjectMapper.readValue(jsonString, typeReference);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public static <T> String stringify(T obj) {
		String res=null;
		try {
			res =  jacksonObjectMapper.writeValueAsString(obj);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return res;
	}

}
