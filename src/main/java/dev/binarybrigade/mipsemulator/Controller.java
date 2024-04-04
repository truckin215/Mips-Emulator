package dev.binarybrigade.mipsemulator;

import dev.binarybrigade.mipsemulator.model.MemoryList;
import dev.binarybrigade.mipsemulator.model.MemoryRow;
import dev.binarybrigade.mipsemulator.model.RegisterList;
import dev.binarybrigade.mipsemulator.model.RegisterRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {
    public TableView<RegisterRow> registerTable;
    public TableColumn<RegisterRow, String> registerNameColumn;
    public TableColumn<RegisterRow, Integer> registerValueColumn;
    public TableView<MemoryRow> memoryTable;
    public TableColumn<MemoryRow, Integer> memoryAddressColumn;
    public TableColumn<MemoryRow, Integer> memoryValueColumn;
    public int numberBase;
    MemoryList memoryList = new MemoryList();

    @FXML
    public void initialize() {
        // initialize base to binary
        numberBase = 2;

        // initialize register table
        registerNameColumn.setCellValueFactory(cellData -> cellData.getValue().name);
        registerValueColumn.setCellValueFactory(cellData -> cellData.getValue().value.asObject());
        registerTable.setItems(RegisterList.registerList);

        // initialize memory table
        memoryAddressColumn.setCellValueFactory(cellData -> cellData.getValue().address.asObject());
        memoryValueColumn.setCellValueFactory(cellData -> cellData.getValue().value.asObject());
        memoryTable.setItems(MemoryList.memoryList);
    }





}