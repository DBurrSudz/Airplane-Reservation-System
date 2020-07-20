
package DataAccessObjects;

import Database.CreateDB;
import Interfaces.FlightDAO;
import Models.Flight;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
/**
 * Implements the Flight Data Access Object interface.
 * @author Daniel Burrell
 */
public class FlightDAOImp implements FlightDAO  {
    private final CreateDB handler;
    private String statement;
    private ResultSet results;
    
    /**
     * Public Constructor.
     */
    public FlightDAOImp(){
        statement = " ";
        handler = CreateDB.getHandler(); //Grabs the handler to the database.
    }
    
    
    /**
     * Gets all the flights that are in the database.
     * @return The list of flights in the database.
     */
    @Override
    public ArrayList<Flight> getFlights(){
        ArrayList<Flight> allFlights = new ArrayList<>();
        try{    
            statement = "SELECT* FROM FLIGHT";
            handler.createConnection();
            results = handler.executeQuery(statement);
            while(results.next()){
                allFlights.add(new Flight(results.getString(1),results.getString(2),results.getString(3),results.getString(4),results.getString(5),results.getString(6),results.getString(7),results.getString(8),results.getString(9),results.getString(10),results.getString(11),results.getString(12)));
            }   
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        finally{
            handler.closeStatement();
            handler.dropConnection();
        }  
        return allFlights;
    }
    
    
}






































