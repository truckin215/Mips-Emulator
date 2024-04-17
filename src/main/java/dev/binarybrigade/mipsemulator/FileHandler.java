package dev.binarybrigade.mipsemulator;

import dev.binarybrigade.mipsemulator.model.MemoryList;
import dev.binarybrigade.mipsemulator.model.MemoryRow;

import java.io.*;

import static dev.binarybrigade.mipsemulator.model.RegisterList.registerList;


public class FileHandler {
    File currentFile;
    BufferedReader reader;
    public FileHandler(File file) {
        currentFile = file;
        try {
            reader = new BufferedReader(new FileReader(currentFile));
            lineReader();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            e.printStackTrace();
        } catch (Exception e) {
          System.out.println("Issue with file input");
        }
    }
    public void lineReader(){
        try {
            int address=0;
            while (reader.ready()) {
                String line = reader.readLine();
                String[] split = line.split(" ");
                if (!(split[0].startsWith("#")||split[0].isEmpty())){
                    for(int i=0;i<split.length;i++){
                        System.out.println(split[i]);
                    }
                    instructionDecoding(split,address);
                    address += 4;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void instructionDecoding(String[] split,int address){
        int opcode =opcodeValue(split[0]);
        String memoryData= Integer.toBinaryString(opcode);
        String srcReg;
        String targReg;
        String immediate;
        String funct;
        String destinationReg;
        String shfitAmount;
        int[] inputRegisters= new int[10];
        int r=0;//register count
        int constant=0;
        int result=0;
        for(int i=1;i<split.length;i++) {
            if(split[i].startsWith("#")){
                break;
            }
            if(split[i].startsWith("$")) {
                if (!split[1].startsWith("zero")) {
                    inputRegisters[r] = findRegisterIndex(split[i].substring(0, 3));
                } else {
                    inputRegisters[r] = 0;
                }
                r++;
            }else{
               constant= Integer.parseInt(split[i]);
            }
        }
        //memory line builder
        // immediate format(addi): 000000(opcode)+00000(source register)+00000(register target)+0000000000000000(16bit immediate value) register target same as src
        if(!(constant==0)){
            memoryData=binaryFormater(memoryData,6);
            srcReg=Integer.toBinaryString(inputRegisters[0]);
            srcReg=binaryFormater(srcReg,5);
            targReg=srcReg;
            immediate=Integer.toBinaryString(constant);
            immediate=binaryFormater(immediate,16);
            System.out.println(memoryData+" "+srcReg+" "+targReg+" "+immediate);
            memoryData= memoryData+srcReg+targReg+immediate;
            result = (int) Long.parseUnsignedLong(memoryData, 2);

        }else{
        // Register format (add): 000000(opcode)+00000(source register)+00000(register target)+00000(Destination register)+00000(Shiftamount)+000000(funct field opcode field for R-types)
            memoryData=binaryFormater(memoryData,6);
            if(r<2) {
                srcReg = binaryFormater(Integer.toBinaryString(inputRegisters[0]), 5);
                targReg = binaryFormater(Integer.toBinaryString(inputRegisters[1]), 5);
                destinationReg = srcReg;
            }else{
                destinationReg = binaryFormater(Integer.toBinaryString(inputRegisters[0]), 5);
                srcReg = binaryFormater(Integer.toBinaryString(inputRegisters[1]), 5);
                targReg = binaryFormater(Integer.toBinaryString(inputRegisters[2]), 5);
            }
            funct=memoryData;
            shfitAmount=binaryFormater("0",5);//shift not currently supported
            System.out.println(memoryData+" "+srcReg+" "+targReg+" "+destinationReg+" "+shfitAmount+" "+funct);
            memoryData= memoryData+srcReg+targReg+destinationReg+shfitAmount+funct;
            result = (int) Long.parseUnsignedLong(memoryData, 2);///This is a overflow error waiting to happen
        }
        //update memory
        MemoryRow targetMemoryRow = MemoryList.memoryList.stream().filter(memoryRow -> memoryRow.getAddress() == address).findFirst().get();
        targetMemoryRow.setValue(result);

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
        opcode=opcode.toUpperCase();
        switch(opcode){
            case "LI":
                // Load Instruction
                return (31);
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
    public static String binaryFormater(String input, int size){
        //function adds zeros to front of strings to match desired length so 11, with size 5 = 00011
        StringBuilder inputBuilder = new StringBuilder(input);
        for(int n = input.length(); n<size; n++){
            inputBuilder.insert(0, "0");
        }
        input = inputBuilder.toString();
        return(input);
    }
}
