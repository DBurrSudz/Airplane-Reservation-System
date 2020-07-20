
package airplane.Dashboard.UI;

import DataAccessObjects.AccountDAOImp;
import DataAccessObjects.BookingDAOImp;
import DataAccessObjects.DAOFactory;
import Models.Booking;
import Models.Flight;
import Utils.Session;
import airplane.AirplaneReservationSystem;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import java.util.Random;

/**
 * FXML Controller class
 *
 * @author Daniel Burrell
 */
public class FlightsViewSecondPageController implements Initializable {
    
    @FXML CheckBox business;
    @FXML CheckBox economy;
    @FXML TextField seats;
    @FXML RadioButton luggage;
    @FXML MaterialDesignIconView A1;
    @FXML MaterialDesignIconView A2;
    @FXML MaterialDesignIconView A3;
    @FXML MaterialDesignIconView B1;
    @FXML MaterialDesignIconView B2;
    @FXML MaterialDesignIconView B3;
    @FXML MaterialDesignIconView C1;
    @FXML MaterialDesignIconView C2;
    @FXML MaterialDesignIconView C3;
    @FXML MaterialDesignIconView D1;
    @FXML MaterialDesignIconView D2;
    @FXML MaterialDesignIconView D3;
    @FXML MaterialDesignIconView E1;
    @FXML MaterialDesignIconView E2;
    @FXML MaterialDesignIconView E3;
    @FXML MaterialDesignIconView F1;
    @FXML MaterialDesignIconView F2;
    @FXML MaterialDesignIconView F3;
    @FXML MaterialDesignIconView G1;
    @FXML MaterialDesignIconView G2;
    @FXML MaterialDesignIconView G3;
    @FXML MaterialDesignIconView H1;
    @FXML MaterialDesignIconView H2;
    @FXML MaterialDesignIconView H3;
    @FXML JFXButton bookFlight;
    @FXML FontAwesomeIconView arrow;
    @FXML Pane businessPane;
    @FXML Pane economyPane;
    private int requiredSeats;
    private static String currentFlight;
    private Flight chosenFlight;
    private ArrayList<String> unavailableSeats;
    private ArrayList<String> selectedSeats;
    private Alert selection;
    private int counter;
    private double seatCost;
    private Session session;
    private String classChosen;
    private AccountDAOImp accountRecord;
    
   
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Gets the ID of the flight selected by the user from the previous page
        currentFlight = FlightsViewController.chosenFlightID;
        //Gets the flight object selected by the user from the previous page
        chosenFlight = FlightsViewController.chosenFlight;
        //Using the selected flight ID to access the ArrayList that holds the list of its unavailable seats from the hashmap
        unavailableSeats = (ArrayList<String>)AirplaneReservationSystem.getUnavailableSeats().get(currentFlight);
        
        
        if(!unavailableSeats.isEmpty()){ //If there are unavailable seats
            //Iterate through all the nodes (seats) in business and economy. Note that the end value used for the loop is possible since both business and economy has the same number of nodes
            for(int i = 0; i < businessPane.getChildren().size(); i++){  
                if(unavailableSeats.contains(businessPane.getChildren().get(i).getId())){ //If the specific node's ID is in the unavailable seats list
                    businessPane.getChildren().get(i).setStyle("-fx-fill: #949494;"); //Change its color
                }
                if(unavailableSeats.contains(economyPane.getChildren().get(i).getId())){
                    economyPane.getChildren().get(i).setStyle("-fx-fill: #949494;");
                }
            }     
        }
        selectedSeats = new ArrayList<>(); //Creates a list that will hold the seats a user selects during the reservation processs
        selection = new Alert(AlertType.ERROR);
        selection.setResizable(false);
        accountRecord = DAOFactory.getAccountDAO();
        counter = 0; //Counter to keep track the number of seats the user has selected
        session = Session.getCurrentSession();
    }
    
    
    /**
     * Deals with the seat selection process ad keeping track of what seats cannot be selected and what seats have
     * @param e The event carried out by click each seat
     */
    @FXML
    private void selectSeat(MouseEvent e){
        if(seats.getText().isEmpty() || (!business.isSelected()) && (!economy.isSelected())){ //User did not select the required things
            selection.setTitle("Field Errors");
            selection.setContentText("Please enter all the required information.");
            selection.showAndWait();
        }
        else{ //The user selected the required things
            try{
             
                requiredSeats = Integer.parseInt(seats.getText().trim()); //Gets the amount of seats required by the user
                if(requiredSeats < 0) throw new NumberFormatException();
                
                if(business.isSelected() && (!economy.isSelected())){ //If the user selects business and not economy
                    classChosen = "Business";
                    seatCost = requiredSeats * 243.00;
                    if(((Node)e.getSource()).getParent() == businessPane){ //Verifies that the user is selecting a seat from business
                        if(counter < requiredSeats){
                            //If the seat is black, turn it red and add its ID to the selected seats arraylist. Increment the counter
                            if(((Node)e.getSource()).getStyle().equals("-fx-fill: #000000;")){ 
                                ((Node)e.getSource()).setStyle("-fx-fill: #ff0000;");
                                selectedSeats.add(((Node)e.getSource()).getId());
                                counter++;

                            }
                            //If the seat is red, turn it black and remove its ID from the selected seats arraylist. Decrement the counter
                            else if(((Node)e.getSource()).getStyle().equals("-fx-fill: #ff0000;")){
                                ((Node)e.getSource()).setStyle("-fx-fill: #000000;");
                                selectedSeats.remove(((Node)e.getSource()).getId());
                                counter--;     
                            }
                            //User pressed a seat that was neither red or black. This would mean a grey seat that signifies that it is reserved already
                            else{
                                selection.setTitle("Reservation Error");
                                selection.setContentText("This seat has already been reserved");
                                selection.showAndWait();
                            }
                        }
                        //If the user has selected the required amount of seats, but wishes to unselect a selected one. Remove it from the selected seats list and decrement the counter
                        else if(counter == requiredSeats && ((Node)e.getSource()).getStyle().equals("-fx-fill: #ff0000;")){
                            ((Node)e.getSource()).setStyle("-fx-fill: #000000;");
                            selectedSeats.remove(((Node)e.getSource()).getId());
                            counter--;
                        }

                        //The user has selected the required amount of seats and is attempting to select another
                        else{
                            selection.setTitle("Reservation Error.");
                            selection.setContentText("Unable to reserve anymore seats.");
                            selection.showAndWait();
                        }
                    }
                    else{ //The user is selecting a seat outside of business class
                        selection.setTitle("Invalid Seat");
                        selection.setContentText("Only a seat from Business Class can be chosen.");
                        selection.showAndWait();  
                    }
                }
                else if(economy.isSelected() && (!business.isSelected())){
                    seatCost = requiredSeats * 150.00;
                    classChosen = "Economy";
                    if(((Node)e.getSource()).getParent() == economyPane){
                        if(counter < requiredSeats){
                            if(((Node)e.getSource()).getStyle().equals("-fx-fill: #000000;")){
                                ((Node)e.getSource()).setStyle("-fx-fill: #ff0000;");
                                selectedSeats.add(((Node)e.getSource()).getId());
                                counter++;
                            }
                            else if(((Node)e.getSource()).getStyle().equals("-fx-fill: #ff0000;")){
                                ((Node)e.getSource()).setStyle("-fx-fill: #000000;");
                                selectedSeats.remove(((Node)e.getSource()).getId());
                                counter--;
                            }
                            else{
                                selection.setTitle("Reservation Error");
                                selection.setContentText("This seat has already been reserved.");
                                selection.showAndWait();     
                            }
                        }
                        else if(counter == requiredSeats && ((Node)e.getSource()).getStyle().equals("-fx-fill: #ff0000;")){
                            ((Node)e.getSource()).setStyle("-fx-fill: #000000;");
                            selectedSeats.remove(((Node)e.getSource()).getId());
                            counter--;
                        }
                        else{
                            selection.setTitle("Reservation Error.");
                            selection.setContentText("Unable to reserve anymore seats.");
                            selection.showAndWait(); 
                        }

                    }
                    else{
                        selection.setTitle("Invalid Seat");
                        selection.setContentText("Only a seat from Economy Class can be chosen.");
                        selection.showAndWait(); 
                    }
                }
                else if(business.isSelected() && economy.isSelected()){ //User selected both business and economy
                    selection.setTitle("Invalid Selection");
                    selection.setContentText("You are only allowed one type of class.");
                    selection.showAndWait();
                }
            }catch(NumberFormatException ex){
                Alert error = new Alert(AlertType.ERROR);
                error.setTitle("Input Error");
                error.setContentText("Please Input a Valid Seat Count");
            }
        }   
    }   
    
    
    /**
     * 
     */
    @FXML
    private void bookFlight(){
        double totalCost = 0.00;
        String carryon = "";
        
        if(luggage.isSelected()){ //User selected Carry On
            totalCost = seatCost + 100.00;
            carryon = "Yes";
        }
        else{
            totalCost = seatCost;
            carryon = "No";
        }
        
        selection.alertTypeProperty().setValue(AlertType.INFORMATION);
        selection.setTitle("Reservation Made");
        selection.setContentText("Your seats have been reserved. The final cost is $" + Double.toString(totalCost));
        selection.showAndWait();
        
        if(Double.parseDouble(accountRecord.getAccount(session.getCurrentUser()).getAccBalance()) < totalCost){ //User does not have enough money in their account
            selection.alertTypeProperty().setValue(AlertType.WARNING);
            selection.setTitle("Reservation Error");
            selection.setContentText("Insufficient Funds in you account to make this booking. Please bill your account.");
            selection.showAndWait();
        }
        else{
            unavailableSeats.addAll(selectedSeats); //Adds the seats the user selected to the list of unavailable seats for the specific flight
            accountRecord.updateAccountBalance(session.getCurrentUser(), Double.parseDouble(accountRecord.getAccount(session.getCurrentUser()).getAccBalance()) - totalCost);
            selection.alertTypeProperty().set(AlertType.CONFIRMATION);
            selection.setContentText("Booking was Successful.");
            selection.showAndWait();
            Random rand = new Random();
            String bookingID = session.getCurrentUser() + currentFlight + String.valueOf(rand.nextInt(Integer.MAX_VALUE)); //Generates an ID for the booking.
        
            BookingDAOImp bookingRecord = DAOFactory.getBookingDAO();
            bookingRecord.addBooking(new Booking(bookingID,chosenFlight.getFlight_origin(),chosenFlight.getFlight_destination(),chosenFlight.getFlight_departure(),chosenFlight.getFlight_arrival(),chosenFlight.getFlight_connection(),carryon,selectedSeats.toString(),classChosen,chosenFlight.getFlight_gate(),session.getCurrentUser(),currentFlight));
        
            DashboardController dashboard = DashboardController.getInstance();
            dashboard.createPage(DashboardPages.FLIGHT_PAGE, "Flights");   
        }
        
        
    }
    
    /**
     * Goes back to the Flights Page
     */
    @FXML
    private void back(){
        DashboardController dashboard = DashboardController.getInstance();
        dashboard.createPage(DashboardPages.FLIGHT_PAGE,"Flights");
    }
    
}























































































































































































































































































