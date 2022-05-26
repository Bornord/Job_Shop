package jobShop_WebProject;

import java.awt.Label;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;

/**
 * classe Admin pour l'utilisateur admin
 * @author arenard2
 *
 */
@Entity
public class Admin extends User {
	public Admin() {
		super();
	}
	public Admin(String name, String surname, String login, String password, int id, Date creationDate) {
		super(name, surname, login, password, id, LabelRole.ADMIN, creationDate);
		super.setStatus(2002);
	}
	public static List<Map<String,String>> getFields(){
		Field[] fields = Admin.class.getDeclaredFields();
		
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
