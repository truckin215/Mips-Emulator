package dev.binarybrigade.mipsemulator;

import dev.binarybrigade.mipsemulator.model.MemoryRow;
import dev.binarybrigade.mipsemulator.model.RegisterList;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;

import static dev.binarybrigade.mipsemulator.model.RegisterList.registerList;


public class FileHandler {
    File currentFile;
    BufferedReader reader;
    public FileHandler(File file) {
        currentFile = file;
        try {
            reader = new BufferedReader(new FileReader(currentFile));
            lineReader();
        }catch (FileNotFoundException e) {
            System.out.println("file not found");
            e.printStackTrace();
        }
    }
    public void lineReader(){
        try {
            int address=0;
            while (reader.ready()) {
                String line = reader.readLine();
                String[] split = line.split(" ");
                if (!split[0].equals("#")){
                    for(int i=0;i<split.length;i++){
                        System.out.println(split[i]);
                    }
                    instructionDecoding(split,address);
                    address=+4;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void instructionDecoding(String[] split,int address){
        int opcode =opcodeValue(split[0]);
        if(!split[2].substring(0, 4).equals("zero")) {
            int register = findRegisterIndex(split[2].substring(0, 2));
        }else{
            int register = 0;
        }
        int constant= Integer.parseInt(split[3]);
        MemoryRow memoryRow = new MemoryRow(address, 16);

    }
    public int findRegisterIndex(String registerName) {
        for (int i = 0; i < registerList.size(); i++) {
            if (registerList.get(i).getName().equals(registerName)) {
                return i; // Return the index if the names match
            }
        }
        return -1; // Return -1 if the register name is not found in the list
    }
    public int opcodeValue(String opcode){
        switch(opcode){
            case "li":
                // Load Instruction
                return (63);
            case "ADD":
                // ADD instruction
                return (32);
            case "ADDI":
                // ADDI instruction
                return (8);
            case "AND":
                // AND instruction
                return (36);
            case "ANDI":
                // ANDI instruction
                return (12);
            case "DIV":
                // DIV instruction
                return (26);
            case "MULT":
                // MULT instruction
                return (24);
            case "NOR":
                // NOR instruction
                return (39);
            case "OR":
                // OR instruction
                return (37);
            case "ORI":
                // ORI instruction
                return (13);
            case "SLL":
                // SLL instruction
                return (0);
            case "SRL":
                // SRL instruction
                return (2);
            case "SUB":
                // SUB instruction
                return (34);
            case "XOR":
                // XOR instruction
                return (38);
            case "XORI":
                // XORI instruction
                return (14);
            case "MFHI":
                // MFHI instruction
                return (16);
            case "MFLO":
                // MFLO instruction
                return (18);
            case "LW":
                // LW instruction
                return (35);
            case "SW":
                // SW instruction
                return (43);
            default:
                // Handle unrecognized opcode
                System.out.println("Unknown opcode: " + opcode);
                break;
        }
        return 0;
    }
}
