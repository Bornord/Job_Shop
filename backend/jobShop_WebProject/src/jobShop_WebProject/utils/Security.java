package jobShop_WebProject.utils;


import java.util.Date;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCrypt;

import jobShop_WebProject.*;

public class Security {
	
	public static User login(String json,DataBase database) {
		User u = null;
		JsonConverter jc = new JsonConverter();
		Map<String, Object> map = jc.toObject(json);
		Map<String, Object> pwd = (Map<String, Object>)map.get("password") ;
		Map<String, Object> mail = (Map<String, Object>)map.get("login") ;
		if(pwd != null && mail != null) {
			u = database.findWithLogin((String)mail.get("String"));
			if(BCrypt.checkpw((String) pwd.get("String"), u.getPassword())) {
				return u;
			}
		}
		return null;
	}
	
	public static Request logout(String json) {
		return new Request(json, 200);
	}
	
	public static User signIn(String json, DataBase database) {
		User user = null;
		JsonConverter jc = new JsonConverter();
		Map<String, Object> map = jc.toObject(json);
		String mail = (String)((Map<String, Object>)map.get("login")).get("String") ;
		if(database.findWithLogin(mail) == null) {
			String name = (String)((Map<String, Object>)map.get("name")).get("String") ;
			String surname = (String)((Map<String, Object>)map.get("surname")).get("String") ;
			Date date = new Date();
			Map<String, Object> role = (Map<String, Object>)map.get("role") ;
			String pwd = (String)((Map<String, Object>)map.get("password")).get("String") ;
			String hashed_pwd = BCrypt.hashpw(pwd, BCrypt.gensalt());
			//!!!!role > int
			if(role.get("Integer").equals(2002)) {
				user = new Admin(name,surname, mail, hashed_pwd, 0, date);
			} else if(role.get("Integer").equals(2001)) {
				user = new Recruiter(name,surname, mail, hashed_pwd, 0, date);
			} else if(role.get("Integer").equals(2000)) {
				user = new Student(name,surname, mail, hashed_pwd, 0, date);
			} else /*1999*/{
				return null;
				//user = new User(name,surname, mail, pwd, 0, LabelRole.UNLOGGED, date);
			}
			database.addUser(user);
			return user;	
		}	
		return null;
	}

	public static Request signOut(String json, DataBase database) {
		User u = null;
		JsonConverter jc = new JsonConverter();
		Map<String, Object> map = jc.toObject(json);
		Map<String, Object> mail = (Map<String, Object>)map.get("login") ;
		if(mail != null) {
			u = database.findWithLogin((String)mail.get("String"));
			database.deleteUser(u);
			return new Request(json, 200);
		}
		return new Request(json, 404);
	}
	
}
