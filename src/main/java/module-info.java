module dev.binarybrigade.mipsemulator {
    requires javafx.controls;
    requires javafx.fxml;


    opens dev.binarybrigade.mipsemulator to javafx.fxml;
    exports dev.binarybrigade.mipsemulator;
    exports dev.binarybrigade.mipsemulator.model;
    opens dev.binarybrigade.mipsemulator.model to javafx.fxml;
}