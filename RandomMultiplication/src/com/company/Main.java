package com.company;
import java.util.Scanner;
import java.util.Random;
public class Main {

    public static void main(String[] args) {
        System.out.println("Press '-1' To EXIT!");
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int Score = 0 ;
	    boolean Finished = false;
	    while(Finished!=true){
            int i = random.nextInt(10)+1;
            int j = random.nextInt(10)+1;
            System.out.println(i+"x"+j+"= ?");
            int Result = i*j;
            int Answer = scanner.nextInt();
            if(Answer==-1){
                Finished = true ;
            }
            else{
                if(Answer!=Result){
                    while(Answer!=Result){
                        System.out.println("Try Again");
                        System.out.println(i + "x" + j + "= ?");
                        int answer = scanner.nextInt();
                        Answer = answer;
                    }
                }
                else{

                }
                System.out.println("Good Job!");
                Score++;
            }
        }
        System.out.println("Your Score Is: "+Score);
        System.out.println("GOOD BYE!");
    }

}
