package com.company;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        double RoundedValue = 0 ;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Enter A number");
        double Enter =  scanner.nextDouble();
        double OriginalValue = Enter ;
        if(Enter<0){
            System.out.println("Please Enter A Positive Number!! ");
        }
        else if(Enter>=0 && Enter <0.5){
            RoundedValue = 0 ;
            System.out.println("Original Value Is: "+OriginalValue);
            System.out.println("Rounded  Value Is: "+RoundedValue);
        }
        else if(Enter > 0.5 && Enter<1){
            RoundedValue = 1 ;
            System.out.println("Original Value Is: "+OriginalValue);
            System.out.println("Rounded  Value Is: "+RoundedValue);
        }
        else{
            int Counter = 0 ;
            while(Enter>1){
                Enter -=1 ;
                Counter +=1 ;
            }
            if ((OriginalValue-Counter>=0.5)){
                RoundedValue = Counter + 1 ;
            }
            else{
                RoundedValue = Counter ;
            }
            System.out.println("Original Value Is: "+OriginalValue);
            System.out.println("Rounded  Value Is: "+RoundedValue);
        }

    }
}