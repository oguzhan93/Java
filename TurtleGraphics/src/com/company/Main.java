package com.company;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        int SağSol = 0, AşağıYukarı = 0, TempSS = 0, TempAY = 0; //sağsol ve aşağı yukarı matrisleri tutacak
        Scanner scanner = new Scanner(System.in);
        String[][] area = new String[20][20];


        boolean FINISHED = false ;
        Yön yön = Yön.East ;
        Pen pen = Pen.UP ;
        System.out.println("\n"+"\033[0;31m"+"                RULES!"+"\033[0m"+"\n");
        System.out.println("Area Size Is 20x20!");
        System.out.println("Initially Pen Is UP And Direction Is East!");
        System.out.println("Pen Does Not Write If The Direction Is Up!\n\n");
        MENU();

        while(FINISHED!=true){
            int x = scanner.nextInt();
            if( x == 1){
                pen = Pen.UP;
                System.out.println("Pen UP!\n\n");

            }
            else if(x==2){
                pen = Pen.DOWN;
                System.out.println("Pen Down!\n\n");

            }
            else if(x==3){                  //Sağa Dönme

                if(yön==Yön.South){
                    yön=Yön.West;
                    System.out.println("WEST!");
                }
                else if(yön==Yön.West){
                    yön = Yön.North;
                    System.out.println("NORTH!");
                }
                else if(yön==Yön.North){
                    yön = Yön.East;
                    System.out.println("EAST!");
                }
                else{
                    yön = Yön.South;
                    System.out.println("SOUTH!");
                }

            }
            else if(x==4){                  //Sola Dönme
                if(yön==Yön.South){
                    yön = Yön.East;
                    System.out.println("EAST!");
                }
                else if(yön==Yön.East){
                    yön = Yön.North;
                    System.out.println("NORTH!");
                }
                else if(yön==Yön.North){
                    yön = Yön.West;
                    System.out.println("WEST!");
                }
                else{
                    yön = Yön.South;
                    System.out.println("SOUTH!");
                }

            }
            else if(x==5){
                System.out.println("How Many Steps Do You Want To Go!");
                int y = scanner.nextInt();

                if(pen==Pen.UP){
                    if(yön==Yön.East){
                        if(y+SağSol<=19){
                            SağSol+=y;


                        }
                        else{
                            SağSol=19;


                        }
                        TempSS=SağSol;
                    }
                    else if(yön==Yön.West){
                        if(SağSol-y>=0){
                            SağSol-=0;


                        }
                        else{
                            SağSol=0;


                        }
                        TempSS=SağSol;
                    }
                    else if(yön==Yön.South){
                        if(AşağıYukarı+y<19){
                            AşağıYukarı+=y;


                        }
                        else{
                            AşağıYukarı=19;


                        }
                        TempAY=AşağıYukarı;
                    }
                    else{
                        if(AşağıYukarı-y>=0){
                            AşağıYukarı-=y;


                        }

                        else{
                            AşağıYukarı=0;


                        }
                        TempAY=AşağıYukarı;
                    }
                }
                else{
                    if(yön==Yön.East){
                        if(y+SağSol<19){           //Buradaki 'if'ler matrislerin dışına taşmayı kontol etmek için
                            for(;SağSol<=TempSS+y;SağSol++){
                                area[AşağıYukarı][SağSol]= "*";



                            }
                            TempSS=SağSol;
                        }
                        else{
                            for(;SağSol<19;SağSol++){
                                area[AşağıYukarı][SağSol]="*";


                            }
                            TempSS=SağSol;
                        }
                    }
                    else if(yön==Yön.West){
                        if(SağSol-y>0){
                            for(;SağSol>=TempSS-y;SağSol--){
                                area[AşağıYukarı][SağSol]="*";


                            }
                            TempSS=SağSol;
                        }
                        else{
                            for(;SağSol>0;SağSol--){
                                area[AşağıYukarı][SağSol]="*";


                            }
                            TempSS=SağSol;
                        }
                    }
                    else if(yön==Yön.South){
                        if(y+AşağıYukarı<19){
                            for(;AşağıYukarı<=TempAY+y;AşağıYukarı++){
                                area[AşağıYukarı][SağSol]="*";


                            }
                            TempAY=AşağıYukarı;
                        }
                        else{
                            for(;AşağıYukarı<19;AşağıYukarı++){
                                area[AşağıYukarı][SağSol]="*";


                            }
                            TempAY=AşağıYukarı;
                        }
                    }
                    else{
                        if(AşağıYukarı-y>0){
                            for(;AşağıYukarı>=TempAY-y;AşağıYukarı--){
                                area[AşağıYukarı][SağSol]="*";


                            }
                            TempAY=AşağıYukarı;
                        }
                        else{
                            for(;AşağıYukarı>0;AşağıYukarı--){
                                area[AşağıYukarı][SağSol]="*";


                            }
                            TempAY=AşağıYukarı;
                        }
                    }
                }
            }
            else if(x==6){


                for(int j = 0 ; j<20 ; j++){
                    for(int p=0 ; p<20 ; p++){
                        if(area[j][p]==null){
                            System.out.print(" ");
                        }
                        else{
                            System.out.print("*");
                        }
                    }
                    System.out.print("\n");
                }
                MENU();
            }
            else if(x==9){
                System.out.println("The Final Version Of The Map!\n\n");
                for(int i = 0 ; i<20 ; i++) {

                }
                    for(int j = 0 ; j<20 ; j++){

                        for(int p=0 ; p<20 ; p++){
                            if(area[j][p]==null){
                                System.out.print(" ");
                            }
                            else{
                                System.out.print("*");
                            }
                        }
                        System.out.println("");
                    }
                FINISHED = true ;
            }
            else if(x==7){
                System.out.println("Current Direction Is : "+yön);
                System.out.println("Pen Status IS : "+pen);
                System.out.println("You Are Here Now : ["+AşağıYukarı+"]["+SağSol+"]");
            }
            else{
                System.out.println("Please Do Not Enter Other Than Those Specified In The Menu!");
                MENU();
            }
        }
    }
    public static enum  Pen {
        UP ,
        DOWN
    }
    public static enum Yön{

        South ,
        North ,
        East ,
        West
    }
    public static void MENU(){
        System.out.println("1 = Pen UP!");
        System.out.println("2 = Pen DOWN!");
        System.out.println("3 = Turn RIGHT!");
        System.out.println("4 = Turn LEFT!");
        System.out.println("5 = MOVE! ");
        System.out.println("6 = Show Map!");
        System.out.println("7 = Show Status!");
        System.out.println("9 = EXIT!");
    }
}

