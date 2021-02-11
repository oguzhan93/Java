package com.company;

import java.io.*;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        rules();
        info();
    }

    public static void info() {
        ArrayList<String> Eng = new ArrayList<String>();
        ArrayList<String> Tur = new ArrayList<String>();
        ArrayList<Integer> previousNumbers = new ArrayList<Integer>();
        String previousNumberPath = "C:\\Users\\hp\\Desktop\\dictionaryFiles\\previousNumber.txt";
        String turPath = "C:\\Users\\hp\\Desktop\\dictionaryFiles\\Tur.txt";
        String engPath = "C:\\Users\\hp\\Desktop\\dictionaryFiles\\Eng.txt";
        Tur = fillTurList(Tur, turPath);
        Eng = fillEngList(Eng, engPath);
        previousNumbers = fillPreviousNumbersList(previousNumbers, previousNumberPath);
        Scanner scanner = new Scanner(System.in);
        System.out.println("\033[0;33m" + "Press 1 to start game." + "\033[0m");
        System.out.println("\033[0;37m" + "Press 2 to add a new word." + "\033[0m");
        System.out.println("\033[0;35m"+"Press 3 to remove a word"+"\033[0m");
        System.out.println("\033[0;36m"+"Press 4 to see number of words."+"\033[0m");
        System.out.println("Press 5 to search a number");
        int i = scanner.nextInt();
        switch (i) {
            case 1:
                game(Tur, Eng, previousNumbers);
                break;
            case 2:
                addNewWord(Eng, Tur, turPath, engPath);
                break;
            case 3:
                deleteWord(Tur,Eng,turPath,engPath);
                break;
            case 4:
                showNumberOfWords(Tur, Eng);
                break;
            case 5:
                findWord(Tur,Eng);
                break;
            default:
                System.out.println("\033[0;31m" + "Please do not enter any number other than those specified in the menu." + "\033[0m");
                info();
        }
    }

    public static void addNewWord(ArrayList<String> Eng, ArrayList<String> Tur, String turPath, String engPath) {
        System.out.println("\033[1;31m" + "Please enter an english word." + "\033[1;30m");
        Scanner scanner = new Scanner(System.in);
        String newWord = scanner.next();
        if (Eng.contains(newWord) == false) {
            System.out.println("\033[1;30m" + "please enter " + "\033[1;30m" + "\033[4;31m" + newWord + "\033[0m" + "\033[1;30m" + "'s turkish mean" + "\033[1;30m");
            String newMean = scanner.next();
            Tur.add(newMean);
            Eng.add(newWord);
            saveFile(Tur,Eng,turPath,engPath);
            System.out.println("\033[4;31m" + newWord + "\033[0m" + "\033[1;30m" + " is added successfully." + "\033[0m");
        } else {
            System.out.println("\033[4;31m" + newWord + "\033[0m" + "\033[1;30m" + " is already exist." + "\033[0m");
            System.out.println("");
        }
        info();
    }

    public static void game(ArrayList<String> Tur, ArrayList<String> Eng, ArrayList<Integer> previousNumbers) {
        if (Tur.size() == 0) {
            System.out.println("There is no word in the list.");
            System.out.println("Please add a new word in your list.");
            info();
        }
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        int i = random.nextInt(Eng.size());
        if (!controlPrevious(previousNumbers, i)) {
            game(Tur, Eng, previousNumbers);
        } else {
        }
        boolean finished = false, writeMessage = true;
        int attempt = 0;
        while (finished != true && attempt < 3) {
            System.out.println("What does " + Eng.get(i) + "'s mean?");
            String answer = scanner.next();
            if (answer.equals(Tur.get(i)) == true) {
                System.out.println("\033[1;32m" + "Correct!" + "\033[0m");
                finished = true;
            } else {
                if (writeMessage) {
                    System.out.println("\033[1;31m" + "Try again" + "\033[0m");
                    writeMessage = false;
                }
                attempt++;
                if (attempt == 2) {
                    getATip(Tur.get(i));
                } else {
                }
            }
        }
        if (attempt == 3) {
            System.out.println("\033[4;31m" + Eng.get(i) + "\033[0m" + "\033[0;37m" + "'s mean is " + "\033[0m" + "\033[4;32m" + Tur.get(i) + "\033[0m");
        } else {
        }
        game(Tur, Eng, previousNumbers);
    }

    public static ArrayList<String> fillTurList(ArrayList<String> Tur, String turPath) {
        File file = new File(turPath);
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine() == true) {
                String line = reader.nextLine();
                Tur.add(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return Tur;
    }

    public static ArrayList<String> fillEngList(ArrayList<String> Eng, String engPath) {
        File file = new File(engPath);
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine() == true) {
                String line = reader.nextLine();
                Eng.add(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return Eng;
    }

    public static ArrayList<Integer> fillPreviousNumbersList(ArrayList<Integer> previousNumbers, String previousNumbersPath) {
        File file = new File(previousNumbersPath);
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine() == true) {
                int number = reader.nextInt();
                previousNumbers.add(number);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        return previousNumbers;
    }

    public static boolean controlPrevious(ArrayList<Integer> controlArray, int number) {
        int size = 200;
        if (controlArray.contains(number) == true) {
            return false;
        } else {
            if (controlArray.size() < size) {
                controlArray.add(number);
            } else {
                controlArray.add(number);
                controlArray.remove((size - 1));
            }
            writePreviousNumbers(controlArray);
            return true;
        }
    }

    public static void writePreviousNumbers(ArrayList<Integer> previousNumbers) {
        String path = "C:\\Users\\hp\\Desktop\\previousNumber.txt";
        File file = new File(path);
        try {
            file.delete();
            file.createNewFile();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
            for (Integer number : previousNumbers) {
                writer.write(number);
                writer.newLine();
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static void showNumberOfWords(ArrayList<String> turArr, ArrayList<String> engArr) {
        System.out.println("There are " + turArr.size() + " words in Turkish.");
        System.out.println("There are " + engArr.size() + " words in English.");
        info();
    }

    public static void getATip(String answer) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        Random random = new Random();
        int lengthOfAnswer = answer.length();
        if (lengthOfAnswer < 8) {
            System.out.println("Here is a tip :");
            for (int index = 0; index < lengthOfAnswer; index++) {
                if (index == 0) {
                    System.out.print(answer.charAt(index) + " ");
                } else if (index == (lengthOfAnswer / 2)) {
                    System.out.print(answer.charAt(index) + " ");
                } else if (index == (lengthOfAnswer - 1)) {
                    System.out.print(answer.charAt(index) + " ");
                } else {
                    System.out.print("_ ");
                }
            }
            System.out.println();
        } else {
            int numberOfHint = random.nextInt(2 * (lengthOfAnswer / 3)) + 4;
            for (int i = 0; i < numberOfHint; i++) {
                int rand = random.nextInt(lengthOfAnswer);
                if (numbers.contains(rand)) {
                    boolean finished = false;
                    while (finished != true) {
                        int newRand = random.nextInt(lengthOfAnswer);
                        if (numbers.contains(newRand)) {
                        } else {
                            numbers.add(newRand);
                            finished = true;
                        }
                    }
                } else {
                    numbers.add(rand);
                }
            }
            System.out.println("Here is a tip :");
            for (int index = 0; index < lengthOfAnswer; index++) {
                if (numbers.contains(index)) {
                    System.out.print(answer.charAt(index) + " ");
                } else {
                    System.out.print("_ ");
                }
            }
            System.out.println();
        }
    }
    public static void deleteWord(ArrayList<String> turArr , ArrayList<String > engArr, String turPath , String engPath){
        Scanner scanner = new Scanner(System.in);
        int i = 1;
        for(String word : engArr){
            System.out.println(i+"- "+word);
            i++;
        }
        System.out.println("Which word would you like to remove?");
        int index = scanner.nextInt();
        index-=1;
        System.out.println("\033[1;31m"+engArr.get(index)+"\033[0m"+" and "+"\033[1;31m"+turArr.get(index)+"\033[0m"+" will be removed!");
        engArr.remove(index);
        turArr.remove(index);
        saveFile(turArr,engArr,turPath,engPath);
        System.out.println("Words removed successfully!");
        info();
    }
    public static void saveFile(ArrayList<String> turArr , ArrayList<String > engArr, String turPath , String engPath){
        File turfile = new File(turPath);
        try {
            turfile.delete();
            turfile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File engFile = new File(engPath);
        try {
            engFile.delete();
            engFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(turPath, true));
            for (String turWrite : turArr) {
                writer.write(turWrite);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(engPath, true));
            for (String engWriter : engArr) {
                writer.write(engWriter);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void rules(){
        System.out.println("\033[1;31m"+"Requirements : There must be ENG, TUR, previousNumber text files in folder named dictionaryFiles on desktop."+"\033[0m");
        System.out.println("\033[0;34m" + "You have 3 attempts." + "\033[0m");
        System.out.println("\033[0;32m" + "Use lower case." + "\033[0m");
    }
    public static void findWord(ArrayList <String> tur, ArrayList <String> eng){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an English or Turkish word.");
        String word = scanner.next();
        for (String index : tur) {
            if (index.compareTo(word) == 0) {
                System.out.println("The meaning of "+word+" is "+eng.get(tur.indexOf(index))+".");
                info();
            }
        }
        for (String index : eng) {
            if (index.compareTo(word) == 0) {
                System.out.println("The meaning of "+word+" is "+tur.get(eng.indexOf(index))+".");
                info();
            }
        }
        System.out.println(word+" does not exist.");
        info();
    }
}
