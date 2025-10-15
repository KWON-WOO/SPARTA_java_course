package codekata;

import java.util.Scanner;

public class Codekata {
    public void executeSolution() {
        int selectSolution;
        Scanner solutionSc = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("솔루션 선택->");
            selectSolution = solutionSc.nextInt();
            switch (selectSolution) {
                case 0:
                    break;
                case 1:
                    System.out.println(solution1(sc.nextInt(), sc.nextInt()));
                    break;
                case 2:
                    System.out.println(solution2(sc.nextInt(), sc.nextInt()));
                    break;
                case 3:
                    System.out.println(solution3(sc.nextInt(), sc.nextInt()));
                    break;
                case 4:
                    System.out.println(solution4(sc.nextInt()));
                default:
            }
        } while (selectSolution != 0);
    }

    public int solution1(int num1, int num2) {   //두 수의 차 구하기
        return num1 - num2;
    }

    public int solution2(int num1, int num2) {   // 두 수의 곱 구하기
        return num1 * num2;
    }

    public int solution3(int num1, int num2) {
        return num1 / num2;
    }

    public int solution4(int age) {
        int year = 2022;
        int answer = (year + 1) - age;
        return answer;
    }
}
