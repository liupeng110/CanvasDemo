package com.spl.canvasdemo31;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 钧 on 2016/9/23.
 * 自定义View
 */
public class MyView extends View {

    Paint paint;// 画笔类, 设置参数
    Bitmap bitmap;// 声明一个位图对象

    List<MyCircle> list = new ArrayList<MyCircle>();

    public MyView(Context context) {
        super(context);
        paint = new Paint();// 实例化画笔
        bitmap =BitmapFactory.decodeResource(
                getResources(), R.drawable.ic_launcher);
        drawThread();// 启动线程
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 触屏的按下事件
        if (event.getAction() == MotionEvent.ACTION_DOWN
            //|| event.getAction() == MotionEvent.ACTION_MOVE
                ){
            // 获取坐标
            float x = event.getX();
            float y = event.getY();
           // Toast.makeText(getContext(), "("+x + "," + y+")", Toast.LENGTH_SHORT).show();
            int r = getRandom(20, 200);
            int c = getRandomColor();
            MyCircle myCircle = new MyCircle(x,y,r,c);
            // 添加到集合
            list.add(myCircle);
            this.invalidate();// 触发重绘 -->onDraw(Canvas canvas)
        }
        return false;
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1){
                MyView.this.invalidate();// 触发重绘 -->onDraw(Canvas canvas)
            }
        }
    };

    private void drawThread(){
        new Thread(){
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(20);
                        update();// 更新所有的对象
                        handler.sendEmptyMessage(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    /**
     * 产生一个随机的argb的颜色
     * @return
     */
    private int getRandomColor(){
        int a = getRandom(0,255);
        int r = getRandom(0,255);
        int g = getRandom(0,255);
        int b = getRandom(0,255);
        return Color.argb(a,r,g,b);//
    }
    /**
     * 产生一个随机整数n, min <= n <= max
     * @param min
     * @param max
     * @return
     */
    private int getRandom(int min, int max){
        return (int)(Math.random()*(max - min + 1)) + min;
    }

    private void update(){
        for (int i = 0; i < list.size(); i++) {
            list.get(i).update();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制这个组件的界面的
        //Canvas canvas -- 画布对象
        canvas.drawColor(Color.parseColor("#f0f0f0"));// 画背景颜色

        // 循环遍历
        for (int i = 0; i < list.size(); i++) {
            list.get(i).draw(canvas);// 绘制每个元素
        }


//        paint.setColor(Color.parseColor("#999999"));// 设置颜色
//        canvas.drawRect(100, 300, 100 + 300, 300 + 100, paint);// 画矩形
//
//
//
//        paint.setColor(Color.GREEN);
//        paint.setStrokeWidth(20);// 设置线条的粗细
//        canvas.drawLine(50, 10, 100, 190, paint); // 线段
//
//        paint.setColor(Color.BLUE);
//        RectF oval = new RectF(450, 430, 450 + 100, 430 + 100);// 2个顶点
//        canvas.drawOval(oval, paint);// 椭圆
//
//        paint.setColor(Color.BLUE);
//        paint.setStyle(Paint.Style.STROKE);// 设置风格为线条:不是实心的
//        paint.setStrokeWidth(5);// 设置线条的粗细
//        canvas.drawCircle(1080/2,1920/2,300,paint);// 根据圆心画实心圆
//
//        paint.setColor(Color.parseColor("#000000"));// 设置颜色
//        int size = 72;
//        paint.setTextSize(size);
//        String text = "这是字符串";
//        int len = text.length();
//        int length = len * size;
//        float tx = length * 1.0f / 2;// 有小数点
//        float ty = size * 1.0f / 2;// 有小数点
//        canvas.drawText(text, 1080/2 - tx, 1920/2 + ty, paint); // 字符串
//
//        canvas.drawBitmap(bitmap, 1080/2, 1920/2+500, paint);


    }


}
