package com.github.nekdenis.facetracker.recognition;

public class RecognizedFace {
    private int id;
    private float smiling;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getSmiling() {
        return smiling;
    }

    public void setSmiling(float smiling) {
        this.smiling = smiling;
    }
}