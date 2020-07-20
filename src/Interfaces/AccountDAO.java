package Interfaces;

import Models.Account;

/**
 * Public Interface for a Data Access Object for an Account
 * @author Daniel Burrell
 */
public interface AccountDAO {
    void addAccount(Account account);
    Account getAccount(String user);
    void updateAccountBalance(String user, double value);
    void updateUserName(String oldUser, String newUser);
    boolean removeAccount(String username);
}












