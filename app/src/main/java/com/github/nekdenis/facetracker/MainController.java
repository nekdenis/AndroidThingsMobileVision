package com.github.nekdenis.facetracker;


import android.content.Context;

import com.github.nekdenis.facetracker.devices.Display;
import com.github.nekdenis.facetracker.recognition.FaceRecognition;
import com.github.nekdenis.facetracker.recognition.RecognizedFace;
import com.github.nekdenis.facetracker.recognition.TrackerCallback;
import com.github.nekdenis.facetracker.recognition.mobilevision.MobileVisionFaceRecognition;

import java.util.Set;

public class MainController implements TrackerCallback {

    private Display display = new Display();
    private FaceRecognition faceRecognition = new MobileVisionFaceRecognition();


    public void onStart(Context context) {

        if (faceRecognition.check(context)) {
            display.init();
            faceRecognition.setCallback(this);
            faceRecognition.init(context);
        }

    }

    public void onStop() {
        faceRecognition.stop();
        display.stop();
    }

    @Override public void update(Set<RecognizedFace> faces) {
        display.updateDisplay((double) faces.size());
    }
}