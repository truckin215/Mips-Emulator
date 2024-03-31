package dev.binarybrigade.mipsemulator.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Register {
    private final SimpleStringProperty name;
    private final SimpleIntegerProperty value;

    public Register(String name, int value) {
        this.name = new SimpleStringProperty(name);
        this.value = new SimpleIntegerProperty(value);
    }
    public Register(String name) {
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
