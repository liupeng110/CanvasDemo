package com.spl.canvasdemo31;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by 钧 on 2016/9/26.
 * 重绘线程
 */
public class DrawThread extends Thread {
    MySurfaceView sv; // MySurfaceView对象引用
    SurfaceHolder sh; // SurfaceHolder对象引用
    boolean flag = false; // 线程执行标志
    int sleep = 30;  // 休眠时间,通过此变量可以调节帧速率

    /**
     * 构造器
     * @param sv
     * @param sh
     */
    public DrawThread(MySurfaceView sv,SurfaceHolder sh){
        this.sv = sv;		//为对象赋值
        this.sh = sh;	//为对象赋值
        this.flag = true;		//设置线程状态为执行
    }
    /**
     * 重写线程的执行方法，用于绘制屏幕和计算帧速率
     */
    public void run(){
        Canvas canvas = null;//声明一个画布对象
        while(flag){//经典的死循环,这个不用解释啦
            try{
                canvas = sh.lockCanvas(null);//获取表面的画布
                synchronized(sh){

                    sv.update();// 更新
                    sv.draw(canvas);		//进行绘制
                }
            }
            catch(Exception e){
                e.printStackTrace();			//捕获并打印异常
            }
            finally{
                if(canvas != null){		//如果canvas不为空
                    sh.unlockCanvasAndPost(canvas);//surfaceHolder解锁并将画布对象传回
                }
            }

            try{
                Thread.sleep(sleep);		//线程休眠一段时间
            }
            catch(Exception e){
                e.printStackTrace();		//捕获并打印异常
            }
        }

    }

}
