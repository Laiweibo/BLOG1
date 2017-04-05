package com.example.administrator.submenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.SubMenu;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        SubMenu sub=menu.addSubMenu(R.string.title);
        sub.setIcon(android.R.drawable.ic_menu_search);
        sub.add(0,1,1,R.string.local);
        sub.add(0,2,2,R.string.network);
        return super.onCreateOptionsMenu(menu);
    }

}