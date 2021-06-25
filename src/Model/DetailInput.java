package Model;

import org.json.simple.JSONObject;

import java.util.Objects;

public class DetailInput{
	private int idRecord;
	private int idStock;
    public DetailInput() {
    }

	public DetailInput(int idRecord, int idStock) {
		this.idRecord = idRecord;
		this.idStock = idStock;
	}

	public int getIdRecord() {
		return idRecord;
	}

	public void setIdRecord(int idRecord) {
		this.idRecord = idRecord;
	}

	public int getIdStock() {
		return idStock;
	}

	public void setIdStock(int idStock) {
		this.idStock = idStock;
	}

	@SuppressWarnings("unchecked")
   	public JSONObject toJSON() {
       	JSONObject element = new JSONObject();
       	element.put("record", this.idRecord);
       	element.put("stock", this.idStock);
       	return element;
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DetailInput that = (DetailInput) o;
		return idRecord == that.idRecord && idStock == that.idStock;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idRecord, idStock);
	}
}
