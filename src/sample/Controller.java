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
    private String currentNumbers = "";

    @FXML
    private void numberSelected(Event event) {
        double val = Double.parseDouble(((Button) event.getSource()).getText());
        try {
            switch (command) {
                case "":
                    String valRemoveIndication = String.valueOf(val).replaceAll("([0-9])\\.0+([^0-9]|$)", "$1$2");
                    String currentNumbersWithoutIndication = currentNumbers.replaceAll("([0-9])\\.0+([^0-9]|$)", "$1$2");
                    currentNumbers = currentNumbersWithoutIndication + valRemoveIndication;
                    calculatorScreen.setText(currentNumbers);
                    break;
                case "sub":
                    double subValue = Double.parseDouble(currentNumbers);
                    subValue -= val;
                    currentNumbers = String.valueOf(subValue);
                    calculatorScreen.setText(String.valueOf(val));
                    command = "";
                    break;
                case "add": {
                    double addValue = Double.parseDouble(currentNumbers);
                    addValue += val;
                    currentNumbers = String.valueOf(addValue);
                    calculatorScreen.setText(String.valueOf(val));
                    command = "";
                    break;
                }
                case "div": {
                    double addValue = Double.parseDouble(currentNumbers);
                    addValue /= val;
                    currentNumbers = String.valueOf(addValue);
                    calculatorScreen.setText(String.valueOf(val));
                    command = "";
                    break;
                }
                case "mul": {
                    double addValue = Double.parseDouble(currentNumbers);
                    addValue *= val;
                    currentNumbers = String.valueOf(addValue);
                    calculatorScreen.setText(String.valueOf(val));
                    command = "";
                    break;
                }
            }
        } catch (NullPointerException ignored) {
        }
    }

    @FXML
    private void equals() {
        calculatorScreen.setText(currentNumbers);
    }

    @FXML
    private void subtract() {
        command = "sub";
        calculatorScreen.setText("-");
    }

    @FXML
    private void multiplication() {
        command = "mul";
        calculatorScreen.setText("*");
    }

    @FXML
    private void addition() {
        command = "add";
        calculatorScreen.setText("+");
    }

    @FXML
    private void divide() {
        command = "div";
        calculatorScreen.setText("÷");
    }

    @FXML
    private void clear() {
        command = "";
        currentNumbers = "";
        calculatorScreen.setText("");
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
