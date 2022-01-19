/**
 * GU_QA_1524 (08.06.2021)
 * Java 1. Homework #4
 *
 * @author Nurgalina Lyaysan
 * @version 08.01.2022 - 09.01.2021
 *
 */

import java.util.Scanner;
import java.util.Random;

class TicTacToe {
    final char SIGN_X = 'x';
    final char SIGN_O = 'o';
    final char SIGN_EMPTY = '.';
    char[][]table;
    Scanner sc;
    Random random;

    public static void main(String[]args) {
        new TicTacToe().game();
    }

    TicTacToe() {
        table = new char[3][3];
        sc = new Scanner(System.in);
        random = new Random();
    }

    void game() {
        initTable();
        printTable();
        while (true) {
            turnHuman();
            if (isWin(SIGN_X)) {
                System.out.println("YOU WON!");
                break;
            }
            if (isTableFull()) {
                System.out.println("Sorry,DRAW...");
                break;
            }
            turnAi();
            printTable();
            if (isWin(SIGN_O)) {
                System.out.println("AI WON!");
                break;
            }
            if (isTableFull()) {
                System.out.println("Sorry,DRAW...");
                break;
            }
        }
        printTable();
    }

    void initTable() {
        for (int y = 0; y < table.length; y++) {
            for (int x = 0; x < table.length; x++) {
                table[y][x] = SIGN_EMPTY;
            }
        }
    }

    void printTable() {
        for (int y = 0; y < table.length; y++) {
            for (int x = 0; x < table.length; x++) {
                System.out.print(table[y][x] + " ");
            }
            System.out.println();
        }
    }

    void turnHuman() {
        int x, y;
        do {
            System.out.print("Enter x y [1..3]: ");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y)); //цикл повторится, если ввели неправильное значение, знак !, знак инверсии, меняет ложь на правду
        table[y][x] = SIGN_X;
    }

    void turnAi() {
        int x, y;
        do {
            x = random.nextInt(3);
            y = random.nextInt(3);
        } while (!isCellValid(x, y));
        table[y][x] = SIGN_O;
    }

    boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x > 2 || y > 2) { // координаты, условия
            return false;
        }
        return table[y][x] == SIGN_EMPTY;
    }

    boolean isWin(char ch){
        for (int i = 0; i < 3; i++) {
            if ((table[i][0] == ch && table[i][1] == ch && table[i][2] == ch) ||
                (table[0][i] == ch && table[1][i] == ch && table[2][i] == ch))
                return true;
        }
            //диагональ
            if (table[0][0] == ch && table[1][1] == ch && table[2][2] == ch) {
                return true;
            }
            if (table[2][0] == ch && table[1][1] == ch && table[0][2] == ch) {
                return true;
            }
            return false; //победы нет
        }


    boolean isTableFull() { //заполнена ли таблица вся, проверяем
        for (int y = 0; y < table.length; y++) {
            for (int x = 0; x < table.length; x++) {
                if (table[y][x] == SIGN_EMPTY) { //есть ли пустая ячейка
                    return false; //таблица не заполнена
                }
            }
        }
        return true; //таблица заполнена
    }

}
