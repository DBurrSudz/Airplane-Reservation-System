
package Interfaces;


import Models.User;
import java.util.List;

/**
 * Public interface for a Data Access Object for a User.
 * @author Daniel Burrell
 */
public interface UserDAO {
    void addUser(User user);
    boolean deleteUser(String usernmae);
    boolean updateUser(String oldUser, User user);
    boolean authenticateUser(String username, String password);
    List<User> getAllUsers();
    User getUser(String user);
}




















