package dev.binarybrigade.mipsemulator.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class CacheRow {
    public SimpleIntegerProperty index;
    public SimpleIntegerProperty valid;

    public SimpleLongProperty data;
    public SimpleIntegerProperty tag;
    public CacheRow(int index) {
        this.index = new SimpleIntegerProperty(index);
        this.valid = new SimpleIntegerProperty(0);
        this.data = new SimpleLongProperty();
        this.tag = new SimpleIntegerProperty();

    }
    public CacheRow(long data, int maxIndex) {
        this.data = new SimpleLongProperty(data);

        this.index = new SimpleIntegerProperty((int) (data % maxIndex));
        this.tag = new SimpleIntegerProperty((int) (data / maxIndex));
        this.valid = new SimpleIntegerProperty(1);
    }

    public SimpleStringProperty getValidAsStringProperty() {
        return new SimpleStringProperty(String.valueOf(valid.get()));
    }

    public long getData() {
        return data.get();
    }
    

    public SimpleStringProperty getDataAsBinary() {
        // convert the int to a binary string
        String binaryString = Long.toBinaryString(data.get());
        // pad the string with 0s to be 32 characters long
        binaryString = "00000000000000000000000000000000".substring(binaryString.length()) + binaryString;
        // insert a space every 8 characters
        binaryString = binaryString.replaceAll("(.{8})", "$1 ");
        // return as a property
        return new SimpleStringProperty(binaryString);
    }
    public SimpleStringProperty getDataAsDecimal() {
        // pad the string with an appropriate # of 0s and return
        return new SimpleStringProperty(String.format("%010d", data.get()));
    }
    public SimpleStringProperty getDataAsHex() {
        // pad the string with an appropriate # of 0s, format as hex, and return
        return new SimpleStringProperty(String.format("%08x", data.get()));
    }
    public SimpleStringProperty getIndexAsBinary() {
        // convert the int to a binary string
        String binaryString = Integer.toBinaryString(index.get());
        // pad the string with 0s to be 32 characters long
        binaryString = "000".substring(binaryString.length()) + binaryString;
        // insert a space every 8 characters
        binaryString = binaryString.replaceAll("(.{8})", "$1 ");
        // return as a property
        return new SimpleStringProperty(binaryString);
    }
    public SimpleStringProperty getIndexAsDecimal() {
        // pad the string with an appropriate # of 0s and return
        return new SimpleStringProperty(String.format("%01d", index.get()));
    }
    public SimpleStringProperty getIndexAsHex() {
        // pad the string with an appropriate # of 0s, format as hex, and return
        return new SimpleStringProperty(String.format("%01x", index.get()));
    }
    public SimpleStringProperty getTagAsBinary() {
        // convert the int to a binary string
        String binaryString = Integer.toBinaryString(tag.get());

        // pad the string with 0s to be 32 characters long
        binaryString = "00000000000000000000000000000000".substring(binaryString.length()) + binaryString;
        // insert a space every 8 characters
        binaryString = binaryString.replaceAll("(.{8})", "$1 ");
        // return as a property
        return new SimpleStringProperty(binaryString);
    }
    public SimpleStringProperty getTagAsDecimal() {
        // pad the string with an appropriate # of 0s and return
        return new SimpleStringProperty(String.format("%010d", tag.get()));
    }
    public SimpleStringProperty getTagAsHex() {
        // pad the string with an appropriate # of 0s, format as hex, and return
        return new SimpleStringProperty(String.format("%08x", tag.get()));
    }
}
