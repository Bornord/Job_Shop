package jobShop_WebProject.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import jobShop_WebProject.Admin;
import jobShop_WebProject.Blog;
import jobShop_WebProject.DataBase;
import jobShop_WebProject.LabelRole;
import jobShop_WebProject.Offer;
import jobShop_WebProject.Profile;
import jobShop_WebProject.Question;
import jobShop_WebProject.Recruiter;
import jobShop_WebProject.Response;
import jobShop_WebProject.Student;
import jobShop_WebProject.SurveyAnswer;
import jobShop_WebProject.User;

public class ObjectConverter {

	/**
	 * QUESTION / RESPONSE
	 * @param questionMap
	 * @param main
	 * @return
	 */
	public static Question toQuestion(Map<String, Object> questionMap, DataBase main) {
		try {			
			String title = (String) ((Map<String, Object>)(questionMap.get("title"))).get("String");
			System.out.println(title);
			System.out.println("------------");
			Map<String, Object> valResp = (Map<String, Object>)(questionMap.get("responses"));
			System.out.println(valResp);
			System.out.println("------------");
			Question q = new Question(title);
			if(valResp.get("String") != null) {
				List<Response> responses = new ArrayList<>();
				responses.add(new Response("",0,true));
				q.setResponses(responses);
				return q;
			}
			
			List<Map<String, Object>> l = (List<Map<String, Object>>)valResp.get("List");
			List<Response> responses = (List<Response>)toListResponse(l, main);
			q.setResponses(responses);
			return q;
		}catch (Exception e) {
			return null;
		}
		
	}

	private static List<Response> toListResponse(List<Map<String, Object>> valResp, DataBase main) {
		List<Response> resp = new ArrayList();
		for (Map<String, Object> r : valResp) {
			resp.add(toResponse(r, main));
		}
		return resp;
	}
	
	private static Response toResponse(Map<String, Object> mapR, DataBase main) {
		System.out.println(mapR);
		String placeholder = (String) ((Map<String, Object>)(mapR.get("placeholder"))).get("String");
		
		//boolean isSelected = (boolean) ((Map<String, Object>)(mapR.get("isSelected"))).get("Boolean");
		
		Map<String, Object> nextQuestion = (Map<String, Object>)(mapR.get("nextQuestion"));
		Response ret;
		if(nextQuestion != null && !nextQuestion.containsKey("Error")){
			Question next = main.addQuestion(toQuestion(nextQuestion, main));
			ret = new Response(placeholder, next.getId(),false);
		} else {
			System.out.println("===============");
			System.out.println(placeholder);
			ret =  new Response(placeholder);
		}

		return ret;
	}
	
	public static Offer toOffer(Map<String, Object> map) {
		Offer offer = null;
		try {
			String title =(String)((Map<String, Object>) map.get("title")).get("String");
			String subTitle =(String)((Map<String, Object>) map.get("subTitle")).get("String");
			String description =(String)((Map<String, Object>) map.get("description")).get("String");
			int salary =(int)((Map<String, Object>) map.get("salary")).get("Integer");
			String Contract =(String)((Map<String, Object>) map.get("contract")).get("String");

			String s = (String)((Map<String, Object>) map.get("startDate")).get("String");
			String e = (String)((Map<String, Object>) map.get("endDate")).get("String");
			LocalDate startDate = LocalDate.parse(s);
			LocalDate endDate = LocalDate.parse(e);
			
			Profile ideal = toProfile((Map<String, Object>) map.get("profile"), true);
			int term =(int)((Map<String, Object>) map.get("term")).get("Integer");
			offer = new Offer(0, title, subTitle, description, salary, Contract, startDate, endDate, term);
			offer.setIdealProfile(ideal);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return offer;
	}
	
	
	/**
	 * PROFILE
	 * @param profile
	 * @param isRecruiter
	 * @return
	 */
	public static Profile toProfile(Map<String, Object> profile, boolean isRecruiter) {
		
		
		int idUser =(int)((Map<String, Object>) profile.get("idUser")).get("Integer");
		System.out.println("id user : " + idUser);
		int term =(int)((Map<String, Object>) profile.get("term")).get("Integer");
		System.out.println("term : " + term);
		boolean recruiter = (boolean)((Map<String, Object>) profile.get("isRecruiter")).get("Boolean");
		System.out.println("isRecruiter : "+recruiter);
		String startDate = (String)((Map<String, Object>) profile.get("startDate")).get("String");
		System.out.println("startDate : "+ startDate);
		String endDate = (String)((Map<String, Object>) profile.get("endDate")).get("String");
		System.out.println("endDate : "+ endDate);
		List<Map<String, Object>> l = (List<Map<String, Object>>)
				((Map<String, Object>) profile.get("surveyAnswer")).get("List");
		System.out.println(" survey answers :: ");
		System.out.println(" before : " + l);
		List<SurveyAnswer> surveyAnswer = toSurveyAnswers(l);
		System.out.println(" after : " + surveyAnswer);
		//TODO String date to Date
		
		LocalDate s = LocalDate.parse(startDate);
		LocalDate e = LocalDate.parse(endDate);
		System.out.println("date after parsing ");
		System.out.println(s);
		System.out.println(e);
		Profile p = new Profile(s, e, term, idUser, recruiter,surveyAnswer);
		return p;
	}
	
	public static List<SurveyAnswer> toSurveyAnswers(List<Map<String, Object>> l){
		List<SurveyAnswer> list = new ArrayList<SurveyAnswer>();
		for (Map<String, Object> map : l) {
			list.add(toSurveyAnswer(map));
		}
		return list;
	}

	private static SurveyAnswer toSurveyAnswer(Map<String, Object> map) {
		int idR =(int)((Map<String, Object>) map.get("idResponse")).get("Integer");
		System.out.println("id response : " + idR);
		int idQ =(int)((Map<String, Object>) map.get("idQuestion")).get("Integer");
		System.out.println("id question : " + idQ);
		String extra =(String)((Map<String, Object>) map.get("extra")).get("String");
		System.out.println("extra : " + extra);
		return new SurveyAnswer(0, idQ, idR, extra);
		
		
	}
	
	
	/**
	 * USER
	 * @param map
	 * @return
	 */
	public static Map<String, String> getInfoUser(Map<String, Object> map){
		Map<String, String> ret = new HashMap<String, String>();
		ret.put("name", (String)((Map<String, Object>) map.get("name")).get("String"));
		ret.put("login",(String)((Map<String, Object>) map.get("login")).get("String"));
		ret.put("surname", (String)((Map<String, Object>) map.get("surname")).get("String"));
		ret.put("password",(String)((Map<String, Object>) map.get("password")).get("String"));
		return ret;
	}

	public static User toUser(Map<String, Object> map) {
		Map<String, String> infos = getInfoUser(map);
		/*(LabelRole)((Map<String, Object>) map.get("role")).get("String")
		//User s = new User(infos.get("name"), infos.get("surname"),
				infos.get("login"), infos.get("password"),
				0, , new Date());*/
				User s = null;
		return s;
	}
	
	public static Student toStudent(Map<String, Object> map) {
		Map<String, String> infos = getInfoUser(map);
		Student s = new Student(infos.get("name"), infos.get("surname"),
				infos.get("login"), infos.get("password"), 0, new Date());
		return s;
	}
	
	public static Recruiter toRecruiter(Map<String, Object> map) {
		Map<String, String> infos = getInfoUser(map);
		String e = (String)((Map<String, Object>) map.get("entreprise")).get("String");
		Recruiter r = new Recruiter(infos.get("name"), infos.get("surname"),
				infos.get("login"), infos.get("password"), 0, new Date(), e);
		return r;
	}
	
	public static Admin toAdmin(Map<String, Object> map) {
		Map<String, String> infos = getInfoUser(map);
		Admin a = new Admin(infos.get("name"), infos.get("surname"),
				infos.get("login"), infos.get("password"), 0, new Date());
		return a;
	}

	
	/**
	 * BLOG
	 * @param map
	 * @return
	 */
	public static Blog toBlog(Map<String, Object> map) {
		String title = (String)((Map<String, Object>) map.get("title")).get("String");
		String subtitle = (String)((Map<String, Object>) map.get("subtitle")).get("String");
		String content = (String)((Map<String, Object>) map.get("content")).get("String");
		int idAuthor = (int)((Map<String, Object>) map.get("idAuthor")).get("Integer");
		return new Blog(title, subtitle, content, new Date(), idAuthor);
	}
}
