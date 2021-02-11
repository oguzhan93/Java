package com.company;

public class Main {

    public static void main(String[] args) {
        for(int I = 0 ; I<=10 ;I++){
            for(int J = 0 ; J<=10 ; J++){
                System.out.println(I+"x"+J+"="+I*J);
            }
            System.out.println("\033[1;31m"+"*-*-*-*-*-*-*-*"+"\033[0m");
        }
    }
}
