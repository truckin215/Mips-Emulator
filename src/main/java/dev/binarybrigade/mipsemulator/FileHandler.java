package dev.binarybrigade.mipsemulator;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;


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
            while (reader.ready()) {
                String line = reader.readLine();
                String[] split = line.split(" ");
                if (!split[0].equals("#")){
                    for(int i=0;i<split.length;i++){
                        System.out.println(split[i]);
                    }
                    instructionDecoding(split);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void instructionDecoding(String[] split){

        final int ADD = 32, ADDI = 8, AND = 36, ANDI = 12, DIV = 26, MULT = 24, NOR = 39, OR = 37, ORI = 13, SLL = 0, SRL = 2, SUB = 34, XOR = 38, XORI = 14, MFHI = 16, MFLO = 18, LW = 35, SW = 43;
        switch(split[0]){
            case "lw":
                // Load Instruction
                break;
            case "ADD":
                // ADD instruction
                break;
            case "ADDI":
                // ADDI instruction
                break;
            case "AND":
                // AND instruction
                break;
            case "ANDI":
                // ANDI instruction
                break;
            case "DIV":
                // DIV instruction
                break;
            case "MULT":
                // MULT instruction
                break;
            case "NOR":
                // NOR instruction
                break;
            case "OR":
                // OR instruction
                break;
            case "ORI":
                // ORI instruction
                break;
            case "SLL":
                // SLL instruction
                break;
            case "SRL":
                // SRL instruction
                break;
            case "SUB":
                // SUB instruction
                break;
            case "XOR":
                // XOR instruction
                break;
            case "XORI":
                // XORI instruction
                break;
            case "MFHI":
                // MFHI instruction
                break;
            case "MFLO":
                // MFLO instruction
                break;
            case "LW":
                // LW instruction
                break;
            case "SW":
                // SW instruction
                break;
            default:
                // Handle unrecognized opcode
                System.out.println("Unknown opcode: " + split[0]);
                break;
        }
    }
}
