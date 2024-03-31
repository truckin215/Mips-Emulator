package dev.binarybrigade.mipsemulator.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MemoryList {
    public ObservableList<MemoryRow> memoryData = FXCollections.observableArrayList();
    private int currentAddress;
    private static final int MAX_MEMORY = 32; // in words

    public MemoryList() {
        for (int i = 0; i < MAX_MEMORY; ++i) {
            memoryData.add(i, new MemoryRow(findAddress(), 0));
        }
    }
    private int findAddress() {
        int nextAddress = currentAddress;
        currentAddress += 4;
        return nextAddress;
    }
}
