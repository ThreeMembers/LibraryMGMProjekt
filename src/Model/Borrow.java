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
		element.put("reader", this.reader.toJSON(Account.with_per_dateleft));
		element.put("employee", this.employee.toJSON(Account.id_name));
		element.put("checkdate", this.checkDate.toJSON());
		element.put("returndate", this.returnDate.toJSON());
		element.put("inprocess", this.inProcess);
		return element;
	}
}
