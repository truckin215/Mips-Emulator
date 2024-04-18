package dev.binarybrigade.mipsemulator;

import dev.binarybrigade.mipsemulator.model.*;


@SuppressWarnings("UnnecessaryLocalVariable")
public class ExecutionHandler {
    // give opcode int values a variable
    private static final int ADD = 32, ADDI = 8, AND = 36, ANDI = 12, DIV = 26, MULT = 24, NOR = 39, OR = 37, ORI = 13, SUB = 34, XOR = 38, XORI = 14, MFHI = 16, MFLO = 18, LW = 35, SW = 43, SLTI = 10, BEQ = 4, J = 2;
    // pointer to the PC register
    private static final RegisterRow programCounter = RegisterList.registerList.get(32);

    public ExecutionHandler() {

    }


    // executes one line of MIPS code
    public static void executeLine() {
        // retrieve the line of code at the program counter
        MemoryRow currentMemoryRow = MemoryList.memoryList.stream().filter(memoryRow -> memoryRow.getAddress() == programCounter.getValue()).findFirst().get();
        String currentWord = currentMemoryRow.getValueAsBinary().get().replaceAll("\\s", "");
        // parses the opcode as a decimal value
        int opcode = Integer.parseInt(currentWord.substring(0, 6), 2);
        // parse register numbers
        int rTypeArgumentRegister0 = Integer.parseInt(currentWord.substring(6, 11), 2);
        int rTypeArgumentRegister1 = Integer.parseInt(currentWord.substring(11, 16), 2);
        int rTypeDestinationRegister = Integer.parseInt(currentWord.substring(16, 21), 2);
        int iTypeSourceRegister = rTypeArgumentRegister0;
        int iTypeDestinationRegister = rTypeArgumentRegister1;
        int iTypeImmediate = Integer.parseInt(currentWord.substring(16, 32).replaceAll("\\s", ""), 2);

        // int declarations
        int result, num0, num1;

        switch (opcode) {
            case ADD:
                // clear ALU
                AluList.clearALU();

                // get int values from each register
                num0 = RegisterList.registerList.get(rTypeArgumentRegister0).getValue();
                num1 = RegisterList.registerList.get(rTypeArgumentRegister1).getValue();

                // send int values to the ALU
                AluList.sendToALU(num0);
                AluList.sendToALU(num1);

                // calculate result
                result = num0 + num1;

                // send result to destination register
                RegisterList.registerList.get(rTypeDestinationRegister).setValue(result);

                // advance the program counter
                programCounter.setValue(programCounter.getValue() + 4);
                break;
            case ADDI:
                // clear ALU
                AluList.clearALU();

                // read value from source register
                num0 = RegisterList.registerList.get(iTypeSourceRegister).getValue();

                // perform addi operation
                result = num0 + iTypeImmediate;

                //send result to destination register
                RegisterList.registerList.get(iTypeDestinationRegister).setValue(result);

                //advance PC
                programCounter.setValue(programCounter.getValue() + 4);
                break;
            case AND:
                // Clear ALU
                AluList.clearALU();

                //get int values
                num0 = RegisterList.registerList.get(rTypeArgumentRegister0).getValue();
                num1 = RegisterList.registerList.get(rTypeArgumentRegister1).getValue();

                //send result to destination
                AluList.sendToALU(num0);
                AluList.sendToALU(num1);

                //calculate result
                result = num0 & num1;

                //send result
                RegisterList.registerList.get(rTypeDestinationRegister).setValue(result);

                //advance the program counter
                programCounter.setValue(programCounter.getValue() + 4);
                break;
            case ANDI:
                //Clear ALU
                AluList.clearALU();

                //get int values
                num0 = RegisterList.registerList.get(iTypeSourceRegister).getValue();

                //calculate
                result = num0 & iTypeImmediate;

                // Store the result in the destination register
                RegisterList.registerList.get(iTypeDestinationRegister).setValue(result);

                // Advance the program counter
                programCounter.setValue(programCounter.getValue() + 4);
                break;
            case DIV:
                // clear ALU
                AluList.clearALU();

                //get int values from each register
                num0 = RegisterList.registerList.get(rTypeArgumentRegister0).getValue();
                num1 = RegisterList.registerList.get(rTypeArgumentRegister1).getValue();

                //send int values to the ALU
                AluList.sendToALU(num0);
                AluList.sendToALU(num1);

                //calculate result
                result = num0 / num1;

                //send result to the destination register
                RegisterList.registerList.get(rTypeDestinationRegister).setValue(result);

                // advance the program counter
                programCounter.setValue(programCounter.getValue() + 4);

                break;
            case MULT:
                //clear ALU
                AluList.clearALU();

                //get int values from each register
                num0 = RegisterList.registerList.get(rTypeArgumentRegister0).getValue();
                num1 = RegisterList.registerList.get(rTypeArgumentRegister1).getValue();

                //send int values to the ALU
                AluList.sendToALU(num0);
                AluList.sendToALU(num1);

                //calculate result
                result = num0 * num1;

                //send result to the destination register
                RegisterList.registerList.get(rTypeDestinationRegister).setValue(result);

                // advance the program counter
                programCounter.setValue(programCounter.getValue() + 4);
                break;
            case NOR:
                // clear ALU
                AluList.clearALU();

                //read value from source registers
                num0 = RegisterList.registerList.get(rTypeArgumentRegister0).getValue();
                num1 = RegisterList.registerList.get(rTypeArgumentRegister1).getValue();

                //perform nor operation
                result = ~(num0 | num1);

                //send result to destination register
                RegisterList.registerList.get(rTypeDestinationRegister).setValue(result);

                // advance PC
                programCounter.setValue(programCounter.getValue() + 4);
                break;
            case OR:
                // clear ALU
                AluList.clearALU();

                // get int values from each register
                num0 = RegisterList.registerList.get(rTypeArgumentRegister0).getValue();
                num1 = RegisterList.registerList.get(rTypeArgumentRegister1).getValue();

                // send int values to the ALU
                AluList.sendToALU(num0);
                AluList.sendToALU(num1);

                // calculate result
                result = num0 | num1;

                // send result to destination register
                RegisterList.registerList.get(rTypeDestinationRegister).setValue(result);

                // advance the program counter
                programCounter.setValue(programCounter.getValue() + 4);
                break;
            case ORI:
                // clear ALU
                AluList.clearALU();

                // get int values from each register
                num0 = RegisterList.registerList.get(iTypeSourceRegister).getValue();

                // send int values to the ALU
                AluList.sendToALU(num0);
                AluList.sendToALU(iTypeImmediate);

                // calculate result
                result = num0 | iTypeImmediate;

                // send result to destination register
                RegisterList.registerList.get(iTypeDestinationRegister).setValue(result);

                // advance the program counter
                programCounter.setValue(programCounter.getValue() + 4);
                break;
            case SUB:
                // clear ALU
                AluList.clearALU();

                // get int values from each register
                num0 = RegisterList.registerList.get(rTypeArgumentRegister0).getValue();
                num1 = RegisterList.registerList.get(rTypeArgumentRegister1).getValue();

                // send int values to the ALU
                AluList.sendToALU(num0);
                AluList.sendToALU(num1);

                // calculate result
                result = num0 - num1;

                // send result to destination register
                RegisterList.registerList.get(rTypeDestinationRegister).setValue(result);

                // advance the program counter
                programCounter.setValue(programCounter.getValue() + 4);
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
            case SLTI:
                // code here
                break;
            case BEQ:
                // code here
                break;
            case J:
                // code here
                break;
        }
    }
}
