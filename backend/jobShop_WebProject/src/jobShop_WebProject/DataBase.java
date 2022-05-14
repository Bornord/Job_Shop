package jobShop_WebProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.*;
import javax.persistence.*;
import javax.annotation.*;
import javax.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jobShop_WebProject.utils.ObjectConverter;

import java.util.Date;
/**
 * classe principale contenant l'entity manager
 * @author arenard2
 *
 */
@NamedQueries({
	@NamedQuery(name = "DataBase.findUserByLogin",
			query = "SELECT u FROM User u WHERE u.login LIKE :login"
	),
	@NamedQuery(
		name = "DataBase.findUserByRole",
				query = "SELECT u FROM User u WHERE u.role LIKE :role"
	)
})



@Singleton
public class DataBase{
	@PersistenceContext(name = "jobshop")
	private EntityManager em;
	
	public DataBase() {}
	
	//@PostConstruct	//-> ne marche pas
	public void initialisation() {
			em.persist(new Admin("Akina", "Renard","akinaLaBoss", "pwdadmin", 0, new Date()));
			em.persist(new Admin("Paula", "Valentina","PaulaLaBoss", "aaaaaaa", 0, new Date()));
		}
		
	public void addUser(User s) {
		em.persist(s);
	}

	public void deleteUser(User s) {
		em.remove(s);
	}
	
	public void addOffer(Offer o) {
		em.persist(o);
	}
	
	public void deleteOffer(Offer o) {
		em.remove(o);
	}
	
	
	public User findUser(int id) {
		return em.find(User.class, id);
	}
	
	public Offer findOffer(int id) {
		return em.find(Offer.class, id);
	}
	
	public Profile match(Offer o) {
		Profile p = null;
		return p;
	}
	
	
	public Collection<Student> getStudents() {
		return em.createQuery("SELECT u FROM User u WHERE u.role LIKE :role").setParameter("role",LabelRole.STUDENT)
				.getResultList();
	}
	
	public User findWithLogin(String l) {
		System.out.println(em.isOpen());
		List list = em.createQuery("SELECT u FROM User u WHERE u.login LIKE :login").setParameter("login",l)
				.setMaxResults(1)
				.getResultList();
		if(list.size() != 0) {
			return (User) list.get(0);
		} else {
			return null;
		}
	}

	public Collection<Recruiter> getRecruiters() {
		Collection<Recruiter> c =(Collection<Recruiter>) em.createQuery("SELECT u FROM User u WHERE u.role LIKE :role")
				.setParameter("role",LabelRole.RECRUITER)
				.getResultList();
		return c;
	}
	public Collection<Admin> getAdmins() {
		return em.createQuery("SELECT u FROM User u WHERE u.role LIKE :role")
				.setParameter("role",LabelRole.ADMIN)
				.getResultList();
		/*ou
		 * return em.createQuery("from Admin", Admin.class).getResultList();
		 */
	}

	public Collection<Question> getQuestions() {
		return em.createQuery("from Question", Question.class).getResultList();
	}
	
	/**
	 * getQuestionByTitre
	 */
	public Question getQuestionByTitle(String title) {
		List list = em.createQuery("select q from Question q where q.title like:title")
			.setParameter("title", title)
			.setMaxResults(1)
			.getResultList();
		if(list.size() != 0) {
			return (Question) list.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * add a question after a question for all the responses
	 * @param newQuestion
	 * @param question
	 */
	public void addQuestionToQuestion(Map<String, Object> newQuestion, Question question) {
		Question previousQuestion = em.find(Question.class, question.getId());
		Question q = ObjectConverter.toQuestion(newQuestion);
		if(q != null){
			previousQuestion.appendToResponse(q,(Response[]) previousQuestion.getResponses().toArray());
			em.persist(q);
		}
	}
	
	/**
	 * add a question after a specific answer
	 * @param newQuestion
	 * @param response
	 */
	public void addQuestionToResponse(Map<String, Object> newQuestion, Response response ) {
		Response previousResp = em.find(Response.class, response.getId());
		Question q = ObjectConverter.toQuestion(newQuestion);
		if(q != null) {
			previousResp.setNextQuestion(q);
			em.persist(q);
		}
	}

	/**
	 * get the questions that have no next question
	 * @return a collection of responses
	 */
	public Collection<Response> getFinalResponses(){
		return em.createQuery("select r from Response r where r.nextQuestion like:next")
				.setParameter("next", null)
				.getResultList();
	}
	
	/**
	 * add a question at the end (after responses that have no next question)
	 * @param questionO
	 */
	public void addQuestionToEnd(Map<String, Object> questionO) {
		
		Question q = ObjectConverter.toQuestion(questionO);
		if(q != null){
			Collection<Response> responses = getFinalResponses();
			for (Response response : responses) {
				response.setNextQuestion(q);
			}
			em.persist(q);
		}
	}
	
}
