/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aaron
 */
public class Timer implements Runnable {

    public final static int STOPPED = 0;
    public final static int RUNNING = 1;
    public final static int OVER = 2;

    private int originalTime;
    private long timePool;
    private int state;

    private Thread timerThread;
    private AlarmController alarm;

    public Timer(int initTime) {
        originalTime = initTime;
        timePool = initTime;
        state = STOPPED;
    }

    // Class Primary Methods
    public void addTime(int timeToAdd) {
        timePool += timeToAdd;
    }

    public void subtractTime(int timeToSubtract) {
        timePool -= timeToSubtract;
    }

    /**
     * Creates a new thread and starts it.
     */
    public void startTime() {
        timerThread = new Thread(this);
        TimerModel.threadCounter++;
        System.out.println("New Thread: " + TimerModel.threadCounter);
        timerThread.start();
    }

    @Override
    public void run() {
        state = RUNNING;

        while (state == RUNNING) {
            tick();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println(ex.toString());
            }

            if (timePool <= 0) {
                state = OVER;
                this.playAlarm();
            }
        }

        TimerModel.threadCounter--;
        System.out.println("Thread dead: " + TimerModel.threadCounter);
    }

    public void stopTime() {
        state = STOPPED;
    }

    public void resetTime() {
        timePool = originalTime;
    }

    public String getConvertedTime() {
        String convertedTime = "";

        long second = (timePool) % 60;
        long minute = (timePool / (60)) % 60;
        long hour = (timePool / (60 * 60)) % 24;

        convertedTime = String.format("%02d:%02d:%02d: %d", hour, minute, second, timePool);

        return convertedTime;
    }

    public void playAlarm() {
        if (alarm == null) {
            alarm = new AlarmController();
        }
        try {
            alarm.playAlarm();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    
    public void stopAlarm() {
        alarm.stopAlarm();
    }

    // Class Helper Methods
    public boolean checkState() {
        if (state == RUNNING) {
            return true;
        } else {
            return false;
        }
    }

    public void tick() {
        timePool --;
    }

    // Getters
    public long getTimePool() {
        return timePool;
    }
}

