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
 * classe Recruiter pour l'utilisateur recruteur
 * @author arenard2
 *
 */
@Entity
public class Recruiter extends User{
	@OneToMany(mappedBy="recruiter", fetch = FetchType.EAGER)
	private List<Offer> offers;
	//ou liste recruteurs
	
	private String company;
	@Transient
	public static String companyPlaceholder = "Entreprise :";
	
	
	public Recruiter() {
		super();
	}
	public Recruiter(String name, String surname, String login, String password, int id,
			Date creationDate, String e) {
		super(name, surname, login, password, id, LabelRole.RECRUITER, creationDate);
		offers = new ArrayList<>();
		company = e;
		super.setStatus(2001);
	}
	
	public Offer getOffer(int id) {
		for (Offer offer : offers) {
			if(offer.getId() == id) {
				return offer;
			}
		}
		return null;
	}
	
	public static List<Map<String,String>> getFields(){
		Field[] fields = Recruiter.class.getDeclaredFields();
		
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
	public void addOffer(Offer o) {
		offers.add(o);
	}
	
	public List<Offer> getOffers(){
		return this.offers;
	}
	
	public String getCompany() {
		return company;
	}
	
	public void setCompany(String company) {
		this.company = company;
	}
	
	public void deleteOffer(int id) {
		for (Offer offer : offers) {
			if (offer.getId() == id) {
				offers.remove(offer);
			}
		}
	}
}
