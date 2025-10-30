package chapter2kiosk;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Menu<T extends MenuItem> implements MenuInterface<T>{
    private String name;
    private List<T> menuItems;
    //메뉴의 생성자
    public Menu(String name){
        this.name = name;
        this.menuItems = new ArrayList<>();
    }
    @Override
    public void addItem(T menuItem){
        menuItems.add(menuItem);
    }

    /**
     * 메뉴를 출력해주는 함수.
     * 별도의 매개변수를 필요로 하지 않으며 forEach메서드를 이용하여 호출하였음.
     * AtomicInteger는 해당 출력문에 인덱스를 출력하기 위해 사용함.
     */

    public void printMenuName(){
        System.out.println("[ "+this.name.toUpperCase()+" MENU ]");
    }

    public void printItemsInfo(){
        AtomicInteger i = new AtomicInteger(0);
        this.menuItems.forEach(menuItem ->
                System.out.printf("%2d. %s | W %-5s | %s\n",
                i.incrementAndGet(),
                setItemNameWidth(menuItem.getName(),15),
                menuItem.getPrice(),
                menuItem.getComment()
        ));
        System.out.print(" 0. 뒤로가기 \n->");
    }

    public String getMenuName(){
        return this.name;
    }

    public String setItemNameWidth(String str, int width){ // 패딩 추가
        int padding = width - str.length();
        return str + " ".repeat(padding);
    }

    public int getSize(){
        return this.menuItems.size();
    }

    @Override
    public void clear() {

    }

    public MenuItem getItem(int index){
        return this.menuItems.get(index);
    }

    /**
     * 리스트를 반환해주기 위해 사용하는 함수. 아마 몇 번 안쓸지도..?
     * @return 메뉴리스트
     */
    public List<T> getMenuList(){
        return this.menuItems;
    }
}