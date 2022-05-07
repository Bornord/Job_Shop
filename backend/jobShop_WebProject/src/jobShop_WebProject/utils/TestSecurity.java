package jobShop_WebProject.utils;

import java.util.Date;

import org.springframework.security.crypto.bcrypt.BCrypt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import jobShop_WebProject.DataBase;
import jobShop_WebProject.Student;
import jobShop_WebProject.User;

import com.auth0.jwt.*;

public class TestSecurity {
	private static Student s;
	private static Student s2;
	private static DataBase main;

	
	public static void testPwd() {
		String hashed_pwd = BCrypt.hashpw("akina", BCrypt.gensalt());
		System.out.println(hashed_pwd);
		System.out.println(BCrypt.checkpw("akkygina",hashed_pwd));
		
		//User u = Security.signIn(sJson, main);
		//System.out.println(u);
	}
	
	public static void testToken(String json) {
		
		String tokenAccess = Security.createAccessToken(s);
		String tokenRefresh = Security.createRefreshToken(s);
		System.out.println("Token d'accès : " + tokenAccess);
		System.out.println("date d'expiration token d'accès : " + JWT.decode(tokenAccess).getExpiresAt());

		System.out.println("date d'expiration token de refresh : " + JWT.decode(tokenRefresh).getExpiresAt());
	}
	
	public static void main(String args[]) {
		s = new Student("Willem", "Nicolas", "wnicolas", "azezaeze", 1, new Date());
		s2 = new Student("name", "urname", "login", "password", 0, new Date());
		main = new DataBase();

		//testPwd();
		String json = JsonConverter.toJson(s);
		System.out.println("Json : " + json);
		testToken(json);
	}
}
