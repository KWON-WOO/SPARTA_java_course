package chapter2kiosk;

import java.util.Scanner;

public class Chapter2Main {
    int selectLevel;
    Scanner sc;
    Kiosk kiosk;
    Menu menu;
    public Chapter2Main(){
        this.menu = new Menu();
        this.kiosk = new Kiosk();
        this.sc = new Scanner(System.in);
    }
    public void execute() {
        menu.showMenuList();
        System.out.println(menu.getMenuList());
    }

}
