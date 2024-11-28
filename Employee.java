package project2;
import java.util.*;

abstract class Employee 
{
    //Profile
    protected String password;
    protected String phoneNo;
    protected String email;

    //Non-profile
    protected int employeeId;
    protected String username;
    protected String name;
    protected String surname;
    protected Date dateOfBirth;
    protected Date dateOfStart;
    

    //constructor
    public Employee(int employeeId, String username, String password,
                    String name, String surname, String phoneNo, Date dateOfBirth,
                    Date dateOfStart, String email) {
        this.employeeId = employeeId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phoneNo = phoneNo;
        this.dateOfBirth = dateOfBirth;
        this.dateOfStart = dateOfStart;
        this.email = email;
    }

    //getters and setters
    public int getEmployeeId()
    {
        return employeeId;
    }

    public abstract String getRole();

    public void setEmployeeId(int employeeId) 
    {
        this.employeeId = employeeId;
    }

    public String getUsername() 
    {
        return username;
    }

    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getSurname() 
    {
        return surname;
    }

    public void setSurname(String surname) 
    {
        this.surname = surname;
    }

    public String getPhoneNo() 
    {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) 
    {
        this.phoneNo = phoneNo;
    }

    public Date getDateOfBirth() 
    {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) 
    {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfStart() 
    {
        return dateOfStart;
    }

    public void setDateOfStart(Date dateOfStart) 
    {
        this.dateOfStart = dateOfStart;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }
}
