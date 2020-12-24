import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Day6Part2 {

    public static void main(String[] args) throws IOException {

        int sumOfCounts = 0;
        
        var file = "Resources/day6actual.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int numOfPeople = 0;
            ArrayList<String> questionsAnswered = new ArrayList<String>(); 

            // Loop through each line
            while((line = br.readLine()) != null) {
                // If line not empty then add 1 to numOfPeople, add all questions answered to questionsAnswered list.
                if(!line.equals("")) {
                    numOfPeople = numOfPeople + 1;
                    var questions = line.split("");
                    questionsAnswered.addAll(Arrays.asList(questions));
                // If blank line then add sum of questions every person answered to sumOfCounts
                } else {
                    sumOfCounts = sumOfCounts + numOfQsEveryoneAnswered(questionsAnswered, numOfPeople);
                    numOfPeople = 0;
                    questionsAnswered.clear();
                }
            }
            // Add last set of questions that everyone answered
            sumOfCounts = sumOfCounts + numOfQsEveryoneAnswered(questionsAnswered, numOfPeople);

            System.out.printf("The sum of counts is: %d \n", sumOfCounts);
        }
    }

    // Takes in a list of questions and number of people answered. 
    // Counts every question that was answered by everyone in that group.
    private static int numOfQsEveryoneAnswered(ArrayList<String> questionsAnswered, int numOfPeople) {
        int sumOfCounts = 0;
        var uniqueQuestions = new HashSet<String>(questionsAnswered);
        for (String string : uniqueQuestions) {
            if(questionsAnswered.stream().filter(item -> item.equals(string)).count() == numOfPeople) {
                sumOfCounts = sumOfCounts + 1;
            }
        }
        return sumOfCounts;
    }
}
