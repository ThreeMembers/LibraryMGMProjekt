package Model;

import org.json.simple.JSONObject;

public class Input extends Model{
    private Account employee;
    private Date inputDate;

    public Account getEmployee() {
        return employee;
    }

    public void setEmployee(Account employee) {
        this.employee = employee;
    }

    public Date getInputDate() {
        return inputDate;
    }

    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }

    public Input(){

    }

    public Input(int id){
        super(id);
    }

    public Input(int id, Account employee, Date inputDate) {
        super(id);
        this.employee = employee;
        this.inputDate = inputDate;
    }

    public Input(Account employee, Date inputDate) {
        this.employee = employee;
        this.inputDate = inputDate;
    }
    @SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON() {
		JSONObject element = super.toJSON();
		element.put("employee", this.employee.toJSON(Account.id_name));
		element.put("inputDate", this.inputDate.toJSON());
		return element;
	}

    
}
