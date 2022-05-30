package jobShop_WebProject;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
public class FirstQuestion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int idFirstQuestion;
	private String nameSurvey;
	
	public FirstQuestion() {}
	public FirstQuestion(int id, String name){
		idFirstQuestion = id;
		nameSurvey = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdFirstQuestion() {
		return idFirstQuestion;
	}
	public void setIdFirstQuestion(int idFirstQuestion) {
		this.idFirstQuestion = idFirstQuestion;
	}
	public String getNameSurvey() {
		return nameSurvey;
	}
	public void setNameSurvey(String nameSurvey) {
		this.nameSurvey = nameSurvey;
	}
}

