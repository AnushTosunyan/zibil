package main.java.sessionbeans;

import main.java.ejb.Item;

import javax.ejb.Remote;
import java.util.ArrayList;

@Remote
public interface Items {
    ArrayList<Item> getItems();
}
