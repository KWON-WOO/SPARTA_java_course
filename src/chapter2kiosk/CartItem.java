package chapter2kiosk;

public class CartItem extends MenuItem{
    private int quantity = 1;
//    public CartItem(String name, double price, String comment){
//        super(name, price, comment);
//    }
    public CartItem(MenuItem menuItem){
        super(menuItem.getName(), menuItem.getPrice(), menuItem.getComment());
    }
    public int getQuantity(){
        return this.quantity;
    }
    public void addQuantity(int num){
        this.quantity += num;
    }
}
