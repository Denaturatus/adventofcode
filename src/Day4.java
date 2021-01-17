import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day4 {
    public static void main(String[] args) {
        ArrayList<String> passports = readFile("input/day4/input");
        printPassports(passports);
        System.out.println("There are " + Integer.toString(part1(passports)) + " valid passports.");
        System.out.println("There are " + Integer.toString(part2(passports)) + " valid passports.");
    }

    private static int part2(ArrayList<String> passports) {
        int counter = 0;
        for (String passport : passports
        ) {
            if (validatePassportPart2(passport))
                counter++;
        }
        return counter;
    }

    private static boolean validatePassportPart2(String passport) {
        String[] fields = passport.split(" ");
        HashMap<String, String> hashMap = new HashMap<>();
        for (String field : fields
             ) {
            String key = field.substring(0,3);
            String value = field.substring(4);
            hashMap.put(key, value);
        }
        if (validatePassport(passport)) {
            if (!validateBirthYearField(hashMap.get("byr")))
                return false;
            if (!validateIssueYearField(hashMap.get("iyr")))
                return false;
            if (!validateExpirationYearField(hashMap.get("eyr")))
                return false;
            if (!validateHeight(hashMap.get("hgt")))
                return false;
            if (!validateHairColor(hashMap.get("hcl")))
                return false;
            if (!validateEyeColor(hashMap.get("ecl")))
                return false;
            if (!validatePassportID(hashMap.get("pid")))
                return false;
        } else
            return false;
        return true;
    }

    private static boolean validateBirthYearField(String birthYear) {
        return birthYear.length() == 4 && Integer.parseInt(birthYear) <= 2002 && Integer.parseInt(birthYear) >= 1920;
    }

    private static boolean validateIssueYearField(String issueYear) {
        return issueYear.length() == 4 && Integer.parseInt(issueYear) <= 2020 && Integer.parseInt(issueYear) >= 2010;
    }

    private static boolean validateExpirationYearField(String expirationYear) {
        return expirationYear.length() == 4 && Integer.parseInt(expirationYear) >= 2020 && Integer.parseInt(expirationYear) <= 2030;
    }

    private static boolean validateHeight(String height) {
        int heightValue;
        if (height.contains("cm")) {
            if (height.length() == 4) {
                return false;
            } else {
                heightValue = Integer.parseInt(height.substring(0,3));
                return heightValue <= 193 && heightValue >= 150;
            }

        } else if (height.contains("in")) {
            heightValue = Integer.parseInt(height.substring(0,2));
            return heightValue <= 76 && heightValue >= 59;
        } else
            return false;
    }

    private static boolean validateHairColor(String hairColor) {
        if (hairColor.length() != 7 && hairColor.charAt(0) != '#')
            return false;
        String string = "abcdef0123456789";
        for (int i = 1; i < 7; i++) {
            char c = hairColor.charAt(i);
            if (!string.contains(String.valueOf(c))) {
                return false;
            }
        }
        return true;
    }

    private static boolean validateEyeColor(String eyeColor) {
        return eyeColor.equals("amb") || eyeColor.equals("blu") || eyeColor.equals("brn") || eyeColor.equals("gry") ||
                eyeColor.equals("grn") || eyeColor.equals("hzl") || eyeColor.equals("oth");
    }

    private static boolean validatePassportID(String passportID) {
        if (passportID.length() != 9)
            return false;
        for (int i = 0; i < 9; i++) {
            char c = passportID.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    private static int part1(ArrayList<String> passports) {
        int counter = 0;
        for (String passport : passports
             ) {
            if (validatePassport(passport))
                counter++;
        }
        return counter;
    }

    private static boolean validatePassport(String passport) {
        return passport.contains("byr") && passport.contains("iyr") && passport.contains("eyr") && passport.contains("hgt")
                && passport.contains("hcl") && passport.contains("ecl") && passport.contains("pid");
    }

    private static ArrayList<String> readFile(String path) {
        ArrayList<String> data = new ArrayList<String>();

        try {
            File input = new File(path);
            Scanner scanner = new Scanner(input);
            StringBuilder stringBuilder = new StringBuilder();
            String lastLineHolder;

            do {
                String line = scanner.nextLine();
                lastLineHolder = line;
                if (line.isBlank() || !scanner.hasNextLine()) {
                    data.add(stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                } else {
                    if (stringBuilder.isEmpty()) {
                        stringBuilder.append(line);
                    }
                    else {
                        stringBuilder.append(" " + line);
                    }
                }
            } while (scanner.hasNextLine());

            stringBuilder.append(data.get(data.size() - 1));
            stringBuilder.append(" " + lastLineHolder);
            data.set(data.size() - 1, stringBuilder.toString());
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        return data;
    }

    private static void printPassports(ArrayList<String> passports) {
        int i = 0;
        for (String passport:
                passports) {
            System.out.println(i + " " + passport);
            i++;
        }
    }
}
