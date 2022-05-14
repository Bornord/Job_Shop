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
		super();
		this.title = title;
		this.responses = Arrays.asList(responses);
	}
	
	public void appendToResponse(Question question, Response... responses ) {
		List<Response> addedResponses = Arrays.asList(responses);
		for (Response r : responses) {
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
