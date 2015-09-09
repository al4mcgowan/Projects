package Model;

public class User {
	private String name; // Person's name
	private String xnum;
	private char[] password;
	private String address;
	private String phoneNumber; // Person's phone number
	private String email;
	private int isAdmin;

	public User(String n, String x, char[] ps, String a, String pn, String e) {
		name = n;
		xnum = x;
		password = ps;
		address = a;
		phoneNumber = pn;
		email = e;
		isAdmin = 0;
	}
	
	public User(String n, String x, char[] ps, String a, String pn, String e, int isAdmin) 
	{
		name = n;
		xnum = x;
		password = ps;
		address = a;
		phoneNumber = pn;
		email = e;
		this.isAdmin = isAdmin;
	}

	public void setName(String n) {
		name = n;
	}

	public void setAddress(String a) {
		address = a;
	}

	public void setEmail(String e) {
		email = e;
	}

	public void setPhoneNumber(String pn) {
		phoneNumber = pn;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}

	public int getAdmin() {
		return isAdmin;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getXnum() {
		return xnum;
	}

	public void setXnum(String xnum) {
		this.xnum = xnum;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

}
