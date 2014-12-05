package com.example.mediaplayer;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class RadioService extends Service {

	private MediaPlayer mPlayer = null;

	// public static final String EXTRA_URL =
	// "com.example.mediaplayer.EXTRA_URL";

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		// Log.v("FFFF",intent.getStringExtra(EXTRA_URL));

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		Log.v("FFFF", "STOP");

		if (mPlayer != null) {
			mPlayer.release();
			// mPlayer=null;
		}
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		RadioBinder nRadioBinder = new RadioBinder();
		// nRadioBinder.
		return nRadioBinder;
	}

	public class RadioBinder extends Binder {
		public boolean isPlaying() {
			boolean ret = false;
			if (mPlayer != null)
				ret = mPlayer.isPlaying();
			return ret;
		}

		public void start(String url) {

			try {
				if (mPlayer == null) {
					mPlayer = new MediaPlayer();
					mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

						@Override
						public void onPrepared(MediaPlayer player) {
							player.start();
						}

					});

					mPlayer.setDataSource(url);
				}
				mPlayer.prepareAsync();

			} catch (IllegalStateException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}

		}

		public void pause() {
			if (mPlayer.isPlaying()) {
				mPlayer.pause();
			}
		}

		public void stop() {
			mPlayer.stop();
			mPlayer.reset();
		}

	}


}
