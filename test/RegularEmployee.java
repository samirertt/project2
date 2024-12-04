package test;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Represents a regular employee in the system, inheriting from the `Employee` class.
 * Allows regular employees with access to basic employee attributes.
 */
public class RegularEmployee extends Employee 
{
	
	private static Scanner scan = new Scanner(new InputStreamReader(System.in, StandardCharsets.UTF_8));


/**
     * Default constructor for the `RegularEmployee` class.
     * Initializes a `RegularEmployee` with default values for all attributes.
     * Used when creating a regular employee with default placeholder data.
     */
    public RegularEmployee()
    {
        this.username = "username";
        this.password = "pass123";
        this.name = "name";
        this.surname = "surname";
    }

/**
     * Parameterized constructor for the `RegularEmployee` class.
     * @param employeeId the employee's unique ID.
     * @param username the employee's username.
     * @param password the employee's password.
     * @param name the employee's name.
     * @param surname the employee's surname.
     * @param phoneNo the employee's phone number.
     * @param role the employee's role.
     * @param dateOfBirth the employee's date of birth.
     * @param dateOfStart the date the employee started working.
     * @param email the employee's email address.
     */
    public RegularEmployee(int employeeId, String username, String password, String name, String surname, String phoneNo,String role, java.sql.Date dateOfBirth, java.sql.Date dateOfStart, String email) {
        super(employeeId, username, password, name, surname, phoneNo,role, dateOfBirth, dateOfStart, email);
    }

	/**
     * Gets the role of the employee.
     * @return the employee's role.
     */
    @Override
    public String getRole() {
        return this.role;
    }

/**
     * Displays the regular employee menu and allows the user to make a selection.
     * Includes display profile, update profile and logout options.
     * @param user the employee currently logged in.
     */
    public static void displayRegularMenu(Employee user) 
    {
        
        Project2.flush_terminal();
        while (true)
        {
            System.out.println("=== "+ user.getRole() + " "+ user.getName()+" "+ user.getSurname() + " ===");
        	System.out.println("\n=== Regular Employee Menu ===");
            System.out.println("[A] Display Profile");
            System.out.println("[B] Update Profile");
            System.out.println("[C] Logout");
            System.out.print("Please select an option: ");
            
            String option=scan.nextLine().toUpperCase();
        
            switch (option) 
            {
                case "A":
                    Project2.flush_terminal();
                    displayNonProfile(user);
                    displayProfile(user);
                    break;
                case "B":
                    Project2.flush_terminal();
                    updateProfile(user);
                    break;
                case "C":
                    ASCIIArt.logging_out();
                    ASCIIArt.image();
                    try
                    {
                        Thread.sleep(2500);
                    }
                    catch (InterruptedException e)
                    {
                        System.out.println("Sleep interrupted: " + e.getMessage());
                    }
                    Project2.flush_terminal();
                    return;
                default:
                    System.out.println("Invalid option, please select a valid option: ");
                    scan.nextLine();
                    break;
            }
        }
    }

	/**
     * Displays basic profile information of the user.
     * Includes role, email and phone number.
     * @param user the employee whose profile information is displayed.
     */
    public static void displayProfile(Employee user) 
    {
        System.out.println("=== "+ user.getRole() + " "+ user.getName()+" "+ user.getSurname() + " ===");
    	System.out.println("Email: " + user.email);
        System.out.println("Phone: " + user.phoneNo);
    }

	/**
     * Allows the user to update their profile information.
     * Includes password, phone number, email and return to menu options.
     * Each option has specific validation rules.
     * @param user the employee whose profile will be updated.
     */
    public static void updateProfile(Employee user) 
    {
    	
    	while(true)
        {
            displayProfile(user);
            System.out.println("\n[A] Update Password");
            System.out.println("[B] Update Phone Number");
            System.out.println("[C] Update E-Mail");
            System.out.println("[D] Return to the Regular Employee Menu");
            System.out.print("Please make a choice:");
            String option = scan.nextLine().toUpperCase();

            switch(option)
            {
                case "A":
                    Project2.flush_terminal();
                    while(true)
                    {
                        System.out.println("\nPassword must contain at least one letter, one number, and one special character(.-_!@#$%^&*)!");
                        System.out.print("\nPlease enter a new password:");
                        String newPass = scan.nextLine();
                        System.out.print("Please enter the same password:");
                        String newPass2 = scan.nextLine();

                        if(!newPass.equals(newPass2))
                        {
                            Project2.flush_terminal();
                            System.out.println("The passwords doesn't match! PLease try again.");
                            continue;
                        }
                        
                        String gethashedpass = Databasefacade.getPassword(user.getUsername());
                        String getsalt = Databasefacade.getSalt(user.getUsername());
                        if(Databasefacade.verifyPassword(newPass,gethashedpass,getsalt))
                        {
                            Project2.flush_terminal();
                            System.out.println("New Password cannot be same with the old password!");
                            continue;
                        }
                        
                        if(newPass.length() < 8)
                        { 
                            System.out.println("Password must be at least 8 characters long!");
                            continue;
                        }

                        if(!newPass.matches(".*[a-zA-Z].*") || !newPass.matches(".*[0-9].*") || !newPass.matches(".*[._!@#$%^&*-].*")) 
                        {
                            continue;
                        }

                        user.setPassword(newPass);
                        Databasefacade.setSalt(user.getUsername());
                        String salt = Databasefacade.getSalt(user.getUsername());
                        String NEW_PASS = Databasefacade.hashPassword(user.getPassword(),salt);
                        Databasefacade.updatePassword(user.getEmployeeId(), NEW_PASS, salt);
                        Project2.flush_terminal();
                        System.out.println("Password successfully updated.");
                        break;
                    }
                    break;
                case "B":
                    Project2.flush_terminal();
                    while(true)
                    {

                        System.out.print("Please enter a new phone number: ");
                        String newPhoneNo = scan.nextLine();
                        
                        if(!newPhoneNo.matches("\\d{10}") ) 
                        {
                            Project2.flush_terminal();
                            System.out.println("Phone number must be 10 digits long!");
                            continue;
                        }

                        boolean isNumeric=true;

                        for (int i = 0; i < newPhoneNo.length(); i++) 
                        {
                            if (!Character.isDigit(newPhoneNo.charAt(i))) 
                            {
                                isNumeric = false;
                            }
                        }
                        if(isNumeric == false)
                        {
                            Project2.flush_terminal();
                            System.out.println("Phone number must be only contain digits!");
                            continue;
                        }

                        user.setPhoneNo(newPhoneNo);
                        Databasefacade.updatePhoneNumber(user.getEmployeeId(),user.getPhoneNo());
                        Project2.flush_terminal();
                        System.out.println("Phone number successfully updated.");
                        break;
                    }
                    break;

                case "C":
                    Project2.flush_terminal();
                    while(true)
                    {
                        System.out.print("Please enter a new e-mail: ");
                        String newEmail = scan.nextLine();

                        String emailFormat = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

                        
                        if(!newEmail.matches(emailFormat)) 
                        {
                            Project2.flush_terminal();
                            System.out.println("Please enter a valid e-mail!");
                            continue;
                        } 
                        
                      
                        user.setEmail(newEmail);
                        Databasefacade.updateEmail(user.getEmployeeId(), user.getEmail());
                        Project2.flush_terminal();
                        System.out.println("E-mail successfully updated.");
                        break;
                    }
                    break;

                case "D":
                    Project2.flush_terminal();
                    System.out.println("Returning to the Regular Employee Menu...");
                    displayRegularMenu(user);
                    return;
                    
                default:
                    Project2.flush_terminal();
                    System.out.println("Invalid option! Please try again.");
                    break;
        
            }   
        }
    }

	/**
     * Displays additional non-profile information of the logged in user.
     * Includes employee ID, username, name, role, surname, employment dates and date of birth.
     * @param user the employee whose non-personal details are displayed.
     */
    public static void displayNonProfile(Employee user) 
    {
        
        System.out.println("Name: " + user.name);
        System.out.println("Surname: " + user.surname);
    	System.out.println("Employee ID: " + user.employeeId);
        System.out.println("Username: " + user.username);
        System.out.println("Role: " + user.role);
        System.out.println("Date of Birth: " + user.dateOfBirth);
        System.out.println("Date of Start: " + user.dateOfStart);

    }
}
