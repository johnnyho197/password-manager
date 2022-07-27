package Model.classes;

import java.util.ArrayList;

public class User implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
	private String userName;
    private String masterPassword;
    private String securityQuestion;
    private String securityAnswer;
    private ArrayList<Account> accounts;

    public User(String userName, String masterPassword, String securityQuestion, String securityAnswer) 
    {
        this.userName = userName;
        this.masterPassword = masterPassword;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.accounts = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMasterPassword() {
        return masterPassword;
    }

    public void setMasterPassword(String masterPassword) {
        this.masterPassword = masterPassword;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }
    
    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public ArrayList<Account> accounts() {
        return accounts;
    }

    public void setAccountArrayList(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }
    
    /**
     * This method supports searchAccount feature
     * @param
     */
    public ArrayList<Account> searchAccount(String inPut) 
    {
    	ArrayList<Account> matches = new ArrayList<Account>();
    	for (Account account : accounts)
    	{
    		if (account.getAccountName().equals(inPut) || account.getUserName().equals(inPut))
    			matches.add(account);
    	}
    	return matches;
    }

    /**
     * This method supports addAccount feature
     * @param
     */
    public boolean addAccount (Account newItem) {
        for (Account account : accounts) {
            if (account.getAccountName() == newItem.getAccountName()) {
                System.out.println("Item already exists.");
                return false;
            }
        }
        accounts.add(newItem);
        System.out.println("New item added.");
        return true;
    }

    public void deleteAccount (Account acc) {
        accounts().remove(acc);
        System.out.println("Item removed.");
    }

    public int getNumberOfExpiredAcc() {
        int counter = 0;
        for (Account account : accounts) {
            if (account.isExpired()) {
                counter++;
            }
        }
        return counter;
    }
}
