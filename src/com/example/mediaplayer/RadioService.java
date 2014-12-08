package com.example.mediaplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;


// crea el bind para que la clase que hemos creado RadiBinder 
// sea conocida por la actividad o fragmento.

public class RadioService extends Service {

	private MediaPlayer mPlayer = null;

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		if (mPlayer != null) {
			mPlayer.release();
		}
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		RadioBinder nRadioBinder = new RadioBinder();
		return nRadioBinder;
	}

}
