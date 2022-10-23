package com.example.big_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost=(TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();

        LayoutInflater inflater= LayoutInflater.from(this);

        inflater.inflate(R.layout.tab1,tabHost.getTabContentView());
        inflater.inflate(R.layout.tab2,tabHost.getTabContentView());
        inflater.inflate(R.layout.tab3,tabHost.getTabContentView());

        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("功能1").setContent(R.id.tab1));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("功能2").setContent(R.id.tab2));
        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("功能3").setContent(R.id.tab3));

        //设置超链接 从strings文件中，获取他的链接。
        TextView tab1_text1 = (TextView) findViewById(R.id.tab1_text1);
        tab1_text1.setMovementMethod(LinkMovementMethod.getInstance());

        TextView tab1_text2 = (TextView) findViewById(R.id.tab1_text2);
        tab1_text2.setMovementMethod(LinkMovementMethod.getInstance());
    }
}

