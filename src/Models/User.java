
package Models;

import javafx.beans.property.SimpleStringProperty;

/**
 * Model to represent a User in the system.
 * @author Daniel Burrell
 */


public class User {
    private SimpleStringProperty username;
    private SimpleStringProperty firstname;
    private SimpleStringProperty lastname;
    private SimpleStringProperty email;
    private SimpleStringProperty age;
    private SimpleStringProperty password;
    private SimpleStringProperty phone;
    private SimpleStringProperty address;
    
    /**
     * Public construct for the User class.
     * @param username The username of the user.
     * @param firstname The first name of the user.
     * @param lastname The last name of the user.
     * @param email The email address of the user.
     * @param age The age of the user.
     * @param password The password of the user.
     * @param phone The phone number of the user.
     * @param address The home address of the user.
     */
    public User(String username, String firstname, String lastname, String email, String password, String age, String phone, String address) {
        this.username = new SimpleStringProperty(username);
        this.firstname = new SimpleStringProperty(firstname);
        this.lastname = new SimpleStringProperty(lastname);
        this.email = new SimpleStringProperty(email);
        this.age = new SimpleStringProperty(age);
        this.password = new SimpleStringProperty(password);
        this.phone = new SimpleStringProperty(phone);
        this.address = new SimpleStringProperty(address);
    }
    
    /**
     * Gets the username of the user.
     * @return The username of the user.
     */
    public String getUsername() {
        return username.get();
    }
    
    /**
     * Sets the username of the user.
     * @param username The username of the user.
     */
    public void setUsername(String username) {
        this.username.set(username);
    }
    
    /**
     * Gets the first name of the user.
     * @return The first name of the user.
     */
    public String getFirstname() {
        return firstname.get();
    }
    
    /**
     * Sets the first name of the user.
     * @param firstname The first name of the user.
     */
    public void setFirstname(String firstname) {
        this.firstname.set(firstname);
    }

    /**
     * Gets the last name of the user.
     * @return The last name of the user.
     */
    public String getLastname() {
        return lastname.get();
    }
    
    /**
     * Sets the last name of the user.
     * @param lastname The last name of the user.
     */
    public void setLastname(String lastname) {
        this.lastname.set(lastname);
    }

    /**
     * Gets the email address of the user.
     * @return The email address of the user.
     */
    public String getEmail() {
        return email.get();
    }
    
    
    /**
     * Sets the email address of the user.
     * @param email The email address of the user.
     */
    public void setEmail(String email) {
        this.email.set(email);
    }
    
    
    /**
     * Gets the age of the user.
     * @return The age of the user.
     */
    public String getAge() {
        return age.get();
    }

    /**
     * Sets the age of the user.
     * @param age The age of the user.
     */
    public void setAge(String age) {
        this.age.set(age);
    }

    
    /**
     * Gets the password of the user.
     * @return The password of the user.
     */
    public String getPassword() {
        return password.get();
    }

    
    /**
     * Sets the password of the user.
     * @param password The password of the user.
     */
    public void setPassword(String password) {
        this.password.set(password);
    }

    
    /**
     * Gets the phone number of the user.
     * @return The phone number of the user.
     */
    public String getPhone() {
        return phone.get();
    }
    
    
    /**
     * Sets the phone number of the user.
     * @param phone The phone number of the user.
     */
    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    
    /**
     * Gets the home address of the user.
     * @return The home address of the user.
     */
    public String getAddress() {
        return address.get();
    }
    
    /**
     * Sets the home address of the user.
     * @param address The home address of the user.
     */
    public void setAddress(String address) {
        this.address.set(address);
    }
    
}















































