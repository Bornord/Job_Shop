package jobShop_WebProject;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * classe Profile composée des informations d'un étudiant utilisateur 
 * ou des informations correspondant à une offre. 
 * @author arenard2
 *
 */

@Entity
public class Profile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDate startDate ;	//aaa-mm-dd
	private LocalDate endDate;
	private int term;
	private int idUser;
	private boolean isRecruiter;
	@OneToMany(mappedBy="idUser", fetch=FetchType.EAGER)
	private List<SurveyAnswer> surveyAnswer;
	
	public Profile() {}
	
	public Profile(LocalDate startDate, LocalDate endDate, int term, int idUser, boolean isRecruiter,
			List<SurveyAnswer> surveyAnswer) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.term = term;	//nombre de mois
		this.idUser = idUser;
		this.isRecruiter = isRecruiter;
		this.surveyAnswer = surveyAnswer;
	}
	
	@Override
		public String toString() {
			String s = "";
			for (SurveyAnswer sa : surveyAnswer) {
				s+="survey answer : " + sa.getExtra() + sa.getId() +", ";
			}
			return "start : " + startDate + ", end : " + endDate + ", term : " + term 
					+", id : " + idUser + ", surveyAnswers : " + s;
		}
	
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public List<SurveyAnswer> getSurveyAnswer() {
		return surveyAnswer;
	}
	public void setSurveyAnswer(List<SurveyAnswer> questionReponse) {
		this.surveyAnswer = questionReponse;
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
	public boolean getIsRecruiter() {
		return isRecruiter;
	}
	public void setIsRecruiter(boolean isRecruiter) {
		this.isRecruiter = isRecruiter;
	}
	
	
	
}
