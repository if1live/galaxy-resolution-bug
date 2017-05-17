package com.fiveminlab.toyclash;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by haruna on 2017-05-17.
 */

class MyGLSurfaceView extends GLSurfaceView {
    private final MyGLRenderer mRenderer;

    public MyGLSurfaceView(Context context){
        super(context);

        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2);

        mRenderer = new MyGLRenderer();
        mRenderer.setContext((MainActivity)context);

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(mRenderer);
    }
}