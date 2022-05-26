package jobShop_WebProject.utils;

public class Request {
	private String json;
	private int code;
	public Request(String json, int co) {
		super();
		this.json = json;
		this.code = co;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getJson() {
		return json;
	}
}
