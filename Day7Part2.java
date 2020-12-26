import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day7Part2 {
    // Hashmap - Key is colour, value is list of colours contained
    static HashMap<String, ArrayList<ColouredBag>> colouredBags = new HashMap<>();
    static int innerBagsCount = 0;

    /**
     * Inner class for coloured bag
     */
    public static class ColouredBag {
        String colour;
        int numberOfBags;

        public ColouredBag(String colour, int numberOfBags) {
            this.colour = colour;
            this.numberOfBags = numberOfBags;
        }
    }

    public static void main(String[] args) throws IOException {

        var file = "Resources/day7test.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            // Parse list of rules
            while ((line = br.readLine()) != null) {
                var seperatedLine = line.split(" ");
                var colourName = seperatedLine[0] + " " + seperatedLine[1];
                var innerColouredBags = new ArrayList<ColouredBag>();
                int numOfInnerColouredBags = (seperatedLine.length - 4) / 4;

                // Gathering inner coloured bags
                for (int i = 0; i < numOfInnerColouredBags; i++) {
                    String name = seperatedLine[5 + (4 * i)] + " " + seperatedLine[(5 + (4 * i)) + 1];
                    ColouredBag bag = new ColouredBag(name, Integer.parseInt(seperatedLine[(4 + (4 * i))]));
                    innerColouredBags.add(bag);
                }

                // Adding to map of coloured bags
                colouredBags.put(colourName, innerColouredBags);

            }


            // Count how many individual bags are inside the shiny gold bag
            for (ColouredBag bag : colouredBags.get("shiny gold")) {
                countInnerBags(colouredBags.get(bag.colour), bag.numberOfBags);
            }
            // countInnerBags(colouredBags.get("shiny gold"), 1);
            System.out.println(innerBagsCount);
        }
    }
    static void countInnerBags(ArrayList<ColouredBag> innerBags, int multipler) {
        if(!innerBags.isEmpty()) { 
            innerBagsCount = innerBagsCount + (innerBags.size() * multipler);
            for (int i = 0; i < innerBags.size(); i++) {
                countInnerBags(colouredBags.get(innerBags.get(i).colour), innerBags.get(i).numberOfBags * multipler);
            }
        }
    }
}
