package Model;

import org.json.simple.JSONObject;

public class Model {
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
