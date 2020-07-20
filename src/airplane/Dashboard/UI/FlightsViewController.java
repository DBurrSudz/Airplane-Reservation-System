
package airplane.Dashboard.UI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.collections.ObservableList;
import DataAccessObjects.DAOFactory;
import DataAccessObjects.FlightDAOImp;
import Models.Flight;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Daniel Burrell
 */
public class FlightsViewController implements Initializable {
    
    @FXML
    private JFXButton next;
    @FXML
    private JFXButton search;
    @FXML
    private TextField origin;
    @FXML
    private TextField destination;
    @FXML
    private TextField airline;
    @FXML
    private TextField name;
    @FXML
    private TableView table;
    @FXML
    private TableColumn flight_id;
    @FXML
    private TableColumn flight_airline;
    @FXML
    private TableColumn flight_origin;
    @FXML
    private TableColumn flight_destination;
    @FXML
    private TableColumn flight_departure;
    @FXML
    private TableColumn flight_arrival;
    @FXML
    private TableColumn flight_connections;
    @FXML
    private TableColumn flight_seats;
    @FXML
    private TableColumn flight_delayed;
    @FXML
    private TableColumn flight_gate;
    @FXML
    private TableColumn flight_dport;
    @FXML
    private TableColumn flight_aport;
   
    private FlightDAOImp flightRecord;
    public static Flight chosenFlight = null;
    public static String chosenFlightID;
 
  
   
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        flightRecord = DAOFactory.getFlightDAO();
        flight_id = new TableColumn("Flight");
        flight_airline = new TableColumn("Airline");
        flight_origin = new TableColumn("Origin");
        flight_destination = new TableColumn("Destination");
        flight_departure = new TableColumn("Departure");
        flight_arrival = new TableColumn("Arrival");
        flight_connections = new TableColumn("Connections");
        flight_seats = new TableColumn("Seats");
        flight_delayed = new TableColumn("Delayed");
        flight_gate = new TableColumn("Gate");
        flight_dport = new TableColumn("Airport Of Departure");
        flight_aport = new TableColumn("Airport Of Arrival");
        table.getColumns().addAll(flight_id,flight_airline,flight_origin,flight_destination,flight_departure,flight_arrival,flight_connections,flight_seats,flight_delayed,flight_gate,flight_dport,flight_aport);
        final ObservableList<Flight> data = FXCollections.observableArrayList(flightRecord.getFlights());
                                                                        
      
             
        flight_id.setCellValueFactory(new PropertyValueFactory<Flight,String>("flight_id"));
        flight_airline.setCellValueFactory(new PropertyValueFactory<Flight,String>("flight_name"));
        flight_origin.setCellValueFactory(new PropertyValueFactory<Flight,String>("flight_origin"));
        flight_destination.setCellValueFactory(new PropertyValueFactory<Flight,String>("flight_destination"));
        flight_departure.setCellValueFactory(new PropertyValueFactory<Flight,String>("flight_departure"));
        flight_arrival.setCellValueFactory(new PropertyValueFactory<Flight,String>("flight_arrival"));
        flight_connections.setCellValueFactory(new PropertyValueFactory<Flight,String>("flight_connection"));
        flight_seats.setCellValueFactory(new PropertyValueFactory<Flight,String>("flight_seats"));
        flight_delayed.setCellValueFactory(new PropertyValueFactory<Flight,String>("flight_delayed"));
        flight_gate.setCellValueFactory(new PropertyValueFactory<Flight,String>("flight_gate"));
        flight_dport.setCellValueFactory(new PropertyValueFactory<Flight,String>("flight_dport"));
        flight_aport.setCellValueFactory(new PropertyValueFactory<Flight,String>("flight_aport"));
       
        
        FilteredList<Flight> filteredList = new FilteredList<>(data, e -> true);
        
        //Filters the observable list based on flight id
        name.textProperty().addListener((observable,oldValue,newValue) -> {
            filteredList.setPredicate(flight -> {
                if(newValue.isEmpty() || newValue == null){
                    return true;
                }   
                if(flight.getFlight_id().contains(newValue) || flight.getFlight_id().contains(newValue.toLowerCase()) || flight.getFlight_id().contains(newValue.toUpperCase())){
                    return true;                        
                }
                else{
                    return false;                                                               
                }                                       
            }); 
        });
        
        
        //Filters the observable list based on airline company
        airline.textProperty().addListener((observable,oldValue,newValue) -> {
            filteredList.setPredicate(flight -> {
                if(newValue.isEmpty() || newValue == null){
                    return true;
                }   
                if(flight.getFlight_name().contains(newValue) || flight.getFlight_name().contains(newValue.toLowerCase()) || flight.getFlight_name().contains(newValue.toUpperCase())){
                    return true;                        
                }
                else{
                    return false;                                                               
                }                                       
            }); 
        });
        
        
        //Filters the observable list based on destination
        destination.textProperty().addListener((observable,oldValue,newValue) -> {
            filteredList.setPredicate(flight -> {
                if(newValue.isEmpty() || newValue == null){
                    return true;
                }   
                if(flight.getFlight_destination().contains(newValue) || flight.getFlight_destination().contains(newValue.toLowerCase()) || flight.getFlight_destination().contains(newValue.toUpperCase())){
                    return true;                        
                }
                else{
                    return false;                                                               
                }                                       
            }); 
        });
        
        //Filters the observable list based on origin
        origin.textProperty().addListener((observable,oldValue,newValue) -> {
            filteredList.setPredicate(flight -> {
                if(newValue.isEmpty() || newValue == null){
                    return true;
                }   
                if(flight.getFlight_origin().contains(newValue) || flight.getFlight_origin().contains(newValue.toLowerCase()) || flight.getFlight_origin().contains(newValue.toUpperCase())){
                    return true;                        
                }
                else{
                    return false;                                                               
                }                                       
            }); 
        });
        
       
        
        table.setItems(filteredList); 
    }
    
    @FXML
    private void populateTable(){
        
    }
    
    /**
     * Brings the user to the flight reservation page
     */
    @FXML
    private void nextPage(){
        chosenFlight = (Flight)table.getSelectionModel().getSelectedItem();
        if(chosenFlight == null){  //User did not select a table row
            Alert error = new Alert(AlertType.ERROR);
            error.setTitle("Flight Selection");
            error.setContentText("Please Select A Flight Before Proceeding");
            error.setResizable(false);
            error.showAndWait();
        }
        else{   //User selected a table row
            chosenFlightID = chosenFlight.getFlight_id();
            DashboardController dashboard = DashboardController.getInstance();
            dashboard.createPage("FlightsViewSecondPage.fxml","Make Reservation");    
        }
            
    }
    
}


















































































































































































































