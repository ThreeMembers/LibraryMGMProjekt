package Model;

import org.json.simple.JSONObject;

public class ActivitiesMessage extends Model{
    @SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON() {
		JSONObject element = super.toJSON();
		element.put("message", this.message);
		return element;
	}

	private String message;

    public ActivitiesMessage(int id, String message) {
        super(id);
        this.message = message;
    }

    public ActivitiesMessage(String message) {
        this.message = message;
    }

    public ActivitiesMessage(int id) {
        super(id);
    }

    public ActivitiesMessage() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
