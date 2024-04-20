package dev.binarybrigade.mipsemulator;

import dev.binarybrigade.mipsemulator.model.*;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;

import java.io.File;

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
    // Create L1 table and column
    public TableView<CacheRow> L1Table;
    public TableColumn<CacheRow, String> L1CacheIndexColumn;
    public TableColumn<CacheRow, String> L1CacheValidColumn;
    public TableColumn<CacheRow, String> L1CacheTagColumn;
    public TableColumn<CacheRow, String> L1CacheDataColumn;

    // Create L2 table and column
    public TableView<CacheRow> L2Table;
    public TableColumn<CacheRow, String> L2CacheIndexColumn;
    public TableColumn<CacheRow, String> L2CacheValidColumn;
    public TableColumn<CacheRow, String> L2CacheTagColumn;
    public TableColumn<CacheRow, String> L2CacheDataColumn;

    // Create L3 table and column
    public TableView<CacheRow> L3Table;
    public TableColumn<CacheRow, String> L3CacheIndexColumn;
    public TableColumn<CacheRow, String> L3CacheValidColumn;
    public TableColumn<CacheRow, String> L3CacheTagColumn;
    public TableColumn<CacheRow, String> L3CacheDataColumn;


    // create a toggle group for the radio buttons
    public ToggleGroup numberBaseGroup;
    // create radio buttons
    public RadioButton binaryRadioButton;
    public RadioButton decmialRadioButton;
    public RadioButton hexRadioButton;

    // hit rate label
    public Label hitRateLabel;

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
        registerValueColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        registerTable.setItems(RegisterList.registerList);
        setRegisterEditListener();

        // initialize memory table with binary values
        new MemoryList();
        memoryAddressColumn.setCellValueFactory(cellData -> cellData.getValue().getAddressAsBinary());
        memoryValueColumn.setCellValueFactory(cellData -> cellData.getValue().getValueAsBinary());
        memoryValueColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        memoryTable.setItems(MemoryList.memoryList);
        setMemoryEditListener();

        // initialize alu table with binary values
        aluColumn.setCellValueFactory(cellData -> cellData.getValue().getValueAsBinary());
        aluTable.setItems(AluList.aluList);

        // initialize L1, L2, and L3 cache to binary values

        L1CacheIndexColumn.setCellValueFactory(cellData -> cellData.getValue().getIndexAsBinary());
        L1CacheValidColumn.setCellValueFactory(cellData -> cellData.getValue().getValidAsStringProperty());
        L1CacheTagColumn.setCellValueFactory(cellData -> cellData.getValue().getTagAsBinary());
        L1CacheDataColumn.setCellValueFactory(cellData -> cellData.getValue().getDataAsBinary());
        L1Table.setItems(CacheHandler.L1.entries);

        L2CacheIndexColumn.setCellValueFactory(cellData -> cellData.getValue().getIndexAsBinary());
        L2CacheValidColumn.setCellValueFactory(cellData -> cellData.getValue().getValidAsStringProperty());
        L2CacheTagColumn.setCellValueFactory(cellData -> cellData.getValue().getTagAsBinary());
        L2CacheDataColumn.setCellValueFactory(cellData -> cellData.getValue().getDataAsBinary());
        L2Table.setItems(CacheHandler.L2.entries);

        L3CacheIndexColumn.setCellValueFactory(cellData -> cellData.getValue().getIndexAsBinary());
        L3CacheValidColumn.setCellValueFactory(cellData -> cellData.getValue().getValidAsStringProperty());
        L3CacheTagColumn.setCellValueFactory(cellData -> cellData.getValue().getTagAsBinary());
        L3CacheDataColumn.setCellValueFactory(cellData -> cellData.getValue().getDataAsBinary());
        L3Table.setItems(CacheHandler.L3.entries);

    }

    @FXML
    public void step() {
        ExecutionHandler.executeLine();
        hitRateLabel.setText(String.format("%.2f", CacheHandler.getHitRate() * 100));
        registerTable.refresh();
        aluTable.setItems(AluList.aluList);
        aluTable.refresh();
        memoryTable.refresh();
        L1Table.refresh();
        L2Table.refresh();
        L3Table.refresh();
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

        // initialize ALU table
        aluColumn.setCellValueFactory(cellData -> cellData.getValue().getValueAsBinary());
        aluTable.refresh();

        // initialize cache tables
        L1CacheIndexColumn.setCellValueFactory(cellData -> cellData.getValue().getIndexAsBinary());
        L1CacheValidColumn.setCellValueFactory(cellData -> cellData.getValue().getValidAsStringProperty());
        L1CacheTagColumn.setCellValueFactory(cellData -> cellData.getValue().getTagAsBinary());
        L1CacheDataColumn.setCellValueFactory(cellData -> cellData.getValue().getDataAsBinary());
        L1Table.refresh();

        L2CacheIndexColumn.setCellValueFactory(cellData -> cellData.getValue().getIndexAsBinary());
        L2CacheValidColumn.setCellValueFactory(cellData -> cellData.getValue().getValidAsStringProperty());
        L2CacheTagColumn.setCellValueFactory(cellData -> cellData.getValue().getTagAsBinary());
        L2CacheDataColumn.setCellValueFactory(cellData -> cellData.getValue().getDataAsBinary());
        L2Table.refresh();

        L3CacheIndexColumn.setCellValueFactory(cellData -> cellData.getValue().getIndexAsBinary());
        L3CacheValidColumn.setCellValueFactory(cellData -> cellData.getValue().getValidAsStringProperty());
        L3CacheTagColumn.setCellValueFactory(cellData -> cellData.getValue().getTagAsBinary());
        L3CacheDataColumn.setCellValueFactory(cellData -> cellData.getValue().getDataAsBinary());
        L3Table.refresh();
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

        // initialize ALU table
        aluColumn.setCellValueFactory(cellData -> cellData.getValue().getValueAsDecimal());
        aluTable.refresh();

        // initialize cache tables
        L1CacheIndexColumn.setCellValueFactory(cellData -> cellData.getValue().getIndexAsDecimal());
        L1CacheValidColumn.setCellValueFactory(cellData -> cellData.getValue().getValidAsStringProperty());
        L1CacheTagColumn.setCellValueFactory(cellData -> cellData.getValue().getTagAsDecimal());
        L1CacheDataColumn.setCellValueFactory(cellData -> cellData.getValue().getDataAsDecimal());
        L1Table.refresh();

        L2CacheIndexColumn.setCellValueFactory(cellData -> cellData.getValue().getIndexAsDecimal());
        L2CacheValidColumn.setCellValueFactory(cellData -> cellData.getValue().getValidAsStringProperty());
        L2CacheTagColumn.setCellValueFactory(cellData -> cellData.getValue().getTagAsDecimal());
        L2CacheDataColumn.setCellValueFactory(cellData -> cellData.getValue().getDataAsDecimal());
        L2Table.refresh();

        L3CacheIndexColumn.setCellValueFactory(cellData -> cellData.getValue().getIndexAsDecimal());
        L3CacheValidColumn.setCellValueFactory(cellData -> cellData.getValue().getValidAsStringProperty());
        L3CacheTagColumn.setCellValueFactory(cellData -> cellData.getValue().getTagAsDecimal());
        L3CacheDataColumn.setCellValueFactory(cellData -> cellData.getValue().getDataAsDecimal());
        L3Table.refresh();
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

        // initialize ALU table
        aluColumn.setCellValueFactory(cellData -> cellData.getValue().getValueAsHex());
        aluTable.refresh();

        // initialize cache tables
        L1CacheIndexColumn.setCellValueFactory(cellData -> cellData.getValue().getIndexAsHex());
        L1CacheValidColumn.setCellValueFactory(cellData -> cellData.getValue().getValidAsStringProperty());
        L1CacheTagColumn.setCellValueFactory(cellData -> cellData.getValue().getTagAsHex());
        L1CacheDataColumn.setCellValueFactory(cellData -> cellData.getValue().getDataAsHex());
        L1Table.refresh();

        L2CacheIndexColumn.setCellValueFactory(cellData -> cellData.getValue().getIndexAsHex());
        L2CacheValidColumn.setCellValueFactory(cellData -> cellData.getValue().getValidAsStringProperty());
        L2CacheTagColumn.setCellValueFactory(cellData -> cellData.getValue().getTagAsHex());
        L2CacheDataColumn.setCellValueFactory(cellData -> cellData.getValue().getDataAsHex());
        L2Table.refresh();

        L3CacheIndexColumn.setCellValueFactory(cellData -> cellData.getValue().getIndexAsHex());
        L3CacheValidColumn.setCellValueFactory(cellData -> cellData.getValue().getValidAsStringProperty());
        L3CacheTagColumn.setCellValueFactory(cellData -> cellData.getValue().getTagAsHex());
        L3CacheDataColumn.setCellValueFactory(cellData -> cellData.getValue().getDataAsHex());
        L3Table.refresh();
    }


    public void openFileChooser() {
        //creates the FileChooser
        FileChooser browser = new FileChooser();
        browser.setTitle("Open File");

        // set the directory
        browser.setInitialDirectory(new File(System.getProperty("user.dir")));

        //Limit by extension
        browser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text/MIPS Files","*.txt","*.s","*.asm"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        //opens file browser
        File file = browser.showOpenDialog(null);
        new FileHandler(file);
        memoryTable.refresh();
    }

    @FXML
    public void setRegisterEditListener() {
        registerValueColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<RegisterRow, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<RegisterRow, String> event) {
                if (numberBaseGroup.getSelectedToggle().toString().contains("Decimal")) {
                    int newValue = Integer.parseInt(event.getNewValue().replaceAll(" ", ""));
                    event.getRowValue().setValue(newValue);
                }
                else if (numberBaseGroup.getSelectedToggle().toString().contains("Binary")) {
                    int newValue = Integer.parseInt(event.getNewValue().replaceAll(" ", ""), 2);
                    event.getRowValue().setValue(newValue);
                }
                else if (numberBaseGroup.getSelectedToggle().toString().contains("Hex")) {
                    int newValue = Integer.parseInt(event.getNewValue().replaceAll(" ", ""), 16);
                    event.getRowValue().setValue(newValue);
                }
                registerTable.refresh();
            }
        });
    }
    @FXML
    public void setMemoryEditListener() {
        memoryValueColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<MemoryRow, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<MemoryRow, String> event) {
                if (numberBaseGroup.getSelectedToggle().toString().contains("Decimal")) {
                    int newValue = Integer.parseInt(event.getNewValue().replaceAll(" ", ""));
                    event.getRowValue().setValue(newValue);
                }
                else if (numberBaseGroup.getSelectedToggle().toString().contains("Binary")) {
                    int newValue = Integer.parseInt(event.getNewValue().replaceAll(" ", ""), 2);
                    event.getRowValue().setValue(newValue);
                }
                else if (numberBaseGroup.getSelectedToggle().toString().contains("Hex")) {
                    int newValue = Integer.parseInt(event.getNewValue().replaceAll(" ", ""), 16);
                    event.getRowValue().setValue(newValue);
                }
                memoryTable.refresh();
            }
        });
    }
}