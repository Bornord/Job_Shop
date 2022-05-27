package jobShop_WebProject.utils;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.auth0.jwt.*;
import com.auth0.jwt.algorithms.Algorithm;

import jobShop_WebProject.*;

public class Security {
	
	public static User login(String json, DataBase database, boolean bcrypt) {
		User u = null;
		//System.out.println("*****debug******* " + json);
		Map<String, Object> map = JsonConverter.toObject(json);
		Map<String, Object> pwd = (Map<String, Object>)map.get("password") ;
		Map<String, Object> mail = (Map<String, Object>)map.get("login") ;
		if(pwd != null && mail != null) {
			u = database.findWithLogin((String)mail.get("String"));
			if(u == null) {
				return null;
			}
			if(BCrypt.checkpw((String) pwd.get("String"), u.getPassword())) {
				//TODO
				//u.setAccessToken(createAccessToken(u));
				//u.setRefreshToken(createRefreshToken(u));
				return u;
			}
		}
		return null;
	}
	
	public static User login(String json, DataBase database) {
		User u = null;
		//System.out.println("*****debug******* " + json);
		Map<String, Object> map = JsonConverter.toObject(json);
		Map<String, Object> pwd = (Map<String, Object>)map.get("password") ;
		Map<String, Object> mail = (Map<String, Object>)map.get("login") ;
		if(pwd != null && mail != null) {
			u = database.findWithLogin((String)mail.get("String"));
			if(u.getPassword().equals((String) pwd.get("String"))) {
				//TODO
				//u.setAccessToken(createAccessToken(u));
				//u.setRefreshToken(createRefreshToken(u));
				return u;
			}
		}
		return null;
	}
	
	public static String createAccessToken(User user) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.HOUR_OF_DAY, 1);
		Date exp = c.getTime();
		return createToken(user, exp);
	}
	
	public static String createRefreshToken(User user) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, 7);
		Date exp = c.getTime();
		return createToken(user, exp);
	}
	
	private static String createToken(User user, Date exp) {
		Algorithm algo = Algorithm.HMAC256(BCrypt.gensalt());
		String token = JWT.create().withClaim("id", user.getId())
				.withClaim("role", user.getRole().toString())
				.withExpiresAt(exp)
				.sign(algo);
		return token;
	}
	
	public static Request logout(String json, DataBase main) {
		//detruire l'accestoken et refreshtoken
		Map<String, Object> map = JsonConverter.toObject(json);
		int id = (int)((Map<String, Object>)map.get("id")).get("Integer");
		User u = main.findUser(id);
		if(u!=null) {
			u.destroyTokens();
			return new Request(JsonConverter.toJson(u), 200);
		} else {
			return new Request(json, 404);
		}
	}
	
	public static <T extends User> void signIn(String json,DataBase database,T user) {
		List<Map<String,String>> fields = Student.getFields();
		Map<String, Object> jsonObject = JsonConverter.toObject(json);
		
		for (Map<String, String> map : fields) {
			boolean found = false;
			String fieldKey = map.keySet().toArray(new String[0])[0];
			String fieldType = map.get(fieldKey);
			if(fieldKey.equalsIgnoreCase("role")
					|| fieldKey.equalsIgnoreCase("creationDate")) {
				continue;
			}
			Object value = null;
			for(String key:jsonObject.keySet()) {
				if(key.equalsIgnoreCase(fieldKey)) {
					value = ((Map<String, Object>) jsonObject.get(key)).get(fieldType);
					found = true;
					if( key.equalsIgnoreCase("password")) {
						String hashed_pwd = BCrypt.hashpw((String) value, BCrypt.gensalt());
						value = hashed_pwd;
					}
				}
			}
			if(found) {
				System.out.println(value);
				Method method = null;
				try {
					method = Student.class.getMethod("set" +capitalize(fieldKey), getClassByName(fieldType));
				} catch (NoSuchMethodException | SecurityException | IllegalArgumentException  e) {
					//e.printStackTrace();
				}
				try {
					method.invoke(user, value);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			}
		}
		Date date = new Date();
		user.setCreationDate(date);
		user.setRole(LabelRole.STUDENT);
	}
	
	public static Student signInStudent(String json, DataBase database) {
		Map<String, Object> jsonObject = JsonConverter.toObject(json);
		String mail = (String)((Map<String, Object>)jsonObject.get("login")).get("String") ;
		if(database.findWithLogin(mail) == null) {
			Student student = new Student();
			signIn(json, database, student);
			System.out.println(student);
			student.setStatus(2000);
			student.setRole(LabelRole.STUDENT);
			database.addUser(student);
			return student;
		}
		return null;
	}
	public static Recruiter signInRecruiter(String json, DataBase database) {
		Map<String, Object> jsonObject = JsonConverter.toObject(json);
		String mail = (String)((Map<String, Object>)jsonObject.get("login")).get("String") ;
		if(database.findWithLogin(mail) == null) {
			Recruiter recruiter = new Recruiter();
			signIn(json, database, recruiter);
			System.out.println(recruiter);
			recruiter.setStatus(2001);
			recruiter.setRole(LabelRole.RECRUITER);
			database.addUser(recruiter);
			return recruiter;
		}
		return null;
	}
	public static Admin signInAdmin(String json, DataBase database) {
		Map<String, Object> jsonObject = JsonConverter.toObject(json);
		String mail = (String)((Map<String, Object>)jsonObject.get("login")).get("String") ;
		if(database.findWithLogin(mail) == null) {
			Admin admin = new Admin();
			signIn(json, database, admin);
			System.out.println(admin);
			admin.setRole(LabelRole.ADMIN);
			admin.setStatus(2002);
			database.addUser(admin);
			return admin;
		}
		return null;
	}
	/*public static User signIn(String json, DataBase database,boolean bcrypt) {
		User u = signIn(json, database);
		if(bcrypt) {
			u.setPassword(BCrypt.hashpw(u.getPassword(), BCrypt.gensalt()));
		}
		return u;
	}*/

	public static Class getClassByName(String name) {
		switch (name) {
		case "String":
			return String.class;
		case "int":case"Int":case "Integer":
			return Integer.class;
		case "Double":case"double":
			return Double.class;
		case "bool":case"boolean":case "Boolean":
			return Boolean.class;
		default:
			break;
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
			if (u!=null) {
				database.deleteUser(u);
				return new Request(JsonConverter.toJson(u), 200);
			}
			//return json;
		}
		return new Request(json, 404);
		//return json;
	}
	public static String capitalize(String str){
	    if(str == null) return str;
	    return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	
	/*User user = null;
	String mail = (String)((Map<String, Object>)map.get("login")).get("String") ;
	if(database.findWithLogin(mail) == null) {
		String name = (String)((Map<String, Object>)map.get("name")).get("String") ;
		String surname = (String)((Map<String, Object>)map.get("surname")).get("String") ;
		Date date = new Date();
		Map<String, Object> role = (Map<String, Object>)map.get("status") ;
		String pwd = (String)((Map<String, Object>)map.get("password")).get("String") ;
		String hashed_pwd = BCrypt.hashpw(pwd, BCrypt.gensalt());
		//!!!!role > int
		if(role.get("Integer").equals(2002)) {
			user = new Admin(name,surname, mail, hashed_pwd, 0, date);
		} else if(role.get("Integer").equals(2001) && map.get("entreprise") != null) {
			String entreprise = (String)((Map<String, Object>)map.get("company")).get("String") ;
			user = new Recruiter(name,surname, mail, hashed_pwd, 0, date, entreprise);
		} else if(role.get("Integer").equals(2000)) {
			user = new Student(name,surname, mail, hashed_pwd, 0, date);
		} else {//1999
			return null;
			//user = new User(name,surname, mail, pwd, 0, LabelRole.UNLOGGED, date);
		}
		//TODO
		//user.setAccessToken(createAccessToken(user));
		//user.setRefreshToken(createRefreshToken(user));
		database.addUser(user);
		return user;	*/
}
