package com.spl.canvasdemo31;

/**
 * Created by 钧 on 2016/9/28.
 */
public class CircleR extends MyCircle {

    public CircleR(float radius, int color){
        super(0, 0);
        this.radius = radius;
        this.color = color;
    }

    float theta = 0; // 环绕的角度

    public void setTheta(float theta) {
        this.theta = theta;
    }

    public void setDt(float dt) {
        this.dt = dt;
    }

    public void setRd(float rd) {
        this.rd = rd;
    }

    /**
     * 设置环绕的中心点
     * @param rx
     * @param ry
     */
    public void setRxRy(float rx,float ry) {
        this.rx = rx;
        this.ry = ry;
    }


    float dt = 1; // 环绕的角度 的 增量;
    float rd = 50; // 环绕半径
    float rx, ry; // 环绕的中心

    /**
     * 计算每一帧的环绕距离
     */
    public void doRoundStep(){
        theta += dt;
        float drx = (float)(Algo.calcAngleMoveX(theta)*rd);
        float dry = (float)(Algo.calcAngleMoveY(theta)*rd);
        cx = rx + drx;
        cy = ry + dry;
    }

    public void update(){// 更新
        doRoundStep();
    }
}
