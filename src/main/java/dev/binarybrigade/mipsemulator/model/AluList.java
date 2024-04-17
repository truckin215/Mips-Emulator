package dev.binarybrigade.mipsemulator.model;

import dev.binarybrigade.mipsemulator.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class AluList {
    public static ObservableList<AluRow> aluList = FXCollections.observableArrayList();


    public static void sendToALU(int value) {
        aluList.add(new AluRow(value));
    }

    public static void clearALU() {
        aluList = FXCollections.observableArrayList();
    }



}
