package helper;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonConverter {
	public static String getJsonText(StudentJson pcJson) throws JsonProcessingException{
		String jsonText = "";

		if(pcJson == null ) {
			return jsonText="null";
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonText = mapper.writeValueAsString(pcJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return jsonText;
	}

	public static String getJsonText(List<StudentJson> pcJsonList) throws JsonProcessingException{
		String jsonText = "";

		if(pcJsonList == null ) {
			return "null";
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonText = mapper.writeValueAsString(pcJsonList);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return jsonText;
	}

	public static StudentJson getPcJson(String jsonText) throws JsonProcessingException{
		StudentJson pcJson=null;

		if(jsonText == null ) {
			return null;
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			pcJson = mapper.readValue(jsonText, StudentJson.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return pcJson;
	}

	public static List<StudentJson> getPcJsonList(String jsonText) throws JsonProcessingException{
		List<StudentJson> pcJsonList=null;

		if(jsonText == null ) {
			return null;
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			pcJsonList = mapper.readValue(jsonText, new TypeReference<>() {});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return pcJsonList;
	}
}
