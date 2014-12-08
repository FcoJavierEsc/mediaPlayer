package com.example.mediaplayer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class List_Item_Fragment extends Fragment {

	public List_Item_Fragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState) {

		
		super.onCreateView(inflater, container, savedInstanceState);
		
		View root = inflater.inflate(R.layout.item_list_view, container, false);
		ListView list = (ListView) root.findViewById(R.id.item_list_view_id);
	
		
		Log.v("MMMM", "tview");
	
		EmisoraList listEmisoras = new EmisoraList();
		listEmisoras.cargar(getResources());
		list.setAdapter(new ListRepresetation(getActivity(), R.layout.list_item_row, listEmisoras.cloneEmisorasList()));
		
		return root;
	}
}
