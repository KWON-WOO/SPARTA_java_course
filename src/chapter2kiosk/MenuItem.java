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

    public String getName(){
        return this.name;
    }

    public double getPrice(){
        return this.price;
    }

    public String getComment(){
        return this.comment;
    }
}
