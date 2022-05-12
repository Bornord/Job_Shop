package jobShop_WebProject.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import jdk.nashorn.api.scripting.JSObject;
import jobShop_WebProject.*;

public class TestObjectToJson {
	public static void main(String args[]) {
		Student s = new Student("Willem", "Nicolas", "wnicolas", "azezaeze", 1, new Date());
		Student s2 = new Student("name", "urname", "login", "password", 0, new Date());
		DataBase main = new DataBase();

		Recruiter r = new Recruiter("willemlerecruteur", "boui", "aze", "assWorld", 9, new Date(), "airbus");
		//Status status = new Status();
		Offer o = new Offer(3, "titre", "sousTitre", "lalaalalalalalalal", 8000, "CDI", new Date());
		
		Profile p = new ProfileComputerScientist(new Date(), new Date(), 3, 2);
		s.setProfile(p);
		Question q = new Question("motivation", new Response("blabla"));
		System.out.println("Object Question to Json : " + (JsonConverter.toJson(q)));
		System.out.println("Json to Object : " + JsonConverter.toObject("\"test\""));
		//System.out.println("Json to Object Question : " + JsonConverter.toObject()));
		
	}
}
