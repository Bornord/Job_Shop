package jobShop_WebProject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jobShop_WebProject.utils.JsonConverter;

public class ServerGetCases {

	/**
	 * print du json de l'arbre d'une question correspondant à la première question d'un survey
	 * @param request on lit l'id d'un survey dont on veut récupérer la première queston
	 * @param response html avec json de l'arbre de la question
	 * @param main
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void getAllQuestions(HttpServletRequest request, HttpServletResponse response, DataBase main)
			throws ServletException, IOException {
		String json = ServerPostCases.readJson(request);
		Map<String, Object> map =  JsonConverter.toObject(json); 
		int id = (int)((Map<String, Object>)map.get("id")).get("Integer");
		Question q = main.getFirstQuestion(id);
		String firstQuestion = JsonConverter.toJson(q);
		printResp(response, firstQuestion);
	}

	public static void getAllSurveys(HttpServletRequest request, HttpServletResponse response, DataBase main) throws IOException {
		Collection<FirstQuestion> allSurveys = main.getSurveys();
		String j = "{[";
		for (FirstQuestion object : allSurveys) {
			j+=JsonConverter.toJson(object)+",";
		}
		j+="]}";
		printResp(response, JsonConverter.toJson(allSurveys));
	}

	public static void test(HttpServletRequest request, HttpServletResponse response, DataBase main) throws IOException {
		
		printResp(response, "{\"message\":\"hello world\"}");
	}
	
	public static void getCurrentSurvey(HttpServletRequest request, HttpServletResponse response, DataBase main) throws IOException {
		FirstQuestion current = main.getCurrentSurvey();
		Question q = main.findQuestion(current.getIdFirstQuestion());
		String jq = JsonConverter.questionToJson(q, main);
		System.out.println("*****************" +jq);
		
		String json = "{question:"+jq+", firstQuestion:"+JsonConverter.toJson(current)+"}";
		printResp(response, json);
		//printResp(response, JsonConverter.toJson(current));
	}
	
	

	public static void getBlogs(HttpServletRequest request, HttpServletResponse response, DataBase main) throws IOException {
		List<Blog> blogs = (List<Blog>) main.getBlogs();
		String j = "[";
		for (Blog object : blogs) {
			j+=JsonConverter.toJson(object);
			if(blogs.indexOf(object) != blogs.size()-1)
					j+=",";
		}
		j+="]";
		printResp(response, j);
	}
	
	private static void printResp(HttpServletResponse response, String toPrint) throws IOException {
		response.setContentType("application/json");
		//response.getWriter().println("<html><body>" + toPrint + "</body></html>");
		response.reset();
		response.getWriter().println(toPrint);
	}

	public static void getStudentFields(HttpServletRequest request, HttpServletResponse response, DataBase main) throws IOException {
		String json = "[";
		List<Map<String,String>> fields = Student.getFields();
		for (Map<String, String> map : fields) {
			String fieldKey = map.keySet().toArray(new String[0])[0];
			String fieldType = map.get(fieldKey);
			String label = "";
			if(fieldKey.equalsIgnoreCase("id")
					|| fieldKey.equalsIgnoreCase("creationDate")
					|| fieldKey.equalsIgnoreCase("role")
					|| fieldKey.equalsIgnoreCase("status")) {
				continue;
			}
			
			try {
				Field f = Student.class.getField(fieldKey+"Placeholder");
				label += f.get(new String());
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
			
			
			json+="{"
					+"\"id\":"
					+"\""+fieldKey+"\""
					+","
					+"\"type\":"
					+"\""+fieldType+"\""
					+","
					+"\"label\":"
					+"\""+label +"\""
					+"}";

			json+=",";
			
		}
		if(json.endsWith(",")) {
			json = json.substring(0, json.length()-1);
		}
		json += "]";
		printResp(response, json);
	}

	public static void getRecruiterFields(HttpServletRequest request, HttpServletResponse response, DataBase main) throws IOException {
		String json = "[";
		List<Map<String,String>> fields = Recruiter.getFields();
		for (Map<String, String> map : fields) {
			String fieldKey = map.keySet().toArray(new String[0])[0];
			String fieldType = map.get(fieldKey);
			String label = "";
			if(fieldKey.equalsIgnoreCase("id")
					|| fieldKey.equalsIgnoreCase("creationDate")
					|| fieldKey.equalsIgnoreCase("role")) {
				continue;
			}
			try {
				Field f = Recruiter.class.getField(fieldKey+"Placeholder");
				label += f.get(new String());
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				//e.printStackTrace();
			}
			
			json+="{"
					+"\"id\":"
					+"\""+fieldKey+"\""
					+","
					+"\"type\":"
					+"\""+fieldType+"\""
					+","
					+"\"label\":"
					+"\""+label +"\""
					+"}";
			if(fields.indexOf(map) != fields.size()-1)
				json+=",";
			
		}
		json += "]";
		printResp(response, json);
	}

}
