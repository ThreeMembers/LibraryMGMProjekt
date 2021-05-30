package Model;

public class Permission extends Model{
	private String position;
	private boolean addNewBk;
	private boolean editBk;
	private boolean delBk;
	private boolean validAcc;
	private boolean refreshAcc;
	private boolean authenBr;
	private boolean registerEmpAcc;
	private boolean delEmp;
	
	
	public Permission(int id, String position, boolean addNewBk, boolean editBk, boolean delBk, boolean validAcc,
			boolean refreshAcc, boolean authenBr, boolean registerEmpAcc, boolean delEmp) {
		
		this.id = id;
		this.position = position;
		this.addNewBk = addNewBk;
		this.editBk = editBk;
		this.delBk = delBk;
		this.validAcc = validAcc;
		this.refreshAcc = refreshAcc;
		this.authenBr = authenBr;
		this.registerEmpAcc = registerEmpAcc;
		this.delEmp = delEmp;
	}
	public Permission(int id, String position) {
		
		this.id = id;
		this.position = position;
	}
	public Permission() {}
	
	public Permission(int id) {
		super();
	}
	
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public boolean isAddNewBk() {
		return addNewBk;
	}
	public void setAddNewBk(boolean addNewBk) {
		this.addNewBk = addNewBk;
	}
	public boolean isEditBk() {
		return editBk;
	}
	public void setEditBk(boolean editBk) {
		this.editBk = editBk;
	}
	public boolean isDelBk() {
		return delBk;
	}
	public void setDelBk(boolean delBk) {
		this.delBk = delBk;
	}
	public boolean isValidAcc() {
		return validAcc;
	}
	public void setValidAcc(boolean validAcc) {
		this.validAcc = validAcc;
	}
	public boolean isRefreshAcc() {
		return refreshAcc;
	}
	public void setRefreshAcc(boolean refreshAcc) {
		this.refreshAcc = refreshAcc;
	}
	public boolean isAuthenBr() {
		return authenBr;
	}
	public void setAuthenBr(boolean authenBr) {
		this.authenBr = authenBr;
	}
	public boolean isRegisterEmpAcc() {
		return registerEmpAcc;
	}
	public void setRegisterEmpAcc(boolean registerEmpAcc) {
		this.registerEmpAcc = registerEmpAcc;
	}
	public boolean isDelEmp() {
		return delEmp;
	}
	public void setDelEmp(boolean delEmp) {
		this.delEmp = delEmp;
	}
	@Override
	public String toString() {
		return "Permission [id=" + id + ", position=" + position + ", addNewBk=" + addNewBk + ", editBk=" + editBk
				+ ", delBk=" + delBk + ", validAcc=" + validAcc + ", refreshAcc=" + refreshAcc + ", authenBr="
				+ authenBr + ", registerEmpAcc=" + registerEmpAcc + ", delEmp=" + delEmp + "]";
	}
	
	
}
