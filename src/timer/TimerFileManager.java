/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timer;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.plaf.metal.MetalIconFactory;
import model.Timer;
import model.TimerModel;
import timerView.TimerView;

/**
 *
 * @author Aaron
 */
public class TimerFileManager {

    TimerView ui;

    public TimerFileManager(TimerView initUI) {
        ui = initUI;
    }

    public void saveTimers() {

        try (FileOutputStream fs = new FileOutputStream("Timers.bin")) {

            ObjectOutputStream os = new ObjectOutputStream(fs);
            ObservableList<Timer> timerList = ui.getTimerModel().getListOfTimers();
            for (Timer writeTimer : timerList) {
                os.writeObject(writeTimer);
            }
            os.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadTimers() {

        try (FileInputStream fi = new FileInputStream("./Timers.bin")) {

            ObjectInputStream os = new ObjectInputStream(fi);
            ObservableList<Timer> timersToLoad = FXCollections.observableArrayList();
            try {
                while (true) {
                    timersToLoad.add((Timer) os.readObject());
                }
            } catch (EOFException e) {
                System.out.println("End of file");
            }
            ui.getTimerModel().setListOfTimers(timersToLoad);
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
