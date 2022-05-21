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
import jobShop_WebProject.Profile;
import jobShop_WebProject.Question;
import jobShop_WebProject.Recruiter;
import jobShop_WebProject.Response;
import jobShop_WebProject.Student;
import jobShop_WebProject.SurveyAnswer;

public class ObjectConverter {

	public static Question toQuestion(Map<String, Object> questionMap) {
		String title = (String) ((Map<String, Object>)(questionMap.get("title"))).get("String");
		Map<String, Object> valResp = (Map<String, Object>)(questionMap.get("responses"));
		List<Map<String, Object>> l = (List<Map<String, Object>>)valResp.get("List");
		List<Response> responses = (List<Response>)toListResponse(l);
		Question q = new Question(title);
		q.setResponses(responses);
		return q;
		
	}

	private static List<Response> toListResponse(List<Map<String, Object>> valResp) {
		List<Response> resp = new ArrayList();
		for (Map<String, Object> r : valResp) {
			resp.add(toResponse(r));
		}
		return resp;
	}
	
	private static Response toResponse(Map<String, Object> mapR) {
		//System.out.println(mapR);
		String placeholder = (String) ((Map<String, Object>)(mapR.get("placeholder"))).get("String");
		
		boolean isSelected = (boolean) ((Map<String, Object>)(mapR.get("isSelected"))).get("Boolean");
		
		Map<String, Object> nextQuestion = (Map<String, Object>)(mapR.get("nextQuestion"));
		if(nextQuestion != null && !nextQuestion.containsKey("Error")){
			return new Response(placeholder, toQuestion(nextQuestion));
		} else {
			return new Response(placeholder);
		}
	}
	
	public static Profile toProfile(Map<String, Object> profile, boolean isRecruiter) {
		int idUser =(int)((Map<String, Object>) profile.get("idUser")).get("Integer");
		int term =(int)((Map<String, Object>) profile.get("term")).get("Integer");
		boolean recruiter = (boolean)((Map<String, Object>) profile.get("isRecruiter")).get("Boolean");
		String startDate = (String)((Map<String, Object>) profile.get("startDate")).get("String");
		String endDate = (String)((Map<String, Object>) profile.get("endDate")).get("String");
		List<Map<String, Object>> l = (List<Map<String, Object>>)
				((Map<String, Object>) profile.get("surveyAnswer")).get("List");
		List<SurveyAnswer> surveyAnswer = toSurveyAnswers(l);
		//TODO String date to Date
		LocalDate s = LocalDate.parse(startDate);
		LocalDate e = LocalDate.parse(endDate);
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
		int idUser =(int)((Map<String, Object>) map.get("idUser")).get("Integer");
		int idR =(int)((Map<String, Object>) map.get("idResponse")).get("Integer");
		int idQ =(int)((Map<String, Object>) map.get("idQuestion")).get("Integer");
		String extra =(String)((Map<String, Object>) map.get("extra")).get("String");
		return new SurveyAnswer(idUser, idQ, idR, extra);
		
		
	}
	
	public static Map<String, String> getInfoUser(Map<String, Object> map){
		Map<String, String> ret = new HashMap<String, String>();
		ret.put("name", (String)((Map<String, Object>) map.get("name")).get("String"));
		ret.put("login",(String)((Map<String, Object>) map.get("login")).get("String"));
		ret.put("surname", (String)((Map<String, Object>) map.get("surname")).get("String"));
		ret.put("password",(String)((Map<String, Object>) map.get("password")).get("String"));
		return ret;
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
}
