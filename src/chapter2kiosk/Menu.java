package chapter2kiosk;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Menu {
    private List<MenuItem> menuItems;

    Menu(){
        this.menuItems = new ArrayList<MenuItem>();
        this.menuItems.add(new MenuItem("PineappleBurger", 8.9, "패티와 파인애플의 절묘한 만남!"));
        this.menuItems.add(new MenuItem("DurianBurger", 9.9, "버거와 두리안의 끔찍한 만남!"));
        this.menuItems.add(new MenuItem("KiwiBurger", 8.8, "파인애플 들어간 올라간 패티도 맛있는데 키위라고 맛 없겠어?"));
    }

    /**
     * 메뉴를 출력해주는 함수.
     * 별도의 매개변수를 필요로 하지 않으며 forEach메서드를 이용하여 호출하였음.
     * AtomicInteger는 해당 출력문에 인덱스를 출력하기 위해 사용함.
     */
    public void showMenuList(){
        AtomicInteger i = new AtomicInteger(0);
        this.menuItems.forEach(menuItem -> {
            System.out.println(i.incrementAndGet()+ ". " + menuItem.getItemName()+
                    "\t| W " + menuItem.getItemPrice() + " | "  + menuItem.getItemComment());
        });
        System.out.println("0. 뒤로가기");
    }

    /**
     * 리스트를 반환해주기 위해 사용하는 함수. 아마 몇 번 안쓸지도..?
     * @return 메뉴리스트
     */
    public List<MenuItem> getMenuList(){
        return this.menuItems;
    }
}
