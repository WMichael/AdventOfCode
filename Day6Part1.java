import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Day6Part1 {

    public static void main(String[] args) throws IOException {

        int sumOfCounts = 0;
        
        // Parse through each line in the file
        // Add each character to a Hashset
        // If there is a blank space then add size of set to sumOfCounts
        // Go to begining of loop.
        var file = "Resources/day6actual.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            Set<String> questionsAnswered = new HashSet<String>(); 
            while((line = br.readLine()) != null) {
                if(!line.equals("")) {
                    var questions = line.split("");
                    questionsAnswered.addAll(Arrays.asList(questions));
                } else {
                    sumOfCounts = sumOfCounts + questionsAnswered.size();
                    questionsAnswered.clear();
                }
            }
            // Add last set of questions
            sumOfCounts = sumOfCounts + questionsAnswered.size();

            System.out.printf("The sum of counts is: %d \n", sumOfCounts);
        }
    }
}
