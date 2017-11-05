package com.github.nekdenis.facetracker.recognition.mobilevision;

import com.github.nekdenis.facetracker.recognition.TrackerInternalCallback;
import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.face.Face;

/**
 * Factory for creating a face tracker to be associated with a new face.  The multiprocessor
 * uses this factory to create face trackers as needed -- one for each individual.
 */
class GraphicFaceTrackerFactory implements MultiProcessor.Factory<Face> {

    private TrackerInternalCallback callback;

    GraphicFaceTrackerFactory(TrackerInternalCallback callback) {
        this.callback = callback;
    }

    @Override
    public Tracker<Face> create(Face face) {
        return new GraphicFaceTracker(callback);
    }
}