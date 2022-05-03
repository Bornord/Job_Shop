package jobShop_WebProject.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import jobShop_WebProject.*;
import jobShop_WebProject.Profile;

public class TestObjectToJson {
	public static void main(String args[]) {
		Student s = new Student("Willem", "Nicolas", "wnicolas", "azezaeze", 1, new Date());
		Student s2 = new Student("name", "urname", "login", "password", 0, new Date());
		DataBase main = new DataBase();

		Recruiter r = new Recruiter("willemlerecruteur", "boui", "aze", "assWorld", 9, new Date());
		//Status status = new Status();
		Offer o = new Offer(3, "titre", "sousTitre", "lalaalalalalalalal", 8000, "CDI", new Date());
		
		Profile p = new ProfileComputerScientist(new Date(), new Date(), null, 3, 2);
		s.setProfile(p);
		
		JsonConverter a = new JsonConverter(main);
		//System.out.println(a.toJson());
		System.out.println(a.toObject("\"test\""));
		System.out.println(a.toObject("{\"akina\":4,\"willem\":27,\"list\":[1,2]}"));
		
	}
}
