package jobShop_WebProject.test;

import java.util.Map;

import jobShop_WebProject.Question;
import jobShop_WebProject.Response;
import jobShop_WebProject.utils.JsonConverter;
import jobShop_WebProject.utils.ObjectConverter;

public class TestQuestionnaire {

	
	public static void main(String[] args) {
		Question domain = new Question("Votre domain ?", new Response("IT"),new Response("Prof"),new Response("artist"));
		Question motivation = new Question("motivation ?",new Response("text"));
		domain.appendToResponse(motivation, new Response("IT"),new Response("artist"), new Response("Prof"));
		Question language = new Question("Les languages ?", new Response("C"), new Response("Python"));
		motivation.appendToResponse(language, new Response("IT"));
		
		//test de transformation -> ne fonctionne pas encore!!
		String json = JsonConverter.toJson(domain);
		Map<String, Object> mapQuestion = JsonConverter.toObject(json);
		Question tDomain = ObjectConverter.toQuestion(mapQuestion);
		
	}
}
