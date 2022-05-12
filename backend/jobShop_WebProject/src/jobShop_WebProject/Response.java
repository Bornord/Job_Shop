package jobShop_WebProject;

import javax.persistence.*;

@Entity
public class Response {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String placeholder;
	private String user_response;
	private boolean isSelelected;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Question nextQuestion;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public String getUser_response() {
		return user_response;
	}

	public void setUser_response(String user_response) {
		this.user_response = user_response;
	}

	public boolean isSelelected() {
		return isSelelected;
	}

	public void setSelelected(boolean isSelelected) {
		this.isSelelected = isSelelected;
	}

	public Question getNextQuestion() {
		return nextQuestion;
	}

	public void setNextQuestion(Question nextQuestion) {
		this.nextQuestion = nextQuestion;
	}

	public Response(String placeholder) {
		this.placeholder = placeholder;
	}
	
	public Response(String placeholder,Question nextQuestion) {
		this.placeholder = placeholder;
		this.nextQuestion= nextQuestion;
	}
	public void setNextQuetsion(Question question) {
		this.nextQuestion = question;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Response) {
			return ((Response) obj).placeholder.equalsIgnoreCase(placeholder);
		}
		return super.equals(obj);
	}
	
	
}
