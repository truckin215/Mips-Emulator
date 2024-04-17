package dev.binarybrigade.mipsemulator;

<<<<<<< Updated upstream
=======
import dev.binarybrigade.mipsemulator.model.MemoryList;
import dev.binarybrigade.mipsemulator.model.MemoryRow;

>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
    public void instructionDecoding(String[] split){

        final int ADD = 32, ADDI = 8, AND = 36, ANDI = 12, DIV = 26, MULT = 24, NOR = 39, OR = 37, ORI = 13, SLL = 0, SRL = 2, SUB = 34, XOR = 38, XORI = 14, MFHI = 16, MFLO = 18, LW = 35, SW = 43;
        switch(split[0]){
            case "lw":
                // Load Instruction
                break;
=======
    public void instructionDecoding(String[] split,int address){
        int opcode =opcodeValue(split[0]);
        String memoryData= Integer.toBinaryString(opcode);
        String srcReg;
        String targReg;
        String immediate;
        int[] targetRegister= new int[10];
        int r=0;//register count
        int constant=0;
        int result=0;
        for(int i=1;i<split.length;i++) {
            if(split[i].startsWith("#")){
                break;
            }
            if(split[i].startsWith("$")) {
                if (!split[1].startsWith("zero")) {
                    targetRegister[r] = findRegisterIndex(split[i].substring(0, 3));
                } else {
                    targetRegister[r] = 0;
                }
                r++;
            }else{
               constant= Integer.parseInt(split[i]);
            }
        }
        //memory line builder
        // immediate format(addi): 000000(opcode)+00000(source register)+00000(register target)+0000000000000000(16bit immediate value) register target likley empty
        if(!(constant==0)){
            memoryData=binaryFormater(memoryData,6);
            srcReg=Integer.toBinaryString(targetRegister[0]);
            srcReg=binaryFormater(srcReg,5);
            targReg=srcReg;
            immediate=Integer.toBinaryString(constant);
            immediate=binaryFormater(srcReg,16);
            System.out.println(memoryData+" "+srcReg+" "+targReg+" "+immediate);
            memoryData= memoryData+srcReg+targReg+immediate;
           result=Integer.parseInt(memoryData, 2);
             System.out.println(result);
        }
        //update memory
        MemoryRow targetMemoryRow = MemoryList.memoryList.stream().filter(memoryRow -> memoryRow.getAddress() == address).findFirst().get();
        targetMemoryRow.setValue(result);
        // Register format (add): 000000(opcode)+00000(source register)+00000(register target)+00000(Destination register)+00000(Shiftamount)+00000(funct field opcode field for R-types)
    }
    public int findRegisterIndex(String registerName) {
        for (int i = 0; i < registerList.size(); i++) {
            if (registerList.get(i).getName().equals(registerName)) {
                return i; // Return the index if the names match
            }
        }
        System.out.println("register not found "+registerName);
        return -1; // Return -1 if the register name is not found in the list
    }
    public int opcodeValue(String opcode){
        switch(opcode){
            case "li":
                // Load Instruction is LUI, or load immediate into register
                return (31);
>>>>>>> Stashed changes
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
