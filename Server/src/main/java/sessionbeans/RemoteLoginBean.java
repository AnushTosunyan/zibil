package main.java.sessionbeans;

import main.java.ejb.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
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
                currUser = new User(dbuserName, dbemail, dbpassword, dbname);
                return "itemPage";
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
}
