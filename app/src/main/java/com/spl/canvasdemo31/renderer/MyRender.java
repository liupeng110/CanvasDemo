package com.spl.canvasdemo31.renderer;

import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyRender implements Renderer {

    //定义小正方形的顶点坐标
    private FloatBuffer quartBuffer;
//    = FloatBuffer.wrap(new float[]{
//            -0.5f, -0.5f,  0.50f,
//            0.5f, -0.5f,  0.50f,
//            -0.5f,  0.5f,  0.50f,
//            0.5f,  0.5f,  0.50f,
//
//    });


    public void setQuartBuffer() {
        // TODO 表面层创建(只有一次)
        Log.i("spl", "surface create");
        // 在硬件内存中准备好三角形的数据
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(3 * 3 * 4);
        byteBuffer.order(ByteOrder.nativeOrder());
        // 填充数据
        quartBuffer = byteBuffer.asFloatBuffer();//// 将字节转化为浮点
        quartBuffer.put(new float[]{
                -0.5f, -0.5f,  0.50f,
                0.5f, -0.5f,  0.50f,
                -0.5f,  0.5f,  0.50f,
                0.5f,  0.5f,  0.50f,
        });
        quartBuffer.flip();//初始化位置和长度
        // OpenGL的原点在左下

    }

    //定义颜色
    int one=0x10000;
    private IntBuffer colorBuffer=IntBuffer.wrap(new int[]{
            one,0,0,one,
            0,one,0,one,
            0,0,one,one,
            one,one,one,one,
    });

    private float rotz; //小正方形旋转角度

    /**绘制每一帧*/
    @Override
    public void onDrawFrame(GL10 gl) {
        //清除屏幕和深度缓存
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT);
        //开启顶点数组坐标
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        //重置矩阵
        gl.glLoadIdentity();
        //开启颜色渲染功能
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        //指定顶点数组坐标
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, quartBuffer);
        //指定颜色坐标数组
        gl.glColorPointer(4, GL10.GL_FIXED, 0, colorBuffer);
        //设置旋转
        gl.glRotatef(rotz, 0, 0, 1);
        //画小正方形
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

        //关闭顶点坐标
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        //关闭颜色渲染功能
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
        rotz+=2.5f;
    }

    /***/
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        //设置场景大小，在这里要特别注意：场景大小设置不正确产生的就不是一个正方形
        gl.glViewport(0, (height-width)/2, width, width);
        float ratio=(float)width/height;
        //设置矩阵模型为投影矩阵
        gl.glMatrixMode(GL10.GL_PROJECTION);
        //重置矩阵
        gl.glLoadIdentity();
        //设置可视区域
        gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        setQuartBuffer();
    }

}
