
package DataAccessObjects;

import Database.CreateDB;
import Interfaces.AccountDAO;
import Models.Account;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implements the Account Data Object Interface
 * @author Daniel Burrell
 */
public class AccountDAOImp implements AccountDAO{
    private CreateDB handler;
    private ResultSet results;
    private String statement;
    
    /**
     * Public Constructor
     */
    public AccountDAOImp(){
        handler = CreateDB.getHandler();
        statement = " ";
    }
    
    
    /**
     * Adds an account to the ACCOUNT table
     * @param account The account data object with the information to add
     */
    @Override
    public void addAccount(Account account){
        statement = "INSERT INTO ACCOUNT VALUES ('" +account.getAccNo()+ "','" +account.getAccBalance()+ "','" +account.getAccUser()+ "')";
        try{
            handler.createConnection();
            boolean executed = handler.executeAction(statement);
            if(executed) System.out.println("Account Created SuccessFully");
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        finally{
            handler.closeStatement();
            handler.dropConnection();
        }

    }
    
    
    /**
     * Gets an account data object
     * @param user The username of the user
     * @return The account data object
     */
    @Override
    public Account getAccount(String user){
        Account account = null;
        statement = "SELECT* FROM ACCOUNT WHERE USR_ID = '" +user+ "'";
        try{
            handler.createConnection();
            results = handler.executeQuery(statement);
            while(results.next()){
                account = new Account(results.getString(1),results.getString(2),results.getString(3));
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        finally{
            handler.closeStatement();
            handler.dropConnection();
        }
        return account;
    }
    
    
    
    /**
     * Updates the account balance for a specific user
     * @param user The username of the user
     * @param value The new value
     */
    @Override
    public void updateAccountBalance(String user, double value){
        statement = "UPDATE ACCOUNT SET ACC_BLNCE = '" +String.valueOf(value)+ "' WHERE USR_ID = '" +user+ "'";
         try{
            handler.createConnection();
            boolean executed = handler.executeAction(statement);
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        finally{
            handler.closeStatement();
            handler.dropConnection();
        }
    }
    
    /**
     * Updates a username in the ACCOUNT table
     * @param oldUser The old username
     * @param newUser The new username
     */
    @Override
    public void updateUserName(String oldUser, String newUser){
        statement = "UPDATE ACCOUNT SET USR_ID = '" +newUser+ "' WHERE USR_ID = '" +oldUser+ "'";
         try{
            handler.createConnection();
            boolean executed = handler.executeAction(statement);
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        finally{
            handler.closeStatement();
            handler.dropConnection();
        }     
    }
    
    
    /**
     * Removes a record from the ACCOUNT table
     * @param username The username of the user
     * @return If the deletion was successful
     */
    @Override
    public boolean removeAccount(String username){
        boolean executed = false;
        statement = "DELETE FROM ACCOUNT WHERE USR_ID = '" +username+ "'";
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
    
    
}






































