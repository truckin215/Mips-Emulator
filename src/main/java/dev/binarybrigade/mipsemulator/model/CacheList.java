package dev.binarybrigade.mipsemulator.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CacheList {
    public ObservableList<CacheRow> entries = FXCollections.observableArrayList();
    private final int maxIndex;
    public CacheList(int maxIndex) {
        this.maxIndex = maxIndex;
        for (int i = 0; i < maxIndex; ++i) {
            entries.add(i, new CacheRow(i));
        }
    }

    public void add(int data) {
        int index = data % maxIndex;
        entries.set(index, new CacheRow(data, index));
    }

    public int getData(int index) {
        return entries.get(index).getData();
    }

    public void loadData(int data) {
        int index = data % maxIndex;
        entries.set(index, new CacheRow(data, maxIndex));
    }

    public boolean miss(int data) {
        int index = data % maxIndex;
        return !(entries.get(index).data.get() == data);
    }

    public int getMaxIndex() {
        return maxIndex;
    }


}
