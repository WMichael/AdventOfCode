import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day9Part2 {
    
    static List<Long> listOfNumbers = new ArrayList<Long>();
    static Long part1Answer = 1038347917l;

    public static void main(String[] args) throws IOException {

        // Parse in numbers
        var file = "Resources/day9actual.txt";
        parseNumbers(file);

        // Find contiguous set of atleast 2 numbers that match to answer in part 1.
        Long answer = findSetOfNumbers();

        System.out.printf("The answer is: %d \n", answer);
    }

    static Long findSetOfNumbers() {
        List<Long> tempSet = new ArrayList<>();
        boolean foundSet = false;
        for (int i = 0; i < listOfNumbers.size(); i++) {

            for (int j = i; j < listOfNumbers.size(); j++) {
                // Add to number to set 
                tempSet.add(listOfNumbers.get(j));

                // Check if list sums to answer
                long sum = tempSet.stream().collect(Collectors.summingLong(Long::longValue));
                if(sum == part1Answer) {
                    // Break from loop if correct list is found
                    foundSet = true;
                    break;
                } else if (sum > part1Answer) {
                    tempSet.clear();
                    break;
                }
            }

            // Break from outer loop if set is found.
            if(foundSet) {
                break;
            }
        }

        // Return the sum of the min and max of the set if set is found, else return 0.
        if(foundSet) {
            return Collections.min(tempSet) + Collections.max(tempSet);
        } else {
            return 0l;
        }
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
