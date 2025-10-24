package chapter2kiosk;

public class MenuItem {
    private String name;
    private double price;
    private String comment;

    MenuItem(String name, double price, String comment){
        this.name = name;
        this.price = price;
        this.comment = comment;
    }
    public void getItemInfo(){
        System.out.println(this.name+
                (this.name.length() < 14 ?"\t\t":"\t") +
                "| W " + this.price + " | "  + this.comment);
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
