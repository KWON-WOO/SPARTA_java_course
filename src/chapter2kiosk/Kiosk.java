package chapter2kiosk;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class Kiosk {
    /**
     * 장바구니에서 결제 시 할인률을 지정해주는 enum클래스
     */
    public enum Discount {
        NATIONAL_MERIT_RECIPIENT(1, price -> price * 0.9),
        SOLDIER(2, price -> price * 0.95),
        STUDENT(3, price -> price * 0.97),
        NORMAL(4, price -> price);

        private final int symbol;
        private final Function<Double, Double> calc;
//        private final Function<T, R> function;

        Discount(int symbol) {
            this.symbol = symbol;
            this.calc = null;
        }

        Discount(int symbol, Function<Double, Double> calc) {
            this.symbol = symbol;
            this.calc = calc;
        }

        public Double apply(Double number1) {
            return calc.apply(number1);
        }

        public int getSymbol() {
            return this.symbol;
        }
    }

    private List<Menu> categories;
    private CartItems cartItems;
    private Menu category;

    Kiosk() {
        //장바구니
        this.cartItems = new CartItems();
        //메뉴 이름을 인자값으로 리스트 안에 메뉴객체 추가.
        this.categories = new ArrayList<>();
    }

    /**
     * 메인에서 갖고 올 category를 리스트에 담는 메서드
     * get(0) -> Burgers
     * get(1) -> Drinks
     * get(2) -> Desserts
     * Menu class 안에 List<MenuItem>이 있음
     */
    public void addCategory(Menu category) {
        this.categories.add(category);
    }

    /**
     * 키오스크 프로젝트가 실제로 실행되는 부분.
     * 종료조건(0 입력) 이전까지 무한루프
     */
    public void start() {
        Scanner sc = new Scanner(System.in);
        int choiceCategory;
        int selectItem = 0;
        int choice;
        int categoriesSize = categories.size();
        boolean cartFlag = false;
        while (true) {
            cartFlag = cartItems.checkCartisNotEmpty();

            //메인메뉴 출력. 주문선택까지 가능.
            printMainMenu(cartFlag);

            try {
                choiceCategory = sc.nextInt();
                // 반복문 while의 종료 조건.
                if (choiceCategory == 0) break;
                //인풋값이 잘못 들어왔을 때 InputMisMatchException 발생시켜줌.
                if (inputExceptionFlag(choiceCategory, categoriesSize))
                    throw new InputMismatchException();

                if (choiceCategory <= categoriesSize) {
                    printMenu(choiceCategory);
                    selectItem = sc.nextInt();

//                System.out.println(this.category.getSize() + "\t"  + selectItem);
                    if (this.category.getSize() < selectItem) {
                        throw new InputMismatchException();
                    }

                    System.out.print("\n 선택한 메뉴: ");
                    this.category.getMenuItem(selectItem - 1).getItemInfo();
                    System.out.print("""
                            위 메뉴를 장바구니에 추가하시겠습니까?
                            1. 확인\t\t2. 취소
                            ->""");
                    choice = sc.nextInt();
                    if (choice == 1) {
                        this.cartItems.addItem(this.category.getMenuItem(selectItem - 1));
                        System.out.println(this.category.getMenuItem(selectItem - 1).getName() + "(이)가 장바구니에 추가되었습니다.");
                    } else if (choice == 2) {
                        System.out.print("취소되었습니다.");
                    } else {
                        throw new IndexOutOfBoundsException();
                    }
                } else {
                    if (choiceCategory == categoriesSize + 1) {
                        order(sc);
                    } else {
                        this.cartItems.clearCart();
                    }
                }
                /** 혹시라도 다른 예외처리가 발생할 수도 있기에 구분해둠.*/
            } catch (IndexOutOfBoundsException e) {
                System.out.print("잘못된 입력값입니다. 다시 입력하시려면 아무 키나 눌러주세요.");
                sc.nextLine(); //버퍼를 비워주기 위한 Input
            } catch (InputMismatchException e) {
                System.out.print("잘못된 입력값입니다. 다시 입력하시려면 아무 키나 눌러주세요.");
                sc.nextLine(); //버퍼를 비워주기 위한 Input
                sc.nextLine();
            }
        }

    }

    /**
     * 메뉴 출력문. 장바구니에 추가한 메뉴가 있을 시 주문 메뉴가 출력됨.
     * 카테고리 확장 가능성을 감안해 오더 출력문 수정.
     */
    public void printMainMenu(Boolean cartFlag) {
        AtomicInteger i = new AtomicInteger(0);
        System.out.println("\n[ MAIN MENU ]");
        this.categories.forEach(menu ->
                System.out.println(i.incrementAndGet() + ". " + menu.getMenuName()));
        System.out.println("0. Exit");

        //장바구니가 비어있지 않을 때 order menu 추가.
        if (cartFlag) {
            System.out.printf("\n[ ORDER MENU ]\n %d. Orders \n%d. Cancel",
                    i.incrementAndGet(),i.incrementAndGet());
        }
    }

    public void printMenu(int choiceCategory) {
        this.category = this.categories.get(choiceCategory - 1);
        this.category.printItemsInfo();
    }

    /**
     * 인풋값이 올바르게 들어왔는지 체크해주는 메서드.
     * @param choiceCategory
     * @return boolean
     */
    public boolean inputExceptionFlag(int choiceCategory, int size){
        if (choiceCategory > size + 2 ||
                (!cartItems.checkCartisNotEmpty() && choiceCategory > size))
            return true;
        else return false;
    }

    public void order(Scanner sc) {
        int choice;
        double price = 0;
        Discount discount;
        System.out.println("\n아래와 같이 주문 하시겠습니까?\n");
        System.out.println("[ Orders ]");
        cartItems.printItemList();

        System.out.println("[ Total ]\nW " + cartItems.getTotalPrice() +
                "\n\n1. 주문\t\t2. 메뉴판");
        choice = sc.nextInt();
        if (choice == 1) {
            System.out.print("""
                    할인 정보를 입력해주세요.
                    1. 국가유공자 : 10%
                    2. 군인      : 5%
                    3. 학생      : 3%
                    4. 일반      : 0%
                    ->""");
            int num = sc.nextInt();
            for (Discount disc : Discount.values()) {
                if (disc.getSymbol() == num) {
                    try {
                        price = disc.apply(cartItems.getTotalPrice());
                        System.out.printf("주문이 완료되었습니다. 금액은 W %-5.2f 입니다.", price);
                        cartItems.clearCart();
                    } catch (Exception e) {
                        System.out.println("입력값이 잘못되었습니다. 다시 입력해주세요");
                        sc.nextLine();
                    }
                }
            }
        }
    }
}
