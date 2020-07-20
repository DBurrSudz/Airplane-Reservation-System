
package Utils;



/**
 * Creates a session object storing the current user of the system.
 * @author Daniel Burrell
 */
public final class Session {
    private static String currentUser = null;
    private static Session session = null;
	
	
    /**
    * Private constructor that sets up a user as the current user of the system.
    * @param user The current user of the system.
    */
    private Session (String user){
        currentUser = user;
    }
	
    /**
    * Creates an instance of the Session class with a specified user.
    * @param username The username of the current user of the system.
    * @return Session object.
    */
    public static Session createSession(String username){
        if(session == null){
            session = new Session(username);
        }
		
        return session;
    }
	
    /**
    * Returns a session object to the caller if one is present.
    * @return Session object.
    */
    public static Session getCurrentSession(){
        if(session == null){
            return null;
        }
        else{
            return session;
        }
    }
	
    /**
    * Returns the current user of a session.
    * @return The user of this session.
    */
    public String getCurrentUser(){
        if(session == null){
            System.out.println("Session is null");
        }
        return currentUser;
    }
    
    /**
     * Sets a user of the system
     * @param user The username of the user
     */
    public void setCurrentUser(String user){
        if(session != null) currentUser = user;
    }
        
	
	
    /**
     * Clears a session and the user in that session.
     */
    public void clearSession(){
        currentUser = null;
        session = null;
    }
	
}































































