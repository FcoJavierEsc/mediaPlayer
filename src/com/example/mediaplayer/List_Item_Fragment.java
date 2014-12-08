package com.example.mediaplayer;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

// vista de las emisoras en lista

public class List_Item_Fragment extends Fragment {
	
	Emisora_Seleccionada mCallback;
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		
		try {
			mCallback = (Emisora_Seleccionada)activity;
		} catch(ClassCastException e){
			throw new ClassCastException(activity.toString()
                    + " debe implementar Emisora_Seleccionada");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		super.onCreateView(inflater, container, savedInstanceState);

		View root = inflater.inflate(R.layout.item_list_view, container, false);
		ListView list = (ListView) root.findViewById(R.id.item_list_view_id);


		final EmisoraList listEmisoras = new EmisoraList();
		listEmisoras.cargar(getResources());


		list.setAdapter(new ListRepresetation(getActivity(),
				R.layout.list_item_row, listEmisoras.cloneEmisorasList()));

		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
                    
				mCallback.onEmisraSeleccionada(listEmisoras.getEmisora(position));
			}
			
		});
		
		return root;
	}
	
	   public interface Emisora_Seleccionada {
	        public void onEmisraSeleccionada(Emisora emisora);
	    }



}
