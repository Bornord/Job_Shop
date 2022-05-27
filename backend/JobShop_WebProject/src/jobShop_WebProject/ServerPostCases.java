package jobShop_WebProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
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
		try {			
			System.out.println("**********************");
			System.out.println();
			System.out.println("         ADD QUESTION TO QUESTION          ");
			String questionJson = readJson(request);	
			Map<String, Object> questionMap =  JsonConverter.toObject(questionJson); 
			//appeler idP dans le json pour différencier de id question
			int previous = (int)((Map<String, Object>)questionMap.get("idQuestion")).get("Integer");
			List<Integer> idResponses = new ArrayList<>();
			List<Map<String, Object>> l = (List<Map<String, Object>>) ((Map<String, Object>)questionMap.get("idResponses")).get("List");
			for (Map<String, Object> map : l) {
				int id = (int) map.get("Integer");
				idResponses.add(id);
			}
			Question question = ObjectConverter.toQuestion((Map<String, Object>)questionMap.get("question"), main);
			System.out.println("-------------------");
			System.out.println(question);
			System.out.println(previous);
			System.out.println(idResponses);
			System.out.println("-----------------");
			
			Question root = main.addQuestionToQuestion(question, previous,idResponses);
			printResp(response, JsonConverter.questionToJson(root, main));
		} catch (Exception e) {
			e.printStackTrace();
			printResp(response, "{\"error\":\"error\"}");
		}
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
		System.out.println("******************");
		String questionJson = readJson(request); 
		System.out.println(questionJson);
		//String questionJson = "{\"id\":0,\"title\":\"Votre domain ?\",\"responses\":[{\"id\":0,\"placeholder\":\"IT\",\"isSelected\":false,\"nextQuestion\":{\"id\":0,\"title\":\"Les languages ?\",\"responses\":[{\"id\":0,\"placeholder\":\"C\",\"isSelected\":false,},{\"id\":0,\"placeholder\":\"Python\",\"isSelected\":false,},]}},{\"id\":0,\"placeholder\":\"Prof\",\"isSelected\":false,},{\"id\":0,\"placeholder\":\"artist\",\"isSelected\":false,},]}";
		try {
			Map<String, Object> questionO =  JsonConverter.toObject(questionJson);
			System.out.println("********************");
			System.out.println(questionO);
			Question current = main.addQuestionToEnd(questionO);
			printResp(response, JsonConverter.questionToJson(current, main));
			
		}catch (Exception e) {
			printResp(response, "{\"error\":\"error\"}");
		}
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
		try {			
			String j = readJson(request);
			Map<String, Object> map = JsonConverter.toObject(j);
			System.out.println("*****************");
			System.out.println("ADD PROFILE STUDENT");
			System.out.println(j);
			System.out.println();
			System.out.println("conversion :: ");
			System.out.println(map);
			Profile profile = ObjectConverter.toProfile(map, false);
			System.out.println();
			System.out.println("Profile ->  ");
			System.out.println();
			main.addProfile(profile);
			System.out.println(profile);
			//faire le match
			main.matchToOffer(profile);
			printResp(response, JsonConverter.toJson(profile));
		} catch (Exception e) {
			printResp(response, "{\"error\":\"error\"}");
		}
	}

	
	/**@param request idUser:..., term:..., isRecruiter:..., start/endDate, surveyAnswer:...
	 * @param response
	 * @param main
	 * @throws IOException
	 */
	public static void addOffer(HttpServletRequest request, HttpServletResponse response, DataBase main) throws IOException {
		try {			
			String j = readJson(request);
			Map<String, Object> map = JsonConverter.toObject(j);
			
			System.out.println("*********************");
			System.out.println();
			System.out.println("           OFFER        ");
			System.out.println();
			Offer offer = ObjectConverter.toOffer(map);
			System.out.println(" offer :: ");
			System.out.println();
			System.out.println(offer);
			int idRecruiter =(int)((Map<String, Object>) map.get("idRecruiter")).get("Integer");
			
			System.out.println("add offer --------");
			main.addOffer(offer,idRecruiter);
			//faire le match
			System.out.println("match to candidate --------");
			
			Profile closest = main.matchToCandidate(offer.getIdealProfile());
			System.out.println();
			System.out.println("closest profile");
			System.out.println(closest);
			if(closest != null) {				
				printResp(response, JsonConverter.toJson(closest));
			}else {
				printResp(response, "{\"notfound\":true}");
			}
		} catch (Exception e) {
			printResp(response, "{\"error\":\"error\"}");
		}
		
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
		if(u == null) {
			System.out.println("*****************************");
			String json = "{\"error\":\"Email ou mot de passe invalide \"}";
			printResp(response, json);
		}else {			
			printResp(response, JsonConverter.toJson(u));
		}
	}
	
	public static void signInStudent(HttpServletRequest request, HttpServletResponse response, DataBase main) throws IOException {
		String j = readJson(request);
		Student u = Security.signInStudent(j, main);
		System.out.println("*****************************");
		System.out.println(JsonConverter.toJson(u));
		printResp(response, JsonConverter.toJson(u));
	}
	public static void signInRecruiter(HttpServletRequest request, HttpServletResponse response, DataBase main) throws IOException {
		String j = readJson(request);
		Recruiter u = Security.signInRecruiter(j, main);
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
