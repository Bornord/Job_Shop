package jobShop_WebProject.utils;

import jobShop_WebProject.Question;
import jobShop_WebProject.Response;

public class TestQuestionnaire {

	
	public static void main(String[] args) {
		Question domain = new Question("Votre domain ?", new Response("IT"),new Response("Prof"),new Response("artist"));
		Question motivation = new Question("motivation ?",new Response("text"));
		domain.appendToResponse(motivation, new Response("IT"),new Response("artist"));
		
	}
}
