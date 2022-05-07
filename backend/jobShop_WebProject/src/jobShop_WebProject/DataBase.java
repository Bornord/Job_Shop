package jobShop_WebProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


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
	@PersistenceContext
	private EntityManager em;
	
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
	
	//@PostConstruct	-> ne marche pas
	public void initialisation() {
		em.persist(new Admin("Akina", "Renard","akinaLaBoss", "pwdadmin", 0, new Date()));
		em.persist(new Admin("Paula", "Valentina","PaulaLaBoss", "aaaaaaa", 0, new Date()));
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
	}
}
