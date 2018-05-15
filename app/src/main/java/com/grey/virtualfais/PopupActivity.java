package com.grey.virtualfais;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class PopupActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popup);

        getWindow().setLayout(200, 300);
        String room_id = getIntent().getStringExtra("room_id");
    }
}
