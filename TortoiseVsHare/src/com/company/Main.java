package com.company;
import java.util.Random;
public class Main {

    public static void main(String[] args) {

        int RabbitScore = 0, TurtleScore = 0;
        boolean Finished = false;
        while (Finished != true) {

            int T = Turtle();
            TurtleScore += T;

            int R = Rabbit();
            RabbitScore += R;

            if (TurtleScore > 80) {
                TurtleScore = 80;
                for (int i = 1; i < 80; i++) {
                    System.out.print("-");
                }
                System.out.print("T");
                System.out.print("|");
                System.out.println("");

            } else if (TurtleScore < 0) {
                TurtleScore = 0;

                System.out.print("T");
                for (int i = 1; i < 80; i++) {
                    System.out.print("-");
                }
                System.out.print("|");
                System.out.println("");
            } else {

                for (int i = 0; i < 80; i++) {
                    if (i + 1 == TurtleScore) {
                        System.out.print("T");
                    } else {
                        System.out.print("-");
                    }
                }
                System.out.print("|");
                System.out.println("");
            }

            if (RabbitScore > 80) {
                RabbitScore = 80;

                for (int j = 1; j < 80; j++) {
                    System.out.print("-");
                }
                System.out.print("R");
                System.out.print("|");
                System.out.println("");
            } else if (RabbitScore < 0) {
                RabbitScore = 0;

                System.out.print("R");
                for (int j = 1; j < 80; j++) {
                    System.out.print("-");
                }
                System.out.print("|");
                System.out.println("");
            } else {

                for (int j = 0; j < 80; j++) {
                    if (j + 1 == RabbitScore) {
                        System.out.print("R");
                    } else {
                        System.out.print("-");
                    }
                }
                System.out.print("|");
                System.out.println("");
            }
            System.out.println("\033[0;31m" + "*********************************************************************************" + "\033[0m");


            if (TurtleScore == 80 && RabbitScore == 80) {
                System.out.println("DRAW!!");
                Finished = true;
            } else if (TurtleScore == 80 && RabbitScore != 80) {
                System.out.println("Turtle Wins!!");
                Finished = true;
            } else if (RabbitScore == 80 && TurtleScore != 80) {
                System.out.println("Rabbit Wins!!");
                Finished = true;
            } else {

            }


        }
    }

    public static int Turtle() {
        int turtleValue = 0;
        Random random = new Random();
        int a = random.nextInt(10) + 1;
        if (a >= 1 && a <= 5) {
            turtleValue += 3;
        } else if (a >= 6 && a <= 7) {
            turtleValue -= 6;
        } else if (a >= 8 && a <= 10) {
            turtleValue += 1;
        }

        return turtleValue;
    }

    public static int Rabbit() {
        int RabbitValue = 0;
        Random b = new Random();
        int x = b.nextInt(10) + 1;
        if (x >= 1 && x <= 2) {
            RabbitValue += 0;
        } else if (x >= 3 && x <= 4) {
            RabbitValue += 9;
        } else if (x == 5) {
            RabbitValue -= 12;
        } else if (x >= 6 && x <= 8) {
            RabbitValue += 1;
        } else if (x >= 9 && x <= 10) {
            RabbitValue -= 2;
        }
        return RabbitValue;
    }

}

