package com.spl.canvasdemo31.renderer;

import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * 渲染一个纯色的三角形
 * @author 
 *
 */
public class TriangleRenderer implements Renderer{
	
	FloatBuffer vertices;// 储存3个顶点

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO 表面层创建(只有一次)
		Log.i("spl", "surface create");
		// 在硬件内存中准备好三角形的数据
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(3 * 2 * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		// 填充数据
		vertices = byteBuffer.asFloatBuffer();//// 将字节转化为浮点
		vertices.put(new float[]{
			140, 300,// 左下角 这里只有x,y OpenGL会自动设置z=0
	               340, 300,//右下角
	               240, 500
				});
		vertices.flip();//初始化位置和长度
		// OpenGL的原点在左下

	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// 表面层改变(多次)
		Log.i("spl", "width="+width +",height="+height);
		gl.glOrthof(0, width, 0, height, 1, -1);
		
		gl.glColor4f(1, 0, 0, 1);// 设置顶点的默认颜色
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);// 通知GL所绘制的是顶点数组

		//把顶点位置传输到GPU并储存
		//2代表x,y(3代表x,y,z);每个点由两个坐标组成
        //GL10.GL_FLOAT告知所使用的数据类型
        //0 代表步长,是位置与位置之间的字节距离
        //vertices 是浮点缓冲区
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, vertices);
	}

	long count = 0;
	@Override
	public void onDrawFrame(GL10 gl) {
		// 表面层的绘制(每秒调用多次)
		gl.glClearColor(0, 1, 0, 1);// 设置清屏颜色
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);// 清空颜色缓冲区
		

		//GL10.GL_TRIANGLES 绘制类型
        //0 代表顶点偏移量, 也就是从第一个顶点开始画起
        //3 顶点数量
        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);// 它将绘制三角形
//        count++;
//        Log.i("spl", "count="+count);

	}

}
