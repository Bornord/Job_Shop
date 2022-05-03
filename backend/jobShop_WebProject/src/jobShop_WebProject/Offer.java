package jobShop_WebProject;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
/**
 * @author arenard2
 * classe Offer, décrit une offre de poste. cet objet sera crée par le Recruiter.
 */
@Entity
public class Offer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String title;
	
	private String subTitle;
	
	private String description;
	
	private int salary;
	
	private String Contract; //changer en enum
	
	private Date dateStart;

	private Profile idealProfile;
	
	private List<Status> status;
	
	@ManyToOne
	private Recruiter recruiter;
	
	public Offer(int id, String title, String subTitle, String description, int salary, String contract,
			Date dateStart) {
		super();
		//this.id = id;
		this.title = title;
		this.subTitle = subTitle;
		this.description = description;
		this.salary = salary;
		Contract = contract;
		this.dateStart = dateStart;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getContract() {
		return Contract;
	}

	public void setContract(String contract) {
		Contract = contract;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}
	
	/**
	 * méthode qui crée le profil idéal correspondant à cette offre
	 */
	public void createIdealProfile() {
		//this.idealProfile = new Profile(...);
	}

	public Profile getIdealProfile() {
		return idealProfile;
	}

	public void setIdealProfile(Profile idealProfile) {
		this.idealProfile = idealProfile;
	}

	public List<Status> getStatus() {
		return status;
	}

	public void setStatus(List<Status> status) {
		this.status = status;
	}

}