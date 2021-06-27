package Model;

import org.json.simple.JSONObject;

public class Borrow extends Model{
	
	public static int borrow_time = 15;
	
    private Account reader;
    private Account employee;
    private Date checkDate;
    private Date returnDate;
    private boolean inProcess;

    public Account getReader() {
        return reader;
    }

    public void setReader(Account reader) {
        this.reader = reader;
    }

    public Account getEmployee() {
        return employee;
    }

    public void setEmployee(Account employee) {
        this.employee = employee;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate() {
        if(checkDate == null || checkDate.isEqual(new Date(0, 0, 0))) {
        	this.returnDate = new Date(0, 0, 0);
        }
        else {
			this.returnDate = Date.addStatic(checkDate, borrow_time);
		}
    }
    public void setReturnDate(Date returnDate) {
    	this.returnDate = returnDate;
    }

    public Borrow(int id) {
        super(id);
    }

    public Borrow() {}
    

    public Borrow(int id, Account reader, Account employee, Date checkDate, Date returnDate, boolean inProcess) {
        super(id);
        this.reader = reader;
        this.employee = employee;
        this.checkDate = checkDate;
        this.returnDate = returnDate;
        this.inProcess = inProcess;
    }

    public Borrow(Account reader, Account employee, Date checkDate, Date returnDate, boolean inProcess) {
        this.reader = reader;
        this.employee = employee;
        this.checkDate = checkDate;
        this.returnDate = returnDate;
        this.inProcess = inProcess;
    }
    
    public Borrow(Account reader, Account employee, Date checkDate) {
    	 this.reader = reader;
         this.employee = employee;
         this.checkDate = checkDate;
         setReturnDate();
         setInProcess(true);
	}

    public boolean isInProcess() {
        return inProcess;
    }

    public void setInProcess(boolean inProcess) {
        this.inProcess = inProcess;
    }

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON() {
		JSONObject element = super.toJSON();
		if(this.reader != null)
			element.put("reader", this.reader.toJSON(Account.id_name));
		if(this.employee != null)
			element.put("employee", this.employee.toJSON7());
		if(this.checkDate != null)
			element.put("checkdate", this.checkDate.toJSON());
		if(this.returnDate != null)
			element.put("returndate", this.returnDate.toJSON());
		element.put("inprocess", this.inProcess);
		return element;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((checkDate == null) ? 0 : checkDate.hashCode());
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + (inProcess ? 1231 : 1237);
		result = prime * result + ((reader == null) ? 0 : reader.hashCode());
		result = prime * result + ((returnDate == null) ? 0 : returnDate.hashCode());
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
		Borrow other = (Borrow) obj;
		if (checkDate == null) {
			if (other.checkDate != null)
				return false;
		} else if (!checkDate.equals(other.checkDate))
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (inProcess != other.inProcess)
			return false;
		if (reader == null) {
			if (other.reader != null)
				return false;
		} else if (!reader.equals(other.reader))
			return false;
		if (returnDate == null) {
			if (other.returnDate != null)
				return false;
		} else if (!returnDate.equals(other.returnDate))
			return false;
		return true;
	}
}
