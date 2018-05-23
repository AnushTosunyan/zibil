package main.java.sessionbeans;

import main.java.ejb.Bid;
import main.java.ejb.User;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@SessionScoped
@ManagedBean(name="ItemPageBean")

public class ItemPageBean {
    @EJB
    private  ItemPage itemPage;

    private int itemId;
    private User user;
    private int amount;

    public void setAmount(int amount) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        session.setAttribute("amount", amount);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    private ArrayList<Bid> bidList = new ArrayList<>();

    public void setBidList(ArrayList<Bid> bidList) {
        this.bidList = bidList;
    }

    public ArrayList<Bid> getBidList() {
        System.out.println(itemId);
        bidList = itemPage.getBids();
        return bidList;
    }

    public void makeBid(){
        itemPage.makeBid();
    }
}
