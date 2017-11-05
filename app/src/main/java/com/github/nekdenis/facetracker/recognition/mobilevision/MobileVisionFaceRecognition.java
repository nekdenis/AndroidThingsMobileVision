package com.github.nekdenis.facetracker.recognition.mobilevision;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.github.nekdenis.facetracker.FaceTrackerActivity;
import com.github.nekdenis.facetracker.recognition.FaceRecognition;
import com.github.nekdenis.facetracker.recognition.RecognizedFace;
import com.github.nekdenis.facetracker.recognition.TrackerCallback;
import com.github.nekdenis.facetracker.recognition.TrackerInternalCallback;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.face.FaceDetector;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MobileVisionFaceRecognition implements FaceRecognition {

    private CameraSource cameraSource;
    private InternalCallback internalCallback = new InternalCallback();
    private Set<RecognizedFace> faces = new HashSet<>();
    private TrackerCallback callback;

    @Override public boolean check(Context context) {
        int rc = ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
        if (rc != PackageManager.PERMISSION_GRANTED) {
            Log.e(FaceTrackerActivity.TAG, "camera permission is not granted");
            return false;
        }

        int code = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context.getApplicationContext());
        if (code != ConnectionResult.SUCCESS) {
            Log.e("EXAMPLE::", "play services are not available");
            return false;
        }
        return true;
    }

    @Override public void init(Context context) {
        createCameraSource(context);
    }

    @Override public void stop() {
        if (cameraSource != null) {
            cameraSource.release();
        }
    }

    @Override public void setCallback(TrackerCallback callback) {
        this.callback = callback;
    }

    private void createCameraSource(Context ctx) {

        Context context = ctx.getApplicationContext();
        FaceDetector detector = new FaceDetector.Builder(context)
                .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                .build();

        detector.setProcessor(
                new MultiProcessor.Builder<>(new GraphicFaceTrackerFactory(internalCallback))
                        .build());

        if (!detector.isOperational()) {
            // Note: The first time that an app using face API is installed on a device, GMS will
            // download a native library to the device in order to do detection.  Usually this
            // completes before the app is run for the first time.  But if that download has not yet
            // completed, then the above call will not detect any faces.
            //
            // isOperational() can be used to check if the required native library is currently
            // available.  The detector will automatically become operational once the library
            // download completes on device.
            Log.w(FaceTrackerActivity.TAG, "Face detector dependencies are not yet available.");
        }

        cameraSource = new CameraSource.Builder(context, detector)
                .setRequestedPreviewSize(1024, 768)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setRequestedFps(25.0f)
                .build();
        try {
            cameraSource.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class InternalCallback implements TrackerInternalCallback {

        @Override public void onFound(RecognizedFace face) {
            faces.add(face);
            callback.update(faces);
        }

        @Override public void onLost(RecognizedFace face) {
            faces.remove(face);
            callback.update(faces);
        }
    }
}

