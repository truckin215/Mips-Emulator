package dev.binarybrigade.mipsemulator;

import dev.binarybrigade.mipsemulator.model.MemoryList;
import dev.binarybrigade.mipsemulator.model.MemoryRow;
import javafx.beans.property.SimpleStringProperty;

import java.io.*;

import static dev.binarybrigade.mipsemulator.model.RegisterList.registerList;


public class FileHandler {
    String [] labels= new String[20];
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
    //    } catch (Exception e) {
        //  System.out.println("Issue with file input");
        }
    }
    public void lineReader(){
        try {
            int address=0;
            int i=0;
            while (reader.ready()) {
                String line = reader.readLine();
                String[] split = line.split(" ");
               top: if(opcodeValue(split[0]) == 100 && !split[0].startsWith("#")){
                    for(int j=0;j<split.length;j++){
                        if() {
                            if (labels[j].equals(split[0])) {
                                final int finalJ = j;
                                int result;
                                MemoryRow targetMemoryRow = MemoryList.memoryList.stream().filter(memoryRow -> memoryRow.getAddress() == Integer.parseInt(labels[finalJ + 1])).findFirst().get();
                                String temp = String.valueOf(targetMemoryRow.getValueAsBinary());
                                temp = temp.substring(0, temp.length() - 16);
                                temp = temp + binaryFormater(Integer.toBinaryString(address), 16);
                                result = Integer.parseInt(temp, 2);
                                targetMemoryRow.setValue(result);
                                break top;
                            }
                        }
                    }
                    labels[i] = split[0];
                    labels[i+1] = String.valueOf(address);
                    i =+ 2;
                }else if (!(split[0].startsWith("#")||split[0].isEmpty()||split[0].startsWith("main"))){
                    for(int r=0;r<split.length;r++){
                        System.out.println(split[r]);
                    }
                    instructionDecoding(split,address);
                    address += 4;
                }
            }
            System.out.println("");
            for (int y=0;y<labels.length;y++){
                System.out.print(labels[y]);
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
        String destinationReg = "";
        String shfitAmount;
        int[] inputRegisters= new int[10];
        int r=0;//register count
        int constant=0;
        int result=0;
        int offset = -1;
       outer: for(int i=1;i<split.length;i++) {
            if(split[i].startsWith("#")){
                break outer;
            }else if(split[i].startsWith("$")) {
                if (!split[i].startsWith("$ze")) {
                    inputRegisters[r] = findRegisterIndex(split[i].substring(0, 3));
                } else {
                    inputRegisters[r] = 0;
                }
                r++;
            }else if (opcode == 35 || opcode == 43){ // lw or sw

                if (Character.isDigit(split[i].charAt(0))) {
                    offset = -1;
                    // parse offset number and register value
                    if (split[i].contains("(")) { // for offset
                        offset = Integer.parseInt(split[i].substring(0, split[i].indexOf('(')));
                        inputRegisters[r] = findRegisterIndex(split[i].substring(split[i].indexOf('$'), split[i].indexOf(")")));
                        constant = offset;
                    }
                    else {
                        try { // for immediate
                            constant = Integer.parseInt(split[i]);
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                else if (Character.isAlphabetic(split[i].charAt(0))) {
                    // treat as label

                }
            }
            else if (!(opcode==4 || opcode==2)) {
               constant= Integer.parseInt(split[i]);
            }
        }
        //memory line builder
        // immediate format(addi): 000000(opcode)+00000(source register)+00000(register target)+0000000000000000(16bit immediate value) register target same as src
        memoryData=binaryFormater(memoryData,6);
        if(!(constant==0) || (offset == 0)){
            if (opcode == 35 || opcode == 43) {
                srcReg = binaryFormater(Integer.toBinaryString(inputRegisters[0]), 5);
                targReg = binaryFormater(Integer.toBinaryString(inputRegisters[1]), 5);
            }
            else {
                srcReg = Integer.toBinaryString(inputRegisters[0]);
                srcReg = binaryFormater(srcReg, 5);
                targReg = srcReg;
            }
            immediate=Integer.toBinaryString(constant);
            immediate=binaryFormater(immediate,16);
            System.out.println(memoryData+" "+srcReg+" "+targReg+" "+immediate);
            memoryData= memoryData+srcReg+targReg+immediate;

        }
        else if(!(opcode==2||opcode==4)){
        // Register format (add): 000000(opcode)+00000(source register)+00000(register target)+00000(Destination register)+00000(Shiftamount)+000000(funct field opcode field for R-types)
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
        }else if(opcode==2){
            //jump code (j): 000000(opcode)+0000000000000000000000000(26bit target address box) note this is a simplified version of the one provided
            boolean found=false;
            boolean adressGiven=true;
            int setAdress=0;
            if(!split[1].equals("")) {
                try {
                setAdress = Integer.parseInt(split[1]);
                } catch (NumberFormatException e) {
                    adressGiven = false;
                }
            }
            if (!adressGiven){
                for(int i=0;i< labels.length;i=+2){
                    if (found) break;
                    if(labels[i].startsWith(split[1])){
                        destinationReg=Integer.toBinaryString(Integer.parseInt(labels[i+1]));
                        found=true;
                    }
                }
            }
            if(found) {
                destinationReg = binaryFormater(destinationReg, 26);
                System.out.println(memoryData+" "+destinationReg);
                memoryData=memoryData+destinationReg;
            }else{
                destinationReg=Integer.toBinaryString(setAdress);
                destinationReg = binaryFormater(destinationReg, 26);
                System.out.println(memoryData+" "+destinationReg);
                memoryData=memoryData+destinationReg;
            }
        }else if(opcode==4){
            //000000(opcode)+00000(first compare)+00000+(second compare)+000000000000000(16bit target address box)
            srcReg = binaryFormater(Integer.toBinaryString(inputRegisters[0]), 5);
            targReg = binaryFormater(Integer.toBinaryString(inputRegisters[1]), 5);
            immediate="0";
            immediate=binaryFormater(immediate,16);
            System.out.println(memoryData+" "+srcReg+" "+targReg+" "+immediate);
        }
        result = (int) Long.parseUnsignedLong(memoryData, 2);///This is a overflow error waiting to happen
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
            case "SLTI":
                // SLTI instruction
                return (10);
            case "SLT":
                //  %d=($s<%t)
                return (42);
            case "BEQ":
                // BEQ instruction
                return (4);
            case "J":
                return (2);
            default:
                // Handle unrecognized opcode
                if(opcode.endsWith(":")){
                    System.out.println("Initialized Label: " + opcode.substring(0,opcode.length()-1));

                    return (100);
                }else {
                    System.out.println("Unknown opcode: " + opcode);
                    return (99);
                }
        }
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
