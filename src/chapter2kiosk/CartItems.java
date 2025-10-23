package chapter2kiosk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartItems {
    private HashMap<String, MenuItem> menuItemList;
    private double totalPrice;

    CartItems(){
        menuItemList = new HashMap<>();
        totalPrice = 0;
    }
    public void addItem(MenuItem menuItem){
        menuItemList.put(menuItem.getName(), menuItem);
    }

    public boolean checkCartisEmpty(){
        if (this.menuItemList.isEmpty()){
            return false;
        } else return true;
    }
}
