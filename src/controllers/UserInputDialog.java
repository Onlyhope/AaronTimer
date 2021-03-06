/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static timer.StartUpConstants.CSS_CLASS_INPUT_BUTTON_HBOX;
import static timer.StartUpConstants.CSS_CLASS_INPUT_LAYOUT_VBOX;
import static timer.StartUpConstants.CSS_CLASS_INPUT_PRIMARY_LAYOUT;
import static timer.StartUpConstants.PATH_CSS;
import static timer.StartUpConstants.PATH_STYLE_SHEET_INPUT_UI;

/**
 *
 * @author Aaron
 */
public class UserInputDialog {

    private String userInput;

    private boolean state;
    private int hours;
    private int minutes;
    private int seconds;
    private int addedTimePool;

    // Components
    private Label title;
    private Label hourInputLabel;
    private Label minuteInputLabel;
    private Label secondInputLabel;
    private TextField hourInputField;
    private TextField minuteInputField;
    private TextField secondInputField;
    private HBox hourContainer;
    private HBox minuteContainer;
    private HBox secondContainer;
    private Button okButton;
    private Button cancelButton;

    // Layout Components
    private Stage window;
    private Scene scene;
    private StackPane primaryLayout;
    private VBox inputLayout;
    private HBox buttonLayout;

    public UserInputDialog(String description) {
        title = new Label(description);
        state = false;

        window = new Stage();
        window.setTitle("Timer");
        window.initModality(Modality.APPLICATION_MODAL);

        hourInputField = new TextField();
        minuteInputField = new TextField();
        secondInputField = new TextField();
        
        hourInputField.setText("0");
        minuteInputField.setText("0");
        secondInputField.setText("0");
        
        hourInputLabel = new Label("Hour: ");
        minuteInputLabel = new Label("Minute: ");
        secondInputLabel = new Label("Second: ");

        hourContainer = new HBox(hourInputLabel, hourInputField);
        minuteContainer = new HBox(minuteInputLabel, minuteInputField);
        secondContainer = new HBox(secondInputLabel, secondInputField);

        okButton = new Button("Ok");
        cancelButton = new Button("Cancel");
        buttonLayout = new HBox(okButton, cancelButton);

        initEventHandlers();
        
        buttonLayout = new HBox(okButton, cancelButton);
        inputLayout = new VBox(title, hourContainer, minuteContainer, secondContainer, buttonLayout);

        primaryLayout = new StackPane(inputLayout);
        
        hourContainer.getStyleClass().add(CSS_CLASS_INPUT_LAYOUT_VBOX);
        minuteContainer.getStyleClass().add(CSS_CLASS_INPUT_LAYOUT_VBOX);
        secondContainer.getStyleClass().add(CSS_CLASS_INPUT_LAYOUT_VBOX);
        primaryLayout.getStyleClass().add(CSS_CLASS_INPUT_PRIMARY_LAYOUT);
        inputLayout.getStyleClass().add(CSS_CLASS_INPUT_LAYOUT_VBOX);
        buttonLayout.getStyleClass().add(CSS_CLASS_INPUT_BUTTON_HBOX);

        scene = new Scene(primaryLayout);
        scene.getStylesheets().add(PATH_STYLE_SHEET_INPUT_UI);

        window.setScene(scene);
    }

    public boolean display() {
        window.showAndWait();

        return state;
    }

    public int getAddedTimePool() {
        return addedTimePool;
    }

    // Class Private Methods
    private void initEventHandlers() {

        okButton.setOnAction(e -> {
            state = true;
            addedTimePool = 0;
            
            checkInputs();
            
            hours = Integer.parseInt(hourInputField.getText());
            minutes = Integer.parseInt(minuteInputField.getText());
            seconds = Integer.parseInt(secondInputField.getText());
            
            addedTimePool += hours * 60 * 60;
            addedTimePool += minutes * 60;
            addedTimePool += seconds;            
            
            window.close();
        });

        cancelButton.setOnAction(e -> {
            state = false;
            window.close();
        });
    }
    
    private void checkInputs() {
        if ("".equals(hourInputField.getText())) {
            hourInputField.setText("0");
        }
        
        if ("".equals(minuteInputField.getText())) {
            minuteInputField.setText("0");
        }
        
        if ("".equals(secondInputField.getText())) {
            secondInputField.setText("0");
        }
    }
}