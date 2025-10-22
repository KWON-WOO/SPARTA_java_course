import chapter1.Chapter1Main;
import chapter2kiosk.Chapter2Main;

import java.util.Scanner;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    public static void main(String[] args) {
        //TIP 캐럿을 강조 표시된 텍스트에 놓고 <shortcut actionId="ShowIntentionActions"/>을(를) 누르면
        // IntelliJ IDEA이(가) 수정을 제안하는 것을 확인할 수 있습니다.
        Chapter1Main chapter1 = new Chapter1Main();
        Chapter2Main chapter2 = new Chapter2Main();
        Scanner sc = new Scanner(System.in);
        int selectChapter;
        System.out.print(
                """
                        1. 계산기
                        2. 키오스크
                        챕터를 선택해주세요.->""");
        selectChapter = sc.nextInt();
        switch (selectChapter) {
            case 1:
                chapter1.execute();
                break;
            case 2:
                chapter2.execute();
                break;
            default:
                System.out.println("입력값이 잘못되었습니다");
        }
    }
}