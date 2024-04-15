package dev.binarybrigade.mipsemulator;

import dev.binarybrigade.mipsemulator.model.*;
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

    // Create ALU table and column
    public TableView<AluRow> aluTable;
    public TableColumn<AluRow, String> aluColumn;

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


        // initialize register table with binary values
        registerNameColumn.setCellValueFactory(cellData -> cellData.getValue().name);
        registerValueColumn.setCellValueFactory(cellData -> cellData.getValue().getValueAsBinary());
        registerTable.setItems(RegisterList.registerList);

        // initialize memory table with binary values
        new MemoryList();
        memoryAddressColumn.setCellValueFactory(cellData -> cellData.getValue().getAddressAsBinary());
        memoryValueColumn.setCellValueFactory(cellData -> cellData.getValue().getValueAsBinary());
        memoryTable.setItems(MemoryList.memoryList);

        // initialize alu table with binary values
        new AluList();
        aluColumn.setCellValueFactory(cellData -> cellData.getValue().getValueAsBinary());
        aluTable.setItems(AluList.aluList);
    }

    // Bound to the binary radio button. Converts memory and register values to binary
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
    // Bound to the decimal radio button. Converts memory and register values to decimal
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
    // Bound to the hexadecimal radio button. Converts memory and register values to hex
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