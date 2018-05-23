package main.java.sessionbeans;

import main.java.ejb.Bid;

import javax.ejb.Remote;
import java.util.ArrayList;

@Remote(ItemPage.class)
public interface ItemPage {
    ArrayList<Bid> getBids();
    void makeBid();
}
