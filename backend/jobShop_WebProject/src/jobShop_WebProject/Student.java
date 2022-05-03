package jobShop_WebProject;

import java.util.Date;
/**
 * classe Student représentant l'utilisateur Student
 * @author arenard2
 *
 */

public class Student extends User{
	/*CV;
	 * 
	 * ecole;
	 */
	private Profile profile;

	public Student(String name, String surname, String login, String password, int id, 
			Date creationDate) {
		super(name, surname, login, password, id, LabelRole.STUDENT, creationDate);
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	
}