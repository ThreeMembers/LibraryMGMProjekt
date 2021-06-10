package Model;

import org.json.simple.JSONObject;

public class DelBookHistory extends Model{
    private Account employee;
    private Date delDate;
    private String message;

    public DelBookHistory(int id) {
        super(id);
    }

    public DelBookHistory() {
    }

    public DelBookHistory(int id, Account employee, Date delDate, String message) {
        super(id);
        this.employee = employee;
        this.delDate = delDate;
        this.message = message;
    }

    public DelBookHistory(Account employee, Date delDate, String message) {
        this.employee = employee;
        this.delDate = delDate;
        this.message = message;
    }

    public Account getEmployee() {
        return employee;
    }

    public void setEmployee(Account employee) {
        this.employee = employee;
    }

    public Date getDelDate() {
        return delDate;
    }

    public void setDelDate(Date delDate) {
        this.delDate = delDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON() {
		JSONObject element = super.toJSON();
		element.put("employee", this.employee.toJSON(Account.id_name));
		element.put("deldate", this.delDate.toJSON());
		return element;
	}
    
}
