package dev.binarybrigade.mipsemulator.model;

public class Register {
    private String name;
    private Integer value;

    public Register(String name, int value) {
        this.name = name;
        this.value = value;
    }
    public Register(String name) {
        this.name = name;
        this.value = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
