/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import model.TimerModel;
import timerView.TimerView;

/**
 *
 * @author Aaron
 */
public class TimerController {

    TimerView ui;
    TimerModel timerModel;

    public TimerController(TimerView initUI) {
        ui = initUI;
        timerModel = ui.getTimerModel();
    }

    // Timer Overview Toolbar Event Handlers
    public void handleAddTimer() {
        UserInputDialog userInput = new UserInputDialog("Set your timer below:");
        try {
            timerModel.addTimer(userInput.returnUserInputINT());
        } catch (NumberFormatException e) {
            // TODO - Alert user wrong input, using dialog input boxes.
            handleAddTimer();
        }
        ui.refreshTimerOverview();
    }

    public void handleRemoveTimer() {
        timerModel.removeTimer();
        ui.refreshTimerOverview();
    }

    public void handleResetAllTimer() {
        timerModel.resetAllTimer();
        ui.refreshUI();
    }

    public void handleClearAll() {
        timerModel.clearAll();
        ui.refreshUI();
    }

    public void handleMoveTimerDown() {
        try {
            timerModel.moveSelectedSlideDown();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        ui.refreshTimerOverview();
    }

    public void handleMoveTimerUp() {
        try {
            timerModel.moveSelectedSlideUp();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        ui.refreshTimerOverview();
    }

    // Timer Display Toolbar Event Handlers
    public void handleAddTime() {
        timerModel.getSelectedTimer().addTime(timerModel.getTimeChange());
        ui.refreshUI();
    }

    public void handleSubtractTime() {
        timerModel.getSelectedTimer().subtractTime(timerModel.getTimeChange());
        ui.refreshUI();
    }

    public void handleStartTime() {
        timerModel.getSelectedTimer().startTime();
        timerModel.startAutomaticRefreshUI();
    }

    public void handleStopTime() {
        timerModel.getSelectedTimer().stopTime();
        ui.refreshUI();
    }

    public void handleResetTime() {
        timerModel.getSelectedTimer().resetTime();
        ui.refreshUI();
    }
    
    public void handleStopAlarm() {
        timerModel.getSelectedTimer().stopAlarm();
    }
}
