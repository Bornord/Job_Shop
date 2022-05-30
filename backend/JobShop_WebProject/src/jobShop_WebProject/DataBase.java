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

//import org.springframework.security.crypto.bcrypt.BCrypt;

import jobShop_WebProject.utils.JsonConverter;
import jobShop_WebProject.utils.ObjectConverter;
import jobShop_WebProject.utils.Security;

import java.util.Date;
/**
 * classe principale contenant l'entity manager
 * @author arenard2
 * http://localhost:8080/JobShop_WebProject/
 * http://localhost:8080/h2console
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
	
	@PostConstruct
	public void initialisation() {
		addAdmin(new Admin("Akina", "Renard","akina@yes.fr", BCrypt.hashpw("test", BCrypt.gensalt()), 0, new Date()));
		addAdmin(new Admin("Paula", "Valentina","PaulaL@Boss.com", BCrypt.hashpw("testP", BCrypt.gensalt()), 0, new Date()));
		addBlog(new Blog("Super blog", "how to find a job", "blablabla content", new Date(), 2));
		addBlog(new Blog("Le blog de Akina", "job shop la meilleure plateforme", "blablabla content", new Date(), 1));
		addRecruiter(new Recruiter("jose", "lerecruteur", "joser@recrutement.com",BCrypt.hashpw("mdp", BCrypt.gensalt()), 0, new Date(), "nasa"));
	}
	
	public void addUser(User s) {
		em.persist(s);
	}

	public void deleteUser(User s) {
		em.clear();
		User del = em.find(User.class, s.getId());
		em.remove(del);
	}
	
	public Blog addBlog(Blog b) {
		if(em.find(User.class, b.getIdAuthor()) != null) {
			em.persist(b);
			return b;
		} else {
			return null;
		}
	}
	
	public void addOffer(Offer o) {
		em.persist(o);
	}
	
	public void deleteOffer(Offer o) {
		em.clear();
		Offer del = em.find(Offer.class, o.getId());
		em.remove(del);
	}
	
	
	public User findUser(int id) {
		return em.find(User.class, id);
	}
	
	public Offer findOffer(int id) {
		return em.find(Offer.class, id);
	}
	

	public Question findQuestion(int id) {
		return em.find(Question.class, id);
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
	public Question addQuestionToQuestion(Question newQuestion, int idPrevious,List<Integer> idResponses) {
		Question previousQuestion = em.find(Question.class, idPrevious);
		System.out.println(previousQuestion);
		if(newQuestion != null){
			addQuestion(newQuestion);
			System.out.println(newQuestion);
			/*Response[] responses = new Response[previousQuestion.getResponses().size()];
			for (int i = 0;i<previousQuestion.getResponses().size();i++) {
				responses[i] = previousQuestion.getResponses().get(i);
			}
			previousQuestion.appendToResponse(newQuestion.getId(),responses);*/
			if(idResponses.size() == 0) {
				
				previousQuestion.getResponses().get(0).setNextQuestion(newQuestion.getId());
				return previousQuestion;
			}
			
			List<Response> previousResponses = previousQuestion.getResponses();
			List<Response> selectedResponses = new ArrayList<>();
			for (Response response : previousResponses) {
				for (int id : idResponses) {					
					if(response.getId() == id) {
						response.setNextQuestion(newQuestion.getId());
					}
				}
			}
			return previousQuestion;
		}
		return null;
	}
	
	/**
	 * add a question after a specific answer
	 * @param newQuestion
	 * @param response
	 */
	public void addQuestionToResponse(Map<String, Object> newQuestion, Response response ) {
		Response previousResp = em.find(Response.class, response.getId());
		Question q = ObjectConverter.toQuestion(newQuestion, this);
		if(q != null) {
			em.persist(q);
			previousResp.setNextQuestion(q.getId());
		}
	}

	/**
	 * get the questions that have no next question
	 * @return a collection of responses
	 */
	public Collection<Response> getFinalResponses(){
		return em.createQuery("select r from Response r where r.nextQuestion = 0")
				.getResultList();
	}
	
	/**
	 * add a question at the end (after responses that have no next question)
	 * @param questionO
	 */
	public Question addQuestionToEnd(Map<String, Object> questionO) {
		System.out.println("************************");
		System.out.println(questionO);
		Question q = ObjectConverter.toQuestion(questionO, this);
		
		System.out.println("**************");
		
		if(q != null){
			Collection<Response> responses = getFinalResponses();
			System.out.println(q.getTitle());
			for(Response response : q.getResponses()) {
				response.setPreviousQuestion(q);
				em.persist(response);
			}
			
			em.persist(q);
			System.out.println("**************");
			System.out.println(responses);
			if(responses == null || responses.size() == 0) {
				FirstQuestion firstQuestion = new FirstQuestion();
				firstQuestion.setNameSurvey("current");
				firstQuestion.setIdFirstQuestion(q.getId());
				System.out.println("**************");
				System.out.println(firstQuestion.getNameSurvey());
				em.persist(firstQuestion);
				return q;
			}
			for (Response response : responses) {
				response.setNextQuestion(q.getId());
			}
		}//TODO : retourner autre chose que le survey courant?
		return em.find(Question.class, getCurrentSurvey().getIdFirstQuestion());
	}

	public Collection<FirstQuestion> getSurveys() {
		return em.createQuery("from FirstQuestion", FirstQuestion.class).getResultList();
		
	}
	
	/**
	 * method match
	 */
	public Offer matchToOffer(Profile profileCandidate) {
		List<Offer> offers = em.createQuery("select o from Offer o").getResultList();
		if(offers.size() == 0) {
			return null;
		}
		return match(offers,profileCandidate);
		
	}
	
	public static Profile  match(Profile profile, List<Profile> profiles) {
		Map<Double, Profile> list = new HashMap();
		for (Profile p : profiles) {
			list.put(match(p,profile), p);
		}
		List<Double> listKeySet = new ArrayList<Double>(list.keySet());
		Collections.sort(listKeySet);
		if(listKeySet.size() == 0){
			return null;
		}
		return list.get(listKeySet.get(listKeySet.size()-1));
	}
	public static Offer  match(List<Offer> offers,Profile profile) {
		Map<Double, Offer> list = new HashMap();
		for (Offer o : offers) {
			list.put(match(o.getIdealProfile(),profile), o);
		}
		List<Double> listKeySet = new ArrayList<Double>(list.keySet());
		Collections.sort(listKeySet);
		if(listKeySet.size() == 0){
			return null;
		}
		return list.get(listKeySet.get(listKeySet.size()-1));
	}
	
	public static double match(Profile recruiter, Profile student) {
		double score = 0.0;
		for (SurveyAnswer saRecruiter : recruiter.getSurveyAnswer()) {
			for (SurveyAnswer saStudent : student.getSurveyAnswer()){
				if(saStudent.getIdQuestion() == saRecruiter.getIdQuestion()){
					if(saStudent.getIdResponse() == saRecruiter.getIdResponse())
						score+=1;
				}
			}
			
		}
		//dates et durée d'emploi en mois
		//System.out.println("date fin :" + recruiter.getStartDate().plusMonths(recruiter.getTerm()));
		if(student.getTerm() >= recruiter.getTerm()) {
			if (student.getStartDate().isBefore(recruiter.getStartDate()) 
					&& recruiter.getStartDate().plusMonths(recruiter.getTerm()).isBefore(student.getEndDate())) {
				score+=1.0;
			} else if(student.getStartDate().plusMonths(recruiter.getTerm()).isBefore(recruiter.getEndDate())) {
				score+=1.0;
			}
		}
		return score;
	}
	
	/**
	 * method match with candidate
	 */
	public Profile matchToCandidate(Profile profile) {
		List<Profile> profiles = em.createQuery("select p from Profile p where p.isRecruiter=false")
				.getResultList();
		return match(profile, profiles);
	}

	public void addFirstQuestion(FirstQuestion firstQuestion) {
		em.persist(firstQuestion);
	}

	public FirstQuestion getCurrentSurvey() {
		List list= em.createQuery("select c from FirstQuestion c where c.nameSurvey like:current")
				.setParameter("current", "current")
				.setMaxResults(1)
				.getResultList();	
		if(list.size() != 0) {
			return (FirstQuestion) list.get(0);
		} else {
			return null;
		}
	}

	public FirstQuestion updateCurrentSurvey(int id) {
		getCurrentSurvey().setNameSurvey("old" + new Date());
		em.find(FirstQuestion.class, id).setNameSurvey("current");
		return em.find(FirstQuestion.class, id);
	}

	public void addProfile(Profile profile) {
		if (!profile.getIsRecruiter()) {
			Student s = em.find(Student.class, profile.getIdUser());
			System.out.println("Student -> ");
			System.out.println();
			System.out.println(s);
			s.setProfile(profile);
		}
		em.persist(profile);
		for(SurveyAnswer sa : profile.getSurveyAnswer()) {
			sa.setIdProfile(profile.getId());
			System.out.println(sa);
			em.persist(sa);
		}
	}
	
	public void addOffer(Offer offer,int idRecruiter) {
		User recruiter = em.find(User.class, idRecruiter);
		try {			
			offer.setRecruiter((Recruiter) recruiter);
			addProfile(offer.getIdealProfile());
			em.persist(offer);
		}catch (Exception e) {
			e.printStackTrace();
		}
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

	public Collection<Blog> getBlogs() {
		return em.createQuery("from Blog", Blog.class).getResultList();
	}

	public Question addQuestion(Question q) {
		em.persist(q);
		List<Response> resp = q.getResponses();
		for (Response r : resp) {
			r.setPreviousQuestion(q);
		}
		return q;	//with id
	}

	/*public void addResponse(int id_Q, Response r) {
		em.persist(r);
		r.setPreviousQuestion(em.find(Question.class, id_Q));
	}*/

	public Question addQuestionToResponse(Question question, int previous) {
		em.persist(question);
		Response previousResponse = em.find(Response.class, previous);
		previousResponse.setNextQuestion(question.getId());
		return previousResponse.getPreviousQuestion();
	}

	public Question getQuestion(int nextQuestion) {
		return em.find(Question.class, nextQuestion);
	}
	
	/**
	 * get the blog created by a user given the user's id
	 * @param id user's id
	 * @return user's blog or null if the user doesn't have a blog
	 */
	public List<Blog> getBlogAuthor(int id) {
		List<Blog> list= em.createQuery("select c from Blog c where c.idAuthor like:id")
				.setParameter("id", id)
				.getResultList();	
		if(list.size() != 0) {
			return list;
		} else {
			return null;
		}
	}
	//public setNextQuestion(Response r, )

	public Status addStatus(Profile profile, Offer offer, LabelStep step) {
		Status status = new Status();
		status.setIdStudent(profile.getIdUser());
		status.setOffer(offer);
		status.setStep(step);
		em.persist(status);
		return status;
	}

	public List<Offer> getOffersFromRecruiter(int id) {
		return ((Recruiter) findUser(id)).getOffers();
	}

	public List<Status> getStatusFromUserId(int id) {
		List<Status> list= em.createQuery("select s from Status s where s.idStudent like:id")
				.setParameter("id", id)
				.getResultList();	
		return list;
	}
}
