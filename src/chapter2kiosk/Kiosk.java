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

                // 음식 카테고리를 선택하였을 시.
                if (choiceCategory <= categoriesSize) {
                    printMenu(choiceCategory);
                    selectItem = sc.nextInt();

                    System.out.print("\n 선택한 메뉴: ");
                    this.category.getMenuItem(selectItem - 1).getItemInfo();
                    System.out.print("""
                            위 메뉴를 장바구니에 추가하시겠습니까?
                            1. 확인\t\t2. 취소
                            ->""");
                    choice = sc.nextInt();

                    // 장바구니에 추가할지 여부를 검사하는 부분. 만약 출력해준 선택지 이외의 값이 들어오면 Exception 발생.
                    if (choice == 1) {
                        this.cartItems.addItem(this.category.getMenuItem(selectItem - 1));
                        System.out.println(this.category.getMenuItem(selectItem - 1).getName() + "(이)가 장바구니에 추가되었습니다.");
                    } else if (choice == 2) {
                        System.out.print("취소되었습니다.");
                    } else {
                        throw new IndexOutOfBoundsException();
                    }
                    // 음식 카테고리를 벗어난 입력값이 주어졌을 시 실행되는 코드.
                } else {
                    //장바구니 값이 없거나 선택 가능한 항목보다 입력값이 클 시 InputMisMatchException 발생시켜줌.
                    if (inputCategoryIndexExceptionFlag(choiceCategory, categoriesSize))
                        throw new InputMismatchException();

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
            System.out.printf("[ ORDER MENU ]\n%d. Orders \n%d. Cancel\n",
                    i.incrementAndGet(), i.incrementAndGet());
        }
    }

    /**
     * 카테고리 안에 들은 메뉴들을 순차적으로 출력해줌.
     *
     * @param choiceCategory 선택한 카테고리가 담김
     */
    public void printMenu(int choiceCategory) {
        this.category = this.categories.get(choiceCategory - 1);
        this.category.printItemsInfo();
    }

    /**
     * 카테고리를 선택하는 인풋값이 올바르게 들어왔는지 체크해주는 메서드.
     * 장바구니 출력 가능 여부를 확인함.
     * 인풋 값이 장바구니에 물건이 없는데 카테고리 인덱스의 최대값을 초과하거나
     * 장바구니를 포함한 인덱스를 초과한 입력값이 들어올 시 true 반환.
     *
     * @param choiceCategory 선택한 카테고리 입력값을 지님.
     * @return boolean Exception 발생 여부를 반환함.
     */
    public boolean inputCategoryIndexExceptionFlag(int choiceCategory, int size) {
        if (choiceCategory > size + 2 || !cartItems.checkCartisNotEmpty())
            return true;
        else return false;
    }

    /**
     * 장바구니와 할인 기능을 담당하는 메서드.
     *
     * @param sc 스캐너이긴 한데... 객체 생성을 줄이고 싶어서 넣어봤다.
     */
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
