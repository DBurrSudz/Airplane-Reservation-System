
package airplane.Dashboard.UI;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import Utils.Session;
import airplane.AirplaneReservationSystem;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Daniel Burrell
 */
public class DashboardController implements Initializable {

    @FXML
    private JFXButton homeButton;
    @FXML
    private JFXButton flightButton;
    @FXML
    private JFXButton accountButton;
    @FXML
    private JFXButton bookButton;
    @FXML
    private Label headingLabel;
    @FXML
    private JFXButton logoutButton;
    @FXML
    private Pane displayArea;
    private static AnchorPane root;
    private Session session;
    private static DashboardController dashboard;

    
    @Override
    /**
    * @param url
    * @param rb
    */
    public void initialize(URL url, ResourceBundle rb) {
        this.createPage(DashboardPages.HOME_PAGE,"Home");
        dashboard = this;
    }   
    
    
    /**
     * Calls the function to load each page
     * @param event The event carried out on each button
     */
    @FXML
    private void loadPage(ActionEvent event){
        if(event.getSource() == homeButton) this.createPage(DashboardPages.HOME_PAGE,"Home");
        if(event.getSource() == flightButton) this.createPage(DashboardPages.FLIGHT_PAGE,"Flights");     
        if(event.getSource() == accountButton) this.createPage(DashboardPages.ACCOUNT_PAGE,"Account Information");
        if(event.getSource() == bookButton) this.createPage(DashboardPages.BOOKING_PAGE,"Bookings");
    }  
    
    
    /**
     * Signs the user out of the system and clears the session object
     * @param event Event performed on the button
     */
    @FXML
    public void signOut(){
        session = Session.getCurrentSession();
	session.clearSession();
        Parent loginPage = AirplaneReservationSystem.getRoot();
        try{
            loginPage = FXMLLoader.load(getClass().getClassLoader().getResource("airplane/Login/Login.fxml"));
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        
        Stage newStage = AirplaneReservationSystem.getWindow();
        Scene newScene = new Scene(loginPage);
        newStage.setScene(newScene);
    }
    
    
    /**
     * Fixes the newly loaded page into the display area
     * @param page The created page
     */
    private void setNode(Node page){
        displayArea.getChildren().clear();
        displayArea.getChildren().add(page);
        page.setLayoutX(0);
        page.setLayoutY(0);
    }
    
    /**
     * Loads the FXML file
     * @param location The location of the file
     * @param heading The new heading to display
     */
    public void createPage(String location, String heading){
        try{
            root = FXMLLoader.load(getClass().getResource(location));
            headingLabel.setText(heading);
            this.setNode(root);   
        }
        catch(IOException error){
            error.printStackTrace();
        }
        
    }
    
    /**
     * Gets the dashboard instance
     * @return The dashboard instance
     */
    public static DashboardController getInstance(){
        return dashboard;
    }

}

































































































































































