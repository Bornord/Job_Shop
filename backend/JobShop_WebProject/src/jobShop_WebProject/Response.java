package jobShop_WebProject;

import javax.persistence.*;

@Entity
public class Response {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String placeholder;
	private String user_response;
	private boolean isSelected;
	
	@ManyToOne(fetch=FetchType.EAGER, optional = true)
	//@Cascade(CascadeType.SAVE_UPDATE)
	private Question previousQuestion;
	
	/*@ManyToOne
	private Question nextQuestion;*/
	
	private int nextQuestion;
	
	public Response() {
	}
	
	public Response(String placeholder) {
		this.placeholder = placeholder;
	}
	
	public Response(String placeholder,int nextQuestion) {
		this.placeholder = placeholder;
		this.nextQuestion= nextQuestion;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlaceholder() {
		return placeholder;
	}
	public Question getPreviousQuestion() {
		return previousQuestion;
	}
	
	public void setPreviousQuestion(Question previousQuestion) {
		this.previousQuestion = previousQuestion;
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

	public boolean getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(boolean isSelelected) {
		this.isSelected = isSelelected;
	}

	public int getNextQuestion() {
		return nextQuestion;
	}

	public void setNextQuestion(int nextQuestion) {
		this.nextQuestion = nextQuestion;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Response) {
			return ((Response) obj).placeholder.equalsIgnoreCase(placeholder);
		}
		return super.equals(obj);
	}
	@Override
	public String toString() {
		return "placeholder : "+placeholder +" , next question : " + nextQuestion  ;
	}
	
}
