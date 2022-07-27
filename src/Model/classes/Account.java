package Model.classes;

import java.util.Date;

import edu.sjsu.yazdankhah.crypto.util.PassUtil;

public class Account implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
	private String accountName;
    private String urlName;
    private String userName;
    private String password;
    private String email;
    private Date expirationDate;
 
    public Account (String accountName, String urlName, String userName, String password, String email, Date expirationDate) {
        this.accountName = accountName;
        this.urlName = urlName;
        this.userName = userName;
        System.out.println("new account password: " + password);
        this.password = password;
        this.email = email;
        this.expirationDate = expirationDate;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
    	
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) { 
		this.expirationDate = expirationDate; 
	}

    public boolean isExpired() {
        if (expirationDate.before(new Date())) {
            return true;
        }
        return false;
    }
}
