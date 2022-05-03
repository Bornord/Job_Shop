package jobShop_WebProject;

import java.awt.Label;
import java.util.Date;

/**
 * classe Admin pour l'utilisateur admin
 * @author arenard2
 *
 */
public class Admin extends User {

	public Admin(String name, String surname, String login, String password, int id, Date creationDate) {
		super(name, surname, login, password, id, LabelRole.ADMIN, creationDate);
	}
	
}
