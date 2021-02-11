package com.company;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        SayıAlma();
    }
    public static void OBEB(){
        ArrayList <Integer> holdI = new ArrayList<Integer>();
        ArrayList <Integer> holdJ = new ArrayList<Integer>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("OBEB'ini almak istediğiniz sayilari girin:");
        int i = scanner.nextInt();
        int j = scanner.nextInt();
        if(i<=0 || j<=0){
            System.out.println("lütfen pozitif bir sayi girin.");
            OBEB();
        }
        else{
            for(int x = 1 ; x<=i ; x++){
                if(i%x==0){
                    holdI.add(x);
                }
                else{

                }
            }
            for(int y = 1 ; y<=j ;y++){
                if(j%y==0){
                    holdJ.add(y);
                }
                else{

                }
            }
            int result = 0 ;
            for(int obeb : holdI){
                if(holdJ.contains(obeb)){
                    result = obeb;
                }
                else{

                }
            }
            if(result!=0){
                System.out.println("OBEB("+i+","+j+") = "+result);
            }
            else{
                System.out.println("sayılar arlarında asal olduğu için obebi yoktur");
            }

        }


    }
    public static void OKEK(){
        System.out.println("         OKEK\n");
        Scanner scanner = new Scanner(System.in);
        boolean Finished = false;
        System.out.println("OKEK'ini almak istediğiniz sayılari girin:");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        if(x<=0 || y<=0){
            System.out.println("Lütfen pozitif bir sayı girin.\n");
            OKEK();
        }
        else {
            int BüyükSayı = x;
            if (y > x) {
                BüyükSayı = y;
            } else {

            }
            while (Finished != true) {
                if ((BüyükSayı % x == 0) && (BüyükSayı % y == 0)) {
                    System.out.println("OKEK("+x+","+y+") = "+BüyükSayı);
                    Finished = true;
                } else {
                    BüyükSayı++;
                }
            }
        }
    }
    public static void SayıAlma(){
        System.out.println("Çıkmak için 0");
        System.out.println("OBEB için 1");
        System.out.println("OKEK için 2\n");
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        if(x==0){

        }
        else if(x==1){
            OBEB();
            SayıAlma();
        }
        else if(x==2){
            OKEK();
            SayıAlma();
        }
        else{
            System.out.println("Lütfen menüdeki sayılar haricinde bir sayı girmeyin.\n");
            SayıAlma();
        }
    }
}