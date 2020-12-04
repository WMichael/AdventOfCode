import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Day1Part1 {
    public static void main(String[] args) throws FileNotFoundException {
        var list = new ArrayList<Integer>();
        var file = "Resources/day1actual.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(Integer.parseInt(line));
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }

        // Iterate through list
        for (Integer currentNum : list) {
            var foundMatch = false;
            var numberToMatch = 2020 - currentNum;
            var countNeeded = numberToMatch == 1010 ? 2 : 1;

            if(numberToMatch > 0) {
                var matchingCount = list.stream().filter(num -> num == numberToMatch).count();
                if(matchingCount >= countNeeded) {
                    foundMatch = true;
                    int sum = currentNum * numberToMatch;
                    System.out.println(sum);
                }
            }
            if (foundMatch) break;
        }
    }
}
