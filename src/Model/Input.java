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
		if(this.employee != null)
			element.put("employee", this.employee.toJSON(Account.id_name));
		if(this.inputDate != null)
			element.put("inputdate", this.inputDate.toJSON());
		return element;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((inputDate == null) ? 0 : inputDate.hashCode());
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
		Input other = (Input) obj;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (inputDate == null) {
			if (other.inputDate != null)
				return false;
		} else if (!inputDate.equals(other.inputDate))
			return false;
		return true;
	}

    
}
