
package Database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Creates a connection to the Derby Embedded Database and handles all database related stuff for the system.
 * @author Daniel Burrell
 */
public final class CreateDB{
    private String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    public static final String JDBC = "jdbc:derby:AirplaneDataBase;create=true";
    private static CreateDB handler = null;
    private static Connection connection = null;
    private static String TABLE_NAME;
    private static DatabaseMetaData dmb;
    private Statement statement = null;
    private static ResultSet results;


    /**
     * Constructor that creates connection to the database and sets up all the
     * tables in the schema.
     *
     */
    private CreateDB(){
        try{
            Class.forName(DRIVER).newInstance();
	}
	catch(ClassNotFoundException | InstantiationException | IllegalAccessException error){
            System.out.println(error);
        }
    }


    /**
     * Creates a database object to be used throughout the system.
     * @return The database object.
     */
    public static CreateDB getHandler(){
        if (handler == null){
            handler = new CreateDB();
        }
        return handler;
    }


    /**
     * Private method to establish a connection to the database and creates a statement.
     */
    public void createConnection(){
        try{
            connection = DriverManager.getConnection(JDBC);
            statement = connection.createStatement();
            dmb = connection.getMetaData();
        }

        catch(SQLException error){
            System.out.println(error);
        }

    }


    /**
     * Drops the connection to the database.
     */
    public void dropConnection(){
        try{
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
        }
        catch(SQLException error){
            System.out.println(error.getMessage());
        }

    }

    /**
     * Closes the statement of the handler instance.
    */
    public void closeStatement(){
        try{
            if(statement != null && !statement.isClosed()){
                statement.close();
            }
        }
        catch(SQLException error){
            System.out.println(error.getMessage());
        }
    }


	/**
	 * Setup the tables of the database.
	 */
    public void setupTables(){
        try{
            setupUser();
            setupFlight();
            setupBooking();
            setupAccount();

        }
        catch(SQLException error){
            System.out.println(error);
            Throwable[] suppressedExceptions = error.getSuppressed();
            if (suppressedExceptions.length > 0){
                for(int i = 0; i <= suppressedExceptions.length;i++){
                    System.out.println(suppressedExceptions[i]+"\n");
                }
            }

        }
        finally{
            closeStatement();
            dropConnection();
        }


    }

    /**
     * Creates the USERS table in the database schema.
     * The responsibility of this table in the schema is to store all the valid
     * users of the system. Users are able to sign up by entering their credentials.
     * This table keeps track of the user's name, contacts, personal username and password and
     * address. The USERS table is also responsible to facilitate logging into the system.
     * @throws SQLException
     *
     */
    private void setupUser() throws SQLException{
        TABLE_NAME = "USERS";
        results = dmb.getTables(null,null,TABLE_NAME.toUpperCase(),null);
        if (results.next()){
            System.out.println("The table " +TABLE_NAME+ " already exists.");
        }

        else{
            statement.execute("CREATE TABLE " +TABLE_NAME+"("+
                                   "USR_ID varchar(200) NOT NULL PRIMARY KEY," +
                                   "USR_FNAME varchar(200) NOT NULL,"+
                                   "USR_LNAME varchar(200) NOT NULL,"+
                                   "USR_EMAIL varchar(200) NOT NULL,"+
                                   "USR_PASSWORD  varchar(200) NOT NULL," +
                                   "USR_AGE      varchar(200)  NOT NULL," +
                                   "USR_PHONE varchar(200) NOT NULL," +
                                   "USR_ADDRESS   varchar(500)  NOT NULL)");
            System.out.println("The table " +TABLE_NAME+ " has been created successfully.");
        }

    }



    /**
     * Creates the ACCOUNT table in the database schema.
     * The responsibility of this table in the schema is to store all the
     * accounts and their information belonging to each user of the system.
     * These accounts keep track of the balance, the date it was created, money owed
     * by the user and whether or not they have a charge.
     * @throws SQLException
     */
    private void setupAccount() throws SQLException{
        TABLE_NAME = "ACCOUNT";
        results = dmb.getTables(null, null, TABLE_NAME.toUpperCase(), null);

        if (results.next()){
            System.out.println("The table " +TABLE_NAME+ " already exists.");
        }

        else{
            statement.execute("CREATE TABLE " + TABLE_NAME + "(" +
                                  "ACC_NO varchar(600) NOT NULL PRIMARY KEY," +
                                  "ACC_BLNCE varchar(600) NOT NULL," +
                                  "USR_ID varchar(200)," +
                                  "FOREIGN KEY(USR_ID) REFERENCES USERS(USR_ID))");
            System.out.println("The table "+TABLE_NAME+" has been created successfully.");
        }
    }


    /**
     * Creates the BOOKING table in the database schema.
     * The responsibility of this table store the information of each booking
     * a user makes in a system. This table tracks the details of a booking which consists
     * of origin and destination information of a flight, the information about the users seating
     * on the flight and gate information.
     * @throws SQLException
     */
    private void setupBooking() throws SQLException{
        TABLE_NAME = "BOOKING";
        results = dmb.getTables(null, null, TABLE_NAME.toUpperCase(), null);
        if (results.next()){
            System.out.println("The table "+TABLE_NAME+" already exists");
        }
        else{
            statement.execute("CREATE TABLE "+TABLE_NAME+"("+
                                  "BK_ID varchar(600) NOT NULL PRIMARY KEY,"+
                                  "BK_ORGN varchar(300) NOT NULL,"+
                                  "BK_DESTIN varchar(300) NOT NULL,"+
                                  "BK_DEPART varchar(10) NOT NULL,"+
                                  "BK_ARRIV varchar(10) NOT NULL,"+
                                  "BK_CONNECTIONS varchar(10) NOT NULL,"+
                                  "BK_CARRYON varchar(10) NOT NULL,"+
                                  "BK_SEATS varchar(200) NOT NULL,"+
                                  "BK_CLASS varchar(10) NOT NULL,"+
                                  "BK_GATE varchar(10) NOT NULL,"+
                                  "USR_ID varchar(200) NOT NULL,"+
                                  "FOREIGN KEY(USR_ID) REFERENCES USERS(USR_ID),"+
                                  "FL_ID varchar(100),"+
                                  "FOREIGN KEY(FL_ID) REFERENCES FLIGHT(FL_ID))");
            System.out.println("The table "+TABLE_NAME+" has been created successfully");
        }

    }


    /**
     * Creates the FLIGHT table in the database schema. This table is responsible
     * for storing the information about the flights coming into and out of the airports.
     *@throws SQLException
     */
    private void setupFlight() throws SQLException{
        TABLE_NAME = "FLIGHT";
        results = dmb.getTables(null, null,TABLE_NAME.toUpperCase(),null);
        if(results.next()){
            System.out.println("The table "+TABLE_NAME+" already exists.");
            //statement.execute("INSERT INTO " +TABLE_NAME+ " VALUES('AA34','American Airlines','Austin, United States','Tokyo, Japan','10:00AM','5:00PM','2','5','No','G30','IKH','HGY')");
            //statement.execute("INSERT INTO " +TABLE_NAME+ " VALUES('DT82','Delta Airlines','Kingston, Jamaica','Cape erde, South Africa','3:00AM','6:00PM','5','2','No','H20','IKL','WRE')");
            //statement.execute("INSERT INTO " +TABLE_NAME+ " VALUES('DT20','Delta Airlines','New York, United States','Paris, France','2:00AM','3:00PM','2','10','Yes','A13','UYL','JFK')");
        }
        else{
            statement.execute("CREATE TABLE "+TABLE_NAME+"("+
                                  "FL_ID varchar(100) NOT NULL PRIMARY KEY,"+
                                  "FL_NAME varchar(50) NOT NULL,"+
                                  "FL_ORGN  varchar(300) NOT NULL,"+
                                  "FL_DESTIN varchar(300) NOT NULL,"+
                                  "FL_DEPART varchar(10) NOT NULL,"+
                                  "FL_ARRIV varchar(10) NOT NULL,"+
                                  "FL_CONNECTIONS varchar(10) NOT NULL,"+
                                  "FL_SEATS varchar(10) NOT NULL,"+
                                  "FL_DELAYED varchar(10) NOT NULL,"+
                                  "FL_GATE varchar(10) NOT NULL,"+
                                  "FL_DAPORT varchar(10) NOT NULL,"+
                                  "FL_AAPORT varchar(10) NOT NULL)");



            System.out.println("The table "+TABLE_NAME+" has been created successfully.");
        }
    }



    /**
     * Drops a table within the database schema.
     * @param table The table to be dropped.
     * @throws SQLException
     */
    public void dropTable(String table) throws SQLException{
        statement = connection.createStatement();
        statement.executeUpdate("DROP TABLE " + table);
        System.out.println("Table " + table + " was dropped succesfully");



    }



    /**
     * Handles querying from controller classes.
     * @param query The SQL command to perform an action in the database.
     * @throws SQLException
     * @return The results of the query.
     */
    public ResultSet executeQuery(String query) throws SQLException{
        try{
            results = statement.executeQuery(query);

        }
        catch(SQLException error){
            System.out.println(error);
            return null;
        }
        return results;
    }



    /**
     * Handles database actions from controller classes.
     * @param action The SQL command to perform an action in the database.
     * @return Whether the operation was successful.
     * @throws SQLException
     */
    public boolean executeAction(String action) throws SQLException{
        try{
            statement.execute(action);
        }
        catch(SQLException error){
            System.out.println(error);
            return false;
        }
        return true;
    }

    
    
    /**
     * Prints a table in the database.
     * @param table The table to be printed out.
     * @throws SQLException
     */
    public void printTable(String table) throws SQLException{
        try{
            results = statement.executeQuery("SELECT* FROM " + table);
            ResultSetMetaData resultMeta = results.getMetaData();

            int columnCount = resultMeta.getColumnCount();
            for (int x = 1; x <= columnCount;x++) System.out.format("%30s", resultMeta.getColumnName(x) + "|");
            while (results.next()){
                System.out.println("");
                for (int x = 1; x <= columnCount;x++) System.out.format("%30s",results.getString(x) + "|");
            }
            System.out.println("");
        }
	catch(SQLException error){
            System.out.println(error.getMessage());
	}
    }


}




