package slot.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class InstructionController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnExit;

    @FXML
    private Label lblInstruction;

    @FXML
    void initialize() {
        assert btnExit != null : "fx:id=\"btnExit\" was not injected: check your FXML file 'Instruction.fxml'.";
        assert lblInstruction != null : "fx:id=\"lblInstruction\" was not injected: check your FXML file 'Instruction.fxml'.";

    }
}