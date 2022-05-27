package jobShop_WebProject;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Entity
public class SurveyAnswer{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int idProfile;
	private int idQuestion;
	private int idResponse;
	private String extra;
	
	public SurveyAnswer() {}
	public SurveyAnswer(int idProfile, int idQ, int idR,String extra) {
		this.idProfile = idProfile;
		this.idQuestion = idQ;
		this.idResponse = idR;
		this.extra = extra;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdProfile() {
		return idProfile;
	}
	public void setIdProfile(int idProfile) {
		this.idProfile = idProfile;
	}
	public int getIdQuestion() {
		return idQuestion;
	}
	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}
	public int getIdResponse() {
		return idResponse;
	}
	public void setIdResponse(int idResponse) {
		this.idResponse = idResponse;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	@Override
	public String toString() {
		return "SurveyAnswer [id=" + id + ", idProfile=" + idProfile + ", idQuestion=" + idQuestion + ", idResponse="
				+ idResponse + ", extra=" + extra + "]";
	}
	
	
}
