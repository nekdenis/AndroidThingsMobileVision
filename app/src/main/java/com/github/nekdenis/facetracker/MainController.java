package com.github.nekdenis.facetracker;


import android.content.Context;

import com.github.nekdenis.facetracker.db.Database;
import com.github.nekdenis.facetracker.db.FirebaseDb;
import com.github.nekdenis.facetracker.devices.SimpleDisplay;
import com.github.nekdenis.facetracker.recognition.FaceRecognition;
import com.github.nekdenis.facetracker.recognition.RecognizedFace;
import com.github.nekdenis.facetracker.recognition.TrackerCallback;
import com.github.nekdenis.facetracker.recognition.mobilevision.MobileVisionFaceRecognition;

import java.util.Set;

public class MainController implements TrackerCallback {

    private SimpleDisplay display;
    private FaceRecognition faceRecognition = new MobileVisionFaceRecognition();
    private Database db = new FirebaseDb();

    public void onStart(Context context, SimpleDisplay display) {
        this.display = display;
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
        db.save(faces.size());
    }
}
