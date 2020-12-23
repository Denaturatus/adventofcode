import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Day2 {
    public static void main(String[] args) {
        ArrayList<String> policiesAndPasswords = new ArrayList<String>();

        try {
            File input = new File("input/day2/input");
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                policiesAndPasswords.add(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        part1(policiesAndPasswords);
        part2(policiesAndPasswords);

    }

    private static void printResult(int counter) {
        if(counter > 1)
            System.out.print("There are " + counter + " valid passwords.");
        else if (counter == 1)
            System.out.print("There is " + counter + " valid password.");
        else
            System.out.print("There are no valid password.");
    }

    private static void part1(ArrayList<String> policiesAndPasswords) {
        int counter = 0;

        for (String line:
                policiesAndPasswords) {
            if (isPasswordOkPart1(line))
                counter++;
        }

        printResult(counter);
    }

    private static boolean isPasswordOkPart1(String line) {
        System.out.println("Current policy and password - " + line);
        int indexOfDash = line.indexOf("-");
        int indexOfSpace = line.indexOf(" ");
        int lowerLimit = Integer.parseInt(line.substring(0, indexOfDash));
        int upperLimit = Integer.parseInt(line.substring(indexOfDash + 1, indexOfSpace));
        char letter = line.charAt(indexOfSpace + 1);
        String password = line.substring(indexOfSpace + 4);

        System.out.println(" -- lowerLimit = " + lowerLimit);
        System.out.println(" -- upperLimit = " + upperLimit);
        System.out.println(" -- letter = " + letter);
        System.out.println(" -- password = " + password);


        int counter = 0;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (c == letter)
                counter++;
        }

        System.out.println(" -- counter = " + counter);

        if(counter <= upperLimit && counter >= lowerLimit) {
            System.out.println(" ---- password is valid :)");
            return true;
        }
        else {
            System.out.println(" ---- password is invalid :(");
            return false;
        }
    }

    private static void part2(ArrayList<String> policiesAndPasswords) {
        int counter = 0;

        for (String line:
                policiesAndPasswords) {
            if (isPasswordOkPart2(line))
                counter++;
        }

        printResult(counter);
    }

    private static boolean isPasswordOkPart2(String line) {
        System.out.println("Current policy and password - " + line);
        int indexOfDash = line.indexOf("-");
        int indexOfSpace = line.indexOf(" ");
        int firstIndex = Integer.parseInt(line.substring(0, indexOfDash)) - 1;
        int secondIndex = Integer.parseInt(line.substring(indexOfDash + 1, indexOfSpace)) - 1;
        char letter = line.charAt(indexOfSpace + 1);
        String password = line.substring(indexOfSpace + 4);

        System.out.println(" -- firstIndex in index zero logic = " + firstIndex);
        System.out.println(" -- secondIndex in index zero logic = " + secondIndex);
        System.out.println(" -- letter = " + letter);
        System.out.println(" -- password = " + password);


        int counter = 0;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (c == letter) {
                if(i == firstIndex || i == secondIndex)
                    counter++;
            }
        }

        System.out.println(" -- counter = " + counter);

        if(counter == 1) {
            System.out.println(" ---- password is valid :)");
            return true;
        }
        else {
            System.out.println(" ---- password is invalid :(");
            return false;
        }
    }
}
