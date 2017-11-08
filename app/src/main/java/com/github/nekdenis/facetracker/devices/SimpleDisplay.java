package com.github.nekdenis.facetracker.devices;


public interface SimpleDisplay {
    void init();

    void updateDisplay(Double value);

    void stop();
}
