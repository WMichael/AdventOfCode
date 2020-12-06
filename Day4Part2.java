import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day4Part2 {

    /**
     * Passport
     */
    public static class Passport {
        String byr = null;
        String iyr = null;
        String eyr = null;
        String hgt = null;
        String hcl = null;
        String ecl = null;
        String pid = null;
        String cid = null;

        // Separate passport info it's own fields and set them to the fields of
        // this passport object.
        public Passport(String info) {
            var seperatedFields = new ArrayList<String>(Arrays.asList(info.split(" ")));
            for (String fieldAndData : seperatedFields) {
                String field = fieldAndData.split(":")[0];
                String data = fieldAndData.split(":")[1];

                switch (field) {
                    case "byr":
                        this.byr = data;
                        break;
                    case "iyr":
                        this.iyr = data;
                        break;
                    case "eyr":
                        this.eyr = data;
                        break;
                    case "hgt":
                        this.hgt = data;
                        break;
                    case "hcl":
                        this.hcl = data;
                        break;
                    case "ecl":
                        this.ecl = data;
                        break;
                    case "pid":
                        this.pid = data;
                        break;
                    case "cid":
                        this.cid = data;
                        break;
                    default:
                        break;
                }
            }
            
        }

        // Checks that the passport is valid or not.
        public boolean validPassport() {
            try {
                if (this.byr == null || Integer.parseInt(byr) < 1920 || Integer.parseInt(byr) > 2002) return false;
                if (this.iyr == null || Integer.parseInt(iyr) < 2010 || Integer.parseInt(iyr) > 2020) return false;
                if (this.eyr == null || Integer.parseInt(eyr) < 2020 || Integer.parseInt(eyr) > 2030) return false;
                if (this.hgt == null || (!this.hgt.contains("in") && !this.hgt.contains("cm"))) {
                    return false;
                } else {
                    int hgtValue = Integer.parseInt(this.hgt.substring(0, this.hgt.length() - 2));
                    if (this.hgt.contains("in")) {
                        if ( hgtValue < 59 || hgtValue > 76) return false;
                    }
                    else {
                        if ( hgtValue < 150 || hgtValue > 193) return false;
                    }
                }
                if (this.hcl == null || !this.hcl.substring(0,1).equals("#") || this.hcl.length() != 7) return false;
                if (this.ecl == null || (
                    !this.ecl.equals("amb") &&
                    !this.ecl.equals("blu") &&
                    !this.ecl.equals("brn") &&
                    !this.ecl.equals("gry") &&
                    !this.ecl.equals("grn") &&
                    !this.ecl.equals("hzl") &&
                    !this.ecl.equals("oth"))) return false;
                if (this.pid == null || this.pid.toString().length() != 9) return false;
            }

            // Catches fields where it can't convert a string to int, indicating the value
            // has one or more non-numeric characters. 
            catch (NumberFormatException e) {
                return false;
            }
            return true;
        }
    }

    public static void main(String[] args) throws IOException {

        var file = "Resources/day4actual.txt";
        var passports = new ArrayList<Passport>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String passportInfo = "";
            String line = br.readLine();

            // Parse in each passport into a string separated by a blank line.
            do {
                if(!line.equals("")) {
                    passportInfo = passportInfo + line + " ";
                }
                else {
                    if(!passportInfo.equals("")) {
                        Passport passport = new Passport(passportInfo);
                        passports.add(passport);
                        passportInfo = "";
                    }
                }
                line = br.readLine();

                // If new line is null then add last passport is not empty.
                if (line == null && passportInfo != "") {
                    Passport passport = new Passport(passportInfo);
                    passports.add(passport);
                }
            } while (line != null);

            int validPassports = (int) passports.stream().filter(passport -> passport.validPassport()).count();
            System.out.println("Number of valid passports: " + validPassports + "/" + passports.size());

        }
    }   
}
