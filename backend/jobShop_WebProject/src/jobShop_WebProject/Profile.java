package jobShop_WebProject;

import java.util.Date;

/**
 * classe Profile composée des informations d'un étudiant utilisateur 
 * ou des informations correspondant à une offre. 
 * @author arenard2
 *
 */
public abstract class Profile {
	protected Date startDate ;
	protected Date endDate;
	protected Domain domain;
	protected int term;
	protected int id;
	
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
	public Domain getDomain() {
		return domain;
	}
	public void setDomain(Domain domain) {
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
