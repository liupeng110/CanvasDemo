package com.spl.canvasdemo31;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * 演示一个小球,向着固定方向运动
 * Surface - 表面
 */
public class World {

    List<MyCircle> list = new ArrayList<MyCircle>();

    public World() {

    }

    public boolean onTouchEvent(MotionEvent event) {
        // 触屏的按下事件
        if (event.getAction() == MotionEvent.ACTION_DOWN
            //|| event.getAction() == MotionEvent.ACTION_MOVE
                ){
            // 获取坐标
            float x = event.getX();
            float y = event.getY();
            int r = 50;
            int c = getRandomColor();
            MyCircle myCircle = new MyCircle(x,y,r,c);

            float dx; //= getRandomD(0.2f,10f);
            float dy;// = getRandomD(0.2f,10f);

            float angle = 0;
            float step = 20;
            dx = (float)(Algo.calcAngleMoveX(angle)*step);
            dy = (float)(Algo.calcAngleMoveY(angle)*step);

            Log.i("spl","dx="+dx+",dy="+dy);
            myCircle.setXY(dx,dy);
            myCircle.setxContainerwidth(1080);// 容器的宽度
            myCircle.setyContainerheight(1920);// 容器的高度
            // 添加到集合
            list.add(myCircle);
        }
        return false;
    }

    public float getRandomD(float min, float max){
        return (float)(Math.random()*(max - min + 1)) + min;
    }

    /**
     * 产生一个随机的argb的颜色
     * @return
     */
    public int getRandomColor(){
        int a = getRandom(80,255);
        int r = getRandom(0,255);
        int g = getRandom(0,255);
        int b = getRandom(0,255);
        return Color.argb(a, r, g, b);//
    }
    /**
     * 产生一个随机整数n, min <= n <= max
     * @param min
     * @param max
     * @return
     */
    public int getRandom(int min, int max){
        return (int)(Math.random()*(max - min + 1)) + min;
    }


    public void update(){
        for (int i = 0; i < list.size(); i++) {
            list.get(i).update();
        }
    }

    public void draw(Canvas canvas) {
        /************你的代码部分**
         * 这里是你自由发挥的地方
         * 你可以画你想画的任何东西
         *********************** */
        canvas.drawColor(Color.parseColor("#f0f0f0"));// 画背景颜色

        // 循环遍历
        for (int i = 0; i < list.size(); i++) {
            list.get(i).draw(canvas);// 绘制每个元素
        }
    }

}
