package sample;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Controller {

    double x = 0, y = 0;
    @FXML
    private TextField calculatorScreen;
    @FXML
    private String command = "";
    private double result = 0.0;
    private String currentNumbers = "";
    private String operationNumbers = "";

    @FXML
    private void getNumber(Event event) {
        double val = Double.parseDouble(((Button) event.getSource()).getText());
        String valRemoveIndication = String.valueOf(val).replaceAll("([0-9])\\.0+([^0-9]|$)", "$1$2");
        if (command.equals("")) {
            currentNumbers += valRemoveIndication;
        } else {
            operationNumbers += valRemoveIndication;
        }
        calculatorScreen.appendText(valRemoveIndication);
    }

    @FXML
    private void equals() {
        switch (command) {
            case "sub" -> result = Double.parseDouble(currentNumbers) - Double.parseDouble(operationNumbers);
            case "add" -> result = Double.parseDouble(currentNumbers) + Double.parseDouble(operationNumbers);
            case "mul" -> result = Double.parseDouble(currentNumbers) * Double.parseDouble(operationNumbers);
            case "div" -> result = Double.parseDouble(currentNumbers) / Double.parseDouble(operationNumbers);
        }
        calculatorScreen.setText(String.valueOf(result));
    }

    @FXML
    private void subtract() {
        command = "sub";
        calculatorScreen.appendText("-");
    }

    @FXML
    private void multiplication() {
        command = "mul";
        calculatorScreen.appendText("*");
    }

    @FXML
    private void addition() {
        command = "add";
        calculatorScreen.appendText("+");
    }

    @FXML
    private void divide() {
        command = "div";
        calculatorScreen.appendText("÷");
    }

    @FXML
    private void clear() {
        command = "";
        currentNumbers = "";
        operationNumbers = "";
        calculatorScreen.setText("");
    }

    @FXML
    private void floatingPoint() {
        currentNumbers += ".";
        calculatorScreen.appendText(".");
    }


    /* /////////////////////////////////////////////////////////////////
    |  All methods below are used for handling the calculator window   |
    ///////////////////////////////////////////////////////////////// */
    @FXML
    void windowDragged(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    @FXML
    void windowPressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    @FXML
    private void handleClosingButton() {
        Platform.exit();
    }

    @FXML
    private void handleMinimizeButton(ActionEvent event) {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setIconified(true);
    }
}
