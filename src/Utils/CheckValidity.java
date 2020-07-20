
package Utils;

import javafx.scene.control.Alert;

/**
 * Checks if an account meets the required information
 * @author Daniel Burrell
 */
public final class CheckValidity {
    private static boolean valid = true;
    private static String errorMessage;
    
    /**
     * Checks the information grabbed from the Register on NewUser.FXML
     * @param username The username inputted by the user.
     * @param firstname The FirstName inputted by the user.
     * @param lastname The LastName inputted by the user.
     * @param email The email address inputted by the user.
     * @param password The password inputted by the user.
     * @param confirmPassword The confirmation password inputted by the user.
     * @param age The age inputted by the user.
     * @param phone The phone number inputted by the user.
     * @param address The address inputted by the user.
     * @return the validity
     */
    public static boolean account(String username,String firstname,String lastname,String email,String password,String confirmPassword,String age,String phone, String address){
        errorMessage = "";  
        if(username.isEmpty() || firstname.isEmpty() || lastname.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()
           || age.isEmpty() || phone.isEmpty() || address.isEmpty()){
            valid = false;
            errorMessage += "Please enter all the required fields.\n";
        }
		
		
        
        if(!password.equals(confirmPassword)){
            valid = false;
            errorMessage += "Please ensure Password and Confirm Password are the same.\n";
        }
        
	/*	
        if(!firstname.matches("[a-zA-Z]+")){
            valid = false;
            errorMessage += "Firstname cannot contain numbers or special characters.\n";
        }
        
        if(!lastname.matches("[a-zA-Z]+")){
            valid = false;
            errorMessage += "Lastname cannot contain numbers or special characters.\n";
        }
        */
        if(!valid){
            Alert newUserAlert = new Alert(Alert.AlertType.ERROR);
            newUserAlert.setResizable(false);
            newUserAlert.setTitle("Profile Error");
            newUserAlert.setContentText(errorMessage);
            newUserAlert.showAndWait();
            
           
        }
        
        System.out.println(valid);
        return valid;
    }
    
}









