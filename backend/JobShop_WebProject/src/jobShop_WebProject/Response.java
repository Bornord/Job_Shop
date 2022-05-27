package jobShop_WebProject;

import javax.persistence.*;

@Entity
public class Response {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String placeholder;
	private boolean isAText;
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
	
	public Response(String placeholder,int nextQuestion,boolean isAText) {
		this.placeholder = placeholder;
		this.nextQuestion= nextQuestion;
		this.isAText = isAText;
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
	
	public boolean getIsAText() {
		return isAText;
	}

	public void setIsAText(boolean isAText) {
		this.isAText = isAText;
	}

	@Override
	public String toString() {
		return "Response [id=" + id + ", placeholder=" + placeholder + ", isAText=" + isAText + ", isSelected="
				+ isSelected + ", previousQuestion=" + previousQuestion + ", nextQuestion=" + nextQuestion + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Response) {
			if(isAText) {
				return ((Response) obj).getIsAText();
			}
			return ((Response) obj).placeholder.equalsIgnoreCase(placeholder);
		}
		return super.equals(obj);
	}

	
}
