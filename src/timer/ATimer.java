/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timer;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import static timer.StartUpConstants.ICON_WINDOW_LOGO;
import static timer.StartUpConstants.PATH_IMAGES;
import timerView.TimerView;

/**
 *
 * @author Aaron
 */
public class ATimer extends Application {

    TimerView ui = new TimerView();

    @Override
    public void start(Stage primaryStage) throws MalformedURLException {

        // SET THE WINDOW ICON
        String imagePath = PATH_IMAGES + ICON_WINDOW_LOGO;
        File file = new File(imagePath);

        // GET AND SET THE IMAGE
        URL fileURL = file.toURI().toURL();
        Image windowIcon = new Image(fileURL.toExternalForm());
        primaryStage.getIcons().add(windowIcon);

        String appTitle = "Timer";

        ui.startUI(primaryStage, appTitle);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
