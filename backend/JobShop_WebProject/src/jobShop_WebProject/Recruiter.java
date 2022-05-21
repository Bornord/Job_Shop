package jobShop_WebProject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	
	private String entreprise;
	
	public Recruiter() {
		super();
	}
	public Recruiter(String name, String surname, String login, String password, int id,
			Date creationDate, String e) {
		super(name, surname, login, password, id, LabelRole.RECRUITER, creationDate);
		offers = new ArrayList<>();
		entreprise = e;
	}
	
	public Offer getOffer(int id) {
		for (Offer offer : offers) {
			if(offer.getId() == id) {
				return offer;
			}
		}
		return null;
	}
	
	public void addOffer(Offer o) {
		offers.add(o);
	}
	
	public List<Offer> getOffers(){
		return this.offers;
	}
	
	public String getEntreprise() {
		return entreprise;
	}
	
	public void setEntreprise(String entreprise) {
		this.entreprise = entreprise;
	}
	
	public void deleteOffer(int id) {
		for (Offer offer : offers) {
			if (offer.getId() == id) {
				offers.remove(offer);
			}
		}
	}
}