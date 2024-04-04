package dev.binarybrigade.mipsemulator.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RegisterRow {
    public final SimpleStringProperty name;
    public final SimpleIntegerProperty value;

    public RegisterRow(String name, int value) {
        this.name = new SimpleStringProperty(name);
        this.value = new SimpleIntegerProperty(value);
    }
    public RegisterRow(String name) {
        this.name = new SimpleStringProperty(name);
        this.value = new SimpleIntegerProperty(0);
    }

    public String getName() {
        return name.getValue();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getValue() {
        return value.getValue();
    }

    public void setValue(int value) {
        this.value.set(value);
    }
}
