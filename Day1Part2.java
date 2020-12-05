import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Day1Part2 {
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
            Integer num1 = currentNum;
            Integer num2;
            Integer num3;

            for (Integer number : list) {
                var numberToMatch = 2020 - (num1 + number);
                if (numberToMatch > 0){
                    var matchingCount = list.stream().filter(num -> num == numberToMatch).count();
                    var countNeeded = numberToMatch == number ? 2 : 1;
                    if(matchingCount >= countNeeded) {
                        foundMatch = true;
                        num2 = number; 
                        num3 = numberToMatch;
                        int sum = num1 * num2 * num3;
                        System.out.println(sum);
                    }
                    if (foundMatch) break;
                }
            }
            if (foundMatch) break;
        }
    }
}
