package com.github.nekdenis.facetracker.recognition.mobilevision;

import com.github.nekdenis.facetracker.recognition.RecognizedFace;
import com.github.nekdenis.facetracker.recognition.TrackerInternalCallback;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

/**
 * Face tracker for each detected individual. This maintains a face graphic within the app's
 * associated face overlay.
 */
class GraphicFaceTracker extends Tracker<Face> {

    private RecognizedFace recognizedFace;

    private TrackerInternalCallback callback;

    GraphicFaceTracker(TrackerInternalCallback callback) {
        recognizedFace = new RecognizedFace();
        this.callback = callback;
    }

    /**
     * Start tracking the detected face instance within the face overlay.
     */
    @Override
    public void onNewItem(int faceId, Face item) {
        recognizedFace.setId(faceId);
    }

    /**
     * Update the position/characteristics of the face within the overlay.
     */
    @Override
    public void onUpdate(FaceDetector.Detections<Face> detectionResults, Face face) {
        recognizedFace.setSmiling(face.getIsSmilingProbability());
        callback.onFound(recognizedFace);
    }

    /**
     * Hide the graphic when the corresponding face was not detected.  This can happen for
     * intermediate frames temporarily (e.g., if the face was momentarily blocked from
     * view).
     */
    @Override
    public void onMissing(FaceDetector.Detections<Face> detectionResults) {
        callback.onLost(recognizedFace);
    }

    /**
     * Called when the face is assumed to be gone for good. Remove the graphic annotation from
     * the overlay.
     */
    @Override
    public void onDone() {
        callback.onLost(recognizedFace);
    }
}