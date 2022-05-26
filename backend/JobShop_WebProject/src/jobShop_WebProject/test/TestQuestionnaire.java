package jobShop_WebProject.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jobShop_WebProject.DataBase;
import jobShop_WebProject.FirstQuestion;
import jobShop_WebProject.Profile;
import jobShop_WebProject.Question;
import jobShop_WebProject.Response;
import jobShop_WebProject.ServerPostCases;
import jobShop_WebProject.Student;
import jobShop_WebProject.SurveyAnswer;
import jobShop_WebProject.utils.JsonConverter;
import jobShop_WebProject.utils.ObjectConverter;

public class TestQuestionnaire {

	public static void testQuestion() {
		Response  it = new Response("IT");
		Question domain = new Question("Votre domain ?",it,new Response("Prof"),new Response("artist"));
		Question language = new Question("Les languages ?", new Response("C"), new Response("Python"));
		//domain.appendToResponse(language, it);
		//Question motivation = new Question("motivation ?",new Response("text"));
		//language.appendToAllResponses(motivation);
		FirstQuestion f = new FirstQuestion(0, "current");
		f.setIdFirstQuestion(3);
		System.out.println("first question to json : " + JsonConverter.toJson(f));
		String json = JsonConverter.toJson(domain);
		System.out.println("toJson : " + json);
		Map<String, Object> mapQuestion = JsonConverter.toObject(json);
		for (String string : mapQuestion.keySet()) {
			System.out.println("key : "+string);
			System.out.println("value : "+mapQuestion.get(string));
		}
//		System.out.println("toMap : " + mapQuestion);
		Question tDomain = ObjectConverter.toQuestion(mapQuestion, new DataBase());
		System.out.println("toQuestion : " + tDomain.toString());
		System.out.println(tDomain.getResponses()/*.get(1).getNextQuestion().getTitle()*/);
		
	}
	
	public static void testMatch() {
		//test match
		DataBase d = new DataBase();
		Profile student = new Profile(LocalDate.of(2022, 6, 4), LocalDate.of(2022, 12, 5),
				3, 0, false, new ArrayList<SurveyAnswer>());
		Profile recruiter1 = new Profile(LocalDate.of(2022, 6, 4), LocalDate.of(2022, 12, 5),
				6, 0, false, new ArrayList<SurveyAnswer>());
		System.out.println("1er match, recruteur cherche pour 6 mois et student pour 3 : " 
				+ d.match(recruiter1, student));
		Profile recruiter2 = new Profile(LocalDate.of(2022, 8, 4), LocalDate.of(2022, 11, 5),
				2, 0, false, new ArrayList<SurveyAnswer>());
		System.out.println("2eme match, recruteur cherche pour 2 mois et student pour 3 " 
				+ "et recruiter dispo après student : "
				+ d.match(recruiter2, student));
		
	}
	public static void testMatch2() {
		DataBase d = new DataBase();
		List<SurveyAnswer> r = new ArrayList<>();
		List<SurveyAnswer> s1 = new ArrayList<>();
		List<SurveyAnswer> s2 = new ArrayList<>();
		r.add(new SurveyAnswer(0, 1, 1, ""));
		r.add(new SurveyAnswer(0, 2, 2, ""));
		r.add(new SurveyAnswer(0, 3, 3, ""));
		r.add(new SurveyAnswer(0, 4, 4, ""));
		
		s1.add(new SurveyAnswer(0, 1, 1, ""));
		s1.add(new SurveyAnswer(0, 2, 2, ""));
		s1.add(new SurveyAnswer(0, 3, 4, ""));
		s1.add(new SurveyAnswer(0, 4, 1, ""));
		
		s2.add(new SurveyAnswer(0, 1, 1, ""));
		s2.add(new SurveyAnswer(0, 2, 2, ""));
		s2.add(new SurveyAnswer(0, 3, 3, ""));
		s2.add(new SurveyAnswer(0, 4, 1, ""));
		
		Profile recruiter1 = new Profile(LocalDate.of(2022, 6, 4), LocalDate.of(2022, 12, 5),
				6, 0, false, r);
		Profile student = new Profile(LocalDate.of(2022, 6, 4), LocalDate.of(2022, 12, 5),
				6, 1, false, s1);
		Profile student2 = new Profile(LocalDate.of(2022, 6, 4), LocalDate.of(2022, 12, 5),
				6, 2, false, s2);
		List<Profile> ss = new ArrayList<>();
		ss.add(student2);
		ss.add(student);
		System.out.println(d.match(recruiter1, ss));
	}
	
	public static void main(String[] args) {
		//testMatch();
		//testMatch2();
		testQuestion();
		//ServerPostCases.addQuestionToEnd(null, null, new DataBase());
	}
}
