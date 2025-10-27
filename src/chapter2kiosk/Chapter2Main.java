package chapter2kiosk;

import java.util.Scanner;

public class Chapter2Main {
    Kiosk kiosk;
    Menu burgers;
    Menu drinks;
    Menu desserts;

    public Chapter2Main(){
        this.kiosk = new Kiosk();
        this.burgers = new Menu("Burgers");
        this.drinks = new Menu("Drinks");
        this.desserts = new Menu("Desserts");
    }

    /**
     * 키오스크 프로젝트에서 메인처럼 활용될 메서드.
     * 메인의 요구사항을 이곳에 반영함.
     */
    public void execute() {

        burgers.setMenuItem(new MenuItem("PineappleBurger", 8.9, "패티와 파인애플의 절묘한 만남!"));
        burgers.setMenuItem(new MenuItem("DurianBurger", 9.9, "버거와 두리안의 끔찍한 만남!"));
        burgers.setMenuItem(new MenuItem("KiwiBurger", 8.8, "파인애플 들어간 올라간 패티도 맛있는데 키위라고 맛 없겠어?"));

        drinks.setMenuItem(new MenuItem("Dr.pepper", 2.5, "체리향과 함께하는 청량감!"));
        drinks.setMenuItem(new MenuItem("MountainDew", 2.5, "자동차 워셔액 아닙니다ㅡㅡ"));
        drinks.setMenuItem(new MenuItem("RedBull", 3.5, "이게.. 버거집에..?"));

        desserts.setMenuItem(new MenuItem("CheeseCake", 7.0, "이건 맛 없을 수가 없다! 꾸덕하고 고소한 치즈케익!"));
        desserts.setMenuItem(new MenuItem("Macaron", 2.5, "설탕에 절여져볼까?"));
        desserts.setMenuItem(new MenuItem("CaramelPudding", 5.8, "버거집에 이런 게 있는 게 맞나? 맛있겠다.."));

        kiosk.addCategory(burgers);
        kiosk.addCategory(drinks);
        kiosk.addCategory(desserts);

        kiosk.start();
    }
}
