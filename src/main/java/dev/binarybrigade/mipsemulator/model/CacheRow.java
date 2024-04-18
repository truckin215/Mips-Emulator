package dev.binarybrigade.mipsemulator.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CacheRow {
    public SimpleIntegerProperty index;
    public SimpleIntegerProperty valid;

    public SimpleIntegerProperty data;
    public SimpleIntegerProperty tag;
    public CacheRow(int index) {
        this.index = new SimpleIntegerProperty(index);
        this.valid = new SimpleIntegerProperty(0);
        this.data = new SimpleIntegerProperty();
        this.tag = new SimpleIntegerProperty();

    }
    public CacheRow(int data, int maxIndex) {
        this.data = new SimpleIntegerProperty(data);
        this.index = new SimpleIntegerProperty(data % maxIndex);
        this.tag = new SimpleIntegerProperty(data / maxIndex);
        this.valid = new SimpleIntegerProperty(1);
    }


    public int getIndex() {
        return index.get();
    }

    public SimpleIntegerProperty indexProperty() {
        return index;
    }

    public void setIndex(int index) {
        this.index.set(index);
    }

    public int getValid() {
        return valid.get();
    }

    public SimpleIntegerProperty validProperty() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid.set(valid);
    }

    public int getTag() {
        return tag.get();
    }

    public SimpleIntegerProperty tagProperty() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag.set(tag);
    }

    public int getData() {
        return data.get();
    }

    public SimpleIntegerProperty dataProperty() {
        return data;
    }

    public void setData(int data) {
        this.data.set(data);
    }

    public SimpleStringProperty getDataAsBinary() {
        // convert the int to a binary string
        String binaryString = Integer.toBinaryString(data.get());
        // pad the string with 0s to be 32 characters long
        binaryString = String.format("%032d", Integer.parseInt(binaryString));
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
        binaryString = String.format("%032d", Integer.parseInt(binaryString));
        // insert a space every 8 characters
        binaryString = binaryString.replaceAll("(.{8})", "$1 ");
        // return as a property
        return new SimpleStringProperty(binaryString);
    }
    public SimpleStringProperty getIndexAsDecimal() {
        // pad the string with an appropriate # of 0s and return
        return new SimpleStringProperty(String.format("%010d", index.get()));
    }
    public SimpleStringProperty getIndexAsHex() {
        // pad the string with an appropriate # of 0s, format as hex, and return
        return new SimpleStringProperty(String.format("%08x", index.get()));
    }
    public SimpleStringProperty getTagAsBinary() {
        // convert the int to a binary string
        String binaryString = Integer.toBinaryString(tag.get());
        // pad the string with 0s to be 32 characters long
        binaryString = String.format("%032d", Integer.parseInt(binaryString));
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
