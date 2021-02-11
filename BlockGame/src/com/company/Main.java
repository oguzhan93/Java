package com.company;
import java.util.*;
public class Main {

    public static void main(String[] args) {
        info();
        game();
    }
    public static void info() {
        System.out.println("There are 7x5 = 35 cell to put number");
        System.out.println("You must put numbers to the table starting from the top");
        System.out.println("If there is a same number in adjacent cell (left to right, right to left or top to bottom) those number add up");
        System.out.println("There is 5 line to put number so you must choose numbers between 1-5 to put number");
        System.out.println("For example;");
    }
    public static void game() {
        int [][] arr = new int[7][5];
        int [] numbers = new int [] {2, 4, 8, 16, 32, 64};
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        int point = 0;
        print(arr);
        System.out.println(" 1 2 3 4 5");
        while (!isFull(arr)) {
            int num = random.nextInt(numbers.length);
            System.out.println("Which column would you like to put "+numbers[num]+"?");
            int move;
            do {
                move = scanner.nextInt();
                if (move < 1 || move > 5 || !isMoveValid(move, arr))
                    System.out.println("Invalid number!\nEnter a new number!");
            }
            while (move < 1 || move > 5 || !isMoveValid(move, arr));
            put(move, numbers[num], arr);
            point += editArr(arr, move);
            print(arr);
        }
        System.out.println("Here is your score: "+point);
    }

    public static boolean isFull(int[][] arr) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                if (arr[i][j] == 0)
                    return false;
            }
        }
        return true;
    }

    public static void put(int move, int num, int arr[][]) {
        for (int j = 0; j < 7; j++) {
            if (arr[j][move - 1] == 0) {
                arr[j][move - 1] = num;
                break;
            }
        }

    }

    public static int editArr(int[][] arr, int move) {
        int line = -1, point = 0;
        for (int i = 0 ; i < 6; i++) {
            if (arr[i + 1][move - 1] == 0) {
                line = i;
                break;
            }
        }
        if (line == -1)
            line = 6;

        boolean right = right(arr, move, line);
        boolean left = left(arr, move, line);
        boolean up = up(arr, move, line);
        if (right) {
            arr[line][move - 1] *= 2;
            point += arr[line][move];
            arr[line][move] = 0;
        }
        if (left) {
            arr[line][move - 1] *= 2;
            point += arr[line][move - 2];
            arr[line][move - 2] = 0;
        }
        if (up) {
            arr[line][move - 1] *= 2;
            point += arr[line - 1][move - 1];
            arr[line - 1][move - 1] = 0;
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                if (arr[j][i] == 0 && arr[j + 1][i] != 0) {
                    arr[j][i] = arr[j + 1][i];
                    arr[j + 1][i] = 0;
                }
            }
        }
        point += reArrangeArr(arr);
        return point;
    }

    public static int reArrangeArr(int[][] arr){
        int point = 0;
        while(isEditable(arr)) {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 4; j++) {
                    if (arr[i][j] != 0 && arr[i][j] == arr[i][j + 1]) {
                        arr[i][j] = arr[i][j] * 2;
                        point += arr[i][j + 1];
                        arr[i][j + 1] = 0;
                    } else if (arr[i][j] != 0 && arr[i][j] == arr[i + 1][j]) {
                        arr[i][j] = arr[i][j] * 2;
                        point += arr[i + 1][j];
                        arr[i + 1][j] = 0;
                    }
                }
            }
        }
        return point;
    }

    public static boolean isEditable(int[][] arr) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (arr[i][j] != 0 && (arr[i][j] == arr[i][j + 1] || arr[i][j] == arr[i + 1][j]))
                    return true;
            }
        }
        return false;
    }

    public static boolean right(int[][] arr, int move, int line){
        if (move == 5)
            return false;
        else
            return 	arr[line][move - 1] == arr[line][move] ? true : false;
    }

    public static boolean left(int[][] arr, int move, int line){
        if (move == 1)
            return false;
        else
            return arr[line][move - 1] == arr[line][move - 2] ? true : false;
    }

    public static boolean up(int[][] arr, int move, int line){
        if (line == 0)
            return false;
        else
            return arr[line][move - 1] == arr[line - 1][move - 1] ? true : false;
    }

    public static boolean isMoveValid(int move, int [][] arr) {
        return arr[6][move - 1] == 0 ? true : false;
    }

    public static void print(int[][] arr) {
        int max = arr[0][0];
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 5; y++) {
                if (arr[x][y] > max)
                    max = arr[x][y];
            }
        }
        String str = String.valueOf(max);
        max = str.length();
        for (int i = 0; i < 7; i++) {
            System.out.print("|");
            for (int j = 0; j < 5; j++) {
                if (arr[i][j] == 0){
                    for (int index = 0; index < max; index++)
                        System.out.print(" ");
                    System.out.print("|");
                }
                else {
                    String temp = String.valueOf(arr[i][j]);
                    int len = temp.length();
                    len = max - len;
                    System.out.print(arr[i][j]);
                    for(int index = 0; index <len; index++)
                        System.out.print(" ");
                    System.out.print("|");

                }
            }
            System.out.println();
        }
    }
}
