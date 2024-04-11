package dev.binarybrigade.mipsemulator;

import dev.binarybrigade.mipsemulator.model.MemoryList;
import dev.binarybrigade.mipsemulator.model.MemoryRow;
import dev.binarybrigade.mipsemulator.model.RegisterList;
import dev.binarybrigade.mipsemulator.model.RegisterRow;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;

public class Controller {
    // Create register table and columns
    public TableView<RegisterRow> registerTable;
    public TableColumn<RegisterRow, String> registerNameColumn;
    public TableColumn<RegisterRow, String> registerValueColumn;

    // Create memory table and columns
    public TableView<MemoryRow> memoryTable;
    public TableColumn<MemoryRow, String> memoryAddressColumn;
    public TableColumn<MemoryRow, String> memoryValueColumn;

    // create a toggle group for the radio buttons
    public ToggleGroup numberBaseGroup;
    // create radio buttons
    public RadioButton binaryRadioButton;
    public RadioButton decmialRadioButton;
    public RadioButton hexRadioButton;

    @FXML
    public void initialize() {
        // add radio buttons to the same group
        numberBaseGroup = new ToggleGroup();
        binaryRadioButton.setToggleGroup(numberBaseGroup);
        decmialRadioButton.setToggleGroup(numberBaseGroup);
        hexRadioButton.setToggleGroup(numberBaseGroup);


        // initialize register table
        registerNameColumn.setCellValueFactory(cellData -> cellData.getValue().name);
        registerValueColumn.setCellValueFactory(cellData -> cellData.getValue().getValueAsBinary());
        registerTable.setItems(RegisterList.registerList);

        // initialize memory table
        new MemoryList();
        memoryAddressColumn.setCellValueFactory(cellData -> cellData.getValue().getAddressAsBinary());
        memoryValueColumn.setCellValueFactory(cellData -> cellData.getValue().getValueAsBinary());
        memoryTable.setItems(MemoryList.memoryList);
    }

    @FXML
    public void binaryView() {
        // initialize register table
        registerValueColumn.setCellValueFactory(cellData -> cellData.getValue().getValueAsBinary());
        registerTable.refresh();

        // initialize memory table
        memoryAddressColumn.setCellValueFactory(cellData -> cellData.getValue().getAddressAsBinary());
        memoryValueColumn.setCellValueFactory(cellData -> cellData.getValue().getValueAsBinary());
        memoryTable.refresh();
    }
    @FXML
    public void decimalView() {
        // initialize register table
        registerValueColumn.setCellValueFactory(cellData -> cellData.getValue().getValueAsDecimal());
        registerTable.refresh();

        // initialize memory table
        memoryAddressColumn.setCellValueFactory(cellData -> cellData.getValue().getAddressAsDecimal());
        memoryValueColumn.setCellValueFactory(cellData -> cellData.getValue().getValueAsDecimal());
        memoryTable.refresh();
    }
    @FXML
    public void hexView() {
        // initialize register table
        registerValueColumn.setCellValueFactory(cellData -> cellData.getValue().getValueAsHex());
        registerTable.refresh();

        // initialize memory table
        memoryAddressColumn.setCellValueFactory(cellData -> cellData.getValue().getAddressAsHex());
        memoryValueColumn.setCellValueFactory(cellData -> cellData.getValue().getValueAsHex());
        memoryTable.refresh();
    }







}