package jobShop_WebProject;

import java.util.Date;

import javax.persistence.*;

/**
 * classe Profile composée des informations d'un étudiant utilisateur 
 * ou des informations correspondant à une offre. 
 * @author arenard2
 *
 */

@Entity
public abstract class Profile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	protected Date startDate ;
	protected Date endDate;
	protected String domain;
	protected int term;
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public int getTerm() {
		return term;
	}
	public void setTerm(int term) {
		this.term = term;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
