package dev.binarybrigade.mipsemulator.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MemoryList {
    public static ObservableList<MemoryRow> memoryList = FXCollections.observableArrayList();
    private int currentAddress;
    public static final int MAX_MEMORY = 128; // in words

    public MemoryList() {
        for (int i = 0; i < MAX_MEMORY; ++i) {
            memoryList.add(i, new MemoryRow(findAddress(), 0));
        }
    }
    private int findAddress() {
        int nextAddress = currentAddress;
        currentAddress += 4;
        return nextAddress;
    }
}
