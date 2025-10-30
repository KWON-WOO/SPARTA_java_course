package chapter2kiosk;

public class MenuItem {
    String name;
    double price;
    String comment;

    MenuItem(String name, double price, String comment) {
        this.name = name;
        this.price = price;
        this.comment = comment;
    }
    public void printItemInfo() {
        System.out.println(this.getName()+
                (this.getName().length() < 14 ?"\t\t":"\t") +
                "| W " + this.getPrice() + " | "  + this.getComment());
    }

    public String getName(){
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getComment(){
        return comment;
    }
}