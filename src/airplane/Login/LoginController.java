/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airplane.Login;

import DataAccessObjects.DAOFactory;
import DataAccessObjects.UserDAOImp;
import Database.CreateDB;
import airplane.AirplaneReservationSystem;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import Utils.Session;


/**
 * LoginFXML Controller Class.
 *
 * @author Daniel Burrell
 */
public class LoginController implements Initializable {
    
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton login;
    @FXML
    private JFXButton newAccount;

    CreateDB handler;
    public static Stage window;
    private Parent registerPage;
    private Parent dashboardPage;   
    private Session session;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        registerPage = AirplaneReservationSystem.getRoot();
        dashboardPage = AirplaneReservationSystem.getRoot();
        window = AirplaneReservationSystem.getWindow();
        
        try{
            handler = CreateDB.getHandler();
            handler.createConnection();
            handler.printTable("USERS");
            handler.dropConnection();
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }   
    
    
    /**
     * Responds to the "Create New Account" button on the login page.
     * This function takes the user to the register page where they are allowed
     * to enter their credentials to sign up for the system.
     * @param event The event that occurs on the node.
     * @throws IOException 
     */
    @FXML
    public void newUserPage(ActionEvent event) throws IOException{
        registerPage = FXMLLoader.load(getClass().getResource("NewUser.fxml"));
        Scene newUserScene = new Scene(registerPage);
        window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(newUserScene);
        window.show();
        
    }
    
    @FXML
    public void login(ActionEvent event){
        String usr = username.getText();
        String pass = password.getText();
        window = (Stage)((Node)event.getSource()).getScene().getWindow();
        UserDAOImp userRecord = DAOFactory.getUserDAO();
        
        if (userRecord.authenticateUser(usr, pass)){	
            try{
                session = Session.createSession(usr);
                dashboardPage = FXMLLoader.load(getClass().getClassLoader().getResource("airplane/Dashboard/UI/Dashboard.fxml"));
                Scene dashboardScene = new Scene(dashboardPage);
                Stage dashboardStage = AirplaneReservationSystem.getWindow();
                dashboardStage.setResizable(false);
                dashboardStage.setScene(dashboardScene);
                dashboardStage.setTitle("QuickAir Dashboard");
                window.close(); 
                dashboardStage.show();
                   
            }
            catch(IOException error){
                error.printStackTrace();
            }
        
        }
            
        else{
            Alert loginAlert = new Alert(AlertType.ERROR);
            loginAlert.setResizable(false);
            loginAlert.setTitle("Login");
            loginAlert.setContentText("Invalid Login Credentials");
            loginAlert.showAndWait();
        }   
    }
}
















































































































































































































