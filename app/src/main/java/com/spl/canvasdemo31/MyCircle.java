package com.spl.canvasdemo31;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by 钧 on 2016/9/23.
 * 负责,画自己
 */
public class MyCircle {

    Paint paint;// 画笔类, 设置参数

    float xContainerwidth;
    float yContainerheight;

    public void setyContainerheight(float yContainerheight) {
        this.yContainerheight = yContainerheight;
    }

    public void setxContainerwidth(float xContainerwidth) {
        this.xContainerwidth = xContainerwidth;
    }


    protected float cx;
    protected float cy;

    public float getCx() {
        return cx;
    }

    public float getCy() {
        return cy;
    }


    float dx;
    float dy;

    public void setStep(float step) {
        this.step = step;
    }

    float step;// 每一帧的运动增量
    float radius;// 半径
    int color; // 颜色

    public void setAngle(float angle) {
        this.angle = angle;
    }

    float angle; // 角度

    private void calcDxDy(){
        dx = (float)(Algo.calcAngleMoveX(angle)*step);
        dy = (float)(Algo.calcAngleMoveY(angle)*step);
    }

    public MyCircle(){}

    public MyCircle(float cx,float cy){
        this.paint = new Paint();
        this.cx = cx;
        this.cy = cy;
    }
    public MyCircle(float cx,float cy,float radius){
        this(cx, cy);
        this.radius = radius;
    }

    public MyCircle(float cx,float cy,float radius, int color){
        this(cx, cy);
        this.radius = radius;
        this.color = color;
    }

    public void setXY(float dx,float dy){
        this.dx = dx;
        this.dy = dy;
    }

    public void draw(Canvas canvas){

        paint.setColor(color);
       // paint.setStyle(Paint.Style.STROKE);// 设置风格为线条:不是实心的
       // paint.setStrokeWidth(5);// 设置线条的粗细
        canvas.drawCircle(cx,cy,radius,paint);// 根据圆心画实心圆
    }

    public void update(){// 更新
        cx += dx;
        cy += dy;
        wall();// 碰撞检测
    }

    /**
     * 碰四周边界反弹
     */
    public void wall(){
        if(cx < 0 + radius || cx > this.xContainerwidth - radius ){
            dx *= -1;
            if (cx < 0 + radius){
                // 在左边
                cx = radius;
            }
            if (cx > this.xContainerwidth - radius){
                // 在左边
                cx = this.xContainerwidth - radius;
            }
        }
        if(cy < 0 + radius || cy > this.yContainerheight - radius ){
            dy *= -1;
            if(cy < 0 + radius ){
                cy = radius;
            }
            if(cy > this.yContainerheight - radius){
                cy = this.yContainerheight - radius;
            }
        }
    }

}
