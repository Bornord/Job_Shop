package jobShop_WebProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jobShop_WebProject.utils.JsonConverter;
import jobShop_WebProject.utils.ObjectConverter;

public class ServerPostCases {

	public static void addQuestionToQuestion(HttpServletRequest request,HttpServletResponse response, DataBase main) {
		String questionJson = readJson(request); 
		int previous = 0;//-> récupérer l'id
		//TODO separer les 2 élements dans le json
		Map<String, Object> question =  JsonConverter.toObject(questionJson); 
		main.addQuestionToQuestion(ObjectConverter.toQuestion(question), previous);
		
	}
	
	public static String readJson(HttpServletRequest request) {
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) {}
		return jb.toString();
	}

	public static void addSurvey(HttpServletRequest request, HttpServletResponse response, DataBase main) {
		String j = readJson(request);//nom survey et 1ère question
 		Map<String, Object> nameAndQuestion = JsonConverter.toObject(j);
 		//TODO
 		String name = "";//recuperer le nom!!
 		Question question1 = ObjectConverter.toQuestion(nameAndQuestion);//sans le name!!
 		
 		FirstQuestion firstQuestion = new FirstQuestion();
 		firstQuestion.setIdFirstQuestion(question1.getId());
 		firstQuestion.setNameSurvey(name);
 		main.addFirstQuestion(firstQuestion, question1);
		
	}

	public static void addQuestionToEnd(HttpServletRequest request, HttpServletResponse response, DataBase main) {
		String questionJson = readJson(request); 
		Map<String, Object> questionO =  JsonConverter.toObject(questionJson); 
		main.addQuestionToEnd(questionO);
	}

	public static void setCurrentSurvey(HttpServletRequest request, HttpServletResponse response, DataBase main) {
		// TODO vérifier que json recu est bien {id = _ }
		String j = readJson(request);
		int id = (int)((Map<String, Object>)JsonConverter.toObject(j)).get("Integer");// new current survey id
		main.updateCurrentSurvey(id);
		
	}

	public static void test(HttpServletRequest request, HttpServletResponse response, DataBase main) throws IOException {
		String j = readJson(request);
		printResp(response, j);
	}

	public static void addProfileStudent(HttpServletRequest request, HttpServletResponse response, DataBase main) {
		// TODO réception json de Profile?
		String j = readJson(request);
		
		//faire le match
	}

	public static void addProfileRecruiter(HttpServletRequest request, HttpServletResponse response, DataBase main) throws IOException {
		// TODO toProfile
		String j = readJson(request);
		Map<String, Object> map = JsonConverter.toObject(j);
		Profile profile = ObjectConverter.toProfile(map);
		main.addProfile(profile);
		//faire le match
		main.matchToCandidate(null);
		printResp(response, JsonConverter.toJson(profile));
		
	}
	
	private static void printResp(HttpServletResponse response, String toPrint) throws IOException {
		response.setContentType("text/html");
		response.getWriter().println("<html><body>" + toPrint + "</body></html>");
	}

	public static void addStudent(HttpServletRequest request, HttpServletResponse response, DataBase main) {
		String j = readJson(request);
		Student s = ObjectConverter.toStudent(JsonConverter.toObject(j));
		main.addStudent(s);
	}

	public static void addRecruiter(HttpServletRequest request, HttpServletResponse response, DataBase main) {
		String j = readJson(request);
		Recruiter r = ObjectConverter.toRecruiter(JsonConverter.toObject(j));
		main.addRecruiter(r);
	}
	
	public static void addAdmin(HttpServletRequest request, HttpServletResponse response, DataBase main) {
		String j = readJson(request);
		Admin r = ObjectConverter.toAdmin(JsonConverter.toObject(j));
		main.addAdmin(r);
	}

}
