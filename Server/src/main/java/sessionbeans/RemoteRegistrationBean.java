package main.java.sessionbeans;

import main.java.ejb.User;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.sql.*;

@Stateless
@Remote(Register.class)
public class RemoteRegistrationBean implements Register {
    private User currUser;
    private String dbuserName;

    Connection connection;
    Statement statement;
    ResultSet resultSet;
    String SQL;

    @Override
    public User getUser() {
        return currUser;
    }

    @Override
    public String checkValidUser(String userName, String password, String email, String name) {

        dbData(userName);

        if(userName.equalsIgnoreCase(dbuserName)) {
            return "failure";
        } else {
            try {

                Class.forName("com.mysql.jdbc.Driver");
                // change db name and password accorrdingly
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","pass");
                statement = connection.createStatement();
                if (connection != null) {
                    // change schema, table and parameter names
                    String sql = "INSERT INTO new_schema.users(name, user_name, password, email) VALUES(?,?,?,?)";
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setString(1, name);
                    ps.setString(2, userName);
                    ps.setString(3, password);
                    ps.setString(4, email);
                    ps.executeUpdate();

                     sql = "SELECT id new_schema.users WHERE user_name = ?";
                     ps = connection.prepareStatement(sql);
                     ps.setString(1, userName);
                     resultSet = ps.executeQuery();
                     int id = 0;
                     if (resultSet.next()) {
                         id = resultSet.getInt("id");
                     }
                    System.out.println("Data Added Successfully");
                    currUser = new User( userName, email, password, name);
                    return "itemList";
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Exception Occurred in the process :" + ex);
            }
            return "failure";
        }
    }

    public void dbData(String userName) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","pass");
            statement = connection.createStatement();
            // if you have a different schema and table name change them accordingly, should also change user_name property if they are different
            SQL = "SELECT * FROM new_schema.users WHERE user_name = ?";
            PreparedStatement pst = connection.prepareStatement(SQL);
            pst.setString(1, userName);
            resultSet = pst.executeQuery();
            if (resultSet.next()) {
                dbuserName = resultSet.getString("user_name"); // here too

            } else {
                dbuserName = null;
            }
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Exception Occured in the process :" + ex);
        }
    }


    @Override
    public User getFirstUser() {
        User user = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","pass");
            statement = connection.createStatement();
            // if you have a different schema and table name change them accordingly, should also change user_name property if they are different
            SQL = "SELECT * FROM new_schema.users ";
            PreparedStatement pst = connection.prepareStatement(SQL);
            resultSet = pst.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getString("user_name"), resultSet.getString("email"), resultSet.getString("password"),resultSet.getString("name") ); // here too
            } else {
                user = null;
            }
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Exception Occured in the process :" + ex);
        }

        return user;
    }

    @Override
    public void deleteUser(String username) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","pass");
            statement = connection.createStatement();
            // if you have a different schema and table name change them accordingly, should also change user_name property if they are different
            SQL = "DELETE FROM new_schema.users WHERE user_name = ?";
            PreparedStatement pst = connection.prepareStatement(SQL);
            pst.setString(1, username);
            pst.executeUpdate();
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Exception Occured in the process :" + ex);
        }
    }
}
