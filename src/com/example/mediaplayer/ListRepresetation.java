package com.example.mediaplayer;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
// Representacion y detalle de la lista de emisoras

public class ListRepresetation extends ArrayAdapter<Emisora> {
    
	private int mLayout= -1;
	 
	public ListRepresetation(Context context, int resource,
			 List<Emisora> objects) {
		super(context, resource, objects);

		mLayout=resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

				
		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View tSIRow = inflater.inflate(mLayout, parent, false);

		Emisora fila = getItem(position);
		
		
		 TextView titulo = (TextView) tSIRow.findViewById(R.id.listitem_titulo);
		 titulo.setText(fila.getEmisoraNombre());
		 TextView subTitulo  = (TextView) tSIRow.findViewById(R.id.listitem_subtitulo);
		 subTitulo.setText(fila.getTipoStr());
		 ImageView imagen = (ImageView) tSIRow.findViewById(R.id.listitem_image);
		 imagen.setImageBitmap(fila.getLogo());
		 ProgressBar proBar = (ProgressBar)tSIRow.findViewById(R.id.listitem_loading);
		 proBar.setVisibility(View.INVISIBLE);
		 
		return tSIRow;
	}

		
}
