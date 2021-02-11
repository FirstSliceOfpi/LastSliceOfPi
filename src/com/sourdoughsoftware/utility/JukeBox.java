package com.sourdoughsoftware.utility;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class JukeBox {
    private Clip clip;
    private FloatControl control;



    JukeBox(String filepath) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(filepath));
            clip = AudioSystem.getClip();
            clip.open(ais);
            control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        }catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    void startJuke() {
        clip.start();
    }
    void turnTheDial(float volume) {control.setValue(volume);}

















}
