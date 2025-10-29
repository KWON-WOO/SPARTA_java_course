package chapter2kiosk;

import java.util.ArrayList;
import java.util.List;

public class CartItems {
    private List<CartItem> menuItems = new ArrayList<>();

    CartItems(){}

    /** 장바구니에 아이템을 추가하는 메서드
     * 만약 중복상품이 들어왔을 경우 주문수량에 1을 더해줌.
     * @param menuItem 주문 상품.
     */
    public void addItem(MenuItem menuItem) {
        boolean duplicateCheck = true;
        for (CartItem cartItem : menuItems) {
            if (cartItem.getName().equals(menuItem.getName())) {
                cartItem.addQuantity(1);
                duplicateCheck = false;
                break;
            }
        }
        if (duplicateCheck)menuItems.add(new CartItem(menuItem));
    }

    /**
     *  장바구니에 담긴 아이템들을 출력해주는 출력문.
     *  아래와 같은 양식으로 출력됨.
     *  주문 갯수 | 주문한 상품 | 가격 | 상품 설명
     */
    public void printItemList(){
        for (CartItem menuItem : this.menuItems) {
            System.out.printf("Order %2d | %s | W %-5s | %s\n",
                    menuItem.getQuantity(),
                    setMenuNameWidth(menuItem.getName(), 15),
                    menuItem.getPrice(),
                    menuItem.getComment()
            );
        }
    }

    /** 물건들의 총합 가격을 띄워줌.
     * forEach 장바구니에 담긴 모든 상품의 가격을 계산해주는 함수.
     * @return 가격 총합
     */
    public double getTotalPrice(){
        double totalPrice = 0;
        for (CartItem item: menuItems){
            totalPrice += item.getPrice() * item.getQuantity();
        }
        return totalPrice;
    }

    /** 가격의 이름 길이가 모두 다르기 때문에 정렬해주기 위한 함수.
     * 지정해준 width보다 문자열의 길이가 짧을 시 공백으로 채워줌
     * @param str 상품의 이름
     * @param width 콘솔에 띄워줄 네임 칸의 길이
     * @return  상품 이름 + 패딩
     */
    public String setMenuNameWidth(String str, int width){ // 패딩 추가
        int padding = width - str.length();
        return str + " ".repeat(padding);
    }

    /** 장바구니의 값의 유무를 판별하는 메서드.
     * @return 장바구니에 물건이 담겨있으면 true 없으면 false를 반환.
     */
    public boolean checkCartisNotEmpty(){
        return !menuItems.isEmpty();
    }

    /** 장바구니를 비워주는 메서드.
     * 주문을 완료했거나 주문내역을 삭제를 선택 할 시 호출.
     */
    public void clearCart(){
        this.menuItems.clear();
    }
}
