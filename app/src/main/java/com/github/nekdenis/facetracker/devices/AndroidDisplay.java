package com.github.nekdenis.facetracker.devices;


import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

public class AndroidDisplay extends TextView implements SimpleDisplay {

    public AndroidDisplay(Context context) {
        super(context);
    }

    public AndroidDisplay(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AndroidDisplay(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AndroidDisplay(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void init() {

    }

    public void updateDisplay(final Double value) {
        post(new Runnable() {
            @Override public void run() {
                setText(value.toString());
            }
        });
    }

    public void stop() {
        setText("");
    }


}
