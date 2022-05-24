package jobShop_WebProject;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Blog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String subtitle;
	private String content;
	private Date date;
	private int idAuthor;
	
	public Blog() {}

	public Blog(String title, String subTitle, String content, Date date, int idAuthor) {
		this.title = title;
		this.subtitle = subTitle;
		this.content = content;
		this.date = date;
		this.idAuthor = idAuthor;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subTitle) {
		this.subtitle = subTitle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getIdAuthor() {
		return idAuthor;
	}

	public void setIdAuthor(int idAuthor) {
		this.idAuthor = idAuthor;
	}
	
	
}
