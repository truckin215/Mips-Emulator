package dev.binarybrigade.mipsemulator.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CacheList {
    public ObservableList<CacheRow> entries = FXCollections.observableArrayList();
    private final int maxIndex;
    public CacheList(int maxIndex) {
        this.maxIndex = maxIndex;
        for (int i = 0; i < maxIndex; ++i) {
            entries.add(i, new CacheRow());
        }
    }

    public void add(int data) {
        int index = data % maxIndex;
        entries.set(index, new CacheRow(data, index));
    }
}
