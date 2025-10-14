package codekata;

import java.util.Scanner;

public class Codekata {
    public static void executeSolution() {
        int selectSolution;
        Scanner solutionSc = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);
         do{
             System.out.print("솔루션 선택->");
            selectSolution = solutionSc.nextInt();
            switch (selectSolution) {
                case 0:
                    break;
                case 1:
                    System.out.println(solution1(sc.nextInt(), sc.nextInt()));
                    break;
                default:
            }
        }while (selectSolution != 0);
    }
    public static int solution1(int num1, int num2) {
        return num1 - num2;
    }
}
