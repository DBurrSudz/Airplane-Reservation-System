
package Models;

import javafx.beans.property.SimpleStringProperty;

/**
 * Model to represent a Booking in the system.
 * @author Daniel Burrell
 */
public class Booking {
    private SimpleStringProperty bking_id;
    private SimpleStringProperty bking_origin;
    private SimpleStringProperty bking_destination;
    private SimpleStringProperty bking_departure;
    private SimpleStringProperty bking_arrival;
    private SimpleStringProperty bking_connection;
    private SimpleStringProperty bking_carryon;
    private SimpleStringProperty bking_seat;
    private SimpleStringProperty bking_class;
    private SimpleStringProperty bking_gate;
    private SimpleStringProperty bking_user;
    private SimpleStringProperty bking_flight;
    
    
    
    /**
     * Public constructor for Booking model.
     * @param bking_id The ID of the booking ticket.
     * @param bking_origin The origin of the flight.
     * @param bking_destination The destination of the flight.
     * @param bking_departure The expected time of departure.
     * @param bking_arrival The expected time of arrival.
     * @param bking_connection The number of connections for the flight.
     * @param bking_carryon Whether the passenger has carry on items.
     * @param bking_seat The seat(s) that the passenger(s) was/were assigned.
     * @param bking_class The class that the passenger(s) was/were assigned.
     * @param bking_gate The gate of the departure for the flight.
     * @param bking_user The username of the current user.
     * @param bking_flight The flight ID that was booked.
     */
    public Booking(String bking_id, String bking_origin, String bking_destination, String bking_departure, String bking_arrival, String bking_connection, String bking_carryon, String bking_seat, String bking_class, String bking_gate, String bking_user, String bking_flight){
        this.bking_id = new SimpleStringProperty(bking_id);
        this.bking_origin = new SimpleStringProperty(bking_origin);
        this.bking_destination = new SimpleStringProperty(bking_destination);
        this.bking_departure = new SimpleStringProperty(bking_departure);
        this.bking_arrival = new SimpleStringProperty(bking_arrival);
        this.bking_connection = new SimpleStringProperty(bking_connection);
        this.bking_carryon = new SimpleStringProperty(bking_carryon);
        this.bking_seat = new SimpleStringProperty(bking_seat);
        this.bking_class = new SimpleStringProperty(bking_class);
        this.bking_gate = new SimpleStringProperty(bking_gate);
        this.bking_user = new SimpleStringProperty(bking_user);
        this.bking_flight = new SimpleStringProperty(bking_flight);
     
    }
    
    
    
    public String getBking_id() {
        return bking_id.get();
    }

    public void setBking_id(String bking_id) {
        this.bking_id.set(bking_id);
    }

    public String getBking_origin() {
        return bking_origin.get();
    }

    public void setBking_origin(String bking_origin) {
        this.bking_origin.set(bking_origin);
    }

    public String getBking_destination() {
        return bking_destination.get();
    }

    public void setBking_destination(String bking_destination) {
        this.bking_destination.set(bking_destination);
    }

    public String getBking_departure() {
        return bking_departure.get();
    }

    public void setBking_departure(String bking_departure) {
        this.bking_departure.set(bking_departure);
    }

    public String getBking_arrival() {
        return bking_arrival.get();
    }

    public void setBking_arrival(String bking_arrival) {
        this.bking_arrival.set(bking_arrival);
    }

    public String getBking_connection() {
        return bking_connection.get();
    }

    public void setBking_connection(String bking_connection) {
        this.bking_connection.set(bking_connection);
    }

    public String getBking_carryon() {
        return bking_carryon.get();
    }

    public void setBking_carryon(String bking_carryon) {
        this.bking_carryon.set(bking_carryon);
    }

    public String getBking_seat() {
        return bking_seat.get();
    }

    public void setBking_seat(String bking_seat) {
        this.bking_seat.set(bking_seat);
    }

    public String getBking_class() {
        return bking_class.get();
    }

    public void setBking_class(String bking_class) {
        this.bking_class.set(bking_class);
    }

    public String getBking_gate() {
        return bking_gate.get();
    }

    public void setBking_gate(String bking_gate) {
        this.bking_gate.set(bking_gate);
    }

    public String getBking_user() {
        return bking_user.get();
    }

    public void setBking_user(String bking_user) {
        this.bking_user.set(bking_user);
    }

    public String getBking_flight() {
        return bking_flight.get();
    }

    public void setBking_flight(String bking_flight) {
        this.bking_flight.set(bking_flight);
    }
    
    
}
































