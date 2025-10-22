package chapter1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Chapter1Main {
    Scanner scanner;
    boolean continueFlag;
    int select_calculator;
    Calculator cal;
    ArithmeticCalculator<Double> calc;

    public Chapter1Main(){              //생성자 호출
        this.scanner = new Scanner(System.in);
        this.continueFlag = true;
        this.cal = new Calculator();
        this.calc = new ArithmeticCalculator<>();
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        String numCheck;
        boolean checkContinue = true;
        while (checkContinue) {
            System.out.print("사용할 계산기 선택.\n" +
//                    "0. 코드카타\n" +
                    "1. Level1\n" +
                    "2. Level2\n" +
                    "3. Level3\n" +
//                    "4. (이후 추가 예정. 아직 미완성)\n" +
                    "exit. 종료.\n" +
                    "->");
            numCheck = scanner.nextLine();
            if (numCheck.equals("exit")) {
                checkContinue = false;
            } else if (numCheck.length() > 1 ||
                    !(numCheck.charAt(0) >= '1' && numCheck.charAt(0) <= '3')) {
                System.out.println("잘못된 입력값입니다.");
            } else {
                select_calculator = numCheck.charAt(0) - '0';
                switch (select_calculator) {
//                    case 0:
//                        new Codekata().executeSolution();
//                        break;
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
    }

    public void level1Calculator() {
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

    public void level2Calculator() {

        boolean loop = true;
        while (loop) {
            loop = cal.execute();
        }
    }

    public void level3Calculator() {
        double num1;
        double num2;
        boolean exitFlag = true;
        boolean exitMenuFlag;
        String selectMenu = "";
        Scanner sc = new Scanner(System.in);
        Scanner scOp =  new Scanner(System.in);
        Scanner scStr = new Scanner(System.in);

        while(exitFlag) {
            try {
                exitMenuFlag = true;
                System.out.print("첫번째 수 입력->");
                num1 = sc.nextDouble();
                System.out.print("두번째 수 입력->");
                num2 = sc.nextDouble();
                System.out.print("사칙연산 기호 입력(+,-,*,/ 이 4개만 입력) ->");
                calc.calculate(num1, num2, scOp.nextLine());

                while(exitMenuFlag) {
                    System.out.print("다음으로 넘어가시려면 아무 키나 입력해주세요.");
                    scStr.nextLine();
                    System.out.println("\n\n\n\n");
                    System.out.println("exit. 종료\n" +
                            "get. 조회\n" +
                            "set. 수정\n" +
                            "remove. 삭제\n" +
                            "select. 선택값보다 큰 값 조회\n" +
                            "계산을 계속하시려면 아무 값이나 입력.");
                    selectMenu = scStr.nextLine().trim();
                    if (selectMenu.equals("exit"))
                        break;
                    exitMenuFlag = calc.execute(selectMenu);
                }
            }catch (InputMismatchException e) {
                System.out.println("입력값이 잘못되었습니다. 다시 입력해주세용");
                sc = new Scanner(System.in);
            }
            if (selectMenu.equals("exit"))
                exitFlag = false;
        }
    }

}
