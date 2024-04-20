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

    public long getData(int index) {
        return entries.get(index).getData();
    }

    public void loadData(long data) {
        if (data == 0) {
            return;
        }
        int index = (int) (data % maxIndex);
        entries.set(index, new CacheRow(data, maxIndex));
    }

    public boolean miss(long data) {
        long index = data % maxIndex;
        return !(entries.get((int) index).data.get() == data);
    }

    public int getMaxIndex() {
        return maxIndex;
    }


}
