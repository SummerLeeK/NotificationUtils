package com.fudaoculture.lee.notificationmanagerutils;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/8/3 0003.
 */

public class ContentActivity extends Activity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_text);

        textView = findViewById(R.id.text);

        textView.setText("ContentActivity");
    }
}
