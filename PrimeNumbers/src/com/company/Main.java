package com.company;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        int Counter = 0 ;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter A Number!");
        int x = scanner.nextInt();
        if(x<=1){
            System.out.println("Please Enter A Number Greater Than '1' ");
        }
        else{
                for(int i = 1 ; i<=x ; i++){
                    if(x%i==0){
                        Counter +=1 ;
                    }
                    else{

                    }
                }
            if(Counter==2){
                System.out.println(x+": Is A Prime Number!");
            }
            else{
                System.out.println(x+": Is Not A Prime Number!");
            }
        }

    }
}
