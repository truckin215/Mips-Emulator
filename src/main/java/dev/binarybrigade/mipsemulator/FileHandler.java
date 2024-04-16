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

                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void instructionDecoding(String Line){

    }
}
