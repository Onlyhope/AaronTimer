/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timerView;

import controllers.TimerController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Timer;
import model.TimerModel;
import static timer.StartUpConstants.CSS_CLASS_TIMER_DISPLAY;
import static timer.StartUpConstants.CSS_CLASS_TIMER_DISPLAY_BUTTONS;
import static timer.StartUpConstants.CSS_CLASS_TIMER_OVERVIEW;
import static timer.StartUpConstants.CSS_CLASS_TIMER_OVERVIEW_BUTTONS;
import static timer.StartUpConstants.CSS_CLASS_TIMER_OVERVIEW_MINI;
import static timer.StartUpConstants.CSS_CLASS_TIMER_OVERVIEW_SCROLLPANE;
import static timer.StartUpConstants.CSS_CLASS_TIMER_OVERVIEW_SELECTED_MINI;
import static timer.StartUpConstants.CSS_CLASS_TIMER_OVERVIEW_TOOLBAR;
import static timer.StartUpConstants.CSS_CLASS_TIMER_UI;
import static timer.StartUpConstants.CSS_CLASS_TIME_DISPLAY;
import static timer.StartUpConstants.CSS_CLASS_TIME_LABEL;
import static timer.StartUpConstants.CSS_CLASS_TIME_TOOLBAR_DISPLAY;
import static timer.StartUpConstants.PATH_STYLE_SHEET_UI;
import timer.TimerFileManager;

/**
 *
 * @author Aaron
 */
public class TimerView {

    TimerModel curTimerModel;
    TimerFileManager timerFileManager;
    TimerController modelController;

    // Application UI
    Stage primaryStage;
    Scene primaryScene;

    // Timer UI Pane
    BorderPane timerPane;

    // UI Components
    // TimerDisplay UI
    BorderPane timerDisplayLayout;
    StackPane timeDisplay;
    FlowPane timeDisplayToolbar;
    Label timeLabel;
    TextField timeInputTextField;

    // TimerDisplay Buttons
    Button addTimeButton;
    Button subtractTimeButton;
    Button startTimeButton;
    Button stopTimeButton;
    Button resetTimeButton;
    Button stopAlarmButton;

    // TimerOverview UI
    HBox timerOverviewLayout;
    VBox timerOverview;
    ScrollPane timerOverviewScrollPane;
    FlowPane timerOverviewToolbar;

    // TimerOverview Buttons
    Button addTimerButton;
    Button removeTimerButton;
    Button saveTimersButton;
    Button resetAllTimerButton;
    Button clearAllTimerButton;
    Button moveTimerUpButton;
    Button moveTimerDownButton;
   
    

    public TimerView() {
        curTimerModel = new TimerModel(this);
    }

    /**
     * Initializes the Timer Application and its UI.
     *
     * @param initPrimaryStage
     * @param windowTitle
     */
    public void startUI(Stage initPrimaryStage, String windowTitle) {

        initTimerOverview();

        initTimerDisplay();

        initOverviewToolbar();

        initModTimerToolbar();

        initEventHandlers();

        primaryStage = initPrimaryStage;
        initWindow(windowTitle);
    }

    // Getters
    public TimerModel getTimerModel() {
        return curTimerModel;
    }

    // Setters
    public void setTimerModel(TimerModel newModel) {
        curTimerModel = newModel;
    }
    
    // Class Helper Methods
    private void initTimerOverview() {
        //TODO
        System.out.println("Initializing Timer Overview");

        timerOverviewLayout = new HBox();
        timerOverview = new VBox();
        timerOverviewToolbar = new FlowPane();
        timerOverviewScrollPane = new ScrollPane(timerOverview);
        timerOverviewScrollPane.setFitToWidth(true);
        timerOverviewScrollPane.setFitToHeight(true);
        
        timerOverviewLayout.getChildren().addAll(timerOverviewToolbar, timerOverviewScrollPane);

        timerOverviewToolbar.getStyleClass().add(CSS_CLASS_TIMER_OVERVIEW_TOOLBAR);
        timerOverviewScrollPane.getStyleClass().add(CSS_CLASS_TIMER_OVERVIEW_SCROLLPANE);
    }

    private void initTimerDisplay() {
        //TODO
        System.out.println("Initializing Timer Display");

        timerDisplayLayout = new BorderPane();
        timeDisplay = new StackPane();
        timeLabel = new Label("00:00:00");
        timeDisplayToolbar = new FlowPane();
        
        timeInputTextField = new TextField();
        timeInputTextField.setPromptText("Time input");
        timeInputTextField.textProperty().addListener(e -> {
            curTimerModel.setTimeChange(timeInputTextField.getText());
        });
        timeDisplayToolbar.getChildren().add(timeInputTextField);

        timeDisplay.getChildren().add(timeLabel);
        timerDisplayLayout.setCenter(timeDisplay);
        timerDisplayLayout.setBottom(timeDisplayToolbar);

        timeLabel.getStyleClass().add(CSS_CLASS_TIME_LABEL);
        timeDisplay.getStyleClass().add(CSS_CLASS_TIME_DISPLAY);
        timeDisplayToolbar.getStyleClass().add(CSS_CLASS_TIME_TOOLBAR_DISPLAY);
        timerDisplayLayout.getStyleClass().add(CSS_CLASS_TIMER_DISPLAY);

    }

    private void initOverviewToolbar() {
        //TODO
        System.out.println("Initializing Overview Toolbar");

        addTimerButton = initChildButton(timerOverviewToolbar, "Add Timer", CSS_CLASS_TIMER_OVERVIEW_BUTTONS, false);
        removeTimerButton = initChildButton(timerOverviewToolbar, "Remove Timer", CSS_CLASS_TIMER_OVERVIEW_BUTTONS, false);
        saveTimersButton = initChildButton(timerOverviewToolbar, "Save Timer", CSS_CLASS_TIMER_OVERVIEW_BUTTONS, false);
        resetAllTimerButton = initChildButton(timerOverviewToolbar, "Reset All", CSS_CLASS_TIMER_OVERVIEW_BUTTONS, false);
        clearAllTimerButton = initChildButton(timerOverviewToolbar, "Clear All", CSS_CLASS_TIMER_OVERVIEW_BUTTONS, false);
        moveTimerUpButton = initChildButton(timerOverviewToolbar, "Move Up", CSS_CLASS_TIMER_OVERVIEW_BUTTONS, false);
        moveTimerDownButton = initChildButton(timerOverviewToolbar, "Move Down", CSS_CLASS_TIMER_OVERVIEW_BUTTONS, false);
    }

    private void initModTimerToolbar() {
        //TODO
        System.out.println("Initializing Timer Toolbar");
        
        addTimeButton = initChildButton(timeDisplayToolbar, "Add Time", CSS_CLASS_TIMER_DISPLAY_BUTTONS, false);
        subtractTimeButton = initChildButton(timeDisplayToolbar, "Remove Time", CSS_CLASS_TIMER_DISPLAY_BUTTONS, false);
        startTimeButton = initChildButton(timeDisplayToolbar, "Start Time", CSS_CLASS_TIMER_DISPLAY_BUTTONS, false);
        stopTimeButton = initChildButton(timeDisplayToolbar, "Stop Time", CSS_CLASS_TIMER_DISPLAY_BUTTONS, false);
        resetTimeButton = initChildButton(timeDisplayToolbar, "Reset Time", CSS_CLASS_TIMER_DISPLAY_BUTTONS, false);
        stopAlarmButton = initChildButton(timeDisplayToolbar, "Stop Alarm", CSS_CLASS_TIMER_DISPLAY_BUTTONS, false);
    }

    private void initEventHandlers() {
        //TODO
        System.out.println("Initializing Timer Event Handlers");

        modelController = new TimerController(this);

        addTimeButton.setOnAction(e -> {
            modelController.handleAddTime();
        });

        subtractTimeButton.setOnAction(e -> {
            modelController.handleSubtractTime();
        });
        
        startTimeButton.setOnAction(e -> {
            modelController.handleStartTime();
        });
        
        stopTimeButton.setOnAction(e -> {
            modelController.handleStopTime();
        });

        resetTimeButton.setOnAction(e -> {
            modelController.handleResetTime();
        });
        
        stopAlarmButton.setOnAction(e -> {
            modelController.handleStopAlarm();
        });

        addTimerButton.setOnAction(e -> {
            modelController.handleAddTimer();
        });

        removeTimerButton.setOnAction(e -> {
            modelController.handleRemoveTimer();
        });
        saveTimersButton.setOnAction(e -> {
            modelController.handleSaveTimer();
        });
        
        resetAllTimerButton.setOnAction(e -> {
            modelController.handleResetAllTimer();
        });

        clearAllTimerButton.setOnAction(e -> {
            modelController.handleClearAll();
        });

        moveTimerDownButton.setOnAction(e -> {
            modelController.handleMoveTimerDown();
        });

        moveTimerUpButton.setOnAction(e -> {
            modelController.handleMoveTimerUp();
        });
    }

    private void initWindow(String windowTitle) {

        primaryStage.setTitle(windowTitle);
        
        timerPane = new BorderPane();
        timerPane.getStyleClass().add(CSS_CLASS_TIMER_UI);
        timerPane.setLeft(timerOverviewLayout);
        timerPane.setCenter(timerDisplayLayout);

        timerFileManager = new TimerFileManager(this);
        timerFileManager.loadTimers();
        
        primaryScene = new Scene(timerPane);

        primaryScene.getStylesheets().add(PATH_STYLE_SHEET_UI);
        primaryStage.setScene(primaryScene);
        // Resizable is false
        //primaryStage.setResizable(false);

        primaryStage.show();
        refreshUI();
        
    }

    private Button initChildButton(Pane toolbar, String description, String cssClass, boolean disabled) {
        Button button = new Button();
        button.setText(description);
        button.getStyleClass().add(cssClass);
        button.setDisable(disabled);
        toolbar.getChildren().add(button);
        return button;
    }

    // Class Primary Methods
    public void refreshUI() {
        this.refreshTimerOverview();
        this.refreshTimeLabel();
    }
    
    /**
     * Refreshes the timeLabel.
     */
    public void refreshTimeLabel() {
        if (curTimerModel.getSelectedTimer() == null) {
            timeLabel.setText("00:00:00");
        } else {
            Timer curTimer = curTimerModel.getSelectedTimer();
            timeLabel.setText(curTimer.getConvertedTime());
        }
    }

    /**
     * Reloads the TimerOverview VBox with existing data.
     */
    public void refreshTimerOverview() {
        timerOverview.getChildren().clear();
        for (Timer timer : curTimerModel.getListOfTimers()) {
            MiniTimerView miniTimerDisplay = new MiniTimerView(this, timer);
            if (curTimerModel.isSelectedTimer(timer)) {
                miniTimerDisplay.getStyleClass().add(CSS_CLASS_TIMER_OVERVIEW_SELECTED_MINI);
            } else {
                miniTimerDisplay.getStyleClass().add(CSS_CLASS_TIMER_OVERVIEW_MINI);
            }
            timerOverview.getChildren().add(miniTimerDisplay);
            miniTimerDisplay.setOnMousePressed(e -> {
                curTimerModel.setSelectedSlide(timer);
                this.refreshTimerOverview();
                this.refreshTimeLabel();
            });
        }
    }

}
