import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day4Part1 {

    public static void main(String[] args) throws IOException {

        var file = "Resources/day4actual.txt";
        var passports = new ArrayList<String>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String passport = "";
            String line = br.readLine();

            // Parse in each passport into a string separated by a blank line.
            do {
                if(!line.equals("")) {
                    passport = passport + line;
                }
                else {
                    if(!passport.equals("")) {
                        passports.add(passport);
                        passport = "";
                    }
                }
                line = br.readLine();

                // If new line is null then add last passport is not empty.
                if (line == null && passport != "") {
                    passports.add(passport);
                }
            } while (line != null);

            int validPassports = (int) passports
                    .stream().filter(p -> p.contains("byr") && p.contains("iyr") && p.contains("eyr")
                            && p.contains("hgt") && p.contains("hcl") && p.contains("ecl") && p.contains("pid"))
                    .count();

            System.out.println(validPassports);
        }
    }   
}
