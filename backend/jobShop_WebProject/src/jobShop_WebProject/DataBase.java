package jobShop_WebProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


import javax.ejb.Singleton;

import javax.persistence.NamedQuery;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
@NamedQuery(
	name = "findUserByLogin",
			query = "SELECT u FROM User u WHERE u.login LIKE :login"
)

@Singleton
public class DataBase implements IMainLocal, IMainRemote{
	@PersistenceContext
	private EntityManager em;
	
	private List<Student> students;	//provisoire
	public DataBase() {
		//students = new ArrayList<>();
	}
	public void addUser(User s) {
		//students.add(s);
		em.persist(s);
	}

	public void deleteUser(User s) {
		//students.add(s);
		em.remove(s);
	}
	
	public User findUser(int id) {
		return em.find(User.class, id);
	}
	
	public Collection<Student> getStudents() {
		return students;
	}
	
	public User findWithLogin(String l) {
		System.out.println(em.isOpen());
		List list = em.createNamedQuery("findUserByLogin").setParameter("login",l).setMaxResults(1).
				getResultList();
		if(list.size() != 0) {
			return (User) list.get(0);
		} else {
			return null;
		}
	}
	
	//@PostConstruct
	public void initialisation() {
		em.persist(new Admin("Akina", "Renard","akinaLaBoss", "pwdadmin", 0, new Date()));
	}
}
