package jobShop_WebProject.test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonClassDescription;

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
		//System.out.println("to Profile : " +ObjectConverter.toProfile(map).toString() );
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
	
	public static void main(String[] args) {
		//testProfile();
		testStudent();
	}
}
