package com.example.mediaplayer;

import java.io.IOException;

import android.media.MediaPlayer;
import android.os.Binder;
import android.util.Log;

public class RadioBinder extends Binder {

	private MediaPlayer mPlayer = null;

	public void startPause(String url) {
		try {
			Log.v("FFFF", "GOOOOL");
			if (mPlayer == null) {
				mPlayer = new MediaPlayer();
				mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
					@Override
					public void onPrepared(MediaPlayer player) {
						player.start();
					}
				});
				mPlayer.setDataSource(url);
				mPlayer.prepareAsync();
			} else if (mPlayer.isPlaying()) {
				mPlayer.pause();
			} else
				mPlayer.start();

		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		if (mPlayer != null) {
			mPlayer.stop();
			mPlayer.reset();
			mPlayer = null;
		}
	}

	public boolean exist() {
		return mPlayer != null;
	}

	public boolean isPlaying() {
		if (mPlayer==null)
			return false;
		
		return mPlayer.isPlaying();
	}

	public void OnDestroy() {
		if (mPlayer != null) {
			stop();
		}
	}
}