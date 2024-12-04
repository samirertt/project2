package test;



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
    
    public Employee() 
    {
        this.username = "";
        this.name = "";
        this.surname = "";
        this.dateOfStart = null;
        this.dateOfBirth = null;
    }

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

    public int getEmployeeId()
    {
        return employeeId;
    }

    public abstract String getRole();

    public void setRole(String role)
    {
        this.role = role;
    }

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

    public boolean setPhoneNo(String phoneNo) 
    {
        this.phoneNo = phoneNo;
        return true;
    }

    public java.sql.Date getDateOfBirth() 
    {
        return dateOfBirth;
    }

    public void setDateOfBirth(java.sql.Date dateOfBirth) 
    {
        this.dateOfBirth = dateOfBirth;
    }

    public java.sql.Date getDateOfStart() 
    {
        return dateOfStart;
    }

    public void setDateOfStart(java.sql.Date dateOfStart) 
    {
        this.dateOfStart = dateOfStart;
    }

    public String getEmail() 
    {
        return email;
    }

    public boolean setEmail(String email) 
    {
        this.email = email;
        return true;
    }
}