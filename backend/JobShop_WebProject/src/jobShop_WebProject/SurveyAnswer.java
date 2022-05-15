package jobShop_WebProject;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
public class SurveyAnswer{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int idUser;
	private int idQuestion;
	private int idResponse;
	private String extra;
	
	public SurveyAnswer() {}
	public SurveyAnswer(int idUser, int idQ, int idR,String extra) {
		this.idUser = idUser;
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
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
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
	
	
}
