package jobShop_WebProject;

import java.util.Date;

import javax.persistence.*;
/**
 * classe Student représentant l'utilisateur Student
 * @author arenard2
 *
 */
@Entity
public class Student extends User{
	/*CV;
	 * 
	 * ecole;
	 */
	@OneToOne(fetch=FetchType.EAGER)
	private Profile profile;

	public Student() {
		super();
	}
	public Student(String name, String surname, String login, String password, int id, 
			Date creationDate) {
		super(name, surname, login, password, id, LabelRole.STUDENT, creationDate);
		super.setStatus(2000);
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	
}
