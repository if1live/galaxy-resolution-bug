package com.fiveminlab.toyclash;

import android.app.ActionBar;
import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

// https://developer.android.com/training/graphics/opengl/environment.html
public class MainActivity extends Activity {
    public static final String Tag = "Resolution";

    private GLSurfaceView mGLView;
    TextView mText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        FrameLayout layout = new FrameLayout(this);

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.
        mGLView = new MyGLSurfaceView(this);
        mText = new TextView(this);
        mText.setTextSize(mText.getTextSize() * 1.5f);

        layout.addView(mGLView);
        layout.addView(mText);

        String packageName = getPackageName();
        mText.setText(packageName);
        mText.bringToFront();

        setContentView(layout);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventAction = event.getAction();
        if(eventAction == MotionEvent.ACTION_DOWN) {
            this.mText.setText(message);
        }
        return true;
    }

    String message = "";
    public void setText(String text) {
        this.message = text;
    }
}
