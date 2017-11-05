package com.github.nekdenis.facetracker.recognition;


import android.content.Context;

public interface FaceRecognition {
    boolean check(Context context);

    void init(Context context);

    void stop();

    void setCallback(TrackerCallback callback);
}
