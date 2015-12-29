/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Aaron
 */
public class AlarmController {
    private MediaPlayer mediaPlayer;

    public void playAlarm() throws MalformedURLException
    {   
        String relPath = new File("").getAbsolutePath();
        String alarmPath = relPath + "/alarm.mp3";
        File file = new File(alarmPath);
        URL fileURL = file.toURI().toURL();
        Media media = new Media(fileURL.toExternalForm());
        this.mediaPlayer = new MediaPlayer(media);
        this.mediaPlayer.play();
    }

    public void stopAlarm()
    {
        this.mediaPlayer.stop();
    }
}
