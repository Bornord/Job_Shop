package jobShop_WebProject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.*;

@Entity
public class Question {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String title;
	
	@OneToMany(mappedBy="id", fetch=FetchType.EAGER)
	private List<Response> responses;
	
	public Question() {
		responses = new ArrayList<Response>();
	}
	public Question(String title, Response... responses) {
		this.title = title;
		this.responses = Arrays.asList(responses);
	}
	
	@Override
	public String toString() {
		String s = "";
		for (Response response : responses) {
			s+="reponse : " + response.getPlaceholder()+",";
		}
		return "titre : "+ this.title + ", id : " + this.id + ", reponses : " + s;
	}
	
	public void appendToAllResponses(Question question) {
		for (Response r : responses) {
			r.setNextQuestion(question);
		}
	}
	
	public void appendToResponse(Question question, String... placeholders ) {
		List<String> addedResponses = Arrays.asList(placeholders);
		for (Response r : this.responses) {
			if(addedResponses.contains(r.getPlaceholder())) {
				r.setNextQuestion(question);
			}
		}
	}
	
	public void appendToResponse(Question question, Response... responses ) {
		List<Response> addedResponses = Arrays.asList(responses);
		for (Response r : this.responses) {
			if(addedResponses.contains(r)) {
				r.setNextQuestion(question);
			}
		}
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Response> getResponses() {
		return responses;
	}
	public void setResponses(List<Response> responses) {
		this.responses = responses;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
