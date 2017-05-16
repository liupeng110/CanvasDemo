package com.spl.canvasdemo31.activity;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.spl.canvasdemo31.renderer.ColoredTriangleRenderer;
import com.spl.canvasdemo31.renderer.CubeRenderer;
import com.spl.canvasdemo31.renderer.OpenGL3DRenderer;
import com.spl.canvasdemo31.renderer.OpenGL3DRendererTex;
import com.spl.canvasdemo31.renderer.OpenGlRender2;
import com.spl.canvasdemo31.renderer.SimpleRenderer;
import com.spl.canvasdemo31.renderer.TextedTriangleRenderer;
import com.spl.canvasdemo31.renderer.TriangleRenderer;


public class MainActivity extends Activity {

    GLSurfaceView glview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 全屏
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //setContentView(new MySurfaceView(this));

        glview = new GLSurfaceView(this);               //几个渲染器复用一个surface
        int index = getIntent().getIntExtra("index",0); //拿到intent中传过来的数据

        //这里可以加载不同的renderer对象
        if(index == 0) {
            glview.setRenderer(new OpenGlRender2(null));  //第一个渲染器
        }
        if(index == 1) {
            glview.setRenderer(new SimpleRenderer());     //第二个渲染器
        }
        if(index == 2) {
            glview.setRenderer(new TriangleRenderer());
        }
        if(index == 3) {
            glview.setRenderer(new ColoredTriangleRenderer());
        }
        if(index == 4) {
            glview.setRenderer(new TextedTriangleRenderer(this,glview));
        }

        if(index == 9) {
            glview.setRenderer(new CubeRenderer());
        }
        if(index == 10) {
            glview.setRenderer(new OpenGL3DRenderer());
        }
        if(index == 11) {
            glview.setRenderer(new OpenGL3DRendererTex(this));
        }
        setContentView(glview);
    }

    @Override
    protected void onPause() {
        glview.onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
        glview.onResume();

    }
}
