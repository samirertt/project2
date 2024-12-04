package test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * The `Databasefacade` class provides a unified interface for interacting with the database.
 * This class includes methods for establishing a connection, performing CRUD operations, verifying and hashing passwords, and generating salts for enhanced security
 */
public class Databasefacade{
    
    private static Connection connection;
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/Employees?useTimezone=true&serverTimezone=UTC";
    
    /**
     * Establishes a connection to the database using the specified URL and credentials.
     * 
     * <p>This method attempts to connect to the database using the JDBC DriverManager. 
     * If the connection is successful, it returns the established connection. 
     * If an error occurs, the SQLException is caught, its stack trace is printed, 
     * and the method returns the connection (which may be null if the connection fails).
     * 
     * @return the established {@link Connection} object if successful, or null if the connection fails.
     */
    public static Connection connection() 
    {
        try {
            connection = DriverManager.getConnection(DATABASE_URL, "root", "addnone2013");
            return connection;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return connection;
    }

    /**
     * Checks if a username exists in the database.
     * @param username the username to check.
     * @return `true` if the username exists, otherwise `false`.
     */
    public static boolean usernameCheck(String username)
    {
        Connection connect = connection();
        String sql_query = "SELECT COUNT(*) FROM non_profile WHERE username = ?";
        boolean exists = false;

        try {
            PreparedStatement prepared_Statement = connect.prepareStatement(sql_query);
            
            prepared_Statement.setString(1, username);

            ResultSet result_Set = prepared_Statement.executeQuery();
            if (result_Set.next()) {
                exists = result_Set.getInt(1) > 0;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return exists;
    }

    /**
     * Hashes a password using the SHA-256 algorithm with a salt.
     * @param password the plain-text password to hash.
     * @param salt the salt to use for hashing.
     * @return the hashed password as a hexadecimal string.
     */
    public static String hashPassword(String password, String salt) {
        try {
            String saltedPassword = password + salt;

            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = messageDigest.digest(saltedPassword.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
            
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No hashing algorithm found!", e);
        }
    }

    /**
     * Verifies if an entered password matches the stored hash.
     * @param enteredPassword the plain-text password entered by the user
     * @param storedHash the stored hashed password from the database
     * @param salt the salt used for hashing the stored password
     * @return `true` if the entered password matches the stored hash, otherwise `false`.
     */
    public static boolean verifyPassword(String enteredPassword, String storedHash, String salt) 
    {
        String enteredHash = hashPassword(enteredPassword, salt);
        boolean flag = enteredHash.trim().equals(storedHash.trim());
        return flag;

    }
    
    /**
     * Generates a random salt value.
     * @return a randomly generated 16-byte salt as a hexadecimal string.
     */
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);

        StringBuilder sb = new StringBuilder();
        for (byte b : saltBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    
    /**
     * Retrieves an employee by their username and password.
     * @param username the employee's username.
     * @param password the employee's password.
     * @return 'Employee' object containing employee details, null if not found
     * @throws SQLException If a database access error occurs.
     */
    public Employee getEmployee(String username, String password) throws SQLException 
    {
        Connection connect = connection();
        String sql_query = """
                        SELECT np.employee_id, np.username, np.role, np.name, np.surname, np.dateofbirth, np.dateofstart,p.employee_id, p.password, p.phone_no, p.e_mail
                        FROM non_profile AS np
                        INNER JOIN profile AS p
                        WHERE np.username = ? COLLATE utf8mb4_bin AND p.password = ? AND np.employee_id = p.employee_id """;
        try (PreparedStatement prepared_Statement  = connect.prepareStatement(sql_query)) {
            prepared_Statement.setString(1, username);
            prepared_Statement.setString(2, password);

            ResultSet result_Set = prepared_Statement.executeQuery();
            if (result_Set.next()) 
            {
                String role = result_Set .getString("role");
                if(role.equals("manager")) 
                {

                    return new Manager(
                            result_Set.getInt("employee_id"),
                            result_Set.getString("username"),
                            result_Set.getString("password"),
                            result_Set.getString("name"),
                            result_Set.getString("surname"),
                            result_Set.getString("phone_no"),
                            result_Set.getString("role"),
                            result_Set.getDate("dateofbirth"),
                            result_Set.getDate("dateofstart"),
                            result_Set.getString("e_mail")
                    );
                }else
                {
                        return new RegularEmployee(
                                result_Set.getInt("employee_id"),
                                result_Set.getString("username"),
                                result_Set.getString("password"),
                                result_Set.getString("name"),
                                result_Set.getString("surname"),
                                result_Set.getString("phone_no"),
                                result_Set.getString("role"),
                                result_Set.getDate("dateofbirth"),
                                result_Set.getDate("dateofstart"),
                                result_Set.getString("e_mail")
                        );
                }
            }
        }
        return null;
    }
    
    /**
     * Inserts a new employee's detail into the database.
     * @param employee the `Employee` object containing the employee's details.
     */
    public static void insertEmployee(Employee employee)
    {
        String profileSQL = "INSERT INTO profile (phone_no, password, e_mail, salt) VALUES (?, ?, ?, ?)";
        String nonProfileSQL = "INSERT INTO non_profile (username, role, name, surname, dateofbirth, dateofstart) VALUES (?, ?, ?, ?, ?, ?)";
        
        try
        {
            PreparedStatement preparedStatementProfile = connection.prepareStatement(profileSQL);
            preparedStatementProfile.setString(1, employee.getPhoneNo()); 
            preparedStatementProfile.setString(2, employee.getPassword()); 
            preparedStatementProfile.setString(3, employee.getEmail()); 
            preparedStatementProfile.setString(4, generateSalt());
            preparedStatementProfile.executeUpdate();
            
            PreparedStatement preparedStatementNonProfile = connection.prepareStatement(nonProfileSQL);
            preparedStatementNonProfile.setString(1, employee.getUsername()); 
            preparedStatementNonProfile.setString(2, employee.getRole()); 
            preparedStatementNonProfile.setString(3, employee.getName()); 
            preparedStatementNonProfile.setString(4, employee.getSurname()); 
            preparedStatementNonProfile.setDate(5, employee.getDateOfBirth());    
            preparedStatementNonProfile.setDate(6, employee.getDateOfStart()); 
            preparedStatementNonProfile.executeUpdate();
            
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }

    /**
     * Updates the username of an employee in the `non_profile` table.
     * If the username violates any constraint an exception is thrown by the database.
     * @param employeeId the ID of the employee whose username will be updated
     * @param username the new username to set.
     */
    public static void updateUsername(int employeeId, String username) {
        String sql = "UPDATE non_profile SET username = ? WHERE employee_id = ?";
        try {
            PreparedStatement prepared_Statement = connection.prepareStatement(sql);
            prepared_Statement.setString(1, username);
            prepared_Statement.setInt(2, employeeId);
            prepared_Statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the hashed password of an employee in the database.
     * If the password violates any constraints, an exception is thrown by the database.
     * @param employeeId the ID of the employee whose password will be updated
     * @param password the new password to set.
     */
    public static void updatePassword(int employeeId, String password)
    {
        String sql = "UPDATE profile SET password = ? WHERE employee_id = ?";
        try {
            PreparedStatement prepared_Statement = connection.prepareStatement(sql);
            prepared_Statement.setString(1, password);
            prepared_Statement.setInt(2, employeeId);
            prepared_Statement.executeUpdate();
           
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Retrieves the hashed password of an employee based on their username.
     * @param username the username of the employee
     * @return the hashed password as a string, or `null` if the username is not found.
     */
    public static String getPassword(String username)
    {
        Connection connect = connection();
        String sql = """
        SELECT p.password
        FROM profile AS p
        INNER JOIN non_profile AS np
        ON p.employee_id = np.employee_id
        WHERE np.username = ?
        """;

        try (PreparedStatement preparedStatement = connect.prepareStatement(sql)) {
            preparedStatement.setString(1, username); 

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getString("password"); 
            } else {
                System.out.println("No matching username found.");
                return null;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
    }
    
    /**
     * Updates the hashed password of an employee in the database.
     * @param employeeId the ID of the employee whose password will be updated.
     * @param password the new hashed password to set.
     * @param salt the salt value to store with the password.
     */
    public static void updatePassword(int employeeId, String password, String salt)
    {
        String sql = "UPDATE profile SET password = ? WHERE employee_id = ?";
        try {
            PreparedStatement prepared_Statement = connection.prepareStatement(sql);
            prepared_Statement.setString(1, password);
            prepared_Statement.setInt(2, employeeId);
            prepared_Statement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Retrieves the salt value associated with an employee's username.
     * @param username the username of the employee.
     * @return the salt value as a string, or `null` if the username is not found.
     */
    public static String getSalt(String username) {
        String sql = """
            SELECT p.salt
            FROM profile AS p
            INNER JOIN non_profile AS np
            ON p.employee_id = np.employee_id
            WHERE np.username = ?
            """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username); // Set the username parameter

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) { 
                    return resultSet.getString("salt"); 
                } else {
                    System.out.println("No salt found for username: " + username);
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Sets a new salt value for the given username in the database.
     * If there is no username, no changes will be made and a message will be printed.
     * @param username the username for which the salt will be updated.
     */
    public static void setSalt(String username) {
        String sql = """
            UPDATE profile AS p
            INNER JOIN non_profile AS np
            ON p.employee_id = np.employee_id
            SET p.salt = ?
            WHERE np.username = ?
            """;

        String salt = generateSalt(); 

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, salt); 
            preparedStatement.setString(2, username); 

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println();
            } else {
                System.out.println("No matching username found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Updates the role of an employee in the database.
     * If the employee ID does not exist, no changes will be made.
     * @param employeeId the ID of the employee whose role will be updated.
     * @param role the new role to set.
     */
    public static void updateRole(int employeeId, String role) {
        String sql = "UPDATE non_profile SET role = ? WHERE employee_id = ?";
        try {
            PreparedStatement prepared_Statement = connection.prepareStatement(sql);
            prepared_Statement.setString(1, role);
            prepared_Statement.setInt(2, employeeId);
            prepared_Statement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Updates the name of an employee in the database.
     * @param employeeId the ID of the employee whose name will be updated
     * @param name the new name to set
     */  
    public static void updateName(int employeeId, String name) {
        String sql = "UPDATE non_profile SET name = ? WHERE employee_id = ?";
        try {
            PreparedStatement prepared_Statement = connection.prepareStatement(sql);
            prepared_Statement.setString(1, name);
            prepared_Statement.setInt(2, employeeId);
            prepared_Statement.executeUpdate();
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Updates the surname of an employee in the database.
     * @param employeeId the ID of the employee whose surname will be updated.
     * @param surname the new surname to set.
     */
    public static void updateSurname(int employeeId, String surname) {
        String sql = "UPDATE non_profile SET surname = ? WHERE employee_id = ?";
        try {
            PreparedStatement prepared_Statement = connection.prepareStatement(sql);
            prepared_Statement.setString(1, surname);
            prepared_Statement.setInt(2, employeeId);
            prepared_Statement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Updates the phone number of an employee in the database.
     * @param employeeId the ID of the employee whose phone number will be updated.
     * @param phoneNo the new phone number to set.
     */
    public static void updatePhoneNumber(int employeeId, String phoneNo) {
        String sql = "UPDATE profile SET phone_no = ? WHERE employee_id = ?";
        try {
            PreparedStatement prepared_Statement = connection.prepareStatement(sql);
            prepared_Statement.setString(1, phoneNo);
            prepared_Statement.setInt(2, employeeId);
            prepared_Statement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Updates the email address of an employee in the database.
     * @param employeeId the ID of the employee whose email address will be updated.
     * @param email the new email address to set.
     */
    public static void updateEmail(int employeeId, String email) {
        String sql = "UPDATE profile SET e_mail = ? WHERE employee_id = ?";
        try {
            PreparedStatement prepared_Statement = connection.prepareStatement(sql);
            prepared_Statement.setString(1, email);
            prepared_Statement.setInt(2, employeeId);
            prepared_Statement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Updates the date of birth of an employee in the database.
     * @param employeeId the ID of the employee whose date of birth will be updated.
     * @param dateOfBirth the new date of birth to set.
     */
    public static void updateDateOfBirth(int employeeId, java.sql.Date dateOfBirth) {
        String sql = "UPDATE non_profile SET dateofbirth = ? WHERE employee_id = ?";
        try {
            PreparedStatement prepared_Statement = connection.prepareStatement(sql);
            prepared_Statement.setDate(1, dateOfBirth);
            prepared_Statement.setInt(2, employeeId);
            prepared_Statement.executeUpdate();
           
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Updates the start date of an employee in the database.
     * @param employeeId the ID of the employee whose start date will be updated.
     * @param dateOfStart the new start date to set.
     */
    public static void updateDateOfStart(int employeeId, java.sql.Date dateOfStart) {
        String sql = "UPDATE non_profile SET dateofstart = ? WHERE employee_id = ?";
        try {
            PreparedStatement prepared_Statement = connection.prepareStatement(sql);
            prepared_Statement.setDate(1, dateOfStart);
            prepared_Statement.setInt(2, employeeId);
            prepared_Statement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays all employees from the `non_profile` table.
     * If the query fails due to a database issue, an exception stack trace will be printed.
     */
    public static void displayAllEmployees()
    {
        String sql_String = "SELECT * FROM non_profile";
        
        try 
        {                                                   
            Statement statement = connection.createStatement();       
            ResultSet resultSet = statement.executeQuery(sql_String);

            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();     
            
            System.out.printf("Display All Employees:%n%n");

            for (int i = 1; i <= numberOfColumns; i++) 
            {
                System.out.printf("\u001B[34m%-15s\u001B[0m\t", metaData.getColumnName(i));
            }
            System.out.println();
            System.out.println("-".repeat(numberOfColumns * 17)); 
            
            while (resultSet.next()) 
            {
                for (int i = 1; i <= numberOfColumns; i++) 
                {
                    System.out.printf("\u001B[32m%-15s\u001B[0m\t", resultSet.getObject(i));
                }
                System.out.println();
            }
    
        }
        catch (SQLException sqlException) 
        {
            sqlException.printStackTrace();
        }
    } 
    
    /**
     * Displays all employees with a specific role from the database.
     * Displays all employees matching the given role by combining the non-profile and profile tables.
     * If no employees match the role, the output will be empty.
     * @param role the role of the employees to display.
     */
    public static void displayEmployeesWithRole(String role){
        String sql_query = """
                        SELECT np.employee_id, np.username, np.role, np.name, np.surname, np.dateofbirth, np.dateofstart,p.employee_id,p.phone_no, p.e_mail
                        FROM non_profile AS np
                        INNER JOIN profile AS p
                        ON np.employee_id = p.employee_id
                        WHERE np.role = ?""";
        try 
        {      
            PreparedStatement prepared_Statement = connection.prepareStatement(sql_query);
            prepared_Statement.setString(1, role);                  
            ResultSet resultSet = prepared_Statement.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();     
            
            System.out.printf("Displaying Employees with Role:%n%n");

            for (int i = 1; i <= numberOfColumns; i++) 
            {
                System.out.printf("\u001B[34m%-15s\u001B[0m\t", metaData.getColumnName(i));
            }
            System.out.println();
            System.out.println("-".repeat(numberOfColumns * 17)); 
            
            while (resultSet.next()) 
            {
                for (int i = 1; i <= numberOfColumns; i++) 
                {
                    System.out.printf("\u001B[32m%-15s\u001B[0m\t", resultSet.getObject(i));
                }
                System.out.println();
            }
        }
        catch (SQLException sqlException) 
        {
            sqlException.printStackTrace();
        }
    }
    
    /**
     * Displays details of an employee with a specific username.
     * If no employees match the username, the message will be printed.
     * @param username he username of the employee to display.
     */
    public static void displayEmployeeWithUsername(String username) {

        String sql_query = """
                SELECT np.employee_id, np.username, np.role, np.name, np.surname, 
                       np.dateofbirth, np.dateofstart, p.phone_no, p.e_mail
                FROM non_profile AS np
                INNER JOIN profile AS p
                ON np.employee_id = p.employee_id
                WHERE np.username = ? COLLATE utf8mb4_bin""";

        getUsernames();
        
        try (PreparedStatement prepared_Statement = connection.prepareStatement(sql_query)) {
            prepared_Statement.setString(1, username);                  
            ResultSet resultSet = prepared_Statement.executeQuery();
    
            if (!resultSet.isBeforeFirst()) {
                
                System.out.println("No data found.");
                return;
            }
    
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();     
            
            System.out.printf("Displaying Employee with Username: %s%n%n", username);
    
            for (int i = 1; i <= numberOfColumns; i++) {
                System.out.printf("\u001B[34m%-15s\u001B[0m\t", metaData.getColumnName(i)); 
            }
            System.out.println();
            System.out.println("-".repeat(numberOfColumns * 17)); 
    
            while (resultSet.next()) {
                for (int i = 1; i <= numberOfColumns; i++) {
                    System.out.printf("\u001B[32m%-15s\u001B[0m\t", resultSet.getObject(i));
                }
                System.out.println();
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    /**
    * Fetches and displays all usernames from the `non_profile` table.
    *Prints a list of usernames retrieved from the database.
    */
    public static void getUsernames(){
        String sql_query = "SELECT username FROM non_profile";

        try (Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql_query)) {

        System.out.println("\nAvailable Usernames:");
        System.out.println("---------------------");

        while (resultSet.next()) {
            System.out.println(resultSet.getString("username"));
        }
        System.out.println("---------------------");
        }
        catch (SQLException sqlException) 
        {
            sqlException.printStackTrace();
            System.err.println("Error retrieving usernames from the database.");
        }
    }
    
    /**
     * Deletes an employee from the database based on their ID.
     * Ensures consistency by first deleting data from the profile table and then from the non-profile table
     * @param employeeId the ID of the employee to delete.
     */
    public static void deleteEmployee(int employeeId) {

        String sqlDeleteProfile = "DELETE FROM profile WHERE employee_id = ?";
        String sqlDeleteNonProfile = "DELETE FROM non_profile WHERE employee_id = ?";
    
        try {

            PreparedStatement preparedStatementNonProfile = connection.prepareStatement(sqlDeleteNonProfile);
            preparedStatementNonProfile.setInt(1, employeeId);
            preparedStatementNonProfile.executeUpdate();
            preparedStatementNonProfile.close();
            
            PreparedStatement preparedStatementProfile = connection.prepareStatement(sqlDeleteProfile);
            preparedStatementProfile.setInt(1, employeeId);
            preparedStatementProfile.executeUpdate();
            preparedStatementProfile.close();

            System.out.println("Deleted Employee with ID: " + employeeId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Closes the active database connection.
     * @param connection the database connection to close.
     */
    public void closeConnection(Connection connection)
    {
        try
        {
            connection.close();
            System.out.println("closing connection with sql");
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
     /**
     * Retrieves employee details from database based on employee ID.
     * @param employee_id the unique ID of the employee.
     * @return An `Employee` object containing the employee's details, or `null` if the employee is not found.
     */
    public static Employee getEmployee(int employee_id)
    {
        String sql_query = """
        SELECT np.employee_id, np.username, np.role, np.name, np.surname, np.dateofbirth, np.dateofstart,
               p.password, p.phone_no, p.e_mail
        FROM non_profile AS np
        INNER JOIN profile AS p ON np.employee_id = p.employee_id
        WHERE np.employee_id = ?""";

        Employee employee=null;
        
        try {
            PreparedStatement prepared_Statement = connection.prepareStatement(sql_query);
            prepared_Statement.setInt(1, employee_id);
    
            ResultSet result_Set = prepared_Statement.executeQuery();
    
            if (result_Set.next()) {  
                String role = result_Set.getString("role");
                if ("Manager".equals(role)) {
                    employee = new Manager(result_Set.getInt("employee_id"),
                        result_Set.getString("username"),
                        result_Set.getString("password"),
                        result_Set.getString("name"),
                        result_Set.getString("surname"),
                        result_Set.getString("phone_no"),
                        result_Set.getString("role"),
                        result_Set.getDate("dateofbirth"),
                        result_Set.getDate("dateofstart"),
                        result_Set.getString("e_mail"));
                } else {
                    employee = new RegularEmployee(result_Set.getInt("employee_id"),
                        result_Set.getString("username"),
                        result_Set.getString("password"),
                        result_Set.getString("name"),
                        result_Set.getString("surname"),
                        result_Set.getString("phone_no"),
                        result_Set.getString("role"),
                        result_Set.getDate("dateofbirth"),
                        result_Set.getDate("dateofstart"),
                        result_Set.getString("e_mail"));
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    
        return employee;
    }

    /**
     * Updates a CSV file with data and imports the data into a database table.
     * @param csvFile the path to the CSV file to be updated and imported.
     * @param data an integer array containing the data to write into the CSV file.
     */
    public static void updateCSV(String csvFile, int[] data) {

        

        String columnName = new File(csvFile).getName().replace(".csv", "");

        String insertSQL = String.format("""
            INSERT INTO SortedArrays (`Index`, `%s`)
            VALUES (?, ?)
            ON DUPLICATE KEY UPDATE `%s` = VALUES(`%s`);
            """, columnName, columnName, columnName);

        try {
            File file = new File(csvFile);

            try (FileWriter writer = new FileWriter(file, false)) { 
                writer.append("Index,").append(columnName).append("\n");

                for (int i = 0; i < data.length; i++) {
                    writer.append(String.valueOf(i)).append(",").append(String.valueOf(data[i])).append("\n");
                }

                //System.out.println("Data successfully written to " + csvFile);
            }

            if (!file.canRead()) {
                System.err.println("Cannot read the file: " + csvFile);
                return;
            }

            //System.out.println("Reading file: " + csvFile);

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile));
                 PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

                String line = reader.readLine(); 
                
                if (line == null) {
                    System.err.println("File is empty: " + csvFile);
                    return;
                }

                connection.setAutoCommit(false); 

                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length < 2) {
                        System.err.println("Skipping line: " + line);
                        continue;
                    }

                    try {
                        int index = Integer.parseInt(parts[0].trim());
                        int value = Integer.parseInt(parts[1].trim());

                        preparedStatement.setInt(1, index);
                        preparedStatement.setInt(2, value);
                        preparedStatement.addBatch();
                    } catch (NumberFormatException e) {
                        System.err.println("Skipping line due to invalid number format: " + line);
                    }
                }

                preparedStatement.executeBatch();
                connection.commit(); 

                System.out.println("Data from " + csvFile + " successfully imported.");
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Database error while processing " + csvFile + ": " + e.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error reading file " + csvFile + ": " + e.getMessage());
        }
    }

    /**
     * Clears the specified table by truncating it. This operation deletes all rows
     * from the table without removing the table structure.
     *
     * @param SortedArrays The name of the table to be cleared. This should be a valid 
     *                     table name in the database.
     */
    public static void clearTable(String SortedArrays) {
        String truncateSQL = "TRUNCATE TABLE " + SortedArrays;

        try (PreparedStatement statement = connection.prepareStatement(truncateSQL)) {
            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error clearing table " + SortedArrays + ": " + e.getMessage());
        }
    }
}
