package jobShop_WebProject;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jobShop_WebProject.utils.JsonConverter;

public class ServerGetCases {

	public static void getAllQuestion(HttpServletRequest request, HttpServletResponse response, DataBase main)
			throws ServletException, IOException {
		//TODO lire idSurvey dans request
		int id = 0;
		Question q = main.getFirstQuestion(id);
		String listQuestion = JsonConverter.toJson(q);
		response.getWriter().println("<html><body>" + listQuestion.toString() + "</body></html>");
	}

	public static void getAllSurveys(HttpServletRequest request, HttpServletResponse response, DataBase main) {
		Collection<SurveyAnswer> allSurveys = main.getSurveys();
		//TODO transformation json -> response
		
	}

	
	public static void getCurrentSurvey(HttpServletRequest request, HttpServletResponse response, DataBase main) {

		FirstQuestion current = main.getCurrentSurvey();
		// TODO print response
		
	}

	public static void getBlogs(HttpServletRequest request, HttpServletResponse response, DataBase main) throws IOException {
		Collection<Blog> blogs = main.getBlogs();
		printResp(response, JsonConverter.toJson(blogs));
	}

	private static void printResp(HttpServletResponse response, String toPrint) throws IOException {
		response.setContentType("text/html");
		response.getWriter().println("<html><body>" + toPrint + "</body></html>");
	}

}
