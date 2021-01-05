import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day9Part1 {
    
    static List<Long> listOfNumbers = new ArrayList<Long>();
    static int preambleSize = 25;

    public static void main(String[] args) throws IOException {

        // Parse in numbers
        var file = "Resources/day9actual.txt";
        parseNumbers(file);

        // Return the first invalid number
        Long firstInvalidNumber = firstInvalidNumber();

        System.out.println(firstInvalidNumber);

    }

    private static Long firstInvalidNumber() {
        Long invalidNumber = -1l;

        // Go through list of numbers starting with number after preamble list.
        for (int i = preambleSize; i < listOfNumbers.size(); i++) {
            Long numberToMatch = listOfNumbers.get(i);
            int startRange = i - preambleSize;
            int endRange = (i - preambleSize) + preambleSize;
            boolean invalidNumberFound = true;

            // Check different combinations for a match
            for(int j = startRange; j <= endRange; j++) {
                for(int k = startRange; k <= endRange; k++) {
                    // If not looking at the same iterators
                    if(j != k) {
                        Long number1 = listOfNumbers.get(j);
                        Long number2 = listOfNumbers.get(k);

                        // If atleast one combination matches the number then invalidNumberFound is set to false.
                        if(numberToMatch == number1 + number2) {
                            invalidNumberFound = false;
                        }
                    }
                }
            }

            if(invalidNumberFound) {
                invalidNumber = numberToMatch; 
            }
        }

        return invalidNumber;
    }

    static void parseNumbers(String file) throws NumberFormatException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            // Parse list of rules
            while ((line = br.readLine()) != null) {
                listOfNumbers.add(Long.parseLong(line));
            }
        }
    }


}
