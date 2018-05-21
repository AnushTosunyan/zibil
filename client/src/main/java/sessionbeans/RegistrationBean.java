package main.java.sessionbeans;

import main.java.ejb.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.*;

@SessionScoped
@ManagedBean(name="RegistrationBean")

public class RegistrationBean{
    private String name;
    private String userName;
    private String password;
    private String email;
    private User currUser;
    private String dbuserName;

    Connection connection;
    Statement statement;
    ResultSet resultSet;
    String SQL;

    public void setName(String name) {
        this.name = name;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getDbuserName() {
        return dbuserName;
    }

    public void setDbuserName(String dbuserName) {
        this.dbuserName = dbuserName;
    }

    public void dbData() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MySQL","root","LoveRocK96");
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

    public String checkValidUser() {

        dbData();

        if(userName.equalsIgnoreCase(dbuserName)) {
            return "failure";
        } else {
            try {

                Class.forName("com.mysql.jdbc.Driver");
                // change db name and password accorrdingly
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MySQL","root","LoveRocK96");
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
                    System.out.println("Data Added Successfully");
                    currUser = new User(userName, email, password, name);
                    return "itemPage";
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Exception Occurred in the process :" + ex);
            }

            return "failure";
        }
    }
}

