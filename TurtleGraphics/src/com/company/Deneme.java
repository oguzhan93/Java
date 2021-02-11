package com.company;
import java.util.Scanner;


public class Deneme {
    public static void main(String[] args) {
        String [][] deneme = new String[20][20];
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int j = scanner.nextInt();
        for(;x<=j;x++) {
            deneme[x][x] = "*";
        }
        for(;x<j;x++){
            if(deneme[x][x]==null){
                System.out.print("-");
            }
            else{
                System.out.print("*");
            }
        }
    }
}