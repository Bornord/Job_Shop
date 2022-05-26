package jobShop_WebProject;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * classe abstraite pour les différents types d'utilisateur
 * @author arenard2
 *
 */
@Entity
public abstract class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	@Transient
	public static String namePlaceholder = "Prenom :";
	private String surname;
	@Transient
	public static String surnamePlaceholder = "Nom :";
	private String login;
	@Transient
	public static String loginPlaceholder = "Email :";
	private String password;
	@Transient
	public static String passwordPlaceholder = "Mot de passe :";
	private String accessToken;
	private String refreshToken;
	private LabelRole role;
	private int status;		//2000 -> Student, 2001 -> Recruiter, 2002 -> Admin
	private Date creationDate;
	
	public User() {}
	public User(String name, String surname, String login, String password, int id, LabelRole role, Date creationDate) {
		this.accessToken = null;
		this.refreshToken = null;
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
		this.role = role;
		this.creationDate = creationDate;
	}
	

	public User(String name, String surname, String login, String password, int id, LabelRole role, int status, Date creationDate) {
		this.accessToken = null;
		this.refreshToken = null;
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
		this.role = role;
		this.status = status;
		this.creationDate = creationDate;
	}
	
	public static List<Map<String,String>> getFields(){
		Field[] fields = User.class.getDeclaredFields();
		
		List<Map<String,String>> res = new ArrayList<>();
		for (Field field : fields) {
			if(!Modifier.isStatic(field.getModifiers())) {				
				Map<String,String> f = new HashMap<>();
				f.put(field.getName(), field.getType().getSimpleName());
				res.add(f);
			}
		}
		return res;
	}
	
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", login=" + login + ", password="
				+ password + ", accessToken=" + accessToken + ", refreshToken=" + refreshToken + ", role=" + role
				+ ", status=" + status + ", creationDate=" + creationDate + "]";
	}
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	
	public void destroyTokens() {
		this.accessToken = null;
		this.refreshToken = null;
	}
		
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LabelRole getRole() {
		return role;
	}
	public void setRole(LabelRole role) {
		this.role = role;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	
}
