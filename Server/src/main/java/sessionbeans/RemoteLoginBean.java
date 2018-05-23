package main.java.sessionbeans;

import main.java.ejb.User;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.sql.*;

@Stateless
public class RemoteLoginBean implements Login {
    private String dbpassword;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private String SQL;

    private String dbuserName;
    private String dbemail;
    private String dbname;
    private int dbId;

    private User currUser;


    public void dbData(String userName) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // change db name and password accordingly
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","pass");
            statement = connection.createStatement();
            // change schema, table and property name accordingly
            SQL = "SELECT * FROM new_schema.users WHERE user_name = ?";
            PreparedStatement pst = connection.prepareStatement(SQL);
            pst.setString(1, userName);
            resultSet = pst.executeQuery();
            if (resultSet.next()) {
                dbuserName = resultSet.getString("user_name"); // here too
                dbpassword = resultSet.getString("password"); // and here
                dbemail = resultSet.getString("email");
                dbname = resultSet.getString("name");
                dbId = resultSet.getInt("id");
            } else {
                dbuserName = null;
                dbpassword = null;
            }
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Exception Occurred in the process :" + ex);
        }
    }

    @Override
    public String checkValidUser(String userName, String password) {
        dbData(userName);

        if (dbuserName == null || dbpassword == null) {
            return "failure";
        }
        if (userName.equalsIgnoreCase(dbuserName)) {
            if(password.equals(dbpassword)){
                currUser = new User( dbuserName, dbemail, dbpassword, dbname);
                return "itemList";
            }
            return"failure";
        } else {
            return "failure";
        }
    }

    @Override
    public User getUser() {
        return currUser;
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
}
