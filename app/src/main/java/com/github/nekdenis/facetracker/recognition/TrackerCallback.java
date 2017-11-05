package com.github.nekdenis.facetracker.recognition;


import java.util.Set;

public interface TrackerCallback {
    void update(Set<RecognizedFace> faces);
}
