package chapter2kiosk;

import java.util.Scanner;

public class Kiosk {
    Kiosk(){}
    public void start() {
        Scanner sc = new Scanner(System.in);
        // 스캐너 선언
        // 반복문 시작

        // List와 Menu 클래스 활용하여 상위 카테고리 메뉴 출력

        // 숫자 입력 받기

        // 입력 받은 숫자가 올바르다면 인덱스로 활용하여 List에 접근하기
        // List<Menu>에 인덱스로 접근하면 Menu만 추출할 수 있겠죠?

        // Menu가 가진 List<MenuItem>을 반복문을 활용하여 햄버거 메뉴 출력

        // 숫자 입력 받기
        // 입력 받은 숫자가 올바르다면 인덱스로 활용해서 Menu가 가지고 있는 List<MenuItem>에 접근하기
        // menu.getMenuItems().get(i); 같은 형식으로 하나씩 들어가서 얻어와야 합니다.
    }
}
