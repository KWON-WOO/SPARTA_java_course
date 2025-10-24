package chapter2kiosk;

import java.util.ArrayList;
import java.util.List;

public class CartItems {
    private List<CartItem> menuItems = new ArrayList<>();

    CartItems(){
    }
    public void addItem(MenuItem menuItem) {
        boolean duplicateCheck = true;
        for (CartItem cartItem : menuItems) {
            if (cartItem.getName().equals(menuItem.getName())) {
                cartItem.addQuantity(1);
                duplicateCheck = false;
                break;
            }
        }
        if (duplicateCheck)menuItems.add(new CartItem(menuItem));
    }
    public void printItemList(){
        for (CartItem menuItem : this.menuItems) {
            System.out.printf("Order %2d | %s | W %-5s | %s\n",
                    menuItem.getQuantity(),
                    setMenuNameWidth(menuItem.getName(), 15),
                    menuItem.getPrice(),
                    menuItem.getComment()
            );
        }
    }
    public double getTotalPrice(){
        double totalPrice = 0;
        for (CartItem item: menuItems){
            totalPrice += item.getPrice() * item.getQuantity();
        }
        return totalPrice;
    }
    public String setMenuNameWidth(String str, int width){ // 패딩 추가
//        int length = checkStringSize(str);
        int padding = width - str.length();
        return str + " ".repeat(padding);
    }
    public boolean checkCartisNotEmpty(){
        return !menuItems.isEmpty();
    }
    public void clearCart(){
        this.menuItems.clear();
    }
}
