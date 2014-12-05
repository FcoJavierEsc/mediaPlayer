package com.example.mediaplayer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mediaplayer.RadioService.RadioBinder;

public class SoundRep extends Fragment implements OnClickListener,
		ServiceConnection {

	// private boolean mIsPrep = false;
	private ImageButton mPlaypause = null;
	private ImageButton mStop = null;

	private RadioBinder mRadioBinder = null;

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

		mPlaypause = (ImageButton) root.findViewById(R.id.btn_play_pause);
		mStop = (ImageButton) root.findViewById(R.id.btn_stop);
		mPlaypause.setOnClickListener(this);
		mStop.setOnClickListener(this);
		mStop.setVisibility(View.GONE);

		mPlaypause.setImageResource(android.R.drawable.ic_media_play);

		Button btnser = (Button) root.findViewById(R.id.btn_start_service);
		btnser.setOnClickListener(this);
		Button stpser = (Button) root.findViewById(R.id.btn_stop_service);
		stpser.setOnClickListener(this);

		Button bndser = (Button) root.findViewById(R.id.btn_bind_service);
		bndser.setOnClickListener(this);
		Button unbndser = (Button) root.findViewById(R.id.btn_unbind_service);
		unbndser.setOnClickListener(this);
		return root;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_play_pause:
			if (mRadioBinder != null) {
				if (mRadioBinder.isPlaying()) {
					mRadioBinder.pause();
				} else {
					Toast.makeText(getActivity(), "TES PLAY",
							Toast.LENGTH_SHORT).show();

					mRadioBinder
							.start("http://4613.live.streamtheworld.com:80/LOS40_SC");
					Toast.makeText(getActivity(), "pues PLAY",
							Toast.LENGTH_SHORT).show();
				}
			} else
				Toast.makeText(getActivity(), "NO ESTA ACTIVO",
						Toast.LENGTH_SHORT).show();
			break;
		case R.id.btn_stop:
			if (mRadioBinder != null) {
				mRadioBinder.stop();
			}
			break;

		// case R.id.btn_bind_service:
		// Intent intentBind = new Intent(getActivity(),RadioService.class);
		// //intentBind.putExtra(RadioService.EXTRA_URL,
		// "http://4613.live.streamtheworld.com:80/LOS40_SC");
		// getActivity().bindService(intentBind,this,Context.BIND_AUTO_CREATE);
		// break;
		//
		case R.id.btn_bind_service:
			if (mRadioBinder == null) {
				Intent intentBind = new Intent(getActivity(),
						RadioService.class);
				Toast.makeText(getActivity(), "Bind antes", Toast.LENGTH_SHORT)
						.show();
				Log.v("=========================", "VAMOS ANTES");
				getActivity().bindService(intentBind, this,
						Context.BIND_AUTO_CREATE);
				Log.v("=========================", "VAMOS despues");
				Toast.makeText(getActivity(), "Bind despues",
						Toast.LENGTH_SHORT).show();

			} else
				Toast.makeText(getActivity(), "Bind no necesario",
						Toast.LENGTH_SHORT).show();

			break;
		case R.id.btn_unbind_service:
			if (mRadioBinder != null) {
				getActivity().unbindService(this);
				mRadioBinder = null;
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
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		// TODO Auto-generated method stub
		mRadioBinder = null;
	}

}
