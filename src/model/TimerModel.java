/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controllers.UserInputDialog;
import java.io.Serializable;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import timerView.TimerView;

/**
 *
 * @author Aaron
 */
public class TimerModel implements Runnable {

    TimerView ui;
    ObservableList<Timer> listOfTimers;
    Timer selectedTimer;

    private int timeChange;

    private Thread autoRefreshThread;
    private Task<Void> autoRefreshTask;
    public static int threadCounter;

    public TimerModel(TimerView initUI) {
        ui = initUI;
        listOfTimers = FXCollections.observableArrayList();
        selectedTimer = null;
        threadCounter = 0;
    }

    // Class Primary Methods
    public void addTimer(int defaultTime) {

        Timer timerToAdd = new Timer(defaultTime);
        listOfTimers.add(timerToAdd);

        if (selectedTimer == null) {
            selectedTimer = timerToAdd;
            ui.refreshTimeLabel();
        }
    }

    public void removeTimer() {
        listOfTimers.remove(selectedTimer);
    }

    public void resetAllTimer() {
        for (int i = 0; i < listOfTimers.size(); i++) {
            listOfTimers.get(i).resetTime();
        }
    }

    public void clearAll() {
        listOfTimers.clear();
        selectedTimer = null;
    }

    public void moveSelectedSlideDown() throws Exception {
        int position = getSelectedSlidePosition();

        if (position == -1) {
            throw new Exception("Position: -1, returned!");
        }

        if (position == listOfTimers.size()) {
            throw new Exception("Positon: last Position alredy");
        }

        listOfTimers.set(position, listOfTimers.get(position + 1));
        listOfTimers.set(position + 1, selectedTimer);
    }

    public void moveSelectedSlideUp() throws Exception {
        int position = getSelectedSlidePosition();

        if (position == -1) {
            throw new Exception("Position: -1, returned!");
        }

        if (position == 0) {
            throw new Exception("Positon: last Position alredy");
        }

        listOfTimers.set(position, listOfTimers.get(position - 1));
        listOfTimers.set(position - 1, selectedTimer);
    }

    public boolean isSelectedTimer(Timer timerComp) {
        if (selectedTimer == timerComp) {
            return true;
        } else {
            return false;
        }
    }

    private class AutoRefreshTask extends Task<Void> {

        @Override
        protected Void call() throws Exception {
            while (threadCounter > 0) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ui.refreshUI();
                    }
                });
                
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ie) {
                    System.out.println(ie.toString());
                }    
            }

            return null;
        }
    }

    /**
     * Refreshes the UI every .4 second.
     */
    public void startAutomaticRefreshUI() {
        autoRefreshTask = new AutoRefreshTask();
        autoRefreshThread = new Thread(autoRefreshTask);
        autoRefreshThread.start();
    }

    //@Override
    protected Void call() throws Exception {
        while (threadCounter > 0) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ui.refreshUI();
                }
            });

            System.out.println("Current threadCount: " + threadCounter);
            try {
                Thread.sleep(400);
            } catch (InterruptedException ex) {
                System.out.println(ex.toString());
            }
        }
        return null;
    }

    @Override
    public void run() {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                while (threadCounter > 0) {
                    ui.refreshUI();
                    System.out.println("Current threadCount: " + threadCounter);
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException ex) {
                        System.out.println(ex.toString());
                    }
                }
            }
        });

        /*ui.refreshUI();
         System.out.println("Current threadCount: " + threadCounter);
         try {
         Thread.sleep(400);
         } catch (InterruptedException ex) {
         System.out.println(ex.toString());
         }*/
    }

    // Class Helper Methods
    private int getSelectedSlidePosition() {
        int position = -1;

        for (int i = 0; i < listOfTimers.size(); i++) {
            if (isSelectedTimer(listOfTimers.get(i))) {
                position = i;
                break;
            }
        }

        return position;
    }

    // Getters
    public Timer getSelectedTimer() {
        return selectedTimer;
    }

    public ObservableList<Timer> getListOfTimers() {
        return listOfTimers;
    }

    public int getTimeChange() {
        return timeChange;
    }

    // Setters
    public void setSelectedSlide(Timer newTimer) {
        selectedTimer = newTimer;
    }

    public void setTimeChange(String timeChangeText) {
        int timeChangeINT = Integer.parseInt(timeChangeText);
        timeChange = timeChangeINT;
    }

    public void setListOfTimers(ObservableList<Timer> listOfTimers) {
        this.listOfTimers = listOfTimers;
    }
}
