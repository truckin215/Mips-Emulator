package dev.binarybrigade.mipsemulator.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class AluList {
    public static ObservableList<AluRow> aluList = FXCollections.observableArrayList();


    public static void sendToALU(int value) {
        aluList.add(new AluRow(value));
    }

    public static void clearALU() {
        aluList = FXCollections.observableArrayList();
    }



}
