package chapter2kiosk;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class Kiosk {

    Scanner sc = new Scanner(System.in);
    private List<Menu> categories;
    private Menu menu;
    private CartItems cartItems = new CartItems();

    int choiceCategory;
    int categoriesSize;
    int choice;
    boolean cartFlag = false;

    //생성자에서 필요한 데이터를 받아오도록 수정.
    Kiosk(ArrayList<Menu> categories) {
        //메뉴 이름을 인자값으로 리스트 안에 메뉴객체 추가.
        this.categories = categories;
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
    public boolean start() {
        categoriesSize = categories.size();
        cartFlag = cartItems.checkCartisNotEmpty();

        printMainMenu(cartFlag);

        choiceCategory = inputValidRange(0, (cartFlag ? categoriesSize + 2 : categoriesSize));

        if (choiceCategory == 0) return false;

        if (choiceCategory <= categoriesSize) {
            addCartItem();
        } else if (choiceCategory == categoriesSize + 1) {
            order();
        } else {
            cartItems.clear();
        }
        return true;
    }

//        int choiceCategory;
//        int selectItem = 0;
//        int choice;
//        int categoriesSize = categories.size();
//        boolean cartFlag = false;


//        while (true) {
//            cartFlag = cartItems.checkCartisNotEmpty();
//
//            //메인메뉴 출력. 주문선택까지 가능.
//            printMainMenu(cartFlag);
//
//            try {
//                choiceCategory = sc.nextInt();
//                // 반복문 while의 종료 조건.
//                if (choiceCategory == 0) return false;
//
//                // 음식 카테고리를 선택하였을 시.
//                if (choiceCategory <= categoriesSize) {
//                    printMenuName(choiceCategory);
//
//                    printMenuItems(choiceCategory);
//                    selectItem = sc.nextInt();
//
//                    System.out.print("\n 선택한 메뉴: ");
//                    this.category.getMenuItem(selectItem - 1).getItemInfo();
//                    System.out.print("""
//                            위 메뉴를 장바구니에 추가하시겠습니까?
//                            1. 확인\t\t2. 취소
//                            ->""");
//                    choice = sc.nextInt();
//
//                    // 장바구니에 추가할지 여부를 검사하는 부분. 만약 출력해준 선택지 이외의 값이 들어오면 Exception 발생.
//                    if (choice == 1) {
//                        this.cartItems.addItem(this.category.getMenuItem(selectItem - 1));
//                        System.out.println(this.category.getMenuItem(selectItem - 1).getName() + "(이)가 장바구니에 추가되었습니다.");
//                    } else if (choice == 2) {
//                        System.out.print("취소되었습니다.");
//                    } else {
//                        throw new IndexOutOfBoundsException();
//                    }
//                    // 음식 카테고리를 벗어난 입력값이 주어졌을 시 실행되는 코드.
//                } else {
//                    //장바구니 값이 없거나 선택 가능한 항목보다 입력값이 클 시 InputMisMatchException 발생시켜줌.
//                    if (inputCategoryIndexExceptionFlag(choiceCategory, categoriesSize))
//                        throw new InputMismatchException();
//
//                    if (choiceCategory == categoriesSize + 1) {
//                        order();
//                    } else {
//                        this.cartItems.clearCart();
//                    }
//                }
//                /** 혹시라도 다른 예외처리가 발생할 수도 있기에 구분해둠.*/
//            } catch (IndexOutOfBoundsException e) {
//                System.out.print("잘못된 입력값입니다. 다시 입력하시려면 아무 키나 눌러주세요.");
//                sc.nextLine(); //버퍼를 비워주기 위한 Input
//            } catch (InputMismatchException e) {
//                System.out.print("잘못된 입력값입니다. 다시 입력하시려면 아무 키나 눌러주세요.");
//                sc.nextLine(); //버퍼를 비워주기 위한 Input
//                sc.nextLine();
//            }
//        }

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
     * 예외처리를 포함한 인풋으로 최대값과 최소값을 지정하여 사용한다.
     * @param min 유효값의 최소값.
     * @param max 유효값의 최대값.
     * @return
     */
    public int inputValidRange(int min, int max) {
        while (true) {
            try {
                int checkNum = sc.nextInt();

                if (min <= checkNum && checkNum <= max) {
                    return checkNum;
                } else {
                    throw new IndexOutOfBoundsException();
                }
            } catch (InputMismatchException e) {
                System.out.print("잘못된 입력값입니다. 다시 입력해주세요->");
                sc.next();
            } catch (IndexOutOfBoundsException e) {
                System.out.print("잘못된 범위입니다. 다시 입력해주세요->");
            }
        }
    }

    public <T extends MenuItem>void printMenuName(MenuInterface<T> menu) {
        String name = menu.getMenuName();

        System.out.printf("[ %s ]\n",
                name.equals("Orders") ? "Orders" : name.toUpperCase() + " MENU");
    }

    /**메뉴 안에 들은 아이템들을 순차적으로 출력해줌.
     * @param menu 출력할 아이템을 담은 메뉴 객체
     */
    public <T extends MenuItem>void printItemsInfo(MenuInterface<T> menu) {
        menu.printItemsInfo();
    }

    /**
     * 장바구니와 할인 기능을 담당하는 메서드.
     */
    public void addCartItem() {
        menu = categories.get(choiceCategory - 1);

        printMenuName(menu);
        printItemsInfo(menu);

        int choiceItem = inputValidRange(0, menu.getSize());

        MenuItem item = menu.getItem(choiceItem - 1);

        System.out.print("\n 선택한 메뉴: ");
        item.getItemInfo();
        System.out.print("""
                    위 메뉴를 장바구니에 추가하시겠습니까?
                    1. 확인\t\t2. 취소
                    ->""");

        choice = inputValidRange(1, 2);

        if (choice == 1) {
            this.cartItems.addItem(item);
            System.out.println(item.getName() + "(이)가 장바구니에 추가되었습니다.");
        } else if (choice == 2) {
            System.out.print("취소되었습니다.");
        }
    }

    public void order() {
        double price = 0;

        printMenuName(cartItems);
        printItemsInfo(cartItems);

        System.out.println("[ Total ]\nW " + cartItems.getTotalPrice() +
                "\n\n1. 주문\t\t2. 메뉴판");
        choice = inputValidRange(1, 2);
        if (choice == 1) {
            System.out.print("""
                    할인 정보를 입력해주세요.
                    1. 국가유공자 : 10%
                    2. 군인      : 5%
                    3. 학생      : 3%
                    4. 일반      : 0%
                    ->""");
            int num = inputValidRange(1, 4);
            for (Discount disc : Discount.values()) {
                if (disc.getSymbol() == num) {
                    price = disc.apply(cartItems.getTotalPrice());
                    System.out.printf("주문이 완료되었습니다. 금액은 W %-5.2f 입니다.", price);
                    cartItems.clear();
                }
            }
        }
    }
}
