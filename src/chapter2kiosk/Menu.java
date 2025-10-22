package chapter2kiosk;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Menu {
    private String name;
    private List<MenuItem> menuItems;
    Menu(String name){
        this.name = name;
        this.menuItems = new ArrayList<>();
    }

    public void setMenuItem(MenuItem menuItem){
        menuItems.add(menuItem);
    }

    /**
     * 메뉴를 출력해주는 함수.
     * 별도의 매개변수를 필요로 하지 않으며 forEach메서드를 이용하여 호출하였음.
     * AtomicInteger는 해당 출력문에 인덱스를 출력하기 위해 사용함.
     */
    public void showMenuInfo(){
        AtomicInteger i = new AtomicInteger(0);
        this.menuItems.forEach(menuItem -> System.out.println(i.incrementAndGet()+ ". " + menuItem.getName()+
                (menuItem.getName().length() < 14 ?"\t\t":"\t") +
                "| W " + menuItem.getPrice() + " | "  + menuItem.getComment()));
        System.out.println("0. 뒤로가기");
    }
    public String getMenuName(){
        return this.name;
    }
    public void setMenuName(String name){
        this.name = name;
        System.out.print("\"" +this.name+"\"으로" + "메뉴명 변경완료.");
    }
    /**
     * 리스트를 반환해주기 위해 사용하는 함수. 아마 몇 번 안쓸지도..?
     * @return 메뉴리스트
     */
    public List<MenuItem> getMenuList(){
        return this.menuItems;
    }
}