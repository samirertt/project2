///home/sam/Desktop/test ; /usr/bin/env /usr/lib/jvm/java-21-openjdk-amd64/bin/java @/tmp/cp_cacv261y7m22z8gj23xosv5vq.argfile test.Project2 
package test;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.*;



public class Project2 
{
    private static Scanner scanner = new Scanner(new InputStreamReader(System.in, StandardCharsets.UTF_8));

    public static void main(String[] args)
    {
        try
        {
            ASCIIArt.display_Proj_intro();
            Databasefacade databaseFacade = new Databasefacade();
            
            Aut auth = new Aut(databaseFacade);
            
            while(true)
            {
                ASCIIArt.display_welcome_msg();
                System.out.println("\n*************************************");
                System.out.println("*                                     *");
                System.out.println("*         [A] Login                   *");
                System.out.println("*         [B] Terminate               *");
                System.out.println("*         [C] Special Acknowledments  *");
                System.out.println("*                                     *");
                System.out.println("***************************************");
                System.out.print("Please make a choice: ");

                String choice = scanner.nextLine().toUpperCase();
                switch(choice)
                {
                    case "A":
                        flush_terminal();
                        Menu(auth);
                        break;
                    case "B":
                        flush_terminal();
                        System.out.println("Terminating the program...");
                        return; 
                    case "C":
                        flush_terminal();
                        ASCIIArt.acknowledment();
                        break;
                    default:
                        flush_terminal();
                        System.out.println("Invalid Option! Try again...");
                        break;
                }
            }
        }
        catch (SQLException sqlException) 
        {
            sqlException.printStackTrace();
        }
        scanner.close();
    } 
    
    public static void Menu(Aut auth)
    {
        Employee user = null;
        while(true)
        {
            System.out.print("\nEnter username:");
            String username = scanner.nextLine();
            System.out.print("\nEnter password:");
            String password = scanner.nextLine();

            try 
            {
                user = auth.authenticate(username, password);

                if (user == null) 
                {
                    ASCIIArt.display_Fail_msg();
                    System.out.println("\nInvalid credentials, please try again.");
                    continue;
                }
            }
            catch (SQLException e) 
            {
                System.out.println("An error occurred while trying to authenticate. Please try again.");
                e.printStackTrace();
                continue;
            }
            
            // Check if the password is "pass123" and ask for a new password
            if(user.getPassword().equals("pass123"))
            {
                while(true)
                {
                    System.out.println("\nPlease set a new password.");
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
                    
                    if(newPass.length() < 8)
                    { 
                        System.out.println("Password must be at least 8 characters long!");
                        continue;
                    }
                    if(!newPass.matches(".*[a-zA-Z].*") || !newPass.matches(".*[0-9].*") || !newPass.matches(".*[.-_!@#$%^&*].*")) 
                    {
                        System.out.println("Password must contain at least one letter, one number, and one special character(.-_!@#$%^&*)!");
                        continue;
                    }

                    user.setPassword(newPass);
                    Databasefacade.setSalt(user.getUsername());
                    String salt = Databasefacade.getSalt(user.getUsername());
                    String NEW_PASS = Databasefacade.hashPassword(user.getPassword(),salt);
                    Databasefacade.updatePassword(user.getEmployeeId(), NEW_PASS, salt);
                    System.out.println("Password successfully updated.");
                    flush_terminal();
                    break;
                }
            }

            // Check the user's role and display the appropriate menu
            String role = user.getRole();

            if(role.equals("Manager"))
            {
                ASCIIArt.display_success_msg();
                try
                {
                    Thread.sleep(1500);
                }
                catch (InterruptedException e)
                {
                    System.out.println("Sleep interrupted: " + e.getMessage());
                }
                flush_terminal();
                Manager.managerMenu(user);
                break;
            }
            else
            {
                ASCIIArt.display_success_msg();
                try
                {
                    Thread.sleep(1500);
                }
                catch (InterruptedException e)
                {
                    System.out.println("Sleep interrupted: " + e.getMessage());
                }
                flush_terminal();
                RegularEmployee.displayRegularMenu(user);
                break;
            } 
        }
    }

    public static void flush_terminal()
    {
        try 
        {
            String operatingSystem = System.getProperty("os.name"); 
            if (operatingSystem.contains("Windows")) 
                // Clear terminal for Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else 
                new ProcessBuilder("bash","-c","clear").inheritIO().start().waitFor();
        } catch (Exception ex) 
        {
            System.out.println("Error clearing terminal: " + ex.getMessage());
        }
    }
        //System.out.print("\033[H\033[2J");
        //System.out.flush();  
    }

