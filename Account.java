package SmallChange;


/**
 * The Account class represents a user account with a name, password, and email.
 * @author yuanyuanliu
 */
public class Account {
	
	/**
	 * the user name of account
	 */
	private String name;
	/**
	 * the password of user account
	 */
	private String password;
	
	/**
	 * the email address of user account
	 */
	private String email;
	
	
	/**
	 * constructs a new Account object with the specified name, password, and email
	 * @param name  the user name of account
	 * @param password the password of user account
	 * @param email the email address of user account
	 */
	public Account(String name, String password,  String email) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
	}
	
	
	/**
	 * Returns the name of the user account
	 * @return the name of the user account
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name of the user account
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the password of the user account
	 * @return the password of the user account
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets the password of the user account
	 * @param password the password of the user account.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

  /**
   * Returns the email address of the user account
   * @return the email address of the user account
   */
	public String getEmail() {
		return email;
	}

/**
 * Sets the email address of the user account
 * @param email the email address of the user account
 */
	public void setEmail(String email) {
		this.email = email;
	}
	

}
