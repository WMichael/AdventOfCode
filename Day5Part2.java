import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Day5Part2 {

    public static void main(String[] args) throws IOException {
        var file = "Resources/day5actual.txt";
        int highestSeatID = 0;
        Boolean[][] seats = new Boolean[128][8];
        Arrays.stream(seats).forEach(a -> Arrays.fill(a, Boolean.FALSE));

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            
            String line;
            while((line = br.readLine()) != null) {
                int seatID; 
                String[] chars = line.split("");
              
                // Find row
                int minRow = 0;
                int maxRow = 127;
                int row = 0;
                for (int i = 0; i < 6; i++) {
                    int halfOfRange = (int) Math.ceil((maxRow - minRow) / 2.0);
                    if (chars[i].equals("F")) {
                        maxRow = maxRow - halfOfRange;
                    } else {
                        minRow = minRow + halfOfRange;
                    }
                }
                row = chars[6].equals("F") ? minRow : maxRow;

                // Find column
                int minColumn = 0;
                int maxColumn = 7;
                int column = 0;
                for (int i = 7; i < 9; i++) {
                    int halfOfRange = (int) Math.ceil((maxColumn - minColumn) / 2.0);
                    if (chars[i].equals("L")) {
                        maxColumn = maxColumn - halfOfRange;
                    } else {
                        minColumn = minColumn + halfOfRange;
                    }
                }
                column = chars[9].equals("L") ? minColumn : maxColumn;

                // Calculate seatID
                seatID = (row * 8) + column;
                
                // Set the highestSeatID
               if(seatID > highestSeatID) { highestSeatID = seatID;}

               // Set seat in seats array to true for row & column
               seats[row][column] = true;

               
            }

            // Print the Row, Column and Seat ID of all empty seats
            System.out.println("Empty seats: ");
            for (int i = 0; i < seats.length; i++) {
                for (int j = 0; j < seats[i].length; j++) {
                    if(!seats[i][j] && seats[i][j-1] && seats[i][j+1]) {
                        System.out.printf("Row: %d, Column: %d, Seat ID: %d \n", i, j, (i * 8) + j);

                        // Shows all empty seats, but the correct seat will be the seat with a seat filled before and after
                        // the correct one. 
                    }
                }
            }
        }

    }
}
