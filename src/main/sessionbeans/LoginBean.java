package main.sessionbeans;

import java.sql.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean(name="LoginBean")


public class LoginBean {
    private String userName;
    private String password;
    private String dbuserName;

    private String dbpassword;
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    String SQL;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDbuserName() {
        return dbuserName;
    }

    public void setDbuserName(String dbuserName) {
        this.dbuserName = dbuserName;
    }

    public String getDbpassword() {
        return dbpassword;
    }

    public void setDbpassword(String dbpassword) {
        this.dbpassword = dbpassword;
    }

    public void dbData() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // change db name and password accordingly
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MySQL","root","***");
            statement = connection.createStatement();
            // change schema, table and property name accordingly
            SQL = "SELECT * FROM new_schema.users WHERE user_name = ?";
            PreparedStatement pst = connection.prepareStatement(SQL);
            pst.setString(1, userName);
            resultSet = pst.executeQuery();
            if (resultSet.next()) {
                dbuserName = resultSet.getString("user_name"); // here too
                dbpassword = resultSet.getString("password"); // and here
            } else {
                dbuserName = null;
                dbpassword = null;
            }
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Exception Occured in the process :" + ex);
        }
    }

    public String checkValidUser() {
        dbData();

        if (dbuserName == null || dbpassword == null) {
            return "failure";
        }
        if (userName.equalsIgnoreCase(dbuserName)) {
                return password.equals(dbpassword) ? "success" : "failure";
        } else {
            return "failure";
        }
    }
}
