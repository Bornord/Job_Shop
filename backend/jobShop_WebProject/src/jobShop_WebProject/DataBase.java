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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	//@PostConstruct	-> ne marche pas
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
		/*List list = em.createNamedQuery("DataBase.findUserByRole").setParameter("role",LabelRole.STUDENT).
				getResultList();
		return list;*/
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
	/*
	 * getQuestionByTitre
	 */
	

	public Collection<Response> getFinalResponses(){
		return em.createQuery("select r from Response r where r.nextQuestion like:next")
				.setParameter("next", null)
				.getResultList();
		
	}
	
	public void addQuestion(Map<String, Object> questionO) {
		Question q;
		Set<String> keys = questionO.keySet();
		if(questionO.containsKey("title")){
			String title = (String) questionO.get("title");
			q = new Question(title);
			em.persist(q);
		}
	}
}
