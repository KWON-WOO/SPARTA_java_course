package chapter2kiosk;

import java.util.Scanner;

public class Chapter2Main {
    int selectLevel;
    Scanner sc;
    Kiosk kiosk;
    Menu menu;
    public Chapter2Main(){
        menu = new Menu();
        kiosk = new Kiosk();
        sc = new Scanner(System.in);
    }
    public void execute() {
        menu.showMenuList();
        System.out.println(menu.getMenuList());
    }

}
