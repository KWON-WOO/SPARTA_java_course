package chapter2kiosk;

import java.util.Scanner;

public class Chapter2Main {
    Kiosk kiosk;
    public Chapter2Main(){
        this.kiosk = new Kiosk();
    }
    public void execute() {
        kiosk.start();
    }
}
