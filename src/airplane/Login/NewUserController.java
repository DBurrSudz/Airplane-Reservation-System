
package airplane.Login;


import DataAccessObjects.AccountDAOImp;
import DataAccessObjects.DAOFactory;
import DataAccessObjects.UserDAOImp;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import Models.User;
import Models.Account;
import Utils.CheckValidity;
import java.util.Random;


/**
 * FXML Controller class
 *
 * @author Daniel Burrell
 */
public class NewUserController implements Initializable {
    @FXML
    private JFXTextField firstname;
    @FXML
    private JFXTextField lastname;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXPasswordField confirmPassword;
    @FXML
    private JFXTextField age;
    @FXML
    private TextArea address;
    @FXML 
    private JFXTextField phone;
    
    private UserDAOImp userRecord;
    private AccountDAOImp accountRecord;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    @FXML
    public void goBack(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene loginScene = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }
    
    @FXML
    public void addUser(ActionEvent event){
        if (CheckValidity.account(username.getText(),firstname.getText() ,lastname.getText(),email.getText(),password.getText(),confirmPassword.getText(),age.getText(),phone.getText(),address.getText())){
            userRecord = DAOFactory.getUserDAO();
            userRecord.addUser(new User(username.getText(),firstname.getText(),lastname.getText(),email.getText(),password.getText(),age.getText(),phone.getText(),address.getText()));
            this.createUserAccount(username.getText());
        }
        else System.out.println("Something is wrong with the submitted data.");
    }
    
    
    private void createUserAccount(String user){
        Random rand = new Random();
        accountRecord = DAOFactory.getAccountDAO();
        accountRecord.addAccount(new Account(String.valueOf(rand.nextInt(Integer.MAX_VALUE)),"0.0",user));
    }
}
















































































































































































