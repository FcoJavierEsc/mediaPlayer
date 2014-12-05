package com.example.mediaplayer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.mediaplayer.RadioService.RadioBinder;

public class SoundRep extends Fragment implements OnClickListener,
		ServiceConnection {

	// private boolean mIsPrep = false;
	private ImageButton mPlaypause = null;
	private ImageButton mStop = null;
	private String mUrl = "http://4613.live.streamtheworld.com:80/LOS40_SC";
	private RadioBinder mRadioBinder = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		super.onCreateView(inflater, container, savedInstanceState);

		View root = inflater.inflate(R.layout.fragment_main, container, false);

		mPlaypause = (ImageButton) root.findViewById(R.id.btn_play_pause);
		mStop = (ImageButton) root.findViewById(R.id.btn_stop);
		mPlaypause.setOnClickListener(this);
		mStop.setOnClickListener(this);
		mStop.setVisibility(View.GONE);

		mPlaypause.setImageResource(android.R.drawable.ic_media_play);

		return root;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_play_pause:
			if (mRadioBinder == null) {
				Intent intentBind = new Intent(getActivity(),
						RadioService.class);
				getActivity().bindService(intentBind, this,
						Context.BIND_AUTO_CREATE);
			} else {
				mStop.setVisibility(View.VISIBLE);
				if (mRadioBinder.isPlaying()) {
					mRadioBinder.pause();
					mPlaypause.setImageResource(android.R.drawable.ic_media_play);
				} else {
					mRadioBinder.start(mUrl);
					mPlaypause.setImageResource(android.R.drawable.ic_media_pause);
				}
			}

			break;
		case R.id.btn_stop:
			if (mRadioBinder != null) {
				mRadioBinder.stop();
				getActivity().unbindService(this);
				
				mRadioBinder = null;
				mStop.setVisibility(View.GONE);
				mPlaypause.setImageResource(android.R.drawable.ic_media_play);
			}
			break;
		}

	}

	@Override
	public void onDestroyView() {

		super.onDestroyView();
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		mRadioBinder = (RadioBinder) service;
		mRadioBinder.start(mUrl);
		mStop.setVisibility(View.VISIBLE);
		mPlaypause.setImageResource(android.R.drawable.ic_media_pause);
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {

		mRadioBinder = null;
	}

}
