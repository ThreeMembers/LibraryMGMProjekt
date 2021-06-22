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
		if(this.employee != null)
			element.put("employee", this.employee.toJSON(Account.id_name));
		if(this.delDate != null)
			element.put("deldate", this.delDate.toJSON());
		return element;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((delDate == null) ? 0 : delDate.hashCode());
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		DelBookHistory other = (DelBookHistory) obj;
		if (delDate == null) {
			if (other.delDate != null)
				return false;
		} else if (!delDate.equals(other.delDate))
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}
    
}
