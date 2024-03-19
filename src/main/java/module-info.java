module dev.binarybrigade.mipsemulator {
    requires javafx.controls;
    requires javafx.fxml;


    opens dev.binarybrigade.mipsemulator to javafx.fxml;
    exports dev.binarybrigade.mipsemulator;
}