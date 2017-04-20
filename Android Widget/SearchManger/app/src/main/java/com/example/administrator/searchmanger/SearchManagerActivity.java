package com.example.administrator.searchmanger;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
public class SearchManagerActivity extends Activity implements OnClickListener {
    private EditText etdata;
    private Button btnsearch;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etdata=(EditText)findViewById(R.id.etdata);
        btnsearch=(Button)findViewById(R.id.btncall);
        btnsearch.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        onSearchRequested();
    }
    /*
     * 为了能够使用search bar,我们必须重写Activity的onSearchRequested的方法，
     * search bar不会自动触发，必须通过一个按钮或者菜单的点击事件触发
     * @see android.app.Activity#onSearchRequested()
     */
    public boolean onSearchRequested(){
        String text=etdata.getText().toString();
        Bundle bundle=new Bundle();
        bundle.putString("data", text);
        //打开浮动搜索框（第一个参数默认添加到搜索框的值）,bundle为传递的数据
        startSearch("andy", false, bundle, false);
	  /*一定要返回true,
	   * 如果返回super.onSearchRequested()方法,则该搜索框默认值无法添加到搜索框中，bundle也无法传递出去
	   */
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
