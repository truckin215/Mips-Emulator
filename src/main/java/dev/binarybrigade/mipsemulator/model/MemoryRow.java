package dev.binarybrigade.mipsemulator.model;

import javafx.beans.property.SimpleIntegerProperty;

public class MemoryRow {
    public final SimpleIntegerProperty address;
    public final SimpleIntegerProperty value;

    public MemoryRow(int address, int value) {
        this.address = new SimpleIntegerProperty(address);
        this.value = new SimpleIntegerProperty(value);
    }

    public int getAddress() {
        return address.get();
    }

    public void setAddress(int address) {
        this.address.set(address);
    }

    public int getValue() {
        return value.get();
    }

    public void setValue(int value) {
        this.value.setValue(value);
    }


}
