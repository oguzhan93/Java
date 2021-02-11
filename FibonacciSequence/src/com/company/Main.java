package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int I = 0 , J = 1 ;
        System.out.println("Please Enter How Many Digits You Want");
        System.out.println("\n");
        int DIGIT = scanner.nextInt();
        if(DIGIT<=0){
            System.out.println("Please Enter A Positive Number!\n");
        }
        else if(DIGIT == 1){
            System.out.println(I);
        }
        else if (DIGIT == 2){
            System.out.println(I+"-"+J);
        }
        else{
            System.out.print(0+"-"+1);
            for(int x = 2 ;x<DIGIT ; x++){
                int sum = I + J ;
                System.out.print("-"+sum);
                I = J ;
                J = sum ;
            }
        }

    }
}
