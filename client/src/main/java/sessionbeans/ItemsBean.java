package main.java.sessionbeans;

import main.java.ejb.Item;
import main.java.ejb.User;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@SessionScoped
@ManagedBean(name="ItemsBean")
public class ItemsBean {

    @EJB
    Items items;

    private String result;

    private ArrayList<Item> itemList = new ArrayList<>();

    public ArrayList<Item> getItemList() {
        itemList = items.getItems();
        return itemList;
    }

    public String getResult() {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        result = "itemPage";
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setItemList(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    private String username;

    public String getUsername() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        username = ((User) session.getAttribute("user")).getUsername();
        return username;
    }

    public String getItemName(){
        return "name";
    }


    public String getDescription(){
        return "desc";
    }


    public int getprice(){
        return 0;
    }
    public String getTimeRemaining(){
        return "000000000000";
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
