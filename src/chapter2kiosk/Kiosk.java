package chapter2kiosk;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Kiosk {
    private List<Menu> categories;
    private Menu category;
    Kiosk() {
        //메뉴 이름을 인자값으로 리스트 안에 메뉴객체 추가.
        this.categories = new ArrayList<>();
        this.categories.add(new Menu("Burgers"));
        this.categories.add(new Menu("Drinks"));
        this.categories.add(new Menu("Desserts"));

        /**
         * get(0) -> Burgers
         * get(1) -> Drinks
         * get(2) -> Desserts
         * Menu class 안에 List<MenuItem>이 있음
         */
        //Burgers
        this.categories.get(0).setMenuItem(new MenuItem("PineappleBurger", 8.9, "패티와 파인애플의 절묘한 만남!"));
        this.categories.get(0).setMenuItem(new MenuItem("DurianBurger", 9.9, "버거와 두리안의 끔찍한 만남!"));
        this.categories.get(0).setMenuItem(new MenuItem("KiwiBurger", 8.8, "파인애플 들어간 올라간 패티도 맛있는데 키위라고 맛 없겠어?"));
        //Drinks
        this.categories.get(1).setMenuItem(new MenuItem("Dr.pepper", 2.5, "체리향과 함께하는 청량감!"));
        this.categories.get(1).setMenuItem(new MenuItem("아침햇살", 3.0, "다들 잊고 사셨죠? 추억의 쌀음료!"));
        this.categories.get(1).setMenuItem(new MenuItem("솔의 눈", 2.5, "이거.. 먹는 사람 있을까..?"));
        //Desserts
        this.categories.get(2).setMenuItem(new MenuItem("CheeseCake", 7.0, "이건 맛 없을 수가 없다! 꾸덕하고 고소한 치즈케익!"));
        this.categories.get(2).setMenuItem(new MenuItem("Macaron", 2.5, "설탕에 절여져볼까?"));
        this.categories.get(2).setMenuItem(new MenuItem("CaramelPudding", 8.8, "버거집에 이런 게 있는 게 맞나? 맛있겠다.."));
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        int choiceCategory = 0;

        while (true) {
            AtomicInteger i = new AtomicInteger(0);
            System.out.println("[ Main Menu ]");
            categories.forEach(
                    menu -> System.out.println(i.incrementAndGet() + ". " + menu.getMenuName()));
            System.out.println("0. Exit");
            try {
                choiceCategory = sc.nextInt();
                if (choiceCategory > 3) throw new Exception();
            } catch (Exception e) {
                System.out.print("잘못된 입력값입니다. 다시 입력하시려면 아무 키나 눌러주세요.");
                sc.nextLine(); //버퍼를 비워주기 위한 Input
                sc.nextLine();
            }
            switch (choiceCategory) {
                case 0:
                    break;
                case 1:
                    categories.get(choiceCategory - 1).showMenuInfo();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
            }
        }
        // 입력 받은 숫자가 올바르다면 인덱스로 활용하여 List에 접근하기
        // List<Menu>에 인덱스로 접근하면 Menu만 추출할 수 있겠죠?

        // Menu가 가진 List<MenuItem>을 반복문을 활용하여 햄버거 메뉴 출력

        // 숫자 입력 받기
        // 입력 받은 숫자가 올바르다면 인덱스로 활용해서 Menu가 가지고 있는 List<MenuItem>에 접근하기
        // menu.getMenuItems().get(i); 같은 형식으로 하나씩 들어가서 얻어와야 합니다.
    }
}
