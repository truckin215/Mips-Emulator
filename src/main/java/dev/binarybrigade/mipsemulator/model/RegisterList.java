package dev.binarybrigade.mipsemulator.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RegisterList {
    public static ObservableList<RegisterRow> registerList = FXCollections.observableArrayList(
            new RegisterRow("$zero"),
            new RegisterRow("$at"),
            new RegisterRow("$v0"),
            new RegisterRow("$v1"),
            new RegisterRow("$a0"),
            new RegisterRow("$a1"),
            new RegisterRow("$a2"),
            new RegisterRow("$a3"),
            new RegisterRow("$t0"),
            new RegisterRow("$t1"),
            new RegisterRow("$t2"),
            new RegisterRow("$t3"),
            new RegisterRow("$t4"),
            new RegisterRow("$t5"),
            new RegisterRow("$t6"),
            new RegisterRow("$t7"),
            new RegisterRow("$s0"),
            new RegisterRow("$s1"),
            new RegisterRow("$s2"),
            new RegisterRow("$s3"),
            new RegisterRow("$s4"),
            new RegisterRow("$s5"),
            new RegisterRow("$s6"),
            new RegisterRow("$s7"),
            new RegisterRow("$t8"),
            new RegisterRow("$t9"),
            new RegisterRow("$k0"),
            new RegisterRow("$k1"),
            new RegisterRow("$gp"),
            new RegisterRow("$sp", MemoryList.MAX_MEMORY * 4),
            new RegisterRow("$fp"),
            new RegisterRow("$ra"),
            new RegisterRow("pc"),
            new RegisterRow("hi"),
            new RegisterRow("lo")
    );

    public static void updateRegisterValue(int registerNumber, int value) {
        registerList.get(registerNumber).setValue(value);
    }
}
