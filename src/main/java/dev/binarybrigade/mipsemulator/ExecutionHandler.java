package dev.binarybrigade.mipsemulator;

import dev.binarybrigade.mipsemulator.model.*;


public class ExecutionHandler {
    // give opcode int values a variable
    private static final int ADD = 32, ADDI = 8, AND = 36, ANDI = 12, DIV = 26, MULT = 24, NOR = 39, OR = 37, ORI = 13, SLL = 0, SRL = 2, SUB = 34, XOR = 38, XORI = 14, MFHI = 16, MFLO = 18, LW = 35, SW = 43;
    // pointer to the PC register
    private static final RegisterRow programCounter = RegisterList.registerList.get(30);

    public ExecutionHandler() {

    }


    // executes one line of MIPS code
    public static void executeLine() {
        // retrieve the line of code at the program counter
        MemoryRow currentMemoryRow = MemoryList.memoryList.stream().filter(memoryRow -> memoryRow.getAddress() == programCounter.getValue()).findFirst().get();
        String currentWord = String.valueOf(currentMemoryRow.getValueAsBinary());
        // parses the opcode as a decimal value
        int opcode = Integer.parseInt(currentWord.substring(0, 5), 2);

        switch (opcode) {
            case ADD:
                // clear ALU
                AluList.clearALU();

                // get register numbers
                int argReg0 = Integer.parseInt(currentWord.substring(6, 10), 2);
                int argReg1 = Integer.parseInt(currentWord.substring(11, 15), 2);
                int destinationReg = Integer.parseInt(currentWord.substring(16, 20), 2);

                // get int values from each register
                int num0 = RegisterList.registerList.get(argReg0).getValue();
                int num1 = RegisterList.registerList.get(argReg1).getValue();

                // send int values to the ALU
                AluList.sendToALU(num0);
                AluList.sendToALU(num1);

                // calculate result
                int result = num0 + num1;

                // send result to destination register
                RegisterList.registerList.get(destinationReg).setValue(result);

                // advance the program counter
                programCounter.setValue(programCounter.getValue() + 4);
                break;
            case ADDI:
                // code here
                break;
            case AND:
                // code here
                break;
            case ANDI:
                // code here
                break;
            case DIV:
                // code here
                break;
            case MULT:
                // code here
                break;
            case NOR:
                // code here
                break;
            case OR:
                // code here
                break;
            case ORI:
                // code here
                break;
            case SLL:
                // code here
                break;
            case SRL:
                // code here
                break;
            case SUB:
                // code here
                break;
            case XOR:
                // code here
                break;
            case XORI:
                // code here
                break;
            case MFHI:
                // code here
                break;
            case MFLO:
                // code here
                break;
            case LW:
                // code here
                break;
            case SW:
                // code here
                break;
        }
    }
}
