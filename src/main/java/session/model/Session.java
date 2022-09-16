package session.model;

public class Session {
	
	private String id;
	private String session_id;
	private String creation_time;
	private String browser_details;
	
	public Session(String id, String session_id, String creation_time, String browser_details) {
		super();
		this.id= id;
		this.session_id = session_id;
		this.creation_time = creation_time;
		this.browser_details = browser_details;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}


	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public String getCreation_time() {
		return creation_time;
	}

	public void setCreation_time(String creation_time) {
		this.creation_time = creation_time;
	}

	public String getBrowser_details() {
		return browser_details;
	}

	public void setBrowser_details(String browser_details) {
		this.browser_details = browser_details;
	}
	
	
}
