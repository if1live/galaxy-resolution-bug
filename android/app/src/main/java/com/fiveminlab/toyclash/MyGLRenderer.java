package com.fiveminlab.toyclash;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.widget.Toast;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by haruna on 2017-05-17.
 */

public class MyGLRenderer implements GLSurfaceView.Renderer {
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {
        // Set the background frame color
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    }

    MainActivity mContext;
    public void setContext(MainActivity ctx) {
        this.mContext = ctx;
    }

    public void onDrawFrame(GL10 unused) {
        // Redraw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
    }

    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES20.glViewport(0, 0, width, height);

        StringBuilder sb = new StringBuilder();

        String packageName = mContext.getPackageName();
        sb.append(packageName);
        sb.append("\n");

        String sizeMsg = width + "x" + height;
        sb.append(sizeMsg);

        Log.d(MainActivity.Tag, packageName);
        Log.d(MainActivity.Tag, sizeMsg);
        mContext.setText(sb.toString());
    }
}
