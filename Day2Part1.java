import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day2Part1 {

    /**
     * Password
     */
    public static class Password {
        public int matchedPolicyLetters; // strips password to count of chars that match the policy letter.
        public String policyLetter;
        public int policyMin;
        public int policyMax;

        public Password(int matchedPolicyLetters, String policyLetter, int policyMin, int policyMax) {
            this.matchedPolicyLetters = matchedPolicyLetters;
            this.policyLetter = policyLetter;
            this.policyMin = policyMin;
            this.policyMax = policyMax;
        }
    }

    public static void main(String[] args) throws IOException {

        var passwords = new ArrayList<Password>();
        var file = "Resources/day2actual.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {

                // Parse in wanted values
                String[] splitLine = line.split(" ");
                int min = Integer.parseInt((((String) splitLine[0]).split("-"))[0]);
                int max = Integer.parseInt((((String) splitLine[0]).split("-"))[1]);
                String policyLetter = splitLine[1].substring(0, splitLine[1].length() - 1);

                // Strip password to just the characters of the policy letter
                List<String> chars = Arrays.asList(splitLine[2].split(""));
                int matchedPolicyLetters = (int) chars.stream().filter(letter -> letter.equals(policyLetter)).count();
                
                // Add to list
                Password passwordObj = new Password(matchedPolicyLetters, policyLetter, min, max);
                passwords.add(passwordObj);
            }
        }

        // Find the valid passwords
        int validPasswords = (int) passwords.stream()
                .filter(password -> password.matchedPolicyLetters >= password.policyMin
                        && password.matchedPolicyLetters <= password.policyMax)
                .count();

        System.out.println(validPasswords);
    }
}
