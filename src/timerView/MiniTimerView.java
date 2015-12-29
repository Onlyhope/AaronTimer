/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timerView;

import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import model.Timer;
import static timer.StartUpConstants.CSS_CLASS_TIMER_OVERVIEW_MINI;

/**
 *
 * @author Aaron
 */
public class MiniTimerView extends FlowPane {
    
    TimerView ui;
    
    private Timer curTimer;
    
    MiniTimerView(TimerView initUI, Timer initTimer) {
        
        ui = initUI;
        curTimer = initTimer;
        

        Label timeLabel = new Label(curTimer.getConvertedTime());
        this.getChildren().add(timeLabel);
        this.getStyleClass().add(CSS_CLASS_TIMER_OVERVIEW_MINI);
    }
    
    public Timer getCurTimer() {
        return curTimer;
    }
}
