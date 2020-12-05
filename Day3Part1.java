import java.io.BufferedReader;
import java.io.FileReader;

public class Day3Part1{
    public static void main(String[] args) throws Exception {
        int right = 1;
        int down = 2;
        int height;
        float width; // Has to be float in order to numberOfRepetions to properly round up.
        String file = "Resources/day3actual.txt";
        char[][] initialMap;
        char[][] fullMap;
        int treesEncountered = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            // Work out height & width
            br.mark(100000);
            height = (int) br.lines().count();
            br.reset();
            br.mark(100000);
            width = br.readLine().length();
            br.reset();

             // Create initial map
             initialMap = new char[height][(int) width];
             int iterator = 0;
             String line;
             while((line = br.readLine()) != null) {
                 initialMap[iterator] = line.toCharArray(); 
                 iterator++;
             }

            // Work out how quickly to get to bottom of map
            int stepsToBottom = height / down;

            // Work out steps to the right
            int stepsToRight = right * stepsToBottom;

            // Number of repetitions of trees
            int numberOfMapReps = (int) Math.ceil(stepsToRight / width);
            
            // Create full map
            fullMap = new char[height][(int) (width * numberOfMapReps)];

            // For number of repitions 
            for (int rep = 0; rep < numberOfMapReps; rep++) {
                // For each row of initialMap
                for (int i = 0; i < initialMap.length; i++) {
                    // For each column of fullMap
                    for (int j = 0; j < initialMap[i].length; j++) {
                        // Column is column of initialMap + (repition * width of Map).
                        // I.e Column 0 of initialMap is copied to columns 0,11,22 for repition.
                        fullMap[i][(int) (j + (rep * width))] = initialMap[i][j];
                    }
                }
            }
            
            // Go through fullMap, mark locations with empty space as O and a tree as X.
            boolean bottomReached = false;
            int currentRow = 0;
            int currentColumn = 0;
            while (!bottomReached) {
                currentRow += down;
                currentColumn += right;
                if(fullMap[currentRow][currentColumn] == '#') {
                    fullMap[currentRow][currentColumn] = 'X';
                    treesEncountered++;
                } else {
                    fullMap[currentRow][currentColumn] = 'O';
                }
                bottomReached = (currentRow == fullMap.length - 1) ? true : false;
            }

            System.out.println(treesEncountered);
        }
        catch (Exception e) {
            throw e;
        }
    }
}
