package test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Date; 
import java.util.*;

/**
 * Represents a Manager, inheriting from the Employee class.
 * Includes displaying, hiring, firing, updating employee information, and running algorithms.
 */
public class Manager extends Employee 
{
    private static Scanner scanner = new Scanner(new InputStreamReader(System.in, StandardCharsets.UTF_8));


    Databasefacade db = new Databasefacade();

    /**
    * Default constructor for the Manager class.
    * Creates a manager with default properties.
    */
    public Manager()
    {
        this.username = "username";
        this.password = "pass123";
        this.name = "name";
        this.surname = "surname";
        this.role ="Manager";
    }
    
    /**
     * Parameterized constructor for the Manager class.
     * @param employeeId the unique ID of the manager
     * @param username the manager's username
     * @param password the manager's password
     * @param name the manager's first name
     * @param surname the manager's last name
     * @param phoneNumber the manager's phone number
     * @param role the role of the manager
     * @param dateOfBirth the manager's date of birth
     * @param dateOfStart the manager's start date
     * @param email the manager's email address
     */
    public Manager(int employeeId, String username, String password, String name, String surname, String phoneNumber,String role, java.sql.Date dateOfBirth, java.sql.Date dateOfStart, String email) 
    {
        super(employeeId, username, password, name, surname, phoneNumber,role, dateOfBirth, dateOfStart, email);
    }

    /**
     * @return the role of the employee as "Manager".
     */
    @Override
    public String getRole() {
        return "Manager";
    }
    /**
     * Displays the Manager menu.
     * Performs manager-specific operations.
     * @param user logged-in manager.
     */
    public static void managerMenu(Employee user)
    {
        boolean flag=true;

        while(flag)
        {
            System.out.printf("\n=== %s %s ===", user.getName(), user.getSurname());
            System.out.println("\n=== MANAGER MENU ===");
            System.out.println("[A] Update Own Profile");
            System.out.println("[B] Display Employees");
            System.out.println("[C] Update Employee Non-profile");
            System.out.println("[D] Hire Employee");
            System.out.println("[E] Fire Employee");
            System.out.println("[F] Algorithms");
            System.out.println("[G] Logout");
            System.out.print("Please make a choice:");
            String option = scanner.nextLine().toUpperCase();
            

            switch(option)
            {
                case "A":
                    Project2.flush_terminal();
                    updateProfile(user);
                    break;
                case "B":
                    Project2.flush_terminal();
                    displayMenu();
                    break;
                case "C":
                    Project2.flush_terminal();
                    updateEmployee();
                    break;
                case "D":
                    Project2.flush_terminal();
                    hireEmployee();
                    break;
                case "E":
                    Project2.flush_terminal();
                    fireEmployee(user.getEmployeeId());
                    break;
                case "F":
                    Project2.flush_terminal();
                    algorithmMenu();
                    break;
                case "G":
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
                    Project2.flush_terminal();
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
    
    /**
     * Updates the manager's own profile.
     * Including password, phone number, email,username, name, surname, date of birth, and start date.
     * Each field has specific validation rules.
     * @param user the manager whose profile is being updated
     */
    public static void updateProfile(Employee user)
    {
        Project2.flush_terminal();
        while(true)
        {
            System.out.println("\n=== UPDATE OWN PROFILE ===");
            System.out.println("Name: " + user.getName());
            System.out.println("Surname: " + user.getSurname());
            System.out.println("Username: " + user.getUsername());
            System.out.println("Phone number: " + user.getPhoneNo());
            System.out.println("E-Mail: " + user.getEmail());
            System.out.println("Date of Birth: " + user.getDateOfBirth());
            System.out.println("Date of Start: " + user.getDateOfStart()); 
            System.out.println("===================================");
            System.out.println("[A] Update Password");
            System.out.println("[B] Update Phone Number");
            System.out.println("[C] Update E-Mail");
            System.out.println("[D] Update Username");
            System.out.println("[E] Update Name");
            System.out.println("[F] Update Surname");
            System.out.println("[G] Update Date of birth");
            System.out.println("[H] Update Date of start");
            System.out.println("[I] Return to the Manager Menu");
            System.out.print("Please make a choice:");
            String option = scanner.nextLine().toUpperCase();

            
            switch(option)
            {
                case "A":
                    Project2.flush_terminal();
                    while(true)
                    {   
                        System.out.println("\nPassword must contain at least one letter, one number, and one special character(.-_!@#$%^&*)!");
                        System.out.print("\nPlease enter a new password:");
                        String newPass = scanner.nextLine();
                        System.out.print("Please enter the same password:");
                        String newPass2 = scanner.nextLine();

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
                        
                        if(newPass.equals(user.password)) 
                        {
                            Project2.flush_terminal();
                            System.out.println("New Password cannot be same with the old password!");
                            continue;
                        }
                        
                        if(newPass.length() < 8)
                        { 
                            Project2.flush_terminal();
                            System.out.println("Password must be at least 8 characters long!");
                            continue;
                        }
                        if(!newPass.matches(".*[a-zA-Z].*") || !newPass.matches(".*[0-9].*") || !newPass.matches(".*[._!@#$%^&*-].*")) 
                        {
                            Project2.flush_terminal();
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
                        String newPhoneNo = scanner.nextLine();

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
                        Databasefacade.updatePhoneNumber(user.getEmployeeId(), user.getPhoneNo());
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
                        String newEmail = scanner.nextLine();

                        
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
                    while(true)
                    {
                        System.out.println("Username can only contain letters, numbers, and underscores.");
                        System.out.print("Please enter a new username: ");
                        String newUsername = scanner.nextLine().trim();

                        if(newUsername.equals(user.username)) 
                        {
                            Project2.flush_terminal();
                            System.out.println("New username cannot be same with the old username!");
                            continue;
                        }
                        else if (newUsername.isEmpty()) 
                        {
                            Project2.flush_terminal();
                            System.out.println("Username cannot be empty or only spaces!");
                            continue;
                        }
                        else if (!newUsername.matches("^[A-Za-z0-9_.]+$")) 
                        {
                            Project2.flush_terminal();
                            continue;
                        } 

                        user.setUsername(newUsername);
                        Databasefacade.updateUsername(user.getEmployeeId(),user.getUsername());
                        Project2.flush_terminal();
                        System.out.println("Username successfully updated.");
                        break;
                    }
                    break;

                case "E":
                    Project2.flush_terminal();
                    while(true)
                    {
                        
                        System.out.print("Please enter a new name: ");
                        String newName = scanner.nextLine();

                        if (newName == null || newName.trim().isEmpty()) 
                        {
                            Project2.flush_terminal();
                            System.out.println("Name cannot be empty!");
                            continue;
                        } 
                        else if (!newName.matches("^[A-Za-z'ÇçĞğİıÖöŞşÜü]+$")) 
                        {
                            Project2.flush_terminal();
                            System.out.println("Name can only contain letters.");
                            continue;
                        }
                        newName = newName.substring(0, 1).toUpperCase() + newName.substring(1);
                        user.setName(newName);
                        Databasefacade.updateName(user.getEmployeeId(),user.getName());
                        Project2.flush_terminal();
                        System.out.println("Name successfully updated.");
                        break;
                    }
                    break;

                case "F":
                    Project2.flush_terminal();
                    while(true)
                    {
                        System.out.print("Please enter a new surname: ");
                        String newSurname = scanner.nextLine();

                        if (newSurname == null || newSurname.trim().isEmpty()) 
                        {
                            Project2.flush_terminal();
                            System.out.println("Surname cannot be empty!");
                            continue;
                        } 
                        else if (!newSurname.matches("^[A-Za-z'ÇçĞğİıÖöŞşÜü]+$")) 
                        {
                            Project2.flush_terminal();
                            System.out.println("Surname can only contain letters.");
                            continue;
                        }

                        newSurname = newSurname.substring(0, 1).toUpperCase() + newSurname.substring(1);
                        user.setSurname(newSurname);
                        Databasefacade.updateSurname(user.getEmployeeId(),user.getSurname());
                        Project2.flush_terminal();
                        System.out.println("Surname successfully updated.");
                        break;
                    }
                    break;
                case "G":
                    Project2.flush_terminal();
                    while(true)
                    {

                        System.out.println("Please write the date of birth (yyyy-MM-dd):");
                        String start = scanner.nextLine();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        try 
                        {
                            
                            LocalDate birthDate = LocalDate.parse(start, formatter);

                            LocalDate age18 = LocalDate.now().minusYears(18);
                            if (birthDate.isAfter(age18))
                            {
                                Project2.flush_terminal();
                                System.out.println("People under 18 years old are not allowed to work.");
                                continue;
                            }

                            if (birthDate.isAfter(LocalDate.now())) 
                            {
                                Project2.flush_terminal();
                                System.out.println("Birth date cannot be in the future.");
                                continue;
                            }
                            
                            Date sqlBirthDate = Date.valueOf(birthDate);
                            user.setDateOfBirth(sqlBirthDate);
                            Databasefacade.updateDateOfBirth(user.getEmployeeId(),user.getDateOfBirth());
                            Project2.flush_terminal();
                            System.out.println("Birth date sucessfully updated.");
                            break;
                        } 
                        catch (Exception e) 
                        {
                            Project2.flush_terminal();
                            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                            continue;
                        }
                    }
                    break;
                case "H":
                    Project2.flush_terminal();
                    while(true)
                    {

                        System.out.println("Please write the date of start (yyyy-MM-dd):");
                        String start = scanner.nextLine();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        try 
                        {
                            LocalDate startDate = LocalDate.parse(start, formatter);
                            if (startDate.isAfter(LocalDate.now())) 
                            {
                                Project2.flush_terminal();
                                System.out.println("Start date cannot be in the future.");
                                continue;
                            }

                            if(startDate.isBefore(user.getDateOfBirth().toLocalDate()))
                            {
                                Project2.flush_terminal();
                                System.out.println("Start date can't be before the birth date.");
                                continue;
                            }

                            Date sqlStartDate = Date.valueOf(startDate);
                            user.setDateOfStart(sqlStartDate);
                            Databasefacade.updateDateOfStart(user.getEmployeeId(),user.getDateOfStart());
                            Project2.flush_terminal();
                            System.out.println("Start date sucessfully updated.");
                            break;
                        } 
                        catch (Exception e) 
                        {
                            Project2.flush_terminal();
                            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                            continue;
                        }
                    }
                    break;

                case "I":
                    Project2.flush_terminal();
                    System.out.println("Returning to the Manager Menu...");
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e)
                    {
                        System.out.println("Sleep interrupted: " + e.getMessage());
                    }
                    Project2.flush_terminal();
                    return;
                default:
                    Project2.flush_terminal();
                    System.out.println("Invalid option! Please try again.");
        
            }
        }
    }

    /**
     * Displays the menu to view employees' information.
     * Options include viewing all employees, employees by role, and employees by username and return to the manager menu.
     */
    public static void displayMenu()
    {
        while(true)
        {
            System.out.println("\n=== DISPLAY MENU ===");
            System.out.println("[A] Display All Employees");
            System.out.println("[B] Display Employees with the Role");
            System.out.println("[C] Display Employee with Username");
            System.out.println("[D] Return to the Manager Menu");
            System.out.print("Please make a choice:");
            String option = scanner.nextLine().toUpperCase();

            switch(option)
            {
                case "A":
                    Project2.flush_terminal();
                    Databasefacade.displayAllEmployees();
                    break;

                case "B":
                    Project2.flush_terminal();
                    boolean flag=true;
                    while(flag)
                    {
                        System.out.println("\n[A] Display Managers");
                        System.out.println("[B] Display Engineers");
                        System.out.println("[C] Display Technicians");
                        System.out.println("[D] Display Interns");
                        System.out.println("[E] Return to the display menu");
                        System.out.print("Please make a choice:");
                        String choice = scanner.nextLine().toUpperCase();
                        String role="";

                        switch(choice)
                        {
                            case "A":
                                Project2.flush_terminal();
                                role ="Manager";
                                Databasefacade.displayEmployeesWithRole(role);
                                break;
                            case "B":
                                Project2.flush_terminal();
                                role ="Engineer";
                                Databasefacade.displayEmployeesWithRole(role);
                                break;
                            case "C":
                                Project2.flush_terminal();
                                role ="Technician";
                                Databasefacade.displayEmployeesWithRole(role);
                                break;
                            case "D":
                                Project2.flush_terminal();
                                role ="Intern";
                                Databasefacade.displayEmployeesWithRole(role);
                                break;
                            case "E":
                                Project2.flush_terminal();
                                flag = false;
                                break;
                            
                            default:
                                Project2.flush_terminal();
                                System.out.println("Invalid input! Please enter a valid choice."); 
                                break;
                        }
                    }
                    break;
                case "C":
                    Project2.flush_terminal();
                    while(true)
                    {
                        String username="";
                        Databasefacade.getUsernames();
                        System.out.print("Please enter a username:");
                        username = scanner.nextLine();
                        if(!Databasefacade.usernameCheck(username))
                        {
                            Project2.flush_terminal();
                            System.out.println("Enter a valid username!");
                            continue;
                        }
                        
                        Project2.flush_terminal();
                        Databasefacade.displayEmployeeWithUsername(username);
                        break;
                    }
                    break;   
                case "D":
                    Project2.flush_terminal();
                    System.out.println("Returning to the Manager Menu...");
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e)
                    {
                        System.out.println("Sleep interrupted: " + e.getMessage());
                    }
                    return;
                    
                default:
                    System.out.println("Invalid option! Please try again.");
                    break;
        
            }
        }
    }

    /**
     * Updates the information of an employee based on the manager's selection.
     * Options include updating password, phone number, email, username, name, surname, date of birth, start date and return main menu.
     * Each field has specific validation rules.
     */
    public static void updateEmployee()
    {
        while(true)
        {
            Employee person = null;
            Databasefacade.displayAllEmployees();
            int id;
            while(true)
            {
                try
                {
                    System.out.print("Please write the id of the employee you want to update:");
                    String userEntry = scanner.nextLine().trim();
                    id = Integer.parseInt(userEntry);
                    break;
                }
                catch(InputMismatchException | NumberFormatException e)
                {
                    System.out.println("Please enter a number!");
                }
            }

            Project2.flush_terminal();
            while(true)
            {
                person =  Databasefacade.getEmployee(id);

                if(person == null)
                {
                    Project2.flush_terminal();
                    System.out.println("Employee does not exist!");
                    break;
                }

                System.out.println("=== Employee Information ===");
                System.out.println("Employee ID: " + person.getEmployeeId());
                System.out.println("Username: " + person.getUsername());
                System.out.println("Role: " + person.getRole());
                System.out.println("Name: " + person.getName());
                System.out.println("Surname: " + person.getSurname());
                System.out.println("Date of Birth: " + person.getDateOfBirth());
                System.out.println("Date of Start: " + person.getDateOfStart());
                System.out.println("=== UPDATE OPTIONS ===");
                System.out.println("[A] Update Username");
                System.out.println("[B] Update Name");
                System.out.println("[C] Update Surname");
                System.out.println("[D] Update Date of birth");
                System.out.println("[E] Update Date of start");
                System.out.println("[F] Return to the Manager Menu");
                System.out.print("Please make a choice:");
                String option = scanner.nextLine().toUpperCase();

                switch(option)
                {
                    case "A":
                        Project2.flush_terminal();
                        while(true)
                        {
                            System.out.print("Please enter a new username: ");
                            String newUsername = scanner.nextLine().trim();

                            if(newUsername.equals(person.username)) 
                            {
                                Project2.flush_terminal();
                                System.out.println("New username cannot be same with the old username!");
                                continue;
                            }
                            else if (newUsername.isEmpty()) 
                            {
                                Project2.flush_terminal();
                                System.out.println("Username cannot be empty.");
                                continue;
                            } 
                            else if (!newUsername.matches("^[A-Za-z0-9_.]+$")) 
                            {
                                Project2.flush_terminal();
                                System.out.println("Username can only contain letters, numbers, and underscores.");
                                continue;
                            }
                            if(Databasefacade.usernameCheck(newUsername))
                            {
                                Project2.flush_terminal();
                                System.out.println("Username exists in the database.PLease enter a new one.");
                                continue;
                            }

                            person.setUsername(newUsername);
                            Databasefacade.updateUsername(person.getEmployeeId(),person.getUsername());
                            Project2.flush_terminal();
                            System.out.println("Username successfully updated.");
                            break;
                        }
                        break;

                    case "B":
                        Project2.flush_terminal();
                        while(true)
                        {
                            System.out.print("Please enter a new name: ");
                            String newName = scanner.nextLine();

                            if (newName == null || newName.trim().isEmpty()) 
                            {
                                Project2.flush_terminal();
                                System.out.println("Name cannot be empty!");
                                continue;
                            } 
                            else if (!newName.matches("^[A-Za-z'ÇçĞğİıÖöŞşÜü]+$")) 
                            {
                                Project2.flush_terminal();
                                System.out.println("Name can only contain letters.");
                                continue;
                            }

                            newName = newName.substring(0, 1).toUpperCase() + newName.substring(1);
                            person.setName(newName);
                            Databasefacade.updateName(person.getEmployeeId(),person.getName());
                            Project2.flush_terminal();
                            System.out.println("Name successfully updated.");
                            break;
                        }
                        break;

                    case "C":
                        
                        while(true)
                        {
                            System.out.print("Please enter a new surname: ");
                            String newSurname = scanner.nextLine();

                            if (newSurname == null || newSurname.trim().isEmpty()) 
                            {
                                Project2.flush_terminal();
                                System.out.println("Surname cannot be empty!");
                                continue;
                            } 
                            if (!newSurname.matches("^[A-Za-z'ÇçĞğİıÖöŞşÜü]+$")) 
                            {
                                Project2.flush_terminal();
                                System.out.println("Surname can only contain letters.");
                                continue;
                            }

                            newSurname = newSurname.substring(0, 1).toUpperCase() + newSurname.substring(1);
                            person.setSurname(newSurname);
                            Databasefacade.updateSurname(person.getEmployeeId(),person.getSurname());
                            Project2.flush_terminal();
                            System.out.println("Surname successfully updated.");
                            break;
                        }
                        break;
                    case "D":
                        Project2.flush_terminal();
                        while(true)
                        {
                            System.out.print("Please write the date of birth (yyyy-MM-dd):");
                            String start = scanner.nextLine();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            try 
                            {
                                LocalDate birthDate = LocalDate.parse(start, formatter);
                                
                                LocalDate age18 = LocalDate.now().minusYears(18);
                                if (birthDate.isAfter(age18))
                                {
                                    Project2.flush_terminal();
                                    System.out.println("People under 18 years old are not allowed to work.");
                                    continue;
                                }

                                if (birthDate.isAfter(LocalDate.now())) 
                                {
                                    Project2.flush_terminal();
                                    System.out.println("Birth date cannot be in the future.");
                                    continue;
                                }
                                Date sqlBirthDate = Date.valueOf(birthDate);
                                person.setDateOfStart(sqlBirthDate);
                                Databasefacade.updateDateOfBirth(person.getEmployeeId(),person.getDateOfBirth());
                                Project2.flush_terminal();
                                System.out.println("Birth date sucessfully updated.");
                                break;
                            } 
                            catch (Exception e) 
                            {
                                Project2.flush_terminal();
                                System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                                continue;
                            }
                        }
                        break;
                    case "E":
                        Project2.flush_terminal();
                        while(true)
                        {
                            
                            System.out.print("Please write the date of start (yyyy-MM-dd):");
                            String start = scanner.nextLine();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            try 
                            {
                                LocalDate startDate = LocalDate.parse(start, formatter);
                                if (startDate.isAfter(LocalDate.now())) 
                                {
                                    Project2.flush_terminal();
                                    System.out.println("Start date cannot be in the future.");
                                    continue;
                                }

                                if (startDate.isBefore(person.getDateOfBirth().toLocalDate()))
                                {
                                    Project2.flush_terminal();
                                    System.out.println("Start date can't be before the birth date.");
                                    continue;
                                }

                                Date sqlStartDate = Date.valueOf(startDate);
                                person.setDateOfStart(sqlStartDate);
                                Databasefacade.updateDateOfStart(person.getEmployeeId(),person.getDateOfStart());
                                Project2.flush_terminal();
                                System.out.println("Start date sucessfully updated.");
                                break;
                            } 
                            catch (Exception e) 
                            {
                                Project2.flush_terminal();
                                System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                                continue;
                            }
                        }
                        break;

                    case "F":
                        Project2.flush_terminal();
                        System.out.println("Returning to the Manager Menu...");
                        try
                        {
                            Thread.sleep(1000);
                        }
                        catch (InterruptedException e)
                        {
                            System.out.println("Sleep interrupted: " + e.getMessage());
                        }
                        return;
                        
                    default:
                        Project2.flush_terminal();
                        System.out.println("Invalid option! Please try again.");
                        break;
                }
            }
        }
    }
    
    /**
     * Allows the manager to hire a new employee.
     * The manager can specify the role of the employee and fill out their information.
     */
    public static void hireEmployee()
    {
        Employee person=null;
        boolean flag=true;
        while(flag)
        {
            System.out.println("=== HIRE AN EMPLOYEE===");
            System.out.println("\n[A] Hire a manager");
            System.out.println("[B] Hire a technician");
            System.out.println("[C] Hire a engineer");
            System.out.println("[D] Hire a intern");   
            System.out.println("[E] Return to the manager menu"); 
            System.out.print("\nPlease choose the role of new employee:");
            String choice = scanner.nextLine().toUpperCase();
            
            switch(choice)
            {
                case "A":
                    Project2.flush_terminal();
                    person = new Manager();
                    flag=false;
                    break;
                case "B":
                    Project2.flush_terminal();
                    person = new RegularEmployee();
                    person.setRole("Technician");
                    flag=false;
                    break;
                case "C":
                    Project2.flush_terminal();
                    person = new RegularEmployee();
                    person.setRole("Engineer");
                    flag=false;
                    break;
                case "D":
                    Project2.flush_terminal();    
                    person = new RegularEmployee();
                    person.setRole("Intern");
                    flag=false;
                    break;
                case "E":
                    Project2.flush_terminal();
                    return;
                default:
                    Project2.flush_terminal();
                    System.out.println("Please enter a valid choice!");
                    break;
            }
        }

        Project2.flush_terminal();
        while(true)
        {
            System.out.println("Write q to return to the manager menu.");
            System.out.println("Username can only contain letters, numbers, and underscores.");
            System.out.print("Please write the username of the new employee:");
            String username = scanner.nextLine();

            if(username.equals("q"))
            {
                Project2.flush_terminal();
                System.out.println("Returning to the manager menu...");
                return;
            }
            
            if(Databasefacade.usernameCheck(username))
            {
                Project2.flush_terminal();
                System.out.println("Username exists in the database.PLease enter a new one.");
                continue;
            }
            if (username.isEmpty()) 
            {
                Project2.flush_terminal();
                System.out.println("Username cannot be empty.");
                continue;
            } 
            else if (!username.matches("^[A-Za-z0-9_.]+$")) 
            {
                Project2.flush_terminal();
                continue;
            }

            person.setUsername(username);
            Project2.flush_terminal();
            System.out.println("Username set succesfully!");
            break;
        }
        
        while(true)
        { 
            System.out.println("Write q to return to the manager menu.");
            System.out.print("Please write the name of the new employee:");
            String newName = scanner.nextLine();

            if(newName.equals("q"))
            {
                Project2.flush_terminal();
                System.out.println("Returning to the manager menu...");
                return;
            }
            
            if (newName == null || newName.trim().isEmpty()) 
            {
                System.out.println("Name cannot be empty!");
                continue;
            } 
            else if (!newName.matches("^[A-Za-z'ÇçĞğİıÖöŞşÜü]+$")) 
            {
                System.out.println("Name can only contain letters.");
                continue;
            }

            newName = newName.substring(0, 1).toUpperCase() + newName.substring(1);
            person.setName(newName);
            Project2.flush_terminal();
            System.out.println("Name set succesfully!");
            break;
        }
        
        while(true)
        {
            System.out.println("Write q to return to the manager menu.");
            System.out.print("Please write the surname of the new employee:");
            String surname = scanner.nextLine();
            String regex = "^[a-zA-Z'çÇğĞıİöÖşŞüÜ]+$";

            if(surname.equals("q"))
            {
                Project2.flush_terminal();
                System.out.println("Returning to the manager menu...");
                return;
            }

            if (surname == null || surname.trim().isEmpty()) 
            {
                Project2.flush_terminal();
                System.out.println("Surname cannot be empty!");
                continue;
            } 
            else if (!surname.matches(regex)) 
            {
                Project2.flush_terminal();
                System.out.println("Surname can only contain letters.");
                continue;
            }

            surname = surname.substring(0, 1).toUpperCase() + surname.substring(1);
            person.setSurname(surname);
            Project2.flush_terminal();
            System.out.println("Surname set succesfully!");
            break;
        }
        
        while(true)
        {
            System.out.println("Write q to return to the manager menu.");
            System.out.print("Please write the date of birth of the new employee (yyyy-MM-dd):");
            String start = scanner.nextLine();

            if(start.equals("q"))
            {
                Project2.flush_terminal();
                System.out.println("Returning to the manager menu...");
                return;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try 
            {
                LocalDate birthDate = LocalDate.parse(start, formatter);
                LocalDate age18 = LocalDate.now().minusYears(18);
                
                if (birthDate.isAfter(age18))
                {
                    Project2.flush_terminal();
                    System.out.println("People under 18 years old are not allowed to work.");
                    continue;
                }
                if (birthDate.isAfter(LocalDate.now())) 
                {
                    Project2.flush_terminal();
                    System.out.println("Birth date cannot be in the future.");
                    continue;
                }
                Date sqlBirthDate = Date.valueOf(birthDate);
                person.setDateOfBirth(sqlBirthDate);
                Databasefacade.updateDateOfBirth(person.getEmployeeId(),person.getDateOfBirth());
                Project2.flush_terminal();
                System.out.println("Birth date set sucessfully!");
                break;
            } 
            catch (Exception e) 
            {
                Project2.flush_terminal();
                System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                continue;
            }
        }

        while(true)
        {
            System.out.println("Write q to return to the manager menu.");
            System.out.print("Please write the date of start of the new employee(yyyy-MM-dd):");
            String start = scanner.nextLine();

            if(start.equals("q"))
            {
                Project2.flush_terminal();
                System.out.println("Returning to the manager menu...");
                return;
            } 

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try 
            {
                LocalDate startDate = LocalDate.parse(start, formatter);
                if (startDate.isAfter(LocalDate.now())) 
                {
                    Project2.flush_terminal();
                    System.out.println("Start date cannot be in the future.");
                    continue;
                }

                if (startDate.isBefore(person.getDateOfBirth().toLocalDate()))
                {
                    Project2.flush_terminal();
                    System.out.println("Start date can't be before the birth date.");
                    continue;
                }

                Date sqlStartDate = Date.valueOf(startDate);
                person.setDateOfStart(sqlStartDate);
                Databasefacade.updateDateOfStart(person.getEmployeeId(),person.getDateOfStart());
                Project2.flush_terminal();
                System.out.println("Start date set sucessfully!");
                break;
            } 
            catch (Exception e) 
            {
                Project2.flush_terminal();
                System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                continue;
            }
        }
        Databasefacade.insertEmployee(person);
        Project2.flush_terminal();
        System.out.println("New employee added successfully!");
    
    }
    
    /**
     * Allows the manager to fire an employee.
     * The Manager selects the employee to be removed from the system by ID.
     * @param userID the ID of the currently logged-in manager to prevent self-firing.
     */
    public static void fireEmployee(int userID)
    {
        Project2.flush_terminal();
        Databasefacade.displayAllEmployees();
        int id;
        while(true)
        {
            System.out.print("\nPlease write the id of the employee you want to fire:");
            try
            {
                System.out.print("Please write the id of the employee you want to update:");
                String userEntry = scanner.nextLine().trim();
                id = Integer.parseInt(userEntry);
                if(userID==id)
                {
                    Project2.flush_terminal();
                    System.out.println("You cannot fire yourself!");
                    continue;
                }
                
            }
            catch(InputMismatchException | NumberFormatException e)
            {
                System.out.println("Please enter a number!");
                continue;
            }
            break;
        }
        Databasefacade.deleteEmployee(id);
    }

    /**
     * Provides a menu to run various sorting algorithms and analyze their performance.
     * Allows the manager to test sorting algorithms, measure execution times, and compare results.
     * Includes Radix Sort, Shell Sort, Heap Sort, and Insertion Sort algorithms.
     * Saves sorted datasets to CSV files.
     */
    public static void algorithmMenu()
    {

        Random randomGenerator = new Random();

        int arraySize = 0;
        boolean validSize = false;
        Project2.flush_terminal();
        while (!validSize) 
        {
            try 
            {
                System.out.print("Enter the dataset size (between 1,000 and 10,000): ");
                String userEntry = scanner.nextLine().trim();
                arraySize = Integer.parseInt(userEntry);

                if (arraySize < 1000 || arraySize > 10000) {
                    System.out.println("Error: Dataset size must be between 1,000 and 10,000.");
                } 
                else 
                {
                    validSize = true;
                }
            } 
            catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid integer between 1,000 and 10,000.");
            }
        }

        Map<String, Double> sortingTimes = new LinkedHashMap<>();

        int[] randomArray = new int[arraySize];
        for (int arrayIndex = 0; arrayIndex < arraySize; arrayIndex++) {
            randomArray[arrayIndex] = randomGenerator.nextInt(20001) - 10000;
        }
        Databasefacade.clearTable("SortedArrays");
        System.out.println("Generated Dataset (Unsorted): " +
                (arraySize <= 13 ? Arrays.toString(randomArray) : Arrays.toString(Arrays.copyOf(randomArray, 13)) + " ..."));
        Databasefacade.updateCSV("/home/sam/Desktop/test/test/RandomArray.csv", randomArray);

        int[] radixArray = Arrays.copyOf(randomArray, randomArray.length); 
        sortingAlgorithms radixSort = new sortingAlgorithms(radixArray);
        double radixSortTime = sortingAlgorithms.measureradixSort(radixSort);
        System.out.println("\nSorted Dataset (Radix Sort): " +
                (arraySize <= 13 ? Arrays.toString(radixSort.dataset) : Arrays.toString(Arrays.copyOf(radixSort.dataset, 13)) + " ..."));
        System.out.println("Time taken to sort using Radix Sort: " + radixSortTime + " ms");
        System.out.println("Radix Sort Comparison: " + (sortingAlgorithms.sortComparator(randomArray, radixSort.dataset) ? "CORRECT" : "INCORRECT"));
        Databasefacade.updateCSV("/home/sam/Desktop/test/test/RadixSort.csv", radixSort.dataset);

        int[] shellArray = Arrays.copyOf(randomArray, randomArray.length); 
        sortingAlgorithms shellSort = new sortingAlgorithms(shellArray);
        double shellSortTime = sortingAlgorithms.measureshellSort(shellSort);
        System.out.println("\nSorted Dataset (Shell Sort): " +
                (arraySize <= 13 ? Arrays.toString(shellSort.dataset) : Arrays.toString(Arrays.copyOf(shellSort.dataset, 13)) + " ..."));
        System.out.println("Time taken to sort using Shell Sort: " + shellSortTime + " ms");
        System.out.println("Shell Sort Comparison: " + (sortingAlgorithms.sortComparator(randomArray, shellSort.dataset) ? "CORRECT" : "INCORRECT"));
        Databasefacade.updateCSV("/home/sam/Desktop/test/test/ShellSort.csv", shellSort.dataset);

        int[] heapArray = Arrays.copyOf(randomArray, randomArray.length); 
        sortingAlgorithms heapSort = new sortingAlgorithms(heapArray);
        double heapSortTime = sortingAlgorithms.measureheapSort(heapSort);
        System.out.println("\nSorted Dataset (Heap Sort): " +
                (arraySize <= 13 ? Arrays.toString(heapSort.dataset) : Arrays.toString(Arrays.copyOf(heapSort.dataset, 13)) + " ..."));
        System.out.println("Time taken to sort using Heap Sort: " + heapSortTime + " ms");
        System.out.println("Heap Sort Comparison: " + (sortingAlgorithms.sortComparator(randomArray, heapSort.dataset) ? "CORRECT" : "INCORRECT"));
        Databasefacade.updateCSV("/home/sam/Desktop/test/test/HeapSort.csv", heapSort.dataset);

        int[] insertionArray = Arrays.copyOf(randomArray, randomArray.length); 
        sortingAlgorithms insertionSort = new sortingAlgorithms(insertionArray);
        double insertionSortTime = sortingAlgorithms.measureInsertionSort(insertionSort);
        System.out.println("\nSorted Dataset (Insertion Sort): " +
                (arraySize <= 13 ? Arrays.toString(insertionSort.dataset) : Arrays.toString(Arrays.copyOf(insertionSort.dataset, 13)) + " ..."));
        System.out.println("Time taken to sort using Insertion Sort: " + insertionSortTime + " ms");
        System.out.println("Insertion Sort Comparison: " + (sortingAlgorithms.sortComparator(randomArray, insertionSort.dataset) ? "CORRECT" : "INCORRECT"));
        Databasefacade.updateCSV("/home/sam/Desktop/test/test/InsertionSort.csv", insertionSort.dataset);

        sortingTimes.put("Radix Sort", radixSortTime);
        sortingTimes.put("Shell Sort", shellSortTime);
        sortingTimes.put("Heap Sort", heapSortTime);
        sortingTimes.put("Insertion Sort", insertionSortTime);

        System.out.println("\nSorting Times:");
        System.out.println("-------------------------------");
        System.out.printf("%-20s | %-10s%n", "Algorithm", "Time (ms)");
        System.out.println("-------------------------------");
        for (Map.Entry<String, Double> entry : sortingTimes.entrySet()) {
            System.out.printf("%-20s | %-10.6f%n", entry.getKey(), entry.getValue());
        }
        System.out.println("-------------------------------");
    }
}
