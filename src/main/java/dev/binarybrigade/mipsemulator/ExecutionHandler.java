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
                // clear ALU
                AluList.clearALU();

                // get register numbers
               int argReg8 = Integer.parseInt(currentWord.substring(6, 10), 2);
               int argReg9 = Integer.parseInt(currentWord.substring(11, 15), 2);
               int destinationRegDiv= Integer.parseInt(currentWord.substring(16, 20), 2);

               //get int values from each register
               int num8 = RegisterList.registerList.get(argReg8).getValue();
               int num9 = RegisterList.registerList.get(argReg9).getValue();

               //send int values to the ALU
               AluList.sendToALU(num8);
               AluList.sendToALU(num9);

               //calculate result
               int quotient = num8 / num9;

               //send result to the destination register
               RegisterList.registerList.get(destinationRegDiv).setValue(quotient);

                // advance the program counter
                programCounter.setValue(programCounter.getValue() + 4);

                break;
            case MULT:
                //clear ALU
                AluList.clearALU();

                // get register numbers
                int argReg10 = Integer.parseInt(currentWord.substring(6, 10), 2);
                int argReg11 = Integer.parseInt(currentWord.substring(11, 15), 2);
                int destinationRegMult= Integer.parseInt(currentWord.substring(16, 20), 2);

                //get int values from each register
                int num10 = RegisterList.registerList.get(argReg10).getValue();
                int num11 = RegisterList.registerList.get(argReg11).getValue();

                //send int values to the ALU
                AluList.sendToALU(num10);
                AluList.sendToALU(num11);

                //calculate result
                int product = num10 * num11;

                //send result to the destination register
                RegisterList.registerList.get(destinationRegMult).setValue(product);

                // advance the program counter
                programCounter.setValue(programCounter.getValue() + 4);


                break;
            case NOR:
                // code here
                break;
            case OR:
                // clear ALU
                AluList.clearALU();

                // get register numbers
                int argReg16 = Integer.parseInt(currentWord.substring(6, 10), 2);
                int argReg17 = Integer.parseInt(currentWord.substring(11, 15), 2);
                int orDestinationReg = Integer.parseInt(currentWord.substring(16, 20), 2);

                // get int values from each register
                int orNum0 = RegisterList.registerList.get(argReg16).getValue();
                int orNum1 = RegisterList.registerList.get(argReg17).getValue();

                // send int values to the ALU
                AluList.sendToALU(orNum0);
                AluList.sendToALU(orNum1);

                // calculate result
                int orResult = orNum0 | orNum1;

                // send result to destination register
                RegisterList.registerList.get(orDestinationReg).setValue(orResult);

                // advance the program counter
                programCounter.setValue(programCounter.getValue() + 4);
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
                // clear ALU
                AluList.clearALU();

                // get register numbers
                int argReg22 = Integer.parseInt(currentWord.substring(6, 10), 2);
                int argReg23 = Integer.parseInt(currentWord.substring(11, 15), 2);
                int destinationRegSub = Integer.parseInt(currentWord.substring(16, 20), 2);

                // get int values from each register
                int num22 = RegisterList.registerList.get(argReg22).getValue();
                int num23 = RegisterList.registerList.get(argReg23).getValue();

                // send int values to the ALU
                AluList.sendToALU(num22);
                AluList.sendToALU(num23);

                // calculate result
                int difference = num22 + num23;

                // send result to destination register
                RegisterList.registerList.get(destinationRegSub).setValue(difference);

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
        }
    }
}
