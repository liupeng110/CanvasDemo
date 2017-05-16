package com.spl.canvasdemo31.renderer;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.opengl.GLUtils;

import com.spl.canvasdemo31.R;
import com.spl.canvasdemo31.util.Utility;

import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * 立方体(贴图)
 */
public class OpenGL3DRendererTex implements Renderer {

    FloatBuffer _mCubeVertexBuffer;
    FloatBuffer _mCubeTexBuffer;
    Context _context;
    public OpenGL3DRendererTex(Context context)
    {
        _context=context;
    }
    //立方体顶点坐标
    private float box[] = {
            // FRONT
            -0.5f, -0.5f,  0.5f,
            0.5f, -0.5f,  0.5f,
            -0.5f,  0.5f,  0.5f,
            0.5f,  0.5f,  0.5f,
            // BACK
            -0.5f, -0.5f, -0.5f,
            -0.5f,  0.5f, -0.5f,
            0.5f, -0.5f, -0.5f,
            0.5f,  0.5f, -0.5f,
            // LEFT
            -0.5f, -0.5f,  0.5f,
            -0.5f,  0.5f,  0.5f,
            -0.5f, -0.5f, -0.5f,
            -0.5f,  0.5f, -0.5f,
            // RIGHT
            0.5f, -0.5f, -0.5f,
            0.5f,  0.5f, -0.5f,
            0.5f, -0.5f,  0.5f,
            0.5f,  0.5f,  0.5f,
            // TOP
            -0.5f,  0.5f,  0.5f,
            0.5f,  0.5f,  0.5f,
            -0.5f,  0.5f, -0.5f,
            0.5f,  0.5f, -0.5f,
            // BOTTOM
            -0.5f, -0.5f,  0.5f,
            -0.5f, -0.5f, -0.5f,
            0.5f, -0.5f,  0.5f,
            0.5f, -0.5f, -0.5f
    };
    //立方体纹理坐标
    private float texCoords[] = {
            // FRONT
            0.0f, 0.0f,
            1.0f, 0.0f,
            0.0f, 1.0f,
            1.0f, 1.0f,
            // BACK
            1.0f, 0.0f,
            1.0f, 1.0f,
            0.0f, 0.0f,
            0.0f, 1.0f,
            // LEFT
            1.0f, 0.0f,
            1.0f, 1.0f,
            0.0f, 0.0f,
            0.0f, 1.0f,
            // RIGHT
            1.0f, 0.0f,
            1.0f, 1.0f,
            0.0f, 0.0f,
            0.0f, 1.0f,
            // TOP
            0.0f, 0.0f,
            1.0f, 0.0f,
            0.0f, 1.0f,
            1.0f, 1.0f,
            // BOTTOM
            1.0f, 0.0f,
            1.0f, 1.0f,
            0.0f, 0.0f,
            0.0f, 1.0f
    };


    private float rotx;
    private float roty;
    private float rotz;
    public void onDrawFrame(GL10 gl) {
        // TODO Auto-generated method stub
        //清除屏幕
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT);
        //重置模型矩阵
        gl.glLoadIdentity();
        //开启顶点坐标功能
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        //设置顶点坐标
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, _mCubeVertexBuffer);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, _mCubeTexBuffer);
        //保存matrix当前状态
        gl.glPushMatrix();
        //向屏幕移入5个单位
        gl.glTranslatef(0.0f, 0.0f, -5.0f);
        //设置旋转轴,以x轴旋转
        gl.glRotatef(rotx, 1, 0, 0);
        //设置旋转轴,以y轴旋转
        gl.glRotatef(roty, 0, 1, 0);
        //设置旋转轴,以z轴旋转
        gl.glRotatef(rotz, 0, 0, 1);
        /***************************************/
        float fscale = 1.0f;
        gl.glScalef(fscale, fscale, fscale);//大小
        //----------------------------------------//
        //绘制第一个立方体面
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
        //绘制第二个立方体面
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 4, 4);
        //绘制第三个立方体面
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 8, 4);
        //绘制第四个立方体面
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 12, 4);
        //绘制第五个立方体面
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 16, 4);
        //绘制第六个立方体面
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 20, 4);
        //将matrix回复成上面push时的 matrix
        gl.glPopMatrix();
        //关闭设置顶点功能
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        //使旋转角度不断变化
        rotx+=0.2f;
        roty+=0.3f;
        rotz+=0.4f;
    }

    public void onSurfaceChanged(GL10 gl, int width, int height) {
        // TODO Auto-generated method stub
        //设置场景大小
        gl.glViewport(0, 0, width, height);
        //设置投影矩阵
        gl.glMatrixMode(GL10.GL_PROJECTION);
        //重置模型矩阵
        gl.glLoadIdentity();
        //设置窗口比例和透视图
        GLU.gluPerspective(gl, 45.0f, (float)width/(float)height, 0.1f, 100.0f);
        //设置模型矩阵
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        //重置模型矩阵
        gl.glLoadIdentity();
    }

    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // TODO Auto-generated method stub
        //将顶点坐标转换为native类型的数据
        _mCubeVertexBuffer= Utility.createFloatBuffer(box);
        _mCubeTexBuffer=Utility.createFloatBuffer(texCoords);
        //开启纹理功能
        gl.glEnable(GL10.GL_TEXTURE_2D);
        //加载纹理 ********************************************
        loadBitmapTex(gl, R.raw.ic_bird);
        //平滑着色
        gl.glShadeModel(GL10.GL_SMOOTH);
        //设置黑色背景
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
        //设置深度缓存
        gl.glClearDepthf(1.0f);
        //启动深度测试
        gl.glEnable(GL10.GL_DEPTH_TEST);
        //深度测试的类型
        gl.glDepthFunc(GL10.GL_LEQUAL);
        //告诉系统对透视进行修正
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
    }
    private void loadBitmapTex(GL10 gl,int res)
    {
        //将图片资源转换为位图资源
        Bitmap bmp=Utility.getTextureFromBitmapResource(_context, res);
        //定义1个长度的数组
        int mTextureID []=new int[1];
        //将纹理号保存到mTextureID
        gl.glGenTextures(1, mTextureID,0);
        //绑定纹理号到纹理目标
        gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextureID[0]);
        //创建纹理
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bmp, 0);
        //设定纹理过滤器
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,GL10.GL_NEAREST);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
        bmp.recycle();
        return;
    }
}
