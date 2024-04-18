package dev.binarybrigade.mipsemulator.model;

import dev.binarybrigade.mipsemulator.FileHandler;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MemoryRow {
    public final SimpleIntegerProperty address;
    public SimpleIntegerProperty value;

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

    public SimpleStringProperty getAddressAsBinary() {
        // convert the int to a binary string
        String binaryString = Integer.toBinaryString(address.get());
        // pad the string with 0s to be 32 characters long
        binaryString = "00000000000000000000000000000000".substring(binaryString.length()) + binaryString;
        // insert a space every 8 characters
        binaryString = binaryString.replaceAll("(.{8})", "$1 ");
        // return as a property
        return new SimpleStringProperty(binaryString);

    }
    public SimpleStringProperty getAddressAsDecimal() {
        // pad the string with an appropriate # of 0s and return
        return new SimpleStringProperty(String.format("%010d", address.get()));
    }
    public SimpleStringProperty getAddressAsHex() {
        // pad the string with an appropriate # of 0s, format as hex, and return
        return new SimpleStringProperty(String.format("%08x", address.get()));
    }
    public SimpleStringProperty getValueAsBinary() {
        // convert the int to a binary string
        String binaryString = Integer.toBinaryString(value.get());
        // pad the string with 0s to be 32 characters long
       // binaryString = "00000000000000000000000000000000".substring(binaryString.length()) + binaryString; was causing an exception
        binaryString = FileHandler.binaryFormater(binaryString, 32);
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
