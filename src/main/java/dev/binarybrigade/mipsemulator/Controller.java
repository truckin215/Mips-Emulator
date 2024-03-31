package dev.binarybrigade.mipsemulator;

import dev.binarybrigade.mipsemulator.model.MemoryList;
import dev.binarybrigade.mipsemulator.model.MemoryRow;
import dev.binarybrigade.mipsemulator.model.Register;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.stream.Collectors;

public class Controller {
    public TableView<Register> registerTable;
    public TableColumn<Register, String> registerNameColumn;
    public TableColumn<Register, Integer> registerValueColumn;
    public TableView<MemoryRow> memoryTable;
    public TableColumn<MemoryRow, Integer> memoryAddressColumn;
    public TableColumn<MemoryRow, Integer> memoryValueColumn;
    public int numberBase;

    @FXML
    public void initialize() {
        // initialize base to binary
        numberBase = 2;

        // initialize register table
        registerNameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        registerValueColumn.setCellValueFactory(new PropertyValueFactory<>("Value"));
        registerTable.setItems(registerList);

        // initialize memory table
        MemoryList memoryList = new MemoryList();
        memoryAddressColumn.setCellValueFactory(cellData -> cellData.getValue().address.asObject());
        memoryValueColumn.setCellValueFactory(cellData -> cellData.getValue().value.asObject());
        memoryTable.setItems(memoryList.memoryData);
    }




    // used to populate register table
    private ObservableList<Register> registerList = FXCollections.observableArrayList(
            new Register("$zero"),
            new Register("$at"),
            new Register("$v0"),
            new Register("$v1"),
            new Register("$a0"),
            new Register("$a1"),
            new Register("$a2"),
            new Register("$a3"),
            new Register("$t0"),
            new Register("$t1"),
            new Register("$t2"),
            new Register("$t3"),
            new Register("$t4"),
            new Register("$t5"),
            new Register("$t6"),
            new Register("$t7"),
            new Register("$s0"),
            new Register("$s1"),
            new Register("$s2"),
            new Register("$s3"),
            new Register("$s4"),
            new Register("$s5"),
            new Register("$s6"),
            new Register("$s7"),
            new Register("$t8"),
            new Register("$t9"),
            new Register("$k0"),
            new Register("$k1"),
            new Register("$gp"),
            new Register("$sp"),
            new Register("$fp"),
            new Register("$ra"),
            new Register("pc"),
            new Register("hi"),
            new Register("lo")
            );
}