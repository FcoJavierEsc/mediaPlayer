package com.example.mediaplayer;

import java.io.IOException;

import com.example.mediaplayer.RadioService.RadioBinder;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
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
import android.widget.Toast;

public class SoundRep extends Fragment implements OnClickListener ,ServiceConnection{


	private boolean mIsPrep = false;
	private Button mPlaypause = null;
	private Button mStop = null;

	private RadioBinder mRadioBinder=null;
	
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

		if (mRadioBinder == null) {
			 Intent intentBind = new Intent(getActivity(),RadioService.class);
			 getActivity().bindService(intentBind,this,Context.BIND_AUTO_CREATE);
			 
		}
		
		Button btnser = (Button) root.findViewById(R.id.btn_start_service);
		btnser.setOnClickListener(this);
		Button stpser = (Button) root.findViewById(R.id.btn_stop_service);
		stpser.setOnClickListener(this);
		

		Button bndser = (Button) root.findViewById(R.id.btn_bind_service);
		stpser.setOnClickListener(this);
		Button unbndser = (Button) root.findViewById(R.id.btn_unbind_service);
		stpser.setOnClickListener(this);
		Button addser = (Button) root.findViewById(R.id.btn_add_service);
		stpser.setOnClickListener(this);
		return root;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_play_pause:
			

			
			break;
		case R.id.btn_stop:
		
			mStop.setVisibility(View.GONE);
			break;
					
			
			
		case R.id.btn_bind_service:
			 Intent intentBind = new Intent(getActivity(),RadioService.class);
			 intentBind.putExtra(RadioService.EXTRA_URL, "http://4613.live.streamtheworld.com:80/LOS40_SC");
		    
			getActivity().bindService(intentBind,this,Context.BIND_AUTO_CREATE);
			break;
		case R.id.btn_unbind_service:
			 getActivity().unbindService(this);
			 if (mRadioBinder != null) {
				 getActivity().unbindService(this);
				 mRadioBinder = null;
			 }
			break;
		
		case R.id.btn_add_service:
			if (mRadioBinder!=null){
				Toast.makeText(getActivity(), "2+3"+mRadioBinder.suma(2,3), Toast.LENGTH_SHORT).show();
			}
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
		
	
	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		mRadioBinder = (RadioBinder) service;
		
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {
		// TODO Auto-generated method stub
		mRadioBinder= null;
	}
	
	
}
