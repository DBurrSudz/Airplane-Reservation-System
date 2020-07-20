
package airplane.Dashboard.UI;

import DataAccessObjects.AccountDAOImp;
import DataAccessObjects.BookingDAOImp;
import DataAccessObjects.DAOFactory;
import DataAccessObjects.UserDAOImp;
import Models.User;
import Utils.Session;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author danie
 */
public class HomeViewController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private JFXButton removeButton;
    @FXML
    private Label contactLabel;
    @FXML
    private Label userNameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label addressLabel;
    
    private Session session;
    private UserDAOImp userRecord;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        session = Session.getCurrentSession();
        userRecord = DAOFactory.getUserDAO();
        this.loadInformation();
       
    }    
    
    private void loadInformation(){
        User currentUser = userRecord.getUser(session.getCurrentUser());
        nameLabel.setText(currentUser.getFirstname() +" " + currentUser.getLastname());
        usernameLabel.setText("Username: " + currentUser.getUsername());
        contactLabel.setText(currentUser.getPhone());
        userNameLabel.setText(currentUser.getUsername());
        emailLabel.setText(currentUser.getEmail());
        addressLabel.setText(currentUser.getAddress());
        
    }
    
    
    
    @FXML
    private void removeUserAccount(){
        ButtonType delete = new ButtonType("Yes, Remove My Information");
        ButtonType cancel = new ButtonType("Nevermind");
        Alert alert = new Alert(AlertType.WARNING,"You will be removing your profile, account information and all bookings you have made.",delete,cancel);
        alert.setTitle("Remove Account and Profile");
        alert.setResizable(false);
        alert.showAndWait();
        
        if(alert.getResult() == delete){
            AccountDAOImp accountRecord = DAOFactory.getAccountDAO();
            BookingDAOImp bookingRecord = DAOFactory.getBookingDAO();
            UserDAOImp userRecord = DAOFactory.getUserDAO();
            
            if(accountRecord.removeAccount(session.getCurrentUser()) && bookingRecord.removeBookingRecord(session.getCurrentUser()) && userRecord.deleteUser(session.getCurrentUser())){
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Account Removal");
                alert.setHeaderText("Removal was Successful!");
                alert.setContentText("You will be redirected to the Log In Page To Complete Changes");
                alert.setResizable(false);
                alert.showAndWait();
                
                DashboardController dashboard = DashboardController.getInstance();
                dashboard.signOut();
            }
        }
        
    }
    
        
    
}

































