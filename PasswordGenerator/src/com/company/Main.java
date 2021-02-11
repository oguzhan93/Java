package com.company;
import java.util.*;
public class Main {

    public static void main(String[] args) {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "1234567890";
        String signs = "!+-/*?.:;,=(){}[]^";
        String password = upper+lower+numbers+signs;
        passwordGenerator(password);
    }
    public static void passwordGenerator(String password) {
        String newPassword = "";
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.println("How long password do you want?");
        int number = scanner.nextInt();
        for (int i = 0; i < number; i++) {
            int rand = random.nextInt(password.length());
            newPassword += password.charAt(rand);
        }
        System.out.println("Here is your new password : "+"\033[0;31m"+newPassword+"\033[0m");
    }
}
