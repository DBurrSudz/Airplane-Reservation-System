
package Interfaces;

import Models.Booking;
import java.util.ArrayList;

/**
 * Public Interface for a Data Access Object for a Booking
 * @author Daniel Burrell
 */
public interface BookingDAO {
    void addBooking(Booking booking);
    ArrayList<Booking> getBookings(String user);
    boolean removeBooking(String bookingID);
    boolean removeBookingRecord(String username);
    void updateUserName(String oldUser, String newUser);
    
}









