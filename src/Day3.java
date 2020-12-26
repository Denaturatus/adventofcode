import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Day3 {
    public static void main(String[] args) {
        ArrayList<String> grid = readFile("input/day3/input");
        printMap(grid);
        part1(grid);
        part2(grid);
    }

    private static void part1(ArrayList<String> grid) {
        modifyGrid(grid, 3);
        int answer = calculateTreesEncountered(grid, 3, 1);
        System.out.println("Answer for part 1 is " + answer);
    }

    private static void part2(ArrayList<String> grid) {
        long answer = 1;
        for (int i = 1; i <= 7; i += 2) {
            modifyGrid(grid, i);
            answer *= calculateTreesEncountered(grid, i, 1);
        }
        modifyGrid(grid, 1);
        answer *= calculateTreesEncountered(grid, 1, 2);
        System.out.println("Answer for part 2 is " + answer);
    }

    private static int calculateTreesEncountered(ArrayList<String> grid, int rightSlopeValue, int downSlopeValue) {
        int i = 0, treeCounter = 0;
        boolean toContinue = false;
        for (String row :
                grid) {
            if (downSlopeValue == 2 && toContinue) {
                toContinue = false;
                continue;
            }
            if (row.charAt(i) == '#') {
                treeCounter++;
            }
            i += rightSlopeValue;
            if (!toContinue)
                toContinue = true;
        }
        return treeCounter;
    }

    private static void modifyGrid(ArrayList<String> grid, int rightSlopeValue) {
        int rightLengthOfLine = rightSlopeValue * grid.size();
        int i = 0;
        for (String row :
             grid) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(row.repeat(Math.max(0, (rightLengthOfLine / row.length()) + 1)));
            grid.set(i, stringBuilder.toString());
            i++;
        }
    }

    private static void printMap(ArrayList<String> grid) {
        int i = 0;
        for (String row:
             grid) {
            System.out.println(i + " " + row);
            i++;
        }
    }

    private static ArrayList<String> readFile(String path) {
        ArrayList<String> data = new ArrayList<String>();

        try {
            File input = new File(path);
            Scanner scanner = new Scanner(input);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                data.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        return data;
    }
}
