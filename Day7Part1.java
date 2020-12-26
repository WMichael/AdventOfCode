import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day7Part1 {
    // Hashmap - Key is colour, value is list of colours contained
    static HashMap<String, ArrayList<String>> colouredBags = new HashMap<>();

    public static void main(String[] args) throws IOException {

        var file = "Resources/day7actual.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            // Parse list of rules
            while ((line = br.readLine()) != null) {
                var seperatedLine = line.split(" ");
                var colourName = seperatedLine[0] + " " + seperatedLine[1];
                var innerColouredBags = new ArrayList<String>();
                int numOfInnerColouredBags = (seperatedLine.length - 4) / 4;

                // Gathering inner coloured bags
                for (int i = 0; i < numOfInnerColouredBags; i++) {
                    innerColouredBags.add(seperatedLine[5 + (4 * i)] + " " + seperatedLine[(5 + (4 * i)) + 1]);
                }

                // Adding to map of coloured bags
                colouredBags.put(colourName, innerColouredBags);
            }

            int numOfBagsWithAtleast1GoldBag = 0;
            for (var colouredBag : colouredBags.entrySet()) {
                boolean atleastOneGoldBag = goldInInnerBags(colouredBag.getValue());
                if (atleastOneGoldBag) {
                    numOfBagsWithAtleast1GoldBag = numOfBagsWithAtleast1GoldBag + 1;
                }
            }
            
            System.out.println(numOfBagsWithAtleast1GoldBag);
        }
    }

    // Recursive function - Checking inner bags for atleast one gold bag
    static boolean goldInInnerBags(ArrayList<String> innerBags) {
        // if the inner bags are atleast 1, then check inner bags otherwise return false.
        if(innerBags.isEmpty()) {
            // if there is a bag that is shiny gold coloured then return true
            if(innerBags.contains("shiny gold")) {
                return true;
            // else look through the second layer of inner bags
            } else {
                boolean goldBagFound = false;
                int iterator = 0;
                // recursively iterate through inner bags until reached the end
                // return if a gold bag is found.
                while (!goldBagFound && iterator < innerBags.size()) {
                    goldBagFound = goldInInnerBags(colouredBags.get(innerBags.get(iterator)));
                    iterator = iterator + 1;
                }
                return goldBagFound;
            }
        } else {
            return false;
        }
    }
}
