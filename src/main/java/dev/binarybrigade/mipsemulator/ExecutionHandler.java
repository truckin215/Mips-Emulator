package dev.binarybrigade.mipsemulator;

import dev.binarybrigade.mipsemulator.model.MemoryList;
import dev.binarybrigade.mipsemulator.model.MemoryRow;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Optional;

public class ExecutionHandler {
    private static final int ADD = 32, ADDI = 8, AND = 36, ANDI = 12, DIV = 26, MULT = 24, NOR = 39, OR = 37, ORI = 13, SLL = 0, SRL = 2, SUB = 34, XOR = 38, XORI = 14, MFHI = 16, MFLO = 18, LW = 35, SW = 43;
    private static SimpleIntegerProperty programCounter;

    public ExecutionHandler() {

    }


    // executes one line of MIPS code
    public static void executeLine() {
        // retrieve the line of code at the program counter
        MemoryRow currentWord = MemoryList.memoryList.stream().filter(memoryRow -> memoryRow.getAddress() == programCounter.get()).findFirst().get();
        String currentLine = String.valueOf(currentWord.getValueAsBinary());
        // parses the opcode as a decimal value
        int opcode = Integer.parseInt(currentLine.substring(0, 5), 2);

        switch (opcode) {
            case ADD:
                // code here
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
