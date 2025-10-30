package chapter2kiosk;

interface MenuInterface<T> {

    void addItem(T item); // T 타입의 아이템을 추가해주는 메서드

    void printItemsInfo(); // 갖고 있는 T 타입의 데이터를 출력해주는 메서드

    String getMenuName(); //

    String setItemNameWidth(String str, int width);

    int getSize();

    void clear();
}
