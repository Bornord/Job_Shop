package jobShop_WebProject;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	private String school;
	@Transient
	public static String schoolPlaceholder = "Ecole :";
	
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
	
	
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public static List<Map<String,String>> getFields(){
		Field[] fields = Student.class.getDeclaredFields();
		
		List<Map<String,String>> res = new ArrayList<>();
		res.addAll(User.getFields());
		for (Field field : fields) {
			if(!Modifier.isStatic(field.getModifiers())) {	
				Map<String,String> f = new HashMap<>();
				f.put(field.getName(), field.getType().getSimpleName());
				res.add(f);
			}
		}
		return res;
	}
}
