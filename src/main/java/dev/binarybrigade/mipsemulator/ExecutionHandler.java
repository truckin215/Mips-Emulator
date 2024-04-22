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
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public static void executeLine() {
        // retrieve the line of code at the program counter
        MemoryRow currentMemoryRow = MemoryList.memoryList.stream().filter(memoryRow -> memoryRow.getAddress() == programCounter.getValue()).findFirst().get();
        String currentWord = currentMemoryRow.getValueAsBinary().get().replaceAll("\\s", "");
        // insert the word into cache memory
        //CacheHandler.insertIntoCache(Integer.parseInt(currentWord, 2));
        CacheHandler.insertIntoCache(Long.parseLong(currentWord, 2));
        // parse the opcode as a decimal value
        int opcode = Integer.parseInt(currentWord.substring(0, 6), 2);
        // parse register numbers as decimal values
        int rTypeArgumentRegister0 = Integer.parseInt(currentWord.substring(6, 11), 2);
        int rTypeArgumentRegister1 = Integer.parseInt(currentWord.substring(11, 16), 2);
        int rTypeDestinationRegister = Integer.parseInt(currentWord.substring(16, 21), 2);
        int iTypeSourceRegister = rTypeArgumentRegister0;
        int iTypeDestinationRegister = rTypeArgumentRegister1;
        int iTypeImmediate = Integer.parseInt(currentWord.substring(16, 32).replaceAll("\\s", ""), 2);

        // int declarations for switch statement
        int result, num0, num1, memoryAddress;

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

                // send int values to the ALU
                AluList.sendToALU(num0);
                AluList.sendToALU(iTypeImmediate);

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
                num0 = RegisterList.registerList.get(rTypeDestinationRegister).getValue();
                num1 = RegisterList.registerList.get(rTypeArgumentRegister0).getValue();

                //send int values to the ALU
                AluList.sendToALU(num0);
                AluList.sendToALU(num1);

                //calculate upper 32 bits and lower 32 bits of result
                long multResult = (long) num0 * num1;
                int hi = (int) (multResult >> 32);
                int lo = (int) (multResult & 2147483647);

                //send result to the hi and lo register
                RegisterList.registerList.get(33).setValue(hi);
                RegisterList.registerList.get(34).setValue(lo);

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
                // clear ALU
                AluList.clearALU();

                // get int values from each register
                num0 = RegisterList.registerList.get(rTypeArgumentRegister0).getValue();
                num1 = RegisterList.registerList.get(rTypeArgumentRegister1).getValue();

                // send int values to the ALU
                AluList.sendToALU(num0);
                AluList.sendToALU(num1);

                // calculate result
                result = num0 ^ num1;

                // send result to destination register
                RegisterList.registerList.get(rTypeDestinationRegister).setValue(result);

                // advance the program counter
                programCounter.setValue(programCounter.getValue() + 4);
                break;
            case XORI:
                // clear ALU
                AluList.clearALU();

                // get int values from each register
                num0 = RegisterList.registerList.get(iTypeSourceRegister).getValue();

                // send int values to the ALU
                AluList.sendToALU(num0);
                AluList.sendToALU(iTypeImmediate);

                // calculate result
                result = num0 ^ iTypeImmediate;

                // send result to destination register
                RegisterList.registerList.get(iTypeDestinationRegister).setValue(result);

                // advance the program counter
                programCounter.setValue(programCounter.getValue() + 4);
                break;
            case MFHI:
                // clear ALU
                AluList.clearALU();

                // send result of hi to destination register
                RegisterList.registerList.get(rTypeDestinationRegister).setValue(RegisterList.registerList.get(33).getValue());

                // advance the program counter
                programCounter.setValue(programCounter.getValue() + 4);
                break;
            case MFLO:
                // clear ALU
                AluList.clearALU();

                // send result of lo to destination register
                RegisterList.registerList.get(rTypeDestinationRegister).setValue(RegisterList.registerList.get(34).getValue());

                // advance the program counter
                programCounter.setValue(programCounter.getValue() + 4);
                break;
            case LW:
                // calculate the memory address
                if (iTypeDestinationRegister != 0) { //offset
                    memoryAddress = RegisterList.registerList.get(iTypeDestinationRegister).getValue() + iTypeImmediate;

                }
                else { // immediate value
                    memoryAddress = iTypeImmediate;
                }
                // get the word from memory
                int loadedWord = MemoryList.memoryList.get(memoryAddress / 4).value.get();
                // load the word into a register
                RegisterList.registerList.get(iTypeSourceRegister).setValue(loadedWord);
                // advance the program counter
                programCounter.setValue(programCounter.getValue() + 4);
                break;
            case SW:
                // calculate the memory address
                if (iTypeDestinationRegister != 0) { //offset
                    memoryAddress = RegisterList.registerList.get(iTypeDestinationRegister).getValue() + iTypeImmediate;
                }
                else { // immediate value
                    memoryAddress = iTypeImmediate;
                }
                // get the word from the register
                int word = RegisterList.registerList.get(iTypeSourceRegister).getValue();
                // store the word in memory
                MemoryList.memoryList.get(memoryAddress / 4).setValue(word);
                // advance the program counter
                programCounter.setValue(programCounter.getValue() + 4);
                break;
            case SLTI:
                // clear ALU
                AluList.clearALU();

                // get source register value
                num0 = RegisterList.registerList.get(iTypeSourceRegister).getValue();

                // compare source register value to immediate and set destination register
                RegisterList.registerList.get(iTypeDestinationRegister).setValue(num0 < iTypeImmediate ? 1 : 0);

                // advance PC
                programCounter.setValue(programCounter.getValue() + 4);
                break;
            case BEQ:
                // first 16 bits for registers, last 16 bits for memory address
                // clear ALU
                AluList.clearALU();

                // get int values from each register
                num0 = RegisterList.registerList.get(rTypeDestinationRegister).getValue();
                num1 = RegisterList.registerList.get(rTypeArgumentRegister0).getValue();

                // get address
                int address = iTypeImmediate;

                // send int values to the ALU
                AluList.sendToALU(num0);
                AluList.sendToALU(num1);

                if (num0 == num1) {
                    programCounter.setValue(address);
                }else{
                    programCounter.setValue(programCounter.getValue() + 4);
                }
            case J:
                // clear ALU
                AluList.clearALU();

                // calculate jump target
                int jumpAddress = Integer.parseInt(currentWord.substring(6),2);

                // shift address left 2 (lowest two bits are '00')
                //int targetAddress = jumpAddress << 2;
                int targetAddress = jumpAddress;

                // set pc to address
                programCounter.setValue(targetAddress);
                break;
        }
    }
}
