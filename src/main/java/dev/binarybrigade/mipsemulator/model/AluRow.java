package dev.binarybrigade.mipsemulator.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class AluRow {
    public SimpleIntegerProperty value;

    public AluRow() {
        this.value = new SimpleIntegerProperty();
    }
    public AluRow(int value) {
        this.value = new SimpleIntegerProperty();
        this.setValue(value);
    }

    public void setValue(int value) {
        this.value.set(value);
    }

    public SimpleStringProperty getValueAsBinary() {
        // convert the int to a binary string
        String binaryString = Integer.toBinaryString(value.get());
        // pad the string with 0s to be 32 characters long
        binaryString = "00000000000000000000000000000000".substring(binaryString.length()) + binaryString;
        // insert a space every 8 characters
        binaryString = binaryString.replaceAll("(.{8})", "$1 ");
        // return as a property
        return new SimpleStringProperty(binaryString);
    }
    public SimpleStringProperty getValueAsDecimal() {
        // pad the string with an appropriate # of 0s and return
        return new SimpleStringProperty(String.format("%010d", value.get()));
    }
    public SimpleStringProperty getValueAsHex() {
        // pad the string with an appropriate # of 0s, format as hex, and return
        return new SimpleStringProperty(String.format("%08x", value.get()));
    }
}
