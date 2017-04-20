package com.example.administrator.searchmanger;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
public class SearchResultActivity extends Activity implements OnClickListener{
    private TextView tvquery,tvdata;
    private Button btnsearch;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchresult);
        tvquery=(TextView)findViewById(R.id.tvquery);
        tvdata=(TextView)findViewById(R.id.tvdata);
        btnsearch=(Button)findViewById(R.id.btnSearch);
        doSearchQuery();
        btnsearch.setOnClickListener(this);
    }
    //接收query和bundle、保存query值（即搜索建议的列表值）
    public void doSearchQuery(){
        final Intent intent = getIntent();
        //获得搜索框里值
        String query=intent.getStringExtra(SearchManager.QUERY);
        tvquery.setText(query);
        //保存搜索记录
        SearchRecentSuggestions suggestions=new SearchRecentSuggestions(this,
                SearchSuggestionSampleProvider.AUTHORITY, SearchSuggestionSampleProvider.MODE);
        suggestions.saveRecentQuery(query, null);
        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
            //获取传递的数据
            Bundle bundled=intent.getBundleExtra(SearchManager.APP_DATA);
            if(bundled!=null){
                String ttdata=bundled.getString("data");
                tvdata.setText(ttdata);
            }else{
                tvdata.setText("no data");
            }
        }
    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        onSearchRequested();
    }
    @Override
    public boolean onSearchRequested(){
        startSearch("onNewIntent", false, null, false);
        return true;
    }
    /*触发search bar的Activity同时也是处理搜索结果的Activity时，
     * 第一步:在配置文件中，处理搜索结果的Activity添加android:launchMode="singleTop"属性
     * 第二步:重写Activity的onNewIntent(Intent intent)
     */
    @Override
    public void onNewIntent(Intent intent){
        super.onNewIntent(intent);
        //获得搜索框里值
        String query=intent.getStringExtra(SearchManager.QUERY);
        tvquery.setText(query);
        //保存搜索记录
        SearchRecentSuggestions suggestions=new SearchRecentSuggestions(this,
                SearchSuggestionSampleProvider.AUTHORITY, SearchSuggestionSampleProvider.MODE);
        suggestions.saveRecentQuery(query, null);
        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
            //获取传递的数据
            Bundle bundled=intent.getBundleExtra(SearchManager.APP_DATA);
            if(bundled!=null){
                String ttdata=bundled.getString("data");
                tvdata.setText(ttdata);
            }else{
                tvdata.setText("no data");
            }
        }
    }
}
