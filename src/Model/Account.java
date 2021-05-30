package Model;


public class Account extends Model {
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
		this.realname = realname;
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
		this.secretCode = secretCode;
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
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	public int getDateLeft() {
		return dateLeft;
	}
	public void setDateLeft(int dateLeft) {
		this.dateLeft = dateLeft;
	}
	public Account(int id, Permission idPermission, String username, String userpassword, String realname, int age,
			boolean gender, String secretCode, Date registerDate, Date expirationDate, int dateLeft) {
		this.id = id;
		this.idPermission = idPermission;
		this.username = username;
		this.userpassword = userpassword;
		this.realname = realname;
		this.age = age;
		this.gender = gender;
		this.secretCode = secretCode;
		this.registerDate = registerDate;
		this.expirationDate = expirationDate;
		this.dateLeft = dateLeft;
	}

	public Account(int id, Permission idPermission, String username, String userpassword, String realname, int age, boolean gender, String secretCode, Date registerDate) {
		super(id);
		this.idPermission = idPermission;
		this.username = username;
		this.userpassword = userpassword;
		this.realname = realname;
		this.age = age;
		this.gender = gender;
		this.secretCode = secretCode;
		this.registerDate = registerDate;
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
