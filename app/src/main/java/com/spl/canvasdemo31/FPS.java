package com.spl.canvasdemo31;

/**
 * Created by 钧 on 2016/9/27.
 */
public class FPS {

    /**
     * 计算平均帧速率
     */
    private long startTime = 0;
    private int numFrames = 0;
    private float fps = 0.0f;

    public float getFPS(){
        numFrames++;
        if(startTime == 0){
            startTime = System.currentTimeMillis();// 获取起始时间
        }else{
            long currentTime = System.currentTimeMillis();//获取当前时间
            long delta = (currentTime - startTime);// 计算时间的流逝
            if(delta >1000){
                fps = numFrames*1.0f / delta * 1000;// 计算平均每秒帧数(fps)
                numFrames = 0;
                startTime = currentTime;
            }
        }
        return fps;
    }
}
