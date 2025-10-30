package chapter2kiosk;

public class Desserts extends MenuItem{
    private String name;
    private double price;
    private String comment;

    Desserts(String name, double price, String comment){
        this.name = name;
        this.price = price;
        this.comment = comment;
    }
    @Override
    public String getName(){return this.name;}

    @Override
    public double getPrice(){ return this.price; }

    @Override
    public String getComment() { return ""; }
}
