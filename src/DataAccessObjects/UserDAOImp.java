
package DataAccessObjects;

import Models.User;
import java.util.List;
import Database.CreateDB;
import Interfaces.UserDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Implements the User Data Access Object interface.
 * @author Daniel Burrell
 */
public class UserDAOImp implements UserDAO{
    private final CreateDB handler;
    private String statement;
    private ResultSet results;
    
    /**
    * Constructor for the UserDAOImp class 
    * Creates a connection to the database.
    */
    public UserDAOImp(){
        statement = "";
        handler = CreateDB.getHandler();
    }
    
    /**
    * Unpacks a user's information after validation and adds them to the 
    * table in the database.
    * @param user The user of the system
    */
    @Override
    public void addUser(User user){
        String username = user.getUsername();
        String firstname = user.getFirstname();
        String lastname = user.getLastname();
        String email = user.getEmail();
        String password = user.getPassword();
        String age = user.getAge();
        String address = user.getAddress();
        String phone = user.getPhone();
		
        statement = "INSERT INTO USERS VALUES ('"+
                                username + "','" + firstname +
                                "','" + lastname + "','" + email + "','" +
                                password + "','" + age + "','"
                                + phone + "','" + address + "')";
        
       
        handler.createConnection();
        try{
            boolean executed = handler.executeAction(statement);
            if(executed) System.out.println("User Pushed succesfully");
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        finally{
            handler.closeStatement();
            handler.dropConnection();
        
        }
    }
        	
    /**
    * Authenticates a user using the username and password.
    * @param username The username to be authenticated.
    * @param password The password to be authenticated.
    * @return Whether the user exists in the system.
    */
    @Override
    public boolean authenticateUser(String username, String password){
        try{
            statement = "SELECT* FROM USERS WHERE USR_ID = '" + username +
                                                  "' AND USR_PASSWORD = '" + password + "'";
            handler.createConnection();
            results = handler.executeQuery(statement);
            return (results.next());
	}
	catch(SQLException error){
            System.out.println("An error occured in the database.");
            System.out.println(error.getMessage());
            return false;
	}
	finally{
            handler.closeStatement();
            handler.dropConnection();
        }
		
    }
    
    
    /**
     * Deletes a user record from the USERS table
     * @param username The username of the user
     * @return If the deletion was successful
     */
    @Override
    public boolean deleteUser(String username){
        boolean executed = false;
        statement = "DELETE FROM USERS WHERE USR_ID = '" +username+ "'";
        try{
            handler.createConnection();
            executed = handler.executeAction(statement);
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        finally{
            handler.closeStatement();
            handler.dropConnection();
        }
        return executed;
    }
    
    
    /**
     * Updates a user record in the USERS table
     * @param oldUser The old username of the user
     * @param user The user data object containing the new information to be added to the database
     * @return If the update was successful
     */
    @Override
    public boolean updateUser(String oldUser, User user){
        boolean executed = false;
        statement = "UPDATE USERS SET USR_ID = '" +user.getUsername()+ "', USR_FNAME = '" +user.getFirstname()+ "', USR_LNAME = '" 
                +user.getLastname()+ "', USR_EMAIL = '" 
                +user.getEmail()+ "', USR_PASSWORD = '" +user.getPassword()+ "', USR_AGE = '" +user.getAge()+ "', USR_PHONE = '" 
                +user.getPhone()+ "', USR_ADDRESS = '" +user.getAddress()+ "' WHERE USR_ID = '" +oldUser+ "'";
        
        try{
            handler.createConnection();
            executed = handler.executeAction(statement);
            
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        finally{
            handler.closeStatement();
            handler.dropConnection();
            
        }
        return executed;
    }
    
    
    
    /**
     * Gets a specific user's information
     * @param user Username of the requested user information.
     * @return A User data object with the requested information.
     */
    @Override
    public User getUser(String user){
        User currentUser = null;
        statement = "SELECT* FROM USERS WHERE USR_ID = '" + user + "'";
        String username = null;
        String firstname = null;
        String lastname = null;
        String email = null;
        String password = null;
        String age = null;
        String address = null;
        String phone = null;
        try{
            handler.createConnection();
            results = handler.executeQuery(statement);
            while(results.next()){
                username = results.getString(1);
                firstname = results.getString(2);
                lastname = results.getString(3);
                email = results.getString(4);
                password = results.getString(5);
                age = results.getString(6);
                phone = results.getString(7);
                address = results.getString(8);
                
            }
            
            currentUser = new User(username,firstname,lastname,email,age,password,phone,address);
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }finally{
            handler.closeStatement();
            handler.dropConnection();
            
        }
        return currentUser;
    }
    
    
    @Override
    public List<User> getAllUsers(){
        return null;
    }
}




































































































































