import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day8Part2 {
    static List<Operation> listOfOperations = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        // Parse in operations
        var file = "Resources/day8actual.txt";
        parseInstructions(file);
        
        // Iterate through operations, each iteration change one jmp to nop vice versa. 
        // Run through instructions after change. If endOfExecution is reached then break from loop.
        for (int i = 0; i < listOfOperations.size(); i++) {

            String originalOperation = listOfOperations.get(i).getOperationType();

            // Check the operation isn't acc
            if(!originalOperation.equals("acc")) {
                String switchToOperation = originalOperation.equals("jmp") ? "nop" : "jmp";
    
                // Switch operation type 
                listOfOperations.get(i).setOperationType(switchToOperation);
    
                if(runInstructions(listOfOperations)) {
                    System.out.printf("The operation on line %d was changed to %s from %s \n",i, switchToOperation, originalOperation);
                    break;
                } else { 
                    // Revert operation change
                    listOfOperations.get(i).setOperationType(originalOperation);
                }

                // Reset operation executed
                listOfOperations.forEach(operation -> {
                    operation.setOperationExecuted(false);
                });
            }
        }
    }

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

        public void setOperationType(String operationType) {
            this.operationType = operationType; 
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

    public static void parseInstructions(String file) throws IOException {
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
    }

    public static boolean runInstructions(List<Operation> instructions) {
        int accumulator = 0;
        int programCount = 0;
        boolean endOfExecution = false;
        // Infinite loop assumed if same instruction is run twice.
        boolean infiniteLoop = false;

        // Check that operations are present
        endOfExecution = instructions.isEmpty();

        while (!endOfExecution && !infiniteLoop) {

            if (programCount < instructions.size()) {
                var operation = instructions.get(programCount);

                // Check if operation has already been executed
                if (operation.isOperationExecuted()) {
                    infiniteLoop = true;
                } else {
                    switch (operation.getOperationType()) {
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
                            programCount = programCount + 1;
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

        return endOfExecution;
    }
}
