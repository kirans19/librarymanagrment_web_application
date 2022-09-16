package staff.operations;

public class LibraryUsers {

	private String id;
	private String email;
	private String first_name;
	private String last_name;
	private String mobile_no;
	public int getDue_amount() {
		return due_amount;
	}
	public void setDue_amount(int due_amount) {
		this.due_amount = due_amount;
	}
	private String address;
	private int due_amount;
	public LibraryUsers() {
		
	}
	public LibraryUsers(String id,String first_name) {
		super();
		this.id = id;
		this.first_name = first_name;
	}
	public LibraryUsers(String id, String email, String first_name, String last_name, String mobile_no,String address) {
		super();
		this.id = id;
		this.email = email;
		this.first_name = first_name;
		this.last_name = last_name;
		this.mobile_no = mobile_no;
		this.address = address;
	}
	public LibraryUsers(String id,String first_name, String last_name, String mobile_no,String address,int due_amount) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.mobile_no = mobile_no;
		this.address = address;
		this.due_amount=due_amount;
	}
	public LibraryUsers(String id, String first_name, String last_name) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	

	
}

