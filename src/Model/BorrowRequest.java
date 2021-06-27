package Model;

import org.json.simple.JSONObject;

public class BorrowRequest extends Model{

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (isAuthen ? 1231 : 1237);
		result = prime * result + ((reader == null) ? 0 : reader.hashCode());
		result = prime * result + ((sendRequestDate == null) ? 0 : sendRequestDate.hashCode());
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
		BorrowRequest other = (BorrowRequest) obj;
		if (isAuthen != other.isAuthen)
			return false;
		if (reader == null) {
			if (other.reader != null)
				return false;
		} else if (!reader.equals(other.reader))
			return false;
		if (sendRequestDate == null) {
			if (other.sendRequestDate != null)
				return false;
		} else if (!sendRequestDate.equals(other.sendRequestDate))
			return false;
		return true;
	}

	private Account reader;
    private Date sendRequestDate;
    private boolean isAuthen;

    public BorrowRequest(int id) {
        super(id);
    }

    public BorrowRequest() {
    }

    public BorrowRequest(int id, Account reader, Date sendRequestDate, boolean authen) {
        super(id);
        this.reader = reader;
        this.sendRequestDate = sendRequestDate;
        this.isAuthen = authen;
    }

    public BorrowRequest(Account reader, Date sendRequestDate) {
        this.reader = reader;
        this.sendRequestDate = sendRequestDate;
    }
    
    public BorrowRequest(Account reader, Date sendRequestDate, boolean authen) {
        this.reader = reader;
        this.sendRequestDate = sendRequestDate;
        this.isAuthen = authen;
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

	public boolean isAuthen() {
		return isAuthen;
	}

	public void setAuthen(boolean isAuthen) {
		this.isAuthen = isAuthen;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON() {
		JSONObject element = super.toJSON();
		if(this.reader != null)
			element.put("reader", this.reader.toJSON(Account.with_per_dateleft));
		if(this.sendRequestDate != null)
			element.put("senddate", this.sendRequestDate.toJSON());
		element.put("authen", this.isAuthen);
		return element;
	}
    
    
}
