package com.github.nekdenis.facetracker.devices;


import com.google.android.things.contrib.driver.ht16k33.AlphanumericDisplay;
import com.google.android.things.contrib.driver.ht16k33.Ht16k33;
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat;

import java.io.IOException;

public class Display {

    private AlphanumericDisplay display;

    public void init() {
        try {
            display = RainbowHat.openDisplay();
            display.setEnabled(true);
            display.setBrightness(Ht16k33.HT16K33_BRIGHTNESS_MAX);
            display.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateDisplay(Double value) {
        try {
            display.display(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            display.clear();
            display.setEnabled(false);
            display.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
