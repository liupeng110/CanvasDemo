package com.spl.canvasdemo31.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.spl.canvasdemo31.MySurfaceView;
import com.spl.canvasdemo31.World;
import com.spl.canvasdemo31.World1;
import com.spl.canvasdemo31.World2;
import com.spl.canvasdemo31.World3;

/**
 * 界面中的包含的是SufaceView
 */
public class GraphicsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 全屏显示
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        int index = getIntent().getIntExtra("index",5);
        World w = null;
        if (index == 5) {
            w = new World1();
        }
        if (index == 6) {
            w = new World();// 测试
        }
        if (index == 7) {
            w = new World2();// 测试
        }
        if (index == 8) {
            w = new World3();// 测试
        }
        setContentView(new MySurfaceView(this,w));

    }

}
