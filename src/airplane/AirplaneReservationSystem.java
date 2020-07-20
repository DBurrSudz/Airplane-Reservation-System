
package airplane;

import Database.CreateDB;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Daniel Burrell
 */
public class AirplaneReservationSystem extends Application {
    
   
    CreateDB handler;
    private static Parent root = null;
    private static Stage window = null;
    private static HashMap<String,ArrayList<String>> unavailableSeats = null;
    
    @Override
    public void start(Stage stage) throws Exception {
        handler = CreateDB.getHandler();
	handler.createConnection();
	handler.setupTables();
        handler.closeStatement();
        handler.dropConnection();
        createUnavailableMap();
        Parent loginPage = FXMLLoader.load(getClass().getResource("Login/Login.fxml"));
        Scene scene = new Scene(loginPage);
        stage.setScene(scene);
        stage.setTitle("Welcome to QuickAir");
        stage.setResizable(false);
        stage.show();
    }
    
    /**
     * Returns a common root object to be used throughout the system by all classes. This
     * object is able to be used by all classes where necessary in order to facilitate getting
     * information about pages from FXML files. Wherever the function is called for the 
     * first time throughout the system, the root object will be
     * instantiated, after which, it is merely returned.
     * @return The root object to store FXML files throughout the system.
     */
    public static Parent getRoot(){
        if(root == null){
            root = new Parent() {};
        }
        return root;
    }
    
    
    /**
     * Returns a stage(window) object to be used throughout the system by other classes when attempting
     * to switch between stages. All classes are able to access this common stage instantiation through
     * this method. On the first call to the function, the stage object will be created, after which
     * it is merely returned.
     * @return The stage object to switch switch throughout the system. 
     */
    public static Stage getWindow(){
        if(window == null){
            window = new Stage();
        }
        return window;
    }
    
    
    
    /**
     * Creates the HashMap that will map a flight's ID to the unavailable seats.
     */
    private static void createUnavailableMap(){
        if(unavailableSeats == null){
            unavailableSeats = new HashMap<>();
            unavailableSeats.put("AA34", new ArrayList<>());
            unavailableSeats.put("DT82", new ArrayList<>());
            unavailableSeats.put("DT20", new ArrayList<>());
        }
    }
    
    /**
     * Gets the HashMap that maps flight IDs to unavailable seats.
     * @return The HashMap that maps flight IDs to unavailable seats.
     */
    public static HashMap getUnavailableSeats(){
        return unavailableSeats;
    }

    
    /**Entry point to the application.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}





















































































