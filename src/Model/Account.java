package Model;

import java.time.LocalDate;
import java.util.Base64;

import org.json.simple.JSONObject;

public class Account extends Model {
	public static final int avaible_date = 100; 
	protected Permission idPermission;
	protected String username;
	protected String userpassword;
	protected String realname;
	protected int age;
	protected boolean gender;
	protected String secretCode;
	protected Date registerDate;
	private Date expirationDate;
	private int dateLeft;
	private String token;
	
	public String getToken() {
		return this.token;
	}
	public void setToken() {
		String raw = this.username + ":" + this.userpassword;
		this.token = Base64.getEncoder().encodeToString(raw.getBytes());
	}
	public Permission getIdPermission() {
		return idPermission;
	}
	public void setIdPermission(Permission idPermission) {
		this.idPermission = idPermission;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname == null ? "NO INFO" : realname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public String getSecretCode() {
		return secretCode;
	}
	public void setSecretCode(String secretCode) {
		this.secretCode = secretCode == null ? "NO INFO" : secretCode;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public boolean setExpirationDate(Date expirationDate) {
		if(this.expirationDate == null) {
			this.expirationDate = expirationDate;
			return true;
		}
		if(this.expirationDate.compareTo(expirationDate) == -1) {
			this.expirationDate = expirationDate;
			return true;
		}else {
			return false;
		}
	}
	public int getDateLeft() {
		Date now = new Date(LocalDate.now().toString());
		this.dateLeft = Date.NumberDaysBetween(now, this.expirationDate);
		return dateLeft;
	}
	@Override
	public String toString() {
		return "Account [idPermission=" + idPermission + ", username=" + username + ", userpassword=" + userpassword
				+ ", realname=" + realname + ", age=" + age + ", gender=" + gender + ", secretCode=" + secretCode
				+ ", registerDate=" + registerDate + ", expirationDate=" + expirationDate + ", dateLeft=" + dateLeft
				+ "]";
	}

	public static final int full_field = 0;
	public static final int id_name = 1;
	public static final int id_name_dateleft = 2;
	public static final int id_name_token = 3;
	public static final int with_per_dateleft = 4;
	
	public JSONObject toJSON(int mode) {
		switch (mode) {
		case 1:
			return toJSON1();
		case 2:
			return toJSON2();
		case 3:
			return toJSON3();
		case 4:
			return toJSON4();
		default:
			return toJSONFull();
		}
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject toJSONFull() {
		JSONObject element = new JSONObject();
		element.put("id", this.id);
		element.put("age", this.age);
		element.put("dateleft", this.getDateLeft());
		element.put("expiration", this.expirationDate.toJSON());
		element.put("gender", this.gender);
		element.put("idpermission", this.idPermission.toJSONPositionOnly());		
		element.put("register", this.registerDate.toJSON());
		element.put("realname", this.realname);
		element.put("token", this.getToken());
		element.put("secretcode", this.secretCode);
		element.put("username", this.username);
		element.put("userpassword", this.userpassword);
		return element;
	}
	@SuppressWarnings("unchecked")
	public JSONObject toJSON1() {
		JSONObject element = new JSONObject();
		element.put("id", this.id);
		element.put("username", this.username);
		return element;
	}
	@SuppressWarnings("unchecked")
	public JSONObject toJSON2() {
		JSONObject element = new JSONObject();
		element.put("id", this.id);
		element.put("dateleft", this.getDateLeft());
		element.put("username", this.username);
		return element;
	}
	@SuppressWarnings("unchecked")
	public JSONObject toJSON3() {
		JSONObject element = new JSONObject();
		element.put("id", this.id);
		element.put("token", this.getToken());
		element.put("username", this.username);
		return element;
	}
	@SuppressWarnings("unchecked")
	public JSONObject toJSON4() {
		JSONObject element = new JSONObject();
		element.put("id", this.id);
		element.put("dateleft", this.getDateLeft());
		element.put("idpermission", this.idPermission.toJSONPositionOnly());
		element.put("username", this.username);
		return element;
	}
	
	
	public Account(int id, Permission idPermission, String username, String userpassword, String realname, int age,
			boolean gender, String secretCode, Date registerDate, Date expirationDate) {
		this.id = id;
		this.idPermission = idPermission;
		this.username = username;
		this.userpassword = userpassword;
		this.realname = realname == null ? "NO INFO" : realname;
		this.age = age;
		this.gender = gender;
		this.secretCode = secretCode == null ? "NO INFO" : secretCode;
		this.registerDate = registerDate;
		setExpirationDate(expirationDate);
		setToken();
		//getDateLeft();
	}

	public Account(
			int id, 
			Permission idPermission, 
			String username, 
			String userpassword, 
			String realname, 
			int age, 
			boolean gender, 
			String secretCode, 
			Date registerDate) 
	{
		super(id);
		this.idPermission = idPermission;
		this.username = username;
		this.userpassword = userpassword;
		this.realname = realname == null ? "NO INFO" : realname;
		this.age = age;
		this.gender = gender;
		this.secretCode = secretCode == null ? "NO INFO" : secretCode;
		this.registerDate = registerDate;
		setExpirationDate(registerDate.add(Account.avaible_date));
		setToken();
		//getDateLeft();
	}

	public Account(int id) {
		super();
	}
	public Account(String username, String userpassword) {
		this.username = username;
		this.userpassword = userpassword;
	}
	public Account(){}
	
}
