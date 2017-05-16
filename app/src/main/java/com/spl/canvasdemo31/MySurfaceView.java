package com.spl.canvasdemo31;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by 钧 on 2016/9/26.
 * Surface - 表面
 */
public class MySurfaceView extends SurfaceView
        implements SurfaceHolder.Callback {
    DrawThread dt; // 负责重绘的线程的引用
    FPS fps;
    Paint paint;// 画笔类, 设置参数

    World world;// 世界对象

    public MySurfaceView(Context context,World world) {
        super(context);
        this.getHolder().addCallback(this);//这里给SurfaceView对象添加创建,销毁和改变时的处理方法(回调函数)的引用; 因为是自己实现了SurfaceHolder.Callback,所以加的是自己
        dt = new DrawThread(this,getHolder());		//初始化重绘线程
        fps = new FPS();
        paint = new Paint();// 实例化画笔
        this.world = world;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 触屏的按下事件
        world.onTouchEvent(event);// 触摸事件
        return false;
    }

    public void update(){
        world.update();// 更新
    }

    @Override
    public void draw(Canvas canvas) {
        /************你的代码部分**
         * 这里是你自由发挥的地方
         * 你可以画你想画的任何东西
         *********************** */
        world.draw(canvas);// 绘制事件

        // 绘制帧速率
        paint.setColor(Color.parseColor("#000000"));// 设置颜色
        int size = 48;
        paint.setTextSize(size);
        String text = "fps: "+fps.getFPS();
        canvas.drawText(text, 100, 100, paint); // 字符串

    }

    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        // 来自于SurfaceHolder.Callback接口:当表面改变时调用的方法; 这里不用写东西
//        dt = null;
//        dt = new DrawThread(this,getHolder());
//        dt.start();
    }

    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        // 来自于SurfaceHolder.Callback接口:当表面创建时调用的方法; 这里要启动线程
        if(dt == null){// 避免空指针错误
            dt = new DrawThread(this,getHolder());
        }
        if (!dt.isAlive()) { // 如果DrawThread没有启动，就启动这个线程
            dt.start();
        }

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        // 来自于SurfaceHolder.Callback接口:当表面销毁时调用的方法; 这里要销毁线程
        dt.flag = false; // 停止线程的执行
        dt = null; // 将dt指向的对象声明为垃圾

    }

}
