package jobShop_WebProject.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonClassDescription;

import jobShop_WebProject.Admin;
import jobShop_WebProject.Profile;
import jobShop_WebProject.Question;
import jobShop_WebProject.Response;
import jobShop_WebProject.Student;
import jobShop_WebProject.SurveyAnswer;
import jobShop_WebProject.utils.JsonConverter;
import jobShop_WebProject.utils.ObjectConverter;

public class TestToObject {

	public static void testProfile() {

		Profile p = new Profile();
		//local date annee, mois, jour
		p.setStartDate(LocalDate.of(2022,1,31));
		p.setEndDate(LocalDate.of(2022,12,31));
		p.setIdUser(1);
		p.setIsRecruiter(true);
		p.setTerm(3);
		List<SurveyAnswer> l = new ArrayList<SurveyAnswer>();
		l.add(new SurveyAnswer(1,2,3, "blabla"));
		p.setSurveyAnswer(l);
		
		String toJson = JsonConverter.toJson(p);
		System.out.println("to Json : "+ toJson);
		Map<String, Object> map = JsonConverter.toObject(toJson);
		System.out.println("to map : " + map);
		System.out.println("to Profile : " +ObjectConverter.toProfile(map, true).toString() );
	}
	
	private static void testStudent() {
		Student s = new Student("Willem", "Nicolas", "wnicolas", "azezaeze", 1, new Date());
		String toJson = JsonConverter.toJson(s);
		System.out.println("to Json : "+ toJson);
		Map<String, Object> map = JsonConverter.toObject(toJson);
		System.out.println("to map : " + map);
		Student student = ObjectConverter.toStudent(map);
		System.out.println("to Student : " + student.getName() + " " + student.getPassword());
	}
	
	public static void testQuestion() {
		//pour addQuestionToQuestion
		String questionJson = "{\"id\":93,\"question\":{\"id\":0,\"title\":\"Votre domain ?\",\"responses\":[{\"id\":0,\"placeholder\":\"IT\",\"isSelected\":false,\"nextQuestion\":{\"id\":0,\"title\":\"Les languages ?\",\"responses\":[{\"id\":0,\"placeholder\":\"C\",\"isSelected\":false,},{\"id\":0,\"placeholder\":\"Python\",\"isSelected\":false,},]}},{\"id\":0,\"placeholder\":\"Prof\",\"isSelected\":false,},{\"id\":0,\"placeholder\":\"artist\",\"isSelected\":false,},]}}";
		Map<String, Object> mapQ = JsonConverter.toObject(questionJson);
		int idPrevious = (int)((Map<String, Object>)mapQ.get("id")).get("Integer");
		Question q = ObjectConverter.toQuestion((Map<String, Object>)mapQ.get("question"));
		System.out.println("question : " + q.toString() + ", previous question id : " + idPrevious);
	}
	
	private static void testAddSurvey() {
		String questionJson = "{\"name\":\"nameSurvey\",\"question\":{\"id\":0,\"title\":\"Votre domain ?\",\"responses\":[{\"id\":0,\"placeholder\":\"IT\",\"isSelected\":false,\"nextQuestion\":{\"id\":0,\"title\":\"Les languages ?\",\"responses\":[{\"id\":0,\"placeholder\":\"C\",\"isSelected\":false,},{\"id\":0,\"placeholder\":\"Python\",\"isSelected\":false,},]}},{\"id\":0,\"placeholder\":\"Prof\",\"isSelected\":false,},{\"id\":0,\"placeholder\":\"artist\",\"isSelected\":false,},]}}";
		Map<String, Object> mapQ = JsonConverter.toObject(questionJson);
		String name = (String)((Map<String, Object>)mapQ.get("name")).get("String");
		Question q = ObjectConverter.toQuestion((Map<String, Object>)mapQ.get("question"));
		System.out.println("first question : " + q.toString() + ", survey name : " + name);
	
	}
	
	public static void main(String[] args) {
		//testProfile();
		Admin a = new Admin("Akina", "Renard","akinaLaBoss", "pwdadmin", 0, new Date());
		System.out.println(JsonConverter.toJson(a));
		testStudent();
		//testQuestion();
		//testAddSurvey();
		
	}
}
