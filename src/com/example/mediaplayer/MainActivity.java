package com.example.mediaplayer;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;



public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    	setContentView(R.layout.activity_item_list_view);

		FragmentManager fgmgr = getSupportFragmentManager();
		
		if (findViewById(R.id.frame_place)!=null){
			List_Item_Fragment listItemFragment = (List_Item_Fragment)fgmgr.findFragmentById(R.id.frame_place);
		
			if (listItemFragment==null){
				listItemFragment = new List_Item_Fragment();
				fgmgr.beginTransaction().add(R.id.frame_place, listItemFragment).commit();
			}
		}
		
//        setContentView(R.layout.activity_layout);
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.container, new SoundRep())
//                    .commit();
//        }
    }


  
}
