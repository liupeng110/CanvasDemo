package com.spl.canvasdemo31.renderer;

import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * 渲染单一背景色
 * @author 
 *
 */
public class SimpleRenderer implements Renderer{

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO 表面层创建(只有一次)
		Log.i("spl", "surface create");
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// 表面层改变(多次)
		Log.i("spl", "width="+width +",height="+height);
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		// 表面层的绘制(每秒调用多次)
        changeColor();
		gl.glClearColor(r, 0, 0, 1);// 设置清屏颜色
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);// 清空颜色缓冲区
	}

    float r=1,g=0,b=1;
    private void changeColor(){
        if (r>1){
            r=0;
        }
        r += 0.01f;

        if (g>1){
            g=0;
        }
        g += 0.01f;

        if (b>1){
            b=0;
        }
        b += 0.01f;
    }

}
