
package airplane.Dashboard.UI;

import DataAccessObjects.AccountDAOImp;
import DataAccessObjects.DAOFactory;
import Models.Account;
import Utils.Session;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;

/**
 * FXML Controller class
 *
 * @author Daniel Burrell
 */
public class AccountViewController implements Initializable {
    @FXML Label accountIDLabel;
    @FXML Label accountBalanceLabel;
    @FXML Label accountUserLabel;
    @FXML JFXButton bill;
    private Session session;
    private AccountDAOImp accountRecord;
    private Account currentAccount;
    private Alert alert;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        session = Session.getCurrentSession();
        accountRecord = DAOFactory.getAccountDAO();
        currentAccount = accountRecord.getAccount(session.getCurrentUser());
        accountIDLabel.setText(currentAccount.getAccNo());
        accountBalanceLabel.setText("$" + currentAccount.getAccBalance());
        accountUserLabel.setText(session.getCurrentUser());
        
        alert = new Alert(AlertType.ERROR);
        alert.setResizable(false);
        
        
    }
    
    
    /**
     * Bills a persons account
     */
    @FXML
    private void billAccount(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Bill Account");
        dialog.setHeaderText("Please Enter The Amount You Wish To Bill");
        dialog.setResizable(false);
        dialog.setContentText("Amount: ");
        dialog.showAndWait();
        if(dialog.getResult() != null){
            if(dialog.getResult().isEmpty()){  //User did not input anything
            alert.alertTypeProperty().set(AlertType.INFORMATION);
            alert.setHeaderText("Empty Field");
            alert.setTitle("Billing");
            alert.setContentText("Please enter an amount.");
            alert.showAndWait();
        }
        else{
            try{
                
                if(Double.parseDouble(dialog.getResult()) < 0){ //User inputted a negative number
                    alert.alertTypeProperty().set(AlertType.ERROR);
                    alert.setHeaderText("Negative Value Submitted");
                    alert.setTitle("Billing Error");
                    alert.setContentText("Please enter a valid amount.");
                    alert.showAndWait();
                }
                else{ //User inputted a valid number
                    accountRecord.updateAccountBalance(session.getCurrentUser(), Double.parseDouble(dialog.getResult()) + Double.parseDouble(currentAccount.getAccBalance()));
                    currentAccount = accountRecord.getAccount(session.getCurrentUser());
                    accountBalanceLabel.setText("$" + String.valueOf(currentAccount.getAccBalance()));
                    alert.alertTypeProperty().set(AlertType.CONFIRMATION);
                    alert.setHeaderText("Thank You");
                    alert.setTitle("Billing Success");
                    alert.setContentText("You Have SuccessFully Billed Your Account");
                    alert.showAndWait();  
                }   
            }
            catch(NumberFormatException ex){ //User inputted something that cannot be converted to a number
                alert.setAlertType(AlertType.ERROR);
                alert.setTitle("Input Error");
                alert.setHeaderText("Invalid Input Type");
                alert.setContentText("Please Enter A Valid Amount");
                alert.showAndWait();
            }
            
        }
            
        }
        
        
    }

    
    
    
}













































