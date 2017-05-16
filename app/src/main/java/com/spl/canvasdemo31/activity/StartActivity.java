package com.spl.canvasdemo31.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.spl.canvasdemo31.R;

/**
 * 列表索引界面
 * 1. Demo1 : xxx
 * 2. Demo2 : xxx
 */
public class StartActivity extends Activity
        implements AdapterView.OnItemClickListener {

    ListView lv;
    ArrayAdapter<String> adapter;
    // 跳转索引
    String[] titles = {

            "1带索引的旋转立方体",
            "2设置清屏颜色(第一步)",
            "3渲染一个纯色的三角形",
            "4渲染一个彩色的三角形",
            "5渲染一个纹理贴图的三角形",//4 OpenGL

            "6小球碰壁",//5
            "7一个小球的测试",
            "8控制一个小球的方向(触摸)",
            "9一个小球环绕另一小球做圆周运动",

            "10立方体(彩色的)", // 9 OpenGL
            "11立方体(纯色的)",
            "12立方体(贴图)"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags( WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_start);
        lv = (ListView) findViewById(R.id.lv);
        adapter = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, titles);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent it = null;
        if(position <= 4 || position >= 9) {
            it = new Intent(this, MainActivity.class);//4-9
        }else{
            it = new Intent(this, GraphicsActivity.class);//0-3    11-12
        }
        it.putExtra("index",position);
        startActivity(it);
    }
}
