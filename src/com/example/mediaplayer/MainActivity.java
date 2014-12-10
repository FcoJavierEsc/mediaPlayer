package com.example.mediaplayer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

// Inicio de app

public class MainActivity extends ActionBarActivity implements
		List_Item_Fragment.Emisora_Seleccionada {

	private Emisora mEmisora = null;
	private FragmentManager mFgmgr = null ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_item_list_view);
//        EmisoraList em = new EmisoraList();
//        em.cargar(getResources());
//        mEmisora=em.getEmisora(0);
		mFgmgr = getSupportFragmentManager();
		if (mEmisora == null) {
			elige();
		} else {
			sintoniza();
		}
	}

	private void elige() {
		if (findViewById(R.id.frame_place) != null) {
	
			List_Item_Fragment listItemFragment = (List_Item_Fragment) mFgmgr
					.findFragmentById(R.id.frame_place);

			if (listItemFragment == null) {
				listItemFragment = new List_Item_Fragment();
				mFgmgr.beginTransaction()
						.add(R.id.frame_place, listItemFragment).commit();
			}
		}
	}

	private void sintoniza() {
		if (findViewById(R.id.frame_place) != null) {
			Fragment cual = mFgmgr.findFragmentById(R.id.frame_place);
			if (cual != null){
				mFgmgr.beginTransaction().remove(cual).commit();
				cual=null;
			}
			SoundRep soundRep = null;

			if (soundRep == null) {
				soundRep = new SoundRep();
				Bundle args = new Bundle();
				args.putString(SoundRep.URL, mEmisora.getUrl());
				soundRep.setArguments(args);
Log.v("PPPP","MANDAMOS "+mEmisora.getUrl());
				mFgmgr.beginTransaction().replace(R.id.frame_place, soundRep)
						.commit();
			}

		}

	}

	@Override
	public void onEmisraSeleccionada(Emisora emisora) {
		Log.v("PASAMOS", "emisora" + emisora.toString());
		mEmisora = emisora;
		sintoniza();
	}
}
