package jobShop_WebProject.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jobShop_WebProject.Question;
import jobShop_WebProject.Response;

public class ObjectConverter {

	public static Question toQuestion(Map<String, Object> questionMap) {
		
		String title = (String) ((Map<String, Object>)(questionMap.get("title"))).get("String");
		Map<String, Object> valResp = (Map<String, Object>)(questionMap.get("responses"));
		List<Response> responses = (List<Response>)((Map<String, Object>)toListResponse((List<Object>)valResp.get("List")));
		Question q = new Question(title);
		q.setResponses(responses);
		return q;
		
	}

	private static List<Response> toListResponse(List<Object> valResp) {
		List<Response> resp = new ArrayList();
		for (Object r : valResp) {
			resp.add(toResponse((Map<String, Object>)  r));
		}
		return resp;
	}
	
	private static Response toResponse(Map<String, Object> mapR) {
		String placeholder = (String) ((Map<String, Object>)(mapR.get("placeholder"))).get("String");
		boolean isSelected = (boolean) ((Map<String, Object>)(mapR.get("isSelected"))).get("Boolean");
		
		Map<String, Object> nextQuestion = (Map<String, Object>)(mapR.get("nextQuestion"));
		Question q= toQuestion(nextQuestion);
		return new Response(placeholder, q);
	}
}
