package com.spl.canvasdemo31.renderer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLUtils;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * 渲染一个纹理贴图的三角形
 * 
 * @author
 * 
 */
public class TextedTriangleRenderer implements Renderer {

	FloatBuffer vertices;// 储存3个顶点

	Context context;
	GLSurfaceView glView;

	final int VERTEX_SIZE = (2 + 2) * 4;
	int textureId;

	public TextedTriangleRenderer(Context context, GLSurfaceView glView) {
		this.context = context;
		this.glView = glView;
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO 表面层创建(只有一次)
		Log.i("spl", "surface create");
		// 在主机的堆内存中(而不是虚拟机的堆内存中)分配一段字节缓存区
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(3 * VERTEX_SIZE);
		byteBuffer.order(ByteOrder.nativeOrder());// 确保字节顺序等于CPU的字节顺序

		vertices = byteBuffer.asFloatBuffer();// // 将字节转化为浮点
		vertices.put(new float[] { 
				
				140, 300, 0.0f, 1.0f,// 左下角 
				340, 300, 1.0f, 1.0f,// 右下角
				240, 500, 0.5f, 0.0f });//顶点
		vertices.flip();// 初始化位置和长度

		Log.i("spl", "surface created:glWiew=" + glView.getWidth() + "x"
				+ glView.getHeight());

		gl.glViewport(0, 0, glView.getWidth(), glView.getHeight());

		gl.glMatrixMode(GL10.GL_PROJECTION);// 设置投影矩阵(平行,透视)
		gl.glLoadIdentity();
		gl.glOrthof(0, glView.getWidth(), 0, glView.getHeight(), 1, -1);// 设置剪裁空间(正交矩形)

		textureId = loadTexture(gl, "ic_bird.png");

		gl.glEnable(GL10.GL_TEXTURE_2D);// 启用纹理
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);// 绑定纹理

		// gl.glColor4f(0, 1, 0, 1);// 默认颜色
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);// 通知GL所有绘制的顶点都有位置(废话)
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);// 告知GL 顶点包含纹理坐标信息,
															// 取代默认色
		// gl.glDisableClientState(GL10.GL_COLOR_ARRAY);// 关闭颜色
		vertices.position(0);
		gl.glVertexPointer(2, GL10.GL_FLOAT, VERTEX_SIZE, vertices);// 把顶点位置传输到GPU并储存
		vertices.position(2);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, VERTEX_SIZE, vertices);// 告知GL纹理坐标在缓冲区的位置

	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// 表面层改变(多次)
		Log.i("spl", "width=" + width + ",height=" + height);
		
	}

	

	@Override
	public void onDrawFrame(GL10 gl) {
		// 表面层的绘制(每秒调用多次)
		gl.glClearColor(1, 1, 1, 1);
		// 清空像素的帧缓冲区
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		
		
        //GL10.GL_TRIANGLES 绘制类型
        //0 代表顶点偏移量, 也就是从第一个顶点开始画起
        //3 顶点数量
        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);// 它将绘制三角形 // 必须


	}

	public Bitmap getBitmap(String path) {
		Bitmap bitmap = null;
		InputStream is = null;
		try {
			is = context.getAssets().open(path);
			bitmap = BitmapFactory.decodeStream(is);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return bitmap;
	}

	// 加载纹理
	public int loadTexture(GL10 gl, String path) {
		Bitmap bitmap = getBitmap(path);
		// 创建纹理对象
		int textureIds[] = new int[1];
		gl.glGenTextures(1, textureIds, 0);// 参数1:要创建多少纹理对象;参数2:纹理id在其中;保存id的起始点
		int textureId = textureIds[0];// 无法返回java对象,所以返回id

		// 纹理上传
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId); // 绑定纹理对象
		// 参数1:文理类型
		// 参数2:多纹理映射层次
		// 参数3:位图
		// 参数4:永远是0
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);// 上传纹理图片(android框架)

		// 设置过滤类型,(需要放大和缩小)
		// GL_NEAREST是一种过滤器类型,他和GL_LINEAR的区别是不做平滑过渡
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
				GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
				GL10.GL_NEAREST);

		// 取消绑定,释放资源,节省内存
		gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);
		bitmap.recycle();
		return textureId;
	}

}
