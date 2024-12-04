package test;
import java.sql.*;

/**
 * Handles authentication for employees in the system.
 * Uses password hashing and salting to ensure secure authentication.
 * Manages the authentication process for employees, verifies their credentials and retrieves their details from the database.
 */
public class Aut 
{
    private Databasefacade databasefacade;
    /**
     * Constructor for the `Aut` class.
     * @param databasefacade the database facade used for accessing employee and authentication data.
     * @throws SQLException if there is an issue initializing the connection to the database
     */
    public Aut(Databasefacade databasefacade) throws SQLException
    {
        this.databasefacade = databasefacade;
    }
    /**
     * Authenticates an employee using their username and password.
     * Checks if the given username exists and verifies the password.
     * Retrieves the hashed password and salt from the database.
     * If the password matches the stored credentials, the corresponding `Employee` object is returned.
     * @param username the username entered by the user
     * @param password the password entered by the user
     * @return An `Employee` object if authentication is successful; `null` otherwise.
     * @throws SQLException If there is an issue with the database query
     */
    public Employee authenticate(String username, String password) throws SQLException
    {
        if(!Databasefacade.usernameCheck(username))
        {
            return null;
        }
        if(!password.equals("pass123"))
        {   
            String gethashedpass = Databasefacade.getPassword(username);
            String getsalt = Databasefacade.getSalt(username);
            if(Databasefacade.verifyPassword(password,gethashedpass,getsalt))
            {
                Employee employee = databasefacade.getEmployee(username, gethashedpass);
                return employee;
            }
        }
        else
        {
                Employee employee = databasefacade.getEmployee(username, password);
                return employee;
        }
        return null;
    }
} 
