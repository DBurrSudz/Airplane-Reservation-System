
package Interfaces;

import Models.Flight;
import java.util.ArrayList;

/**
 * Public interface for a Data Access Object for a Flight.
 * @author Daniel Burrell
 */
public interface FlightDAO {
    //void addFlight(Flight flight);
    ArrayList<Flight> getFlights();
}











