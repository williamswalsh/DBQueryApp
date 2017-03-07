package account;

public class UserAccount {
	private long userID;
	private String firstName;
	private String lastName;
	
	// Constructors
	public UserAccount(){}
	public UserAccount(long userID, String firstName, String lastName){
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	// Accessors
	public long getUserID() {
		return userID;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}	
	
	// Mutators
	public void setUserID(long userID) {
		this.userID = userID;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
