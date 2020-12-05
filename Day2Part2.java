import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day2Part2 {

    /**
     * Password
     */
    public static class Password {
        public int matchedPolicyLetters; // strips password to count of chars that match the policy letter.
        public String policyLetter;
        public int position1;
        public int position2;

        public Password(int matchedPolicyLetters, String policyLetter, int position1, int position2) {
            this.matchedPolicyLetters = matchedPolicyLetters;
            this.policyLetter = policyLetter;
            this.position1 = position1;
            this.position2 = position2;
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
                int position1 = Integer.parseInt((((String) splitLine[0]).split("-"))[0]);
                int position2 = Integer.parseInt((((String) splitLine[0]).split("-"))[1]);
                String policyLetter = splitLine[1].substring(0, splitLine[1].length() - 1);

                // work out matchedPolicyLetters
                List<String> chars = Arrays.asList(splitLine[2].split(""));
                int matchedPolicyLetters = 0;
                if (chars.get(position1 - 1).equals(policyLetter)) matchedPolicyLetters++;
                if (chars.get(position2 - 1).equals(policyLetter)) matchedPolicyLetters++;
                
                // Add to list
                Password passwordObj = new Password(matchedPolicyLetters, policyLetter, position1, position2);
                passwords.add(passwordObj);
            }
        }

        // Find the valid passwords
        int validPasswords = (int) passwords.stream()
                .filter(password -> password.matchedPolicyLetters == 1)
                .count();

        System.out.println(validPasswords);
    }
}
