package Model.classes;

import java.io.*;
import java.util.HashMap;

public class Database {
    private HashMap<String, User> users;
    private static Database dbObject;

    // create a constant filename provided fixed path in the current user's home directory
    private static final String FILENAME = System.getProperty("user.home") + File.separator + "keypass.data";

    // default constructor creates a list of existing users from serialized file
    private Database() {
        // users = new ArrayList<>();
        initFromFile();
    }

    /**
     * This method for creating a singleton Database object
     * @return a Database object
     */
    public static Database getInstance() {

        //create object if it's not already created
        if (dbObject == null) {
            dbObject = new Database();
        }

        //return the singleton object
        return dbObject;
    }

    /**
     * This method for reading/deserializing database from file
     */
    @SuppressWarnings("unchecked")
	public void initFromFile() {
        try {
            FileInputStream file = new FileInputStream(FILENAME);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            users = (HashMap<String, User>) in.readObject();

            in.close();
            file.close();
        } catch (FileNotFoundException ex) {
            users = new HashMap<>();
        } catch (IOException | ClassNotFoundException ex) {
            System.exit(1);
        }
    }

    /**
     * This method for writing/serializing database object into file
     */
    public void saveToFile() {
        // saves users to file.
        try
        {
            FileOutputStream file = new FileOutputStream(FILENAME);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(users);

            out.close();
            file.close();
        } catch (IOException ex) {
            System.exit(1);
        }
    }

    /**
     * This method supports the login feature
     * @param userName
     * @return User object
     */
    public User getUser(String userName, String masterPassword) {
    	System.out.println("Getting user " + userName);
        // given username doesn't exist in database
        if (!users.containsKey(userName)) {
        	System.out.println("Username " + userName + " doesn't exist");
            return null;
        // username and masterPassword don't match
        } else if (!users.get(userName).getMasterPassword().equals(masterPassword)) {
        	System.out.println(userName + " password doesn't match. expect " + users.get(userName).getMasterPassword() + " got " + masterPassword);
            return null;
        }
        System.out.println("Found user " + userName);
        // correct input, User object found
        return users.get(userName);
    }

    /**
     * This method supports the sign up feature
     * @param newUser
     */
    public boolean addUser(User newUser) {
        if (users.containsKey(newUser.getUserName())) {
            System.out.println("Username already exists.");
            return false;
        } else {
            users.put(newUser.getUserName(), newUser);
            System.out.println("New user added.");
            return true;
            }
        }
    /**
     * This method will check if the information user entered matched
     * the information in the database
     * @param userName
     * @param securityQuestion
     * @param securityAnswer
     * @return true or false
     */
    public boolean resetPassword(String userName, String securityQuestion, String securityAnswer)
    {
    	System.out.println("Checking... ");
    	if(!users.containsKey(userName))
    	{
    		System.out.println("Username " + userName + " doesn't exist.");
            return false;
    	} else if(! users.get(userName).getSecurityQuestion().equals(securityQuestion))   		
    	{
    		System.out.println("Security question doesn't match with account.");
    		return false;
    	} else if(! users.get(userName).getSecurityAnswer().equals(securityAnswer))
    	{
    		System.out.println("Security answer doesn't match with account.");
    		return false;
    	}
    	System.out.println("Verified!");
		return true;
    }
    /**
     * This method will update user's password
     * @param u
     * @param newMasterPassword
     * @return username
     */
    
    public User updatePassword(String username, String newMasterPassword) {
    	users.get(username).setMasterPassword(newMasterPassword);
    	return users.get(username);
    }
    
    public User modifyInformation(String username, String newMasterPassword, String securityQuestion, String securityAnswer) {
    	users.get(username).setMasterPassword(newMasterPassword);
    	users.get(username).setSecurityQuestion(securityQuestion);
    	users.get(username).setSecurityAnswer(securityAnswer);
		return users.get(username);
    	
    }
    
}

