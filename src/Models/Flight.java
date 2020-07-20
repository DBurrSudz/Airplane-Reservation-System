
package Models;

import javafx.beans.property.SimpleStringProperty;

/**
 * Model to represent a Flight in the system.
 * @author Daniel Burrell
 */
public class Flight {
    private SimpleStringProperty flight_id;
    private SimpleStringProperty flight_name;
    private SimpleStringProperty flight_arrival;
    private SimpleStringProperty flight_origin;
    private SimpleStringProperty flight_gate;
    private SimpleStringProperty flight_departure;
    private SimpleStringProperty flight_destination;
    private SimpleStringProperty flight_connection;
    private SimpleStringProperty flight_seats;
    private SimpleStringProperty flight_delayed;
    private SimpleStringProperty flight_dport;
    private SimpleStringProperty flight_aport;
    
    
    /**
     * Public constructor.
     * @param flight_id The ID of the flight.
     * @param flight_name The name of the airline company.
     * @param flight_origin The origin location of the flight.
     * @param flight_destination The destination location of the flight.
     * @param flight_departure The expected time of departure for the flight.
     * @param flight_arrival The expected time of arrival for the flight.
     * @param flight_connection The number of connections the flight is expected to make.
     * @param flight_seats The number of seats that are available on the flight.
     * @param flight_delayed The status of the flight if it is delayed.
     * @param flight_gate The gate the flight is to depart from.
     * @param flight_dport The airport the flight is to depart from.
     * @param flight_aport The airport the flight is to arrive at.
     */
    public Flight(String flight_id, String flight_name,String flight_origin, String flight_destination, String flight_departure,String flight_arrival, String flight_connection, String flight_seats, String flight_delayed,String flight_gate, String flight_dport, String flight_aport){
        this.flight_id = new SimpleStringProperty(flight_id);
        this.flight_name = new SimpleStringProperty(flight_name);
        this.flight_arrival = new SimpleStringProperty(flight_arrival);
        this.flight_origin = new SimpleStringProperty(flight_origin);
        this.flight_gate = new SimpleStringProperty(flight_gate);
        this.flight_departure = new SimpleStringProperty(flight_departure);
        this.flight_destination = new SimpleStringProperty(flight_destination);
        this.flight_connection = new SimpleStringProperty(flight_connection);
        this.flight_seats = new SimpleStringProperty(flight_seats);
        this.flight_delayed = new SimpleStringProperty(flight_delayed);
        this.flight_dport = new SimpleStringProperty(flight_dport);
        this.flight_aport = new SimpleStringProperty(flight_aport);
    }
    
    
    /**
     * Gets the airport that the flight is departing from.
     * @return The airport code that the flight is departing from.
     */
    public String getFlight_dport() {
        return flight_dport.get();
    }
    
    
    /**
     * Sets the airport that the flight is departing from.
     * @param flight_dport The airport code that the flight is departing from.
     */
    public void setFlight_dport(String flight_dport) {
        this.flight_dport.set(flight_dport);
    }
    
    /**
     * Gets the airport that the flight is arriving at.
     * @return The airport code that the flight is arriving at.
     */
    public String getFlight_aport() {
        return flight_aport.get();
    }
    
    /**
     * Sets the airport that the flight is arriving at.
     * @param flight_aport The airport that the flight is arriving at.
     */
    public void setFlight_aport(String flight_aport) {
        this.flight_aport.set(flight_aport);
    }
    
    
    /**
     * Gets the ID of the flight.
     * @return The ID of the flight.
     */
    public String getFlight_id() {
        return flight_id.get();
    }
    
    /**
     * Sets the ID of the flight.
     * @param flight_id The ID of the flight.
     */
    public void setFlight_id(String flight_id) {
        this.flight_id.set(flight_id);
    }
    
    /**
     * Gets the airline company of the flight.
     * @return The airline company of the flight.
     */
    public String getFlight_name() {
        return flight_name.get();
    }
    
    
    /**
     * Sets the airline company of the flight.
     * @param flight_name The airline company of the flight.
     */
    public void setFlight_name(String flight_name) {
        this.flight_name.set(flight_name);
    }
    
    
    /**
     * Gets the expected arrival time of the flight.
     * @return The expected arrival time of the flight.
     */
    public String getFlight_arrival() {
        return flight_arrival.get();
    }
    
    /**
     * Sets the expected arrival time of the flight.
     * @param flight_arrival The expected arrival time of the flight.
     */
    public void setFlight_arrival(String flight_arrival) {
        this.flight_arrival.set(flight_arrival);
    }
    
    /**
     * Gets the origin location of the flight.
     * @return The origin location of the flight.
     */
    public String getFlight_origin() {
        return flight_origin.get();
    }
    
    /**
     * Sets the origin location of the flight.
     * @param flight_origin The origin location of the flight.
     */
    public void setFlight_origin(String flight_origin) {
        this.flight_origin.set(flight_origin);
    }
    
    
    /**
     * Gets the gate that the flight is expected to depart from.
     * @return The gate that the flight is expected to depart from.
     */
    public String getFlight_gate() {
        return flight_gate.get();
    }
    
    
    /**
     * Sets the gate that the flight is expected to depart from.
     * @param flight_gate The gate that the flight is expected to depart from.
     */
    public void setFlight_gate(String flight_gate) {
        this.flight_gate.set(flight_gate);
    }
    
    
    /**
     * Gets the expected departure time of the flight.
     * @return The expected departure time of the flight.
     */
    public String getFlight_departure() {
        return flight_departure.get();
    }

    /**
     * Sets the expected departure time of the flight.
     * @param flight_departure The expected departure time of the flight.
     */
    public void setFlight_departure(String flight_departure) {
        this.flight_departure.set(flight_departure);
    }

    public String getFlight_destination() {
        return flight_destination.get();
    }

    public void setFlight_destination(String flight_destination) {
        this.flight_destination.set(flight_destination);
    }

    public String getFlight_connection() {
        return flight_connection.get();
    }

    public void setFlight_connection(String flight_connection) {
        this.flight_connection.set(flight_connection);
    }

    public String getFlight_seats() {
        return flight_seats.get();
    }

    public void setFlight_seats(String flight_seats) {
        this.flight_seats.set(flight_seats);
    }

    public String getFlight_delayed() {
        return flight_delayed.get();
    }

    public void setFlight_delayed(String flight_delayed) {
        this.flight_delayed.set(flight_delayed);
    }
    
}

























































