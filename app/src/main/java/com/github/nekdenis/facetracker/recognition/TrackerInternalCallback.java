package com.github.nekdenis.facetracker.recognition;

public interface TrackerInternalCallback {
    void onFound(RecognizedFace face);

    void onLost(RecognizedFace face);
}