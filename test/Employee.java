package test;

/**
 * Abstract class representing an Employee in the system.
 * This class includes various profile-related and non-profile details.
 * Includes getters and setters for accessing and updating these details.
 */
abstract class Employee 
{
    public String password;
    public String phoneNo;
    public String email;

    public int employeeId;
    public String username;
    public String name;
    public String surname;
    public String role;
    public java.sql.Date dateOfBirth;
    public java.sql.Date dateOfStart;

    /**
     * Default constructor initializing an empty Employee object.
     */
    public Employee() 
    {
        this.username = "";
        this.name = "";
        this.surname = "";
        this.dateOfStart = null;
        this.dateOfBirth = null;
    }

    //constructor
     /**
     * Constructor to initialize an Employee object with all attributes.
     * @param employeeId the unique ID for the employee.
     * @param username the employee's username.
     * @param password the employee's password.
     * @param name the employee's name.
     * @param surname the employee's surname.
     * @param phoneNo the employee's phone number.
     * @param role the employee's role
     * @param dateOfBirth the employee's date of birth.
     * @param dateOfStart the employee started their employment.
     * @param email the employee's email address.
     */
    public Employee(int employeeId, String username, String password,
                    String name, String surname, String phoneNo,String role, java.sql.Date  dateOfBirth,
                    java.sql.Date  dateOfStart, String email) {
        this.employeeId = employeeId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
        this.surname = surname;
        this.phoneNo = phoneNo;
        this.dateOfBirth = dateOfBirth;
        this.dateOfStart = dateOfStart;
        this.email = email;
    }

    //getters and setters
    /** 
     * Gets the unique employee ID.
     * @return the employee ID.
     */
    public int getEmployeeId()
    {
        return employeeId;
    }

    /**
     * Abstract method to get the role of the employee.
     * @return the role of the employee
     */
    public abstract String getRole();

    /**
     * Sets the employee's role.
     * @param role the role to set.
     */
    public void setRole(String role)
    {
        this.role = role;
    }

    /**
     * Sets the unique ID of the employee.
     * @param employeeId the unique ID to set.
     */
    public void setEmployeeId(int employeeId) 
    {
        this.employeeId = employeeId;
    }

    /**
     * Gets the username of the employee.
     * @return the username of the employee.
     */
    public String getUsername() 
    {
        return username;
    }

    /**
     * Sets the username of the employee.
     * @param username the username to set
     */
    public void setUsername(String username) 
    {
        this.username = username;
    }

    /**
     * Gets the password of the employee.
     * @return the password of the employee.
     */
    public String getPassword() 
    {
        return password;
    }

    /**
     * Sets the password of the employee.
     * @param password the password to set.
     */
    public void setPassword(String password) 
    {
        this.password = password;
    }

    /**
     * Gets the name of the employee.
     * @return the name of the employee.
     */
    public String getName() 
    {
        return name;
    }

    /**
     * Sets the name of the employee.
     * @param name the name to set.
     */
    public void setName(String name) 
    {
        this.name = name;
    }

    /**
     * Gets the surname of the employee.
     * @return the surname of the employee.
     */
    public String getSurname() 
    {
        return surname;
    }

    /**
     * Sets the surname of the employee.
     * @param surname the surname to set.
     */
    public void setSurname(String surname) 
    {
        this.surname = surname;
    }

    /**
     * Gets the phone number of the employee.
     * @return the phone number of the employee.
     */
    public String getPhoneNo() 
    {
        return phoneNo;
    }

    /**
     * Sets the phone number of the employee.
     * @param phoneNo the phone number to set.
     * @return true if the phone number is set successfully.
     */
    public boolean setPhoneNo(String phoneNo) 
    {
        this.phoneNo = phoneNo;
        return true;
    }

    /**
     * Gets the date of birth of the employee.
     * @return the date of birth of the employee.
     */
    public java.sql.Date getDateOfBirth() 
    {
        return dateOfBirth;
    }

    /**
     * Sets the date of birth of the employee.
     * @param dateOfBirth the date of birth to set.
     */
    public void setDateOfBirth(java.sql.Date dateOfBirth) 
    {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets the start date of the employee's employment.
     * @return the start date of employment.
     */
    public java.sql.Date getDateOfStart() 
    {
        return dateOfStart;
    }

    /**
     * Sets the start date of the employee's employment.
     * @param dateOfStart the start date of employment to set.
     */
    public void setDateOfStart(java.sql.Date dateOfStart) 
    {
        this.dateOfStart = dateOfStart;
    }

    /**
     * Gets the email address of the employee.
     * @return the email adress of the employee.
     */
    public String getEmail() 
    {
        return email;
    }

    /**
     * Sets the email adress of the employee.
     * @param email the email adress to set.
     * @return true if the email is set successfully.
     */
    public boolean setEmail(String email) 
    {
        this.email = email;
        return true;
    }
}
