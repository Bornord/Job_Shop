package jobShop_WebProject.utils;

import java.util.Date;

import org.springframework.security.crypto.bcrypt.BCrypt;

import jobShop_WebProject.DataBase;
import jobShop_WebProject.Student;
import jobShop_WebProject.User;

public class TestSecurity {

	public static void main(String args[]) {
		Student s = new Student("Willem", "Nicolas", "wnicolas", "azezaeze", 1, new Date());
		Student s2 = new Student("name", "urname", "login", "password", 0, new Date());
		DataBase main = new DataBase();
		JsonConverter a = new JsonConverter(s);
		String sJson =a.toJson();
		
		//User u = Security.signIn(sJson, main);
	
		String hashed_pwd = BCrypt.hashpw("akina", BCrypt.gensalt());
		System.out.println(hashed_pwd);
		System.out.println(BCrypt.checkpw("akkygina",hashed_pwd));
		//System.out.println(u);
	}
}
