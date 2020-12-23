import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day5Part1 {

    public static void main(String[] args) throws IOException {
        var file = "Resources/day5actual.txt";
        int highestSeatID = 0;

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
    
            //    System.out.printf("Row: %d, Column: %d, Seat ID: %d \n", row, column, seatID );
               if(seatID > highestSeatID) { highestSeatID = seatID;}
            }

            System.out.printf("Highest SeatID is %d \n", highestSeatID);
        }
    }
}
