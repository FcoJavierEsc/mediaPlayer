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

// fragnent de atencion al usuario para escuchar, pausar o para una
//emisora.


public class SoundRep extends Fragment implements OnClickListener,
		ServiceConnection {
    final static String URL="com.example.mediaplayer.URL";
	
	
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
				mRadioBinder.startPause(mUrl);
			}

			break;
		case R.id.btn_stop:
			mRadioBinder.stop();
			break;
		}
		visibilidad();
	}

	private void visibilidad() {
		if (mRadioBinder != null) {
			if (mRadioBinder.exist())
				mStop.setVisibility(View.VISIBLE);
			else
				mStop.setVisibility(View.GONE);

			if (mRadioBinder.isPlaying())
				mPlaypause.setImageResource(android.R.drawable.ic_media_pause);
			else
				mPlaypause.setImageResource(android.R.drawable.ic_media_play);
		} else
			mStop.setVisibility(View.GONE);
	}

	@Override
	public void onDestroyView() {

		super.onDestroyView();
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		mRadioBinder = (RadioBinder) service;
		mRadioBinder.startPause(mUrl);
		mStop.setVisibility(View.VISIBLE);
		mPlaypause.setImageResource(android.R.drawable.ic_media_pause);
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {

		mRadioBinder = null;
	}

}
