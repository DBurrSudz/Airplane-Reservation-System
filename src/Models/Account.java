
package Models;

/**
 *  Model to represent an Account in the System
 * @author Daniel Burrell
 */
public class Account {
    private String accNo;
    private String accBalance;
    private String accUser;
    
    /**
     * Public Constructor
     * @param accNo The account number
     * @param accBalance The account balance
     * @param accUser The account user
     */
    public Account(String accNo,String accBalance, String accUser){
        this.accNo = accNo;
        this.accBalance = accBalance;
        this.accUser = accUser;
    }
    
    /**
     * Gets the account number of an account
     * @return The account number
     */
    public String getAccNo() {
        return accNo;
    }
    
    
    /**
     * Sets the account number for an account
     * @param accNo The account number
     */
    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    
    /**
     * Gets the account balance of an account
     * @return The account balance of an account
     */
    public String getAccBalance() {
        return accBalance;
    }
    
    
    /**
     * Sets the account balance of an account
     * @param accBalance The account balance
     */
    public void setAccBalance(String accBalance) {
        this.accBalance = accBalance;
    }

    /**
     * Gets the account user of an account
     * @return The account user
     */
    public String getAccUser() {
        return accUser;
    }
    
    /**
     * Sets the account user of an account
     * @param accUser The account user
     */
    public void setAccUser(String accUser) {
        this.accUser = accUser;
    }
    
}






















