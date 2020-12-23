import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Day1 {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();

        try {
            File input = new File("input/day1/input");
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                numbers.add(Integer.parseInt(data));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        part1(numbers);
        part2(numbers);

    }

    private static void part1(ArrayList<Integer> numbers) {
        int a = 0, b = 0;
        boolean flag = false;

        for (int i = 0; i < numbers.size(); i++) {
            for (int j = 0; j < numbers.size(); j++) {
                if (i == j)
                    continue;

                if(numbers.get(i) + numbers.get(j) == 2020) {
                    a = numbers.get(i);
                    b = numbers.get(j);
                    flag = true;
                    break;
                }
            }
            if(flag)
                break;
        }

        int sum = a + b;
        int result = a * b;
        System.out.println("Part 1 - First number is " + a + ", second number is " + b + ". Their sum is " + sum +
                " and the" + " final result is " + result + ".");
    }

    private static void part2(ArrayList<Integer> numbers) {
        int a = 0, b = 0, c = 0;
        boolean flag = false;

        for (int i = 0; i < numbers.size(); i++) {
            for (int j = 0; j < numbers.size(); j++) {
                for (int k = 0; k < numbers.size(); k++) {
                    if (i == j || i == k || j == k)
                        continue;

                    if (numbers.get(i) + numbers.get(j) + numbers.get(k) == 2020) {
                        a = numbers.get(i);
                        b = numbers.get(j);
                        c = numbers.get(k);
                        flag = true;
                        break;
                    }
                }
                if(flag)
                    break;
            }
            if(flag)
                break;
        }

        int sum = a + b + c;
        int result = a * b * c;
        System.out.println("Part 2 - First number is " + a + ", second number is " + b + ", third number is " + c +
                ". Their " + "sum is " + sum + " and the" + " final result is " + result + ".");
    }
}
