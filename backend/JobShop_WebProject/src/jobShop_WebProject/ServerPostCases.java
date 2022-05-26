package jobShop_WebProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jobShop_WebProject.utils.JsonConverter;
import jobShop_WebProject.utils.ObjectConverter;
import jobShop_WebProject.utils.Request;
import jobShop_WebProject.utils.Security;

public class ServerPostCases {
	
	/**
	 * 
	 * @param request{idP:..., question :{...}} 
	 * @param response
	 * @param main
	 * @throws IOException
	 */
	public static void addQuestionToQuestion(HttpServletRequest request,HttpServletResponse response, DataBase main) throws IOException {
		String questionJson = readJson(request);	
		Map<String, Object> questionMap =  JsonConverter.toObject(questionJson); 
		//appeler idP dans le json pour différencier de id question
		int previous = (int)((Map<String, Object>)questionMap.get("idP")).get("Integer");
		Question question = ObjectConverter.toQuestion((Map<String, Object>)questionMap.get("question"), main);
		Question root = main.addQuestionToQuestion(question, previous);
		printResp(response, JsonConverter.questionToJson(root, main));
	}
	
	/**
	 * 
	 * @param request contenant : {idP:..., question :{...}}  avec idP l'id de la réponse précédente (qui doit déjà exister dans la bdd)
	 * @param response 
	 * @param main
	 * @throws IOException 
	 */
	public static void addQuestionToResponse(HttpServletRequest request,HttpServletResponse response, DataBase main) throws IOException {
		String questionJson = readJson(request);
		Map<String, Object> questionMap =  JsonConverter.toObject(questionJson); 
		//appeler idP dans le json pour différencier de id question
		int previous = (int)((Map<String, Object>)questionMap.get("idP")).get("Integer");
		Question question = ObjectConverter.toQuestion((Map<String, Object>)questionMap.get("question"), main);
		Question root = main.addQuestionToResponse(question, previous);
		printResp(response, JsonConverter.questionToJson(root, main));
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

	public static void addSurvey(HttpServletRequest request, HttpServletResponse response, DataBase main) throws IOException {
		String j = readJson(request);//nom survey et 1ère question
 		Map<String, Object> nameAndQuestion = JsonConverter.toObject(j);
 		//on recupère le nom
 		String name = (String)((Map<String, Object>)nameAndQuestion.get("name")).get("String");
 		Question question1 = ObjectConverter.toQuestion((Map<String, Object>)nameAndQuestion.get("question"), main);
 		
 		Question qWithId = main.addQuestion(question1);
 		//main.addResponse(qWithId.getId(), question1.getResponses().get(0));
 		FirstQuestion firstQuestion = new FirstQuestion(qWithId.getId(), name);
 		main.addFirstQuestion(firstQuestion);
		printResp(response, "{id:"+ qWithId.getId() + ",name:\""+name+"\"}");
	}

	public static void addQuestionToEnd(HttpServletRequest request, HttpServletResponse response, DataBase main) throws IOException {
		String questionJson = readJson(request); 
		//String questionJson = "{\"id\":0,\"title\":\"Votre domain ?\",\"responses\":[{\"id\":0,\"placeholder\":\"IT\",\"isSelected\":false,\"nextQuestion\":{\"id\":0,\"title\":\"Les languages ?\",\"responses\":[{\"id\":0,\"placeholder\":\"C\",\"isSelected\":false,},{\"id\":0,\"placeholder\":\"Python\",\"isSelected\":false,},]}},{\"id\":0,\"placeholder\":\"Prof\",\"isSelected\":false,},{\"id\":0,\"placeholder\":\"artist\",\"isSelected\":false,},]}";
		Map<String, Object> questionO =  JsonConverter.toObject(questionJson); 
		Question current = main.addQuestionToEnd(questionO);
		printResp(response, JsonConverter.questionToJson(current, main));
	}
	
	/**
	 * @param request id:... new current survey id
	 * @param response current survey
	 */
	public static void setCurrentSurvey(HttpServletRequest request, HttpServletResponse response, DataBase main) throws IOException {
		String j = readJson(request);
		Map<String, Object> map = JsonConverter.toObject(j);
		int id = (int)((Map<String, Object>)map.get("id")).get("Integer");
		main.updateCurrentSurvey(id);
		printResp(response, JsonConverter.toJson(main.getCurrentSurvey()));
	}

	public static void test(HttpServletRequest request, HttpServletResponse response, DataBase main) throws IOException {
		String j = readJson(request);
		System.out.println("***************\n" + j + "\n\n_\n******************");
		Map<String, Object> map = JsonConverter.toObject(j);
		String ouioui = (String)((Map<String, Object>)map.get("test")).get("String");
		
		printResp(response, "{ \"retour\":\"" + ouioui +" beaucoup\"}");
	}

	public static void addProfileStudent(HttpServletRequest request, HttpServletResponse response, DataBase main) throws IOException {
		String j = readJson(request);
		Map<String, Object> map = JsonConverter.toObject(j);
		Profile profile = ObjectConverter.toProfile(map, false);
		main.addProfile(profile);
		//faire le match
		main.matchToOffer(profile);
		printResp(response, JsonConverter.toJson(profile));
	}

	
	/**@param request idUser:..., term:..., isRecruiter:..., start/endDate, surveyAnswer:...
	 * @param response
	 * @param main
	 * @throws IOException
	 */
	public static void addProfileRecruiter(HttpServletRequest request, HttpServletResponse response, DataBase main) throws IOException {
		String j = readJson(request);
		Map<String, Object> map = JsonConverter.toObject(j);
		Profile profile = ObjectConverter.toProfile(map, true);
		main.addProfile(profile);
		//faire le match
		main.matchToCandidate(profile);
		printResp(response, JsonConverter.toJson(profile));
		
	}
	
	private static void printResp(HttpServletResponse response, String toPrint) throws IOException {
		response.setContentType("application/json");
		response.reset();
		response.getWriter().println(toPrint);
	}
	
	/**
	 * @param request login:... password:...
	 * @param response
	 * @param main
	 * @throws IOException
	 */
	public static void login(HttpServletRequest request, HttpServletResponse response, DataBase main) throws IOException {
		String j = readJson(request);
		//User u = Security.login(j, main);
		User u = Security.login(j, main, true);
		printResp(response, JsonConverter.toJson(u));
	}
	
	public static void signin(HttpServletRequest request, HttpServletResponse response, DataBase main) throws IOException {
		String j = readJson(request);
		User u = Security.signIn(j, main);
		printResp(response, JsonConverter.toJson(u));
	}

	/**
	 * @param request id:... (id de la personne qui se déconnecte)
	 * @param response request
	 * @param main
	 * @throws IOException
	 */
	public static void logout(HttpServletRequest request, HttpServletResponse response, DataBase main) throws IOException {
		String j = readJson(request);
		Request u = Security.logout(j, main);
		String r = "";
		if (u.getCode() == 200)
			r = ",\"user\":"+u.getJson();
		printResp(response, "{\"code\":"+u.getCode()+ r +"}");
	}
	
	/**
	 * @param request login:...
	 * @param response
	 * @param main
	 * @throws IOException
	 */
	public static void signout(HttpServletRequest request, HttpServletResponse response, DataBase main) throws IOException {
		String j = readJson(request);
		Request u = Security.signOut(j, main);
		//quoi mettre dans response? TODO
		String r = "";
		if (u.getCode() == 200)
			r = ",\"user\":"+u.getJson();
		printResp(response, "{\"code\":"+u.getCode()+ r +"}");
	}

	public static void addBlog(HttpServletRequest request, HttpServletResponse response, DataBase main) throws IOException {
		String j = readJson(request);
		String res = "";
		Map<String, Object> map = JsonConverter.toObject(j);
		
		Blog blog = main.addBlog(ObjectConverter.toBlog(map));
		if(blog != null) {
			res = "{id:"+blog.getId()+",idAuthor:"+blog.getIdAuthor()+"}";
		} else {
			res = "{error:\"author does't exist\"}";
		}
		printResp(response, res);
	}
	/**
	 * @param request id:... id de l'auteur du blog
	 * @param response
	 * @param main
	 * @throws IOException
	 */
	public static void getBlogAuthor(HttpServletRequest request, HttpServletResponse response, DataBase main) throws IOException {
		String j = readJson(request);
		Map<String, Object> map = JsonConverter.toObject(j);
		int id = (int)((Map<String, Object>) map.get("id")).get("Integer");
		Blog blog = main.getBlogAuthor(id);
		printResp(response, JsonConverter.toJson(blog));
	}

}
