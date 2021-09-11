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
        ArrayList<String> english = new ArrayList<String>();
        ArrayList<String> turkish = new ArrayList<String>();
        ArrayList<Integer> previousNumbers = new ArrayList<Integer>();
        String previousNumberPath = "C:\\Users\\oğuzhan\\Desktop\\dictionaryFiles\\previousNumber.txt";
        String path = "C:\\Users\\oğuzhan\\Desktop\\dictionaryFiles\\Words.txt";
        fillLists(turkish, english, path);
        fillPreviousNumbersList(previousNumbers, previousNumberPath);
        Scanner scanner = new Scanner(System.in);
        System.out.println("\033[0;33m" + "Press 1 to start game." + "\033[0m");
        System.out.println("\033[0;37m" + "Press 2 to add a new word." + "\033[0m");
        System.out.println("\033[0;35m" + "Press 3 to remove a word" + "\033[0m");
        System.out.println("\033[0;36m" + "Press 4 to get number of words." + "\033[0m");
        System.out.println("\033[0;31m" + "Press 5 to search a word." + "\033[0m");
        System.out.println("\033[0;38m" + "Press 6 to show all the words." + "\033[0m");
        int i = scanner.nextInt();
        switch (i) {
            case 1:
                game(turkish, english, previousNumbers);
                break;
            case 2:
                addNewWord(turkish, english, path);
                break;
            case 3:
                deleteWord(turkish, english, path);
                break;
            case 4:
                showNumberOfWords(turkish, english);
                break;
            case 5:
                findWord(turkish, english);
                break;
            case 6:
                getWords(turkish, english);
                break;
            default:
                System.out.println("\033[0;31m" + "Please do not enter any number other than those specified in the menu." + "\033[0m");
                info();
        }
    }

    public static void addNewWord(ArrayList<String> turkish, ArrayList<String> english, String path) {
        System.out.println("\033[1;31m" + "Please enter an english word." + "\033[1;30m");
        Scanner scanner = new Scanner(System.in);
        String newWord = scanner.next();
        if (!english.contains(newWord)) {
            System.out.println("\033[1;30m" + "please enter " + "\033[1;30m" + "\033[4;31m" + newWord + "\033[0m" + "\033[1;30m" + "'s turkish mean" + "\033[1;30m");
            String newMean = scanner.next();
            turkish.add(newMean);
            english.add(newWord);
            saveFile(turkish, english, path);
            System.out.println("\033[4;31m" + newWord + "\033[0m" + "\033[1;30m" + " is added successfully." + "\033[0m");
        } else {
            System.out.println("\033[4;31m" + newWord + "\033[0m" + "\033[1;30m" + " is already exist." + "\033[0m");
            System.out.println();
        }
        info();
    }

    public static void game(ArrayList<String> turkish, ArrayList<String> english, ArrayList<Integer> previousNumbers) {
        if (turkish.size() == 0) {
            System.out.println("There is no word in the list.");
            System.out.println("Please add a new word in your list.");
            info();
        }
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        int i = random.nextInt(english.size());
        if (!controlPrevious(previousNumbers, i)) {
            game(turkish, english, previousNumbers);
        }
        boolean finished = false, writeMessage = true;
        int attempt = 0;
        while (!finished && attempt < 3) {
            System.out.println("What does " + english.get(i) + "'s mean?");
            String answer = scanner.next();
            if (answer.equals(turkish.get(i))) {
                System.out.println("\033[1;32m" + "Correct!" + "\033[0m");
                finished = true;
            } else {
                if (writeMessage) {
                    System.out.println("\033[1;31m" + "Try again" + "\033[0m");
                    writeMessage = false;
                }
                attempt++;
                if (attempt == 2) {
                    getATip(turkish.get(i));
                }
            }
        }
        if (attempt == 3) {
            System.out.println("\033[4;31t" + english.get(i) + "\033[0m" + "\033[0;37m" + "'s mean is " + "\033[0m" + "\033[4;32m" + turkish.get(i) + "\033[0m");
        }
        game(turkish, english, previousNumbers);
    }

    public static void fillLists(ArrayList<String> turkish, ArrayList<String> english, String path) {
        File file = new File(path);
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] words = line.split("-");
                english.add(words[0]);
                turkish.add(words[1]);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    public static void fillPreviousNumbersList(ArrayList<Integer> previousNumbers, String previousNumbersPath) {
        File file = new File(previousNumbersPath);
        try {
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                int number = reader.nextInt();
                previousNumbers.add(number);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public static boolean controlPrevious(ArrayList<Integer> controlArray, int number) {
        int size = 200;
        if (controlArray.contains(number)) {
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
        String path = "C:\\Users\\oğuzhan\\Desktop\\dictionaryFiles\\previousNumber.txt";
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
        int min = (answer.length() * 5) / 10, max = (answer.length() * 8) / 10, loop = 0; //number of letters to show
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        Random random = new Random();
        int numberOfHint = random.nextInt(max - min);
        numberOfHint = numberOfHint + min;
        do{
            int number = random.nextInt(answer.length());
            if (!numbers.contains(number)) {
                loop++;
                numbers.add(number);
            }
        }
        while (loop < numberOfHint);
        System.out.println("Here is a tip :");
        for (int index = 0; index < answer.length(); index++) {
            if (numbers.contains(index)) {
                System.out.print(answer.charAt(index) + " ");
            } else {
                System.out.print("_ ");
            }
        }
        System.out.println();
    }

    public static void deleteWord(ArrayList<String> turArr, ArrayList<String> engArr, String path) {
        Scanner scanner = new Scanner(System.in);
        int i = 1;
        for (String word : engArr) {
            System.out.println(i + "- " + word);
            i++;
        }
        System.out.println("Which word would you like to remove?");
        int index = scanner.nextInt();
        index -= 1;
        System.out.println("\033[1;31m" + engArr.get(index) + "\033[0m" + " and " + "\033[1;31m" + turArr.get(index) + "\033[0m" + " will be removed!");
        engArr.remove(index);
        turArr.remove(index);
        saveFile(turArr, engArr, path);
        System.out.println("Words removed successfully!");
        info();
    }

    public static void saveFile(ArrayList<String> turArr, ArrayList<String> engArr, String path) {

        File file = new File(path);
        try {
            file.delete();
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
            for (int i = 0; i < turArr.size(); i++) {
                StringBuilder builder = new StringBuilder();
                builder.append(engArr.get(i));
                builder.append("-");
                builder.append(turArr.get(i));
                writer.write(builder.toString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void rules() {
        System.out.println("\n\033[1;31m" + "Requirements : There must be Words and previousNumber text files in folder named dictionaryFiles on desktop." + "\033[0m");
        System.out.println("\033[0;34m" + "You have 3 attempts." + "\033[0m");
        System.out.println("\033[0;32m" + "Use lower case." + "\033[0m\n");
    }

    public static void findWord(ArrayList<String> tur, ArrayList<String> eng) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an English or Turkish word.");
        String word = scanner.next();
        for (String index : tur) {
            if (index.compareTo(word) == 0) {
                System.out.println("The meaning of " + word + " is " + eng.get(tur.indexOf(index)) + ".");
                info();
            }
        }
        for (String index : eng) {
            if (index.compareTo(word) == 0) {
                System.out.println("The meaning of " + word + " is " + tur.get(eng.indexOf(index)) + ".");
                info();
            }
        }
        System.out.println(word + " does not exist.");
        info();
    }

    public static void getWords(ArrayList<String> turkish, ArrayList<String> english) {
        for (int i = 0; i < turkish.size(); i++) {
            StringBuilder builder = new StringBuilder();
            builder.append(i + 1);
            builder.append(". ");
            builder.append(english.get(i));
            builder.append("-");
            builder.append(turkish.get(i));

            System.out.println(builder.toString());
        }
        info();
    }

    public static void autoAddNewWords(ArrayList<String> turkish, ArrayList<String> english) {
        String savePath = "C:\\Users\\oğuzhan\\Desktop\\dictionaryFiles\\Words.txt";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the path of the word list.");
        String path = scanner.next();
        File file = new File(path);
        try {
            Scanner reader = new Scanner(file);
            while(reader.hasNextLine()){
                String line = reader.nextLine();
                String [] words = line.split("-");
                english.add(words[0]);
                turkish.add(words[1]);
            }
        } catch(IOException exception) {
            System.out.println("Error message: " + exception.getMessage());
        }
        saveFile(turkish, english, savePath);
        info();
    }
}
