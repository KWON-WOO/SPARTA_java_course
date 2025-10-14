import chapter1.Calculator;
import chapter1.MyCalculator;
import codekata.Codekata;

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
        while (checkContinue) {
            System.out.print("사용할 계산기 선택.\n" +
                    "0. 코드카타\n" +
                    "1. Level1\n" +
                    "2. Level2\n" +
                    "3. Level3\n" +
                    "4. (이후 추가 예정. 아직 미완성)\n" +
                    "exit. 종료.\n" +
                    "->");
            numCheck = scanner.nextLine();
            if (numCheck.equals("exit")) {
                checkContinue = false;
            }else if (numCheck.length() > 1 ||
                    !(numCheck.charAt(0) >= '1' && numCheck.charAt(0) <= '3')) {
                System.out.println("잘못된 입력값입니다.");
            } else {
                select_calculator = numCheck.charAt(0) - '0';
                System.out.println(select_calculator);
                switch (select_calculator) {
                    case 0:
                        new Codekata().executeSolution();
                        break;
                    case 1:
                        level1Calculator();
                        break;
                    case 2:
                        level2Calculator();
                        break;
                    case 3:
                        level3Calculator();
                        break;
                    default:
                        System.out.println("다시 입력해주세요.");

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

    public static void level1Calculator() {
        int num1;
        int num2;
        String num1Str;
        String operatorStr;
        char operator;
        boolean checkExit;
        checkExit = true;
        do {                //반복 실행을 위한 반복문. 1회는 실행해야하기에 do-while문 이용.
            Scanner scanner = new Scanner(System.in);
            System.out.print("첫번째 정수 입력(끝내고 싶을 시 exit 입력) ->");
            num1Str = scanner.nextLine();      //종료조건 검사도 병행하기 위해 String으로 값을 받아옴.
            System.out.println(num1Str);
            if (num1Str.equals("exit"))
                checkExit = false;      //do-while문을 종료하기 위한 boolean.
            else {
                num1 = Integer.parseInt(num1Str);       //받아온 데이터 정수값으로 변환.
                System.out.println("두번째 정수 입력 ->");
                num2 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("사칙연산 기호 입력(+,-,*,/ 이 4개만 입력) ->");
                operatorStr = scanner.nextLine();
                operator = operatorStr.charAt(0);
                switch (operator) {
                    case '+':
                        System.out.println("결과값 ->" + (num1 + num2));
                        break;
                    case '-':
                        System.out.println("결과값 ->" + (num1 - num2));
                        break;
                    case '*':
                        System.out.println("결과값 ->" + (num1 * num2));
                        break;
                    case '/':
                        if (num2 == 0) {
                            System.out.println("2번째 입력값이 0입니다. 처음부터 다시 입력해주세요.");
                        } else {
                            System.out.println("결과값 ->" + (num1 / num2));
                        }
                        break;
                    default:
                        System.out.println("잘못된 값을 입력 받았습니다. 다시 입력해주세요");
                        break;
                }
            }
        } while (checkExit);
    }

    public static void level2Calculator() {
        Calculator cal = new Calculator();
        boolean loop = true;
        while (loop) {
            loop = cal.excute();
        }
    }

    public static void level3Calculator() {
        System.out.println("미완성");
    }
}