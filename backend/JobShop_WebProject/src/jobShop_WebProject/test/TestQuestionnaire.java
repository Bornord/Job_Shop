package jobShop_WebProject.test;

import java.util.Map;

import jobShop_WebProject.Question;
import jobShop_WebProject.Response;
import jobShop_WebProject.utils.JsonConverter;
import jobShop_WebProject.utils.ObjectConverter;

public class TestQuestionnaire {

	
	public static void main(String[] args) {
		Response  it = new Response("IT");
		Question domain = new Question("Votre domain ?",it,new Response("Prof"),new Response("artist"));
		//Question language = new Question("Les languages ?", new Response("C"), new Response("Python"));
		//domain.appendToResponse(language, it);
		//Question motivation = new Question("motivation ?",new Response("text"));
		//language.appendToAllResponses(motivation);
		

		String json = JsonConverter.toJson(domain);
		System.out.println("toJson : " + json);
		Map<String, Object> mapQuestion = JsonConverter.toObject(json);
		System.out.println("toMap : " + mapQuestion);
		Question tDomain = ObjectConverter.toQuestion(mapQuestion);
		System.out.println("toQuestion : " + tDomain.toString());
	}
}
