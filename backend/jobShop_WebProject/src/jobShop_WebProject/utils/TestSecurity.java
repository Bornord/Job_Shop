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
		//creer un token :
		/*Algorithm algo = Algorithm.HMAC256(BCrypt.gensalt());
		String token = JWT.create().withClaim("name", s.getName())
				.withClaim("surname", s.getSurname())
				.withClaim("login", s.getLogin())
				.sign(algo);

		DecodedJWT dec = JWT.decode(token);*/
		//exemple:
		/*DecodedJWT s = JWT.decode("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c");
		System.out.println(s.getClaim("name"));*/
		System.out.println("Token : " + Security.getLoginToken(json));
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
