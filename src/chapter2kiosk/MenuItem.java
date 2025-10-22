package chapter2kiosk;

public class MenuItem {
    private String name;
    private double price;
    private String comment;

    MenuItem(){
    }

    MenuItem(String name, double price, String comment){
        this.name = name;
        this.price = price;
        this.comment = comment;
    }

    public String getItemName(){
        return this.name;
    }

    public double getItemPrice(){
        return this.price;
    }

    public String getItemComment(){
        return this.comment;
    }
}
