package com.spl.canvasdemo31.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Utility {
	//转换为Native类型的浮点缓存
	public static FloatBuffer createFloatBuffer(float data[])
	{
		ByteBuffer vbb=ByteBuffer.allocateDirect(data.length*4);
		vbb.order(ByteOrder.nativeOrder());
		FloatBuffer outBuffer=vbb.asFloatBuffer();
		outBuffer.put(data).position(0);
		return outBuffer;
	}
	//将资源图片转换为位图bitmap
	public static Bitmap getTextureFromBitmapResource(Context context,int resourceId)
	{
		Bitmap bitmap=null;
		Matrix yFlipMatrix=new Matrix();
		yFlipMatrix.postScale(1, -1);
		try
		{	
			bitmap=BitmapFactory.decodeResource(context.getResources(), resourceId);
			return Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),yFlipMatrix,false);
		}catch(Exception e)
		{
			return null;
		}finally
		{
			if(bitmap!=null)
				bitmap.recycle();
		}
		
		
	}
}
