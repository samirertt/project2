package test;
import java.sql.*;

public class Aut 
{
    private Databasefacade databasefacade;
    
    public Aut(Databasefacade databasefacade) throws SQLException
    {
        this.databasefacade = databasefacade;
    }

    public Employee authenticate(String username, String password) throws SQLException
    {
        //kullanıcı adı var mı
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

