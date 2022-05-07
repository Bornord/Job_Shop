package jobShop_WebProject;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jobShop_WebProject.Student;
import jobShop_WebProject.utils.Security;


@WebServlet("/Server")
public class Server extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
	@EJB
	private DataBase main;
	
	public Server() {
		super();
		//main = new DataBase();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String op = request.getParameter("op");
		//response.getWriter().println("<html><body>Helloooooo</body></html>");
		switch(op) {
		case "addStudent" :
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String login = request.getParameter("login");
			String pwd = request.getParameter("pwd");
			Student student = new Student(nom, prenom, login, pwd, 0, new Date());
			main.addUser(student);
			request.getRequestDispatcher("index.html").forward(request, response);
			break;
		case "addRecruiter" :
			String nomr = request.getParameter("nom");
			String prenomr = request.getParameter("prenom");
			String loginr = request.getParameter("login");
			String pwdr = request.getParameter("pwd");
			Recruiter recruiter = new Recruiter(nomr, prenomr, loginr, pwdr, 0, new Date());
			main.addUser(recruiter);
			request.getRequestDispatcher("index.html").forward(request, response);
			break;
		case "login" :
			String json = request.getParameter("json");
			Security.login(json, main);
			request.getRequestDispatcher("index.html").forward(request, response);
			break;
		case "viewStudent" :
			Collection<Student> l = main.getStudents();
			
			request.setAttribute("l", l);
			request.getRequestDispatcher("viewStudent.jsp").forward(request, response);
			
			break;
		case "viewRecruiter" :
			Collection<Recruiter> r = main.getRecruiters();
			
			request.setAttribute("r", r);
			request.getRequestDispatcher("viewRecruiter.jsp").forward(request, response);
			
			break;
		case "viewAdmin" :
			Collection<Admin> admin = main.getAdmins();
			
			request.setAttribute("admin", admin);
			request.getRequestDispatcher("viewAdmin.jsp").forward(request, response);
			
			break;
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
