package main.java.sessionbeans;

import main.java.ejb.Item;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import java.sql.*;
import java.util.ArrayList;

@Stateful
@Remote(Items.class)
public class RemoteItemsBean implements Items{
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    String SQL;

    @Override
    public ArrayList<Item> getItems() {

        ArrayList<Item> items = new ArrayList<>();
        Item item;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // change db name and password accordingly
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MySQL","root","LoveRocK96");
            statement = connection.createStatement();
            // change schema, table and property name accordingly
            SQL = "SELECT * FROM new_schema.items";
            PreparedStatement pst = connection.prepareStatement(SQL);
            resultSet = pst.executeQuery();
            while (resultSet.next()){
                item = new Item(resultSet.getString("name"), resultSet.getString("description"), resultSet.getInt("price"));
                items.add(item);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Exception Occurred in the process :" + ex);
        }
        return items;
    }

}
