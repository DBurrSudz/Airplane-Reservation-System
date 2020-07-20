
package DataAccessObjects;

import Database.CreateDB;
import Interfaces.BookingDAO;
import Models.Booking;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Implements the Booking Data Object Interface
 * @author Daniel Burrell
 */
public class BookingDAOImp implements BookingDAO {
    private final CreateDB handler;
    private String statement;
    private ResultSet results;
    
    
    /**
     * Public Constructor
     */
    public BookingDAOImp(){
        handler = CreateDB.getHandler();
        statement = " ";
    }
    
    
    /**
     * Adds a booking to the database
     * @param booking The booking data object with the values
     */
    @Override
    public void addBooking(Booking booking){
        statement = "INSERT INTO BOOKING VALUES ('" +booking.getBking_id()+"','" +booking.getBking_origin()+"','" 
                +booking.getBking_destination()+"','" 
                +booking.getBking_departure()+"','"+booking.getBking_arrival()+"','" 
                +booking.getBking_connection()+"','" +booking.getBking_carryon()+"','" 
                +booking.getBking_seat()+"','" +booking.getBking_class()+"','" +booking.getBking_gate()+"','" 
                +booking.getBking_user()+"','" +booking.getBking_flight()+"')";
        
        handler.createConnection();
        try{
            boolean executed = handler.executeAction(statement);
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        finally{
            handler.closeStatement();
            handler.dropConnection();
        }
    }
    
    
    /**
     * Gets the bookings for a specific user from the database.
     * @param user The username of the user.
     * @return List of bookings for the specified user
     */
    @Override
    public ArrayList<Booking> getBookings(String user){
        ArrayList<Booking> allBookings = new ArrayList<>();
        try{
            statement = "SELECT* FROM BOOKING WHERE USR_ID = '" +user+ "'";
            handler.createConnection();
            results = handler.executeQuery(statement);
            while(results.next()){
                allBookings.add(new Booking(results.getString(1),results.getString(2),results.getString(3),results.getString(4),results.getString(5),results.getString(6),results.getString(7),results.getString(8),results.getString(9),results.getString(10),results.getString(11),results.getString(12)));
            }    
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        finally{
            handler.closeStatement();
            handler.dropConnection();
        }
        return allBookings;
    }
    
    
    /**
     * Removes a specific booking from the database
     * @param bookingID The ID of the booking to remove
     * @return If the removal was successful.
     */
    @Override
    public boolean removeBooking(String bookingID){
        boolean executed = false;
        statement = "DELETE FROM BOOKING WHERE BK_ID = '" +bookingID+ "'";
        try{
            handler.createConnection();
            executed = handler.executeAction(statement);
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        finally{
            handler.closeStatement();
            handler.dropConnection();
        }
        return executed;
    }
    
    
    /**
     * Updates a username in the BOOKING table
     * @param oldUser The old username
     * @param newUser  The new username
     */
    @Override
    public void updateUserName(String oldUser, String newUser){
        statement = "UPDATE BOOKING SET USR_ID = '" +newUser+ "' WHERE USR_ID = '" +oldUser+ "'";
         try{
            handler.createConnection();
            boolean executed = handler.executeAction(statement);
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        finally{
            handler.closeStatement();
            handler.dropConnection();
        }   
    }
    
    
    /**
     * Removes a booking record from the BOOKING table
     * @param username The username of the person using the system
     * @return If the removal was successful
     */
    @Override
    public boolean removeBookingRecord(String username){
        statement = "DELETE FROM BOOKING WHERE USR_ID = '" +username+ "'";
        boolean executed = false;
        try{
            handler.createConnection();
            executed = handler.executeAction(statement);
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        finally{
            handler.closeStatement();
            handler.dropConnection();
        }
        return executed;
    } 
}
























































