package com.example.mediaplayer;

import java.io.IOException;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class SoundRep extends Fragment implements OnClickListener {

	MediaPlayer mPlayer = null;
	private boolean mIsPrep = false;
	Button mPlaypause = null;
	Button mStop = null;

	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		super.onCreateView(inflater, container, savedInstanceState);

		View root = inflater.inflate(R.layout.fragment_main, container, false);

		Log.v("TTT", "ESOES");

		mPlaypause = (Button) root.findViewById(R.id.btn_play_pause);
		mStop = (Button) root.findViewById(R.id.btn_stop);
		mPlaypause.setOnClickListener(this);
		mStop.setOnClickListener(this);
		mStop.setVisibility(View.GONE);

		mPlaypause.setText("PLAY");

		if (mPlayer == null) {
			// mPlayer = MediaPlayer.create(root.getContext(), R.raw.uno);
			mPlayer = new MediaPlayer();

			// mPlayer.setDataSource("http://4613.live.streamtheworld.com:80/LOS40_SC");
			mPlayer.setOnPreparedListener(new OnPreparedListener() {

				@Override
				public void onPrepared(MediaPlayer mp) {
					mIsPrep = true;

					play();
				}

			});
		}
		return root;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_play_pause:
			if (mPlayer.isPlaying()) {

			} else {
				if (mIsPrep) {
					play();
				} else {
					try {
						mPlayer.setDataSource("http://4613.live.streamtheworld.com:80/LOS40_SC");
						mPlayer.prepare();
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
			break;
		case R.id.btn_stop:
			mPlayer.stop();
			mPlayer.reset();
			mStop.setVisibility(View.GONE);
			break;
		}

	}

	private void play() {
		mPlayer.pause();
		mPlaypause.setText("PLAY");
		mStop.setVisibility(View.GONE);
	}

	@Override
	public void onDestroyView() {

		super.onDestroyView();
		
		if (mPlayer!=null){
			mPlayer.release();
			mPlayer=null;
		}
	}
	
	
}
