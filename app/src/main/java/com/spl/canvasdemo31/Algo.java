package com.spl.canvasdemo31;

/**
 * Created by 钧 on 2016/9/27.
 */
/**
 * 算法类
 * 该类作为一个工具类为其他类提供计算运动轨迹的方法
 * @author spl
 *
 */
public class Algo {

    /*****************************************
     * 根据角度计算在X轴上的位置
     * @param angle
     * @return
     *****************************************/
    public static double calcAngleMoveX(double angle){
        return (double)(Math.cos(angle*Math.PI / 180));
    }
    /*****************************************
     * 根据角度计算在Y轴上的位置
     * @param angle
     * @return
     *****************************************/
    public static double calcAngleMoveY(double angle){
        return (double)(Math.sin(angle*Math.PI / 180));
    }

    /**
     * 根据dx,dy反求角度
     * @return
     */
    public static double getAngle(double dx, double dy){
        return Math.atan2(dy, dx)*180/Math.PI;
    }
}
