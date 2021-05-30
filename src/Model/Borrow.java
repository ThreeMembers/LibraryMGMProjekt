package Model;

public class Borrow extends Model{
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

    public Borrow(int id) {
        super(id);
    }

    public Borrow() {
    }

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

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isInProcess() {
        return inProcess;
    }

    public void setInProcess(boolean inProcess) {
        this.inProcess = inProcess;
    }
}
