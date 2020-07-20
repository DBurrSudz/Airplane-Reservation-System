
package airplane.Dashboard.UI;

import DataAccessObjects.BookingDAOImp;
import DataAccessObjects.DAOFactory;
import Models.Booking;
import Utils.Session;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class BookingViewController implements Initializable {
    
    @FXML TableView table;
    @FXML JFXButton remove;
    @FXML
    private TableColumn booking_id;
    @FXML
    private TableColumn booking_origin;
    @FXML
    private TableColumn booking_destination;
    @FXML
    private TableColumn booking_departure;
    @FXML
    private TableColumn booking_arrival;
    @FXML
    private TableColumn booking_connection;
    @FXML
    private TableColumn booking_carryon;
    @FXML
    private TableColumn booking_seats;
    @FXML
    private TableColumn booking_class;
    @FXML
    private TableColumn booking_gate;
    @FXML
    private TableColumn booking_user;
    @FXML
    private TableColumn booking_flight;
    private ObservableList<Booking> data;
    private BookingDAOImp bookingRecord;
    private Session session;
    private Booking chosenBooking = null;
    private Alert message;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        session = Session.getCurrentSession();
        bookingRecord = DAOFactory.getBookingDAO();
        booking_id = new TableColumn("Booking ID");
        booking_origin = new TableColumn("Origin");
        booking_destination = new TableColumn("Destination");
        booking_departure = new TableColumn("Departure");
        booking_arrival = new TableColumn("Arrival");
        booking_connection = new TableColumn("Connections");
        booking_carryon = new TableColumn("Carry On");
        booking_seats = new TableColumn("Seats");
        booking_class = new TableColumn("Class");
        booking_gate = new TableColumn("Gate");
        booking_user = new TableColumn("User");
        booking_flight = new TableColumn("Flight ID");
        table.getColumns().addAll(booking_id,booking_origin,booking_destination,booking_departure,booking_arrival,booking_connection,booking_carryon,booking_seats,booking_class,booking_gate,booking_user,booking_flight);
        data = FXCollections.observableArrayList(bookingRecord.getBookings(session.getCurrentUser()));
        
        
        booking_id.setCellValueFactory(new PropertyValueFactory<Booking,String>("bking_id"));
        booking_origin.setCellValueFactory(new PropertyValueFactory<Booking,String>("bking_origin"));
        booking_destination.setCellValueFactory(new PropertyValueFactory<Booking,String>("bking_destination"));
        booking_departure.setCellValueFactory(new PropertyValueFactory<Booking,String>("bking_departure"));
        booking_arrival.setCellValueFactory(new PropertyValueFactory<Booking,String>("bking_arrival"));
        booking_connection.setCellValueFactory(new PropertyValueFactory<Booking,String>("bking_connection"));
        booking_carryon.setCellValueFactory(new PropertyValueFactory<Booking,String>("bking_carryon"));
        booking_seats.setCellValueFactory(new PropertyValueFactory<Booking,String>("bking_seat"));
        booking_class.setCellValueFactory(new PropertyValueFactory<Booking,String>("bking_class"));
        booking_gate.setCellValueFactory(new PropertyValueFactory<Booking,String>("bking_gate"));
        booking_user.setCellValueFactory(new PropertyValueFactory<Booking,String>("bking_user"));
        booking_flight.setCellValueFactory(new PropertyValueFactory<Booking,String>("bking_flight"));
        
        table.setItems(data);
        
        message = new Alert(AlertType.ERROR);
        message.setResizable(false);   
    }
    
    
    /**
     * Removes a selected booking from the table view and by extension the database
     */
    @FXML
    private void removeBooking(){
        chosenBooking = (Booking)table.getSelectionModel().getSelectedItem();
        if(chosenBooking == null){ //User did not choose a table row
            message.alertTypeProperty().set(AlertType.ERROR);
            message.setTitle("Remove Booking");
            message.setContentText("Please Select A Booking To Remove It");
            message.showAndWait();
        }
        else{ //User selected a table row
            if(bookingRecord.removeBooking(chosenBooking.getBking_id())){  //Booking was successfully removed from the database
                message.alertTypeProperty().set(AlertType.CONFIRMATION);
                message.setTitle("Booking Removal");
                message.setContentText("The Booking was Removed Successfully");
                message.showAndWait();
                table.getItems().remove(chosenBooking);
                table.refresh();
            }
            else{ //There was some error from the database while removing the booking
                message.alertTypeProperty().set(AlertType.ERROR);
                message.setTitle("Error");
                message.setContentText("An error occurred while removing this booking.");
                message.showAndWait();
            }
        }
    }
    
}













































