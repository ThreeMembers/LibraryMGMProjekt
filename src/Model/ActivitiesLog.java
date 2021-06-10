package Model;

import org.json.simple.JSONObject;

public class ActivitiesLog extends Model{
    private Account account;
    private ActivitiesMessage activitiesMessage;
    private DateTime input;

    public ActivitiesLog(int id, Account account, ActivitiesMessage activitiesMessage, DateTime input) {
        super(id);
        this.account = account;
        this.activitiesMessage = activitiesMessage;
        this.input = input;
    }

    public ActivitiesLog(Account account, ActivitiesMessage activitiesMessage, DateTime input) {
        this.account = account;
        this.activitiesMessage = activitiesMessage;
        this.input = input;
    }

    public ActivitiesLog(int id) {
        super(id);
    }

    public ActivitiesLog() {
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public ActivitiesMessage getActivitiesMessage() {
        return activitiesMessage;
    }

    public void setActivitiesMessage(ActivitiesMessage activitiesMessage) {
        this.activitiesMessage = activitiesMessage;
    }

    public DateTime getInput() {
        return input;
    }

    public void setInput(DateTime input) {
        this.input = input;
    }

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON() {
		JSONObject element = super.toJSON();
		element.put("username", this.account.toJSON(Account.id_name));
		element.put("messeger", this.activitiesMessage.getMessage());
		element.put("datetime", this.input.toJSON());
		return element;
	}
    
}
