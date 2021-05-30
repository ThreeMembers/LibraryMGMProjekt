package Model;

public class BorrowRequest extends Model{
    private Account reader;
    private Date sendRequestDate;

    public BorrowRequest(int id) {
        super(id);
    }

    public BorrowRequest() {
    }

    public BorrowRequest(int id, Account reader, Date sendRequestDate) {
        super(id);
        this.reader = reader;
        this.sendRequestDate = sendRequestDate;
    }

    public BorrowRequest(Account reader, Date sendRequestDate) {
        this.reader = reader;
        this.sendRequestDate = sendRequestDate;
    }

    public Account getReader() {
        return reader;
    }

    public void setReader(Account reader) {
        this.reader = reader;
    }

    public Date getSendRequestDate() {
        return sendRequestDate;
    }

    public void setSendRequestDate(Date sendRequestDate) {
        this.sendRequestDate = sendRequestDate;
    }
}
