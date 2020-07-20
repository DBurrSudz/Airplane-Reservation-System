
package DataAccessObjects;

/**
 *Creates the various Data Access Objects throughout the system.
 * @author Daniel Burrell
 */
public class DAOFactory {
    private static UserDAOImp userDataObject = null;
    private static FlightDAOImp flightDataObject = null;
    private static BookingDAOImp bookingDataObject = null;
    private static AccountDAOImp accountDataObject = null;
    
    
    /**
     * Creates a User Data Object
     * @return User Data Object
     */
    public static UserDAOImp getUserDAO(){
        if(userDataObject == null){
            userDataObject = new UserDAOImp();
        }
		
	return userDataObject;
    }
    
    
    /**
     * Creates a Flight Data Object
     * @return Flight Data Object
     */
    public static FlightDAOImp getFlightDAO(){
        if(flightDataObject == null){
            flightDataObject = new FlightDAOImp();
        }
        return flightDataObject;
    }
    
    
    /**
     * Creates a Booking Data Object
     * @return Booking Data Object
     */
    public static BookingDAOImp getBookingDAO(){
        if(bookingDataObject == null){
            bookingDataObject = new BookingDAOImp();
        }
        return bookingDataObject;
    }
    
    
    /**
     * Creates an Account Data Object
     * @return Account Data Object
     */
    public static AccountDAOImp getAccountDAO(){
        if(accountDataObject == null){
            accountDataObject = new AccountDAOImp();
        }
        return accountDataObject;
    }
}























