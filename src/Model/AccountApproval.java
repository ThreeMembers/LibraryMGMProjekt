package Model;

import org.json.simple.JSONObject;

public class AccountApproval extends Account{
    @SuppressWarnings("unchecked")
	@Override
	public JSONObject toJSON() {
    	JSONObject element = super.toJSON();
    	element.put("isauthenticate", this.isAuthenticate);
    	return element;
	}

	private boolean isAuthenticate;

    public AccountApproval(
    		int id, 
    		Permission idPermission, 
    		String username, 
    		String userpassword, 
    		String realname, 
    		int age, 
    		boolean gender, 
    		String secretCode, 
    		Date registerDate, 
    		boolean isAuthenticate) {
        super(
        		id, 
        		idPermission, 
        		username, 
        		userpassword, 
        		realname, 
        		age, 
        		gender, 
        		secretCode, 
        		registerDate);
        this.isAuthenticate = isAuthenticate;
    }

    public AccountApproval(int id){
        super(id);
    }

    public AccountApproval(int id, boolean isAuthenticate) {
        super(id);
        this.isAuthenticate = isAuthenticate;
    }

    public AccountApproval(
    		String username, 
    		String userpassword, 
    		boolean isAuthenticate) {
        super(username, userpassword);
        this.isAuthenticate = isAuthenticate;
    }

    public AccountApproval(
    		boolean isAuthenticate) {
        this.isAuthenticate = isAuthenticate;
    }
    public  AccountApproval(){}

    public boolean isAuthenticate() {
        return isAuthenticate;
    }

    public void setAuthenticate(boolean authenticate) {
        isAuthenticate = authenticate;
    }
}
