package jobShop_WebProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
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
	
	public Question getFirstQuestion(int idSurvey) {
		return em.find(Question.class, (em.find(FirstQuestion.class, idSurvey)).getIdFirstQuestion());
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
	 * @param idPrevious
	 */
	public void addQuestionToQuestion(Question newQuestion, int idPrevious) {
		Question previousQuestion = em.find(Question.class, idPrevious);
		if(newQuestion != null){
			previousQuestion.appendToResponse(newQuestion,(Response[]) previousQuestion.getResponses().toArray());
			em.persist(newQuestion);
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

	public Collection<SurveyAnswer> getSurveys() {
		return em.createQuery("from SurveyAnswer", SurveyAnswer.class).getResultList();
		
	}
	
	/**
	 * method match
	 */
	public Profile matchToOffer(Profile profile) {
		List<Profile> profiles = em.createQuery("select p from Profile p where p.isRecruiter=false")
		.getResultList();
		return match(profile, profiles);
		
	}
	
	public static Profile  match(Profile profile, List<Profile> profiles) {
		Map<Double, Profile> list = new HashMap();
		for (Profile p : profiles) {
			list.put(match(p,profile), p);
		}
		List<Double> listKeySet = (List<Double>)list.keySet();
		Collections.sort(listKeySet);
		return list.get(listKeySet.get(0));
	}
	
	public static double match(Profile recruiter, Profile student) {
		double score = 0.0;
		for (SurveyAnswer saRecruiter : recruiter.getSurveyAnswer()) {
			for (SurveyAnswer saStudent : student.getSurveyAnswer()){
				if(saStudent.getIdQuestion() == saRecruiter.getIdQuestion()){
					if(saStudent.getIdResponse() == saRecruiter.getIdResponse())
						score++;
				}
			}
			
		}
		return score;
	}
	
	/**
	 * method match with candidate
	 */
	public Profile matchToCandidate(Profile profile) {
		List<Profile> profiles = em.createQuery("select p from Profile p where p.isRecruiter=true")
				.getResultList();
		return match(profile, profiles);
	}

	public void addFirstQuestion(FirstQuestion firstQuestion, Question question) {
		em.persist(firstQuestion);
		em.persist(question);
		
	}

	public SurveyAnswer getCurrentSurvey() {
		List list= em.createQuery("select c from SurveyAnswer c where c.extra like:current")
				.setParameter("current", "current")
				.setMaxResults(1)
				.getResultList();	
		if(list.size() != 0) {
			return (SurveyAnswer) list.get(0);
		} else {
			return null;
		}
	}

	public SurveyAnswer updateCurrentSurvey(int id) {
		getCurrentSurvey().setExtra("");//TODO changer? etat surveyAnswer différent de current
		em.find(SurveyAnswer.class, id).setExtra("current");
		return em.find(SurveyAnswer.class, id);
	}

	public void addProfile(Profile profile) {
		em.persist(profile);
	}

	public void addAdmin(Admin a) {
		em.persist(a);
	}
	
	public void addRecruiter(Recruiter a) {
		em.persist(a);
	}
	
	public void addStudent(Student s) {
		em.persist(s);
		
	}
}
