package Model;

import org.json.simple.JSONObject;

public class Model {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Model other = (Model) obj;
		if (id != other.id)
			return false;
		return true;
	}

	protected int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Model(int id) {
		this.id = id;
	}
	
	public Model() {}
	
	@SuppressWarnings("unchecked")
	public JSONObject toJSON() {
		JSONObject element = new JSONObject();
		element.put("id", this.id);
		return element;
	}

}
