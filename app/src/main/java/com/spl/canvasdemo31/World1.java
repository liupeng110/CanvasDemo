package com.spl.canvasdemo31;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * 多个小球碰壁的演示
 * Surface - 表面
 */
public class World1 extends World {

    List<MyCircle> list = new ArrayList<MyCircle>();

    public World1() {

    }

    public boolean onTouchEvent(MotionEvent event) {
        // 触屏的按下事件
        if (event.getAction() == MotionEvent.ACTION_DOWN
            //|| event.getAction() == MotionEvent.ACTION_MOVE
                ){
            // 获取坐标
            float x = event.getX();
            float y = event.getY();
            int r = getRandom(20, 60);// 小球的半径
            int c = getRandomColor();// 小球的颜色
            MyCircle myCircle = new MyCircle(x,y,r,c);

            float dx; //= getRandomD(0.2f,10f);
            float dy;// = getRandomD(0.2f,10f);

            float angle = getRandom(0,360);// 小球的角度(初始方向)
            float step = getRandomD(2, 10);// 步长=每一帧所走的距离=速度
            dx = (float)(Algo.calcAngleMoveX(angle)*step);
            dy = (float)(Algo.calcAngleMoveY(angle)*step);

            Log.i("spl","dx="+dx+",dy="+dy);
            myCircle.setXY(dx,dy);
            myCircle.setxContainerwidth(1080);
            myCircle.setyContainerheight(1920);
            // 添加到集合
            list.add(myCircle);
        }
        return false;
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
