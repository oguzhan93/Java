package com.company;
import java.util.Scanner ;
public class Main {

    public static void main(String[] args) {
        Scanner Girdi = new Scanner(System.in);
        PerfectNumber Num1 = new PerfectNumber();
        System.out.println("Enter An Integer Number ");
        int Integer = Girdi.nextInt();
        Num1.SetSayıDeğeri(Integer);
        Num1.IsItPerfect(Integer);


    }
}
