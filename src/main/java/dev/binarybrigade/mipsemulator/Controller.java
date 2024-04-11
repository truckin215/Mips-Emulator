package dev.binarybrigade.mipsemulator;

import dev.binarybrigade.mipsemulator.model.MemoryList;
import dev.binarybrigade.mipsemulator.model.MemoryRow;
import dev.binarybrigade.mipsemulator.model.RegisterList;
import dev.binarybrigade.mipsemulator.model.RegisterRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {
    public TableView<RegisterRow> registerTable;
    public TableColumn<RegisterRow, String> registerNameColumn;
    public TableColumn<RegisterRow, String> registerValueColumn;
    public TableView<MemoryRow> memoryTable;
    public TableColumn<MemoryRow, String> memoryAddressColumn;
    public TableColumn<MemoryRow, String> memoryValueColumn;

    // create a toggle group for the radio buttons
    public ToggleGroup numberBaseGroup;
    // create radio buttons
    @FXML
    RadioButton binaryRadioButton;
    @FXML
    RadioButton decmialRadioButton;
    @FXML
    RadioButton hexRadioButton;

    // global memory list for our memory table
    MemoryList memoryList = new MemoryList();

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