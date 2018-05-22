package main.java.ejb;

import javax.persistence.*;

@Entity
@Table(name = "bids")
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bidId")
    private int bidId;

    @Column(name = "amount")
    private int amount;

    @Column(name = "userId")
    private int userId;

    @Column(name = "itemId")
    private int itemId;

    public Bid(int amount, int userId, int itemId) {
        this.amount = amount;
        this.userId = userId;
        this.itemId = itemId;
    }

    public int getBidId() {
        return bidId;
    }

    public int getAmount() {
        return amount;
    }

    public int getUserId() {
        return userId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setBidId(int bidId) {
        this.bidId = bidId;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
