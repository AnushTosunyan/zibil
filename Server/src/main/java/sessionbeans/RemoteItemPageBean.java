package main.java.sessionbeans;

import main.java.ejb.Bid;
import main.java.ejb.User;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;

@Stateful
@Remote(ItemPage.class)
public class RemoteItemPageBean implements ItemPage{
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    String SQL;

    @Override
    public ArrayList<Bid> getBids(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        int id = (int) session.getAttribute("itemId");

        ArrayList<Bid> bids = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306?useSSL=false","root","pass");
            statement = connection.createStatement();
            System.out.println(id);
            SQL = "SELECT * FROM new_schema.bids WHERE itemId = ?";
            PreparedStatement pst = connection.prepareStatement(SQL);
            pst.setInt(1, id);
            resultSet = pst.executeQuery();

            while (resultSet.next()){
                System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAA");
                bids.add(new Bid(resultSet.getInt("amount"), resultSet.getInt("userId"),id));
            }
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Exception Occurred in the process :" + ex);
        }
        System.out.println(bids.size());
        return bids;
    }

    @Override
    public void makeBid() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        int userId = ((User) session.getAttribute("user")).getUserId();
        int itemId = (int) session.getAttribute("itemId");
        int amount = (int) session.getAttribute("amount");
        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306?useSSL=false","root","pass");
            statement = connection.createStatement();
            if (connection != null) {
                // change schema, table and parameter names
                String sql = "INSERT INTO new_schema.bids(amount, userId, itemId) VALUES(?,?,?)";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, amount);
                ps.setInt(2, userId);
                ps.setInt(3, itemId);
                ps.executeUpdate();
                System.out.println("Bid Added Successfully");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exception Occurred in the process :" + ex);
        }

    }
}
