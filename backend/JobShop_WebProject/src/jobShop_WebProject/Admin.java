package jobShop_WebProject;

import java.awt.Label;
import java.util.Date;

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
	}
	
}
