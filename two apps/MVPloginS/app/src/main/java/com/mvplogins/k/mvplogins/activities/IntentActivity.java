package com.mvplogins.k.mvplogins.activities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.mvplogins.k.mvplogins.R;

public class IntentActivity extends Activity {
    TextView txt,txt1,txt2,txt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
        txt = findViewById(R.id.txt);
        txt1 = findViewById(R.id.txt1);
        txt2  = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        txt.setMovementMethod(new ScrollingMovementMethod());
        txt1.setMovementMethod(new ScrollingMovementMethod());
        txt2.setMovementMethod(new ScrollingMovementMethod());
        txt3.setMovementMethod(new ScrollingMovementMethod());
    }
}
