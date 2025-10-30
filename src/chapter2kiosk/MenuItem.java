package chapter2kiosk;

public abstract class MenuItem {
    public void getItemInfo() {
        System.out.println(this.getName()+
                (this.getName().length() < 14 ?"\t\t":"\t") +
                "| W " + this.getPrice() + " | "  + this.getComment());
    }

    public abstract String getName();

    public abstract double getPrice();

    public abstract String getComment();
}
