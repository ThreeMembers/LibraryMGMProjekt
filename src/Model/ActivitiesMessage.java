package Model;

public class ActivitiesMessage extends Model{
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
