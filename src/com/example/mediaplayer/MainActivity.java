package com.example.mediaplayer;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;



public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        if (savedInstanceState == null) {
        	FragmentManager manager = getSupportFragmentManager();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new SoundRep())
                    .commit();
        }
    }


  
}
