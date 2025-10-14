import chapter1.MyCalculator;

import java.util.Scanner;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    public static void main(String[] args) {
        //TIP 캐럿을 강조 표시된 텍스트에 놓고 <shortcut actionId="ShowIntentionActions"/>을(를) 누르면
        // IntelliJ IDEA이(가) 수정을 제안하는 것을 확인할 수 있습니다.
        Scanner scanner = new Scanner(System.in);
        String numCheck;
        boolean checkContinue = true;
        int select_calculator;
        while(checkContinue){
            System.out.println("사용할 계산기 선택.\n" +
                    "1. Level1\n" +
                    "2. Level2\n" +
                    "3. Level3\n" +
                    "4. (이후 추가 예정. 아직 미완성)" +
                    "exit. 종료.");
            numCheck = scanner.nextLine();
            if (numCheck.length() > 1 ||
                    !(numCheck.charAt(0) >= '1' && numCheck.charAt(0) <= '3')) {
                System.out.println("잘못된 입력값입니다.");
            } else if (numCheck.equals("exit")) {
                checkContinue = false;
            }else {
                select_calculator = numCheck.charAt(0) - '0';
                switch (select_calculator) {
                    case '1': level1Calculator(); break;
                    case '2': level2Calculator(); break;
                    case '3': level3Calculator(); break;
                }
            }
        }

//        MyCalculator calculator = new MyCalculator();
//        int num1 = 11340;
////        System.out.println(~num1);
//        char num = '9';
//        String str = "";
////        int number = (int)num;
////        System.out.println( num > '0');
////        System.out.println(calculator.sum(num1++,11340));
//        System.out.println(calculator.calculate("28+33*311"));
    }
    public static void level1Calculator(){

    }
    public static void level2Calculator(){

    }
    public static void level3Calculator(){

    }
}