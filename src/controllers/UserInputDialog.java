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
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Aaron
 */
public class UserInputDialog {
    
    private String userInput;
    
    private Label title;
    private TextField userInputTextField;
    private Button okButton;
    private Button cancelButton;
    
    // Layout Components
    private Stage window;
    private Scene scene;
    private BorderPane primaryLayout;
    private HBox buttonHContainer;
    
    public UserInputDialog(String description) {
        title = new Label(description);
    }
    
    public int returnUserInputINT() throws NumberFormatException{
        
        window = new Stage();
        window.setTitle("Timer");
        window.initModality(Modality.APPLICATION_MODAL);
        
        
        userInputTextField = new TextField();
        okButton = new Button("Ok");
        cancelButton = new Button("Cancel");
        
        okButton.setOnAction(e -> {
            userInput = userInputTextField.getText();
            window.close();
        });
        
        cancelButton.setOnAction( e -> {
            window.close();
        });
        
        buttonHContainer = new HBox(okButton, cancelButton);
        
        primaryLayout = new BorderPane();
        primaryLayout.setTop(title);
        primaryLayout.setCenter(userInputTextField);
        primaryLayout.setBottom(buttonHContainer);
        //primaryLayout.getStyleClass().add();
        //buttonHContainer.getStyleClass().add();
        
        scene = new Scene(primaryLayout, 300, 150);
        //scene.getStylesheets().add();
        
        window.setScene(scene);
        window.showAndWait();
        
        int intNum = Integer.parseInt(userInput);
        
        if (intNum <= 0)
            throw new NumberFormatException();
        
        return Integer.parseInt(userInput);
    }
}
