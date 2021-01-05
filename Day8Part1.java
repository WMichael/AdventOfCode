import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day8Part1 {

    /**
     * Operation
     */
    public static class Operation {
        private String operationType;
        private boolean postiveNumber;
        private int value;
        private boolean operationExecuted;

        public Operation(String operationType, boolean postiveNumber, int value) {
            this.operationType = operationType;
            this.postiveNumber = postiveNumber;
            this.value = value;
        }

        public String getOperationType() {
            return operationType;
        }

        public boolean isPostiveNumber() {
            return postiveNumber;
        }

        public int getValue() {
            return value;
        }

        public boolean isOperationExecuted() {
            return operationExecuted;
        }

        public void setOperationExecuted(boolean operationExecuted) {
            this.operationExecuted = operationExecuted;
        }
    }

    public static void main(String[] args) throws IOException {
        List<Operation> listOfOperations = new ArrayList<>();
        int accumulator = 0;
        int programCount = 0;
        boolean endOfExecution = false;

        // Parse in operations
        var file = "Resources/day8actual.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            // Parse list of rules
            while ((line = br.readLine()) != null) {
                // Get attributes of line
                String[] attributes = line.split(" ");
                String operationType = attributes[0];
                Boolean sign = attributes[1].split("")[0].equals("+");
                int value = Integer.parseInt(attributes[1].substring(1));

                // Create and add operation to list
                Operation operation = new Operation(operationType, sign, value);
                listOfOperations.add(operation);
            }
        }

        // Check that operations are present
        if (listOfOperations.isEmpty()) {
            endOfExecution = true;
        }

        // Execute operations in list
        while(!endOfExecution) { 

            if (programCount < listOfOperations.size()){
                var operation = listOfOperations.get(programCount);
    
                // Check if operation has already been executed
                if (operation.isOperationExecuted()) {
                    endOfExecution = true;
                } else {
                    switch (operation.getOperationType()) {
                        // No operation
                        case "nop":
                            programCount = programCount + 1;
                            break;
        
                        // Accumulator operation
                        case "acc":
                            if (operation.isPostiveNumber()) {
                                accumulator = accumulator + operation.getValue();
                            } else {
                                accumulator = accumulator - operation.getValue();
                            }
                            programCount = programCount + 1;
                            break;
        
                        // Jump line operation
                        case "jmp":
                            if (operation.isPostiveNumber()) { 
                                programCount = programCount + operation.getValue();
                            } else {
                                programCount = programCount - operation.getValue();
                            }
                            break;
                    
                        default:
                            break;
                    }
                    
                    // Operation has now been executed
                    operation.setOperationExecuted(true);
                }
            } else {
                endOfExecution = true;
            }
        }

        System.out.printf("The value in the accumulator was: %d \n", accumulator);
    }
}
