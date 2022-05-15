package jobShop_WebProject.test;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import jobShop_WebProject.*;
import jobShop_WebProject.utils.JsonConverter;

public class TestObjectToJson {
	
	
	
	public static void testToObject() {
		Student s = new Student("Willem", "Nicolas", "wnicolas", "azezaeze", 1, new Date());
		Student s2 = new Student("name", "urname", "login", "password", 0, new Date());
		DataBase main = new DataBase();

		Recruiter r = new Recruiter("willemlerecruteur", "boui", "aze", "assWorld", 9, new Date(), "airbus");
		//Status status = new Status();
		Offer o = new Offer(3, "titre", "sousTitre", "lalaalalalalalalal", 8000, "CDI", LocalDate.of(2025, 6, 1));
		
		Profile p = new ProfileComputerScientist(new Date(), new Date(), 3, 2);
		s.setProfile(p);
		Question q = new Question("motivation", new Response("blabla"));
		String qJson = JsonConverter.toJson(q);
		
		Map<String, Object> map = JsonConverter.toObject(qJson);
		System.out.println("Object Question to Json : " + (qJson));
		System.out.println("Json to Object Question : " + map);
		//System.out.println("responses : " + map.get("responses"));
		//System.out.println("Json to Object : " + JsonConverter.toObject("\"test\""));
		//System.out.println("Student to Json : " + JsonConverter.toJson(s));
		//System.out.println("Json to Object Student : " + JsonConverter.toObject(JsonConverter.toJson(s)));
		
	}
	
	public static void main(String args[]) {
		testToObject();
	}

}
