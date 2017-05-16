package com.spl.canvasdemo31;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;

/**
 * 作业3: 一个小球,环绕着另一小球做圆周运动
 * Surface - 表面
 */
public class World3 extends World {

    MyCircle myCircle;
    // 小卫星
    CircleR circleR;

    public World3() {
        int r = 80;// 小球的半径
        int c = getRandomColor();// 小球的颜色
        myCircle = new MyCircle(1080/2,1920/2,r,c);

        float dx = 0; //= getRandomD(0.2f,10f);
        float dy = 0;// = getRandomD(0.2f,10f);

//        float angle = getRandom(0,360);// 小球的角度(初始方向)
//        float step = getRandomD(2, 10);// 步长=每一帧所走的距离=速度
//        dx = (float)(Algo.calcAngleMoveX(angle)*step);
//        dy = (float)(Algo.calcAngleMoveY(angle)*step);

        Log.i("spl","dx="+dx+",dy="+dy);
        myCircle.setXY(dx,dy);
        myCircle.setxContainerwidth(1080);
        myCircle.setyContainerheight(1920);

        // 创建卫星
        circleR = new CircleR(30, getRandomColor());
        circleR.setRd(150);
        circleR.setRxRy(myCircle.cx,myCircle.cy);
        circleR.setDt(20);
    }


    public boolean onTouchEvent(MotionEvent event) {
        // 触屏的按下事件
        if (event.getAction() == MotionEvent.ACTION_DOWN
            //|| event.getAction() == MotionEvent.ACTION_MOVE
                ){
            // 获取坐标
            float x = event.getX();
            float y = event.getY();

            // 计算两点之间的距离
            float dx = x - myCircle.getCx();
            float dy = y - myCircle.getCy();

            // 计算出两点的夹角
            float angle = (float) Algo.getAngle(dx,dy);
            // 计算出两点的距离
            float d = (float) (Math.sqrt(dx * dx + dy * dy));
            float step = d * 0.01f;// 步长=每一帧所走的距离=速度
            dx = (float)(Algo.calcAngleMoveX(angle)*step);
            dy = (float)(Algo.calcAngleMoveY(angle)*step);
            myCircle.setXY(dx,dy);

        }
        return false;
    }

    public int getRandomColor(){
        int a = getRandom(200,255);
        int r = getRandom(0,255);
        int g = getRandom(0,255);
        int b = getRandom(0,255);
        return Color.argb(a, r, g, b);//
    }

    public void update(){
        myCircle.update();
        circleR.update();
        circleR.setRxRy(myCircle.cx,myCircle.cy);
    }

    public void draw(Canvas canvas) {
        /************你的代码部分**
         * 这里是你自由发挥的地方
         * 你可以画你想画的任何东西
         *********************** */
        canvas.drawColor(Color.parseColor("#f0f0f0"));// 画背景颜色

        myCircle.draw(canvas);
        circleR.draw(canvas);
    }

}
