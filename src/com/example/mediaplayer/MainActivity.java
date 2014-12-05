package com.example.mediaplayer;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;



public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new SoundRep())
                    .commit();
        }
    }


  
}
