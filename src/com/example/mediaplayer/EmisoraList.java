package com.example.mediaplayer;

import java.util.LinkedList;
import java.util.List;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
// Singleton de la lista de emisoras


public class EmisoraList {

	
	/**
	 * Singleton
	 */
	private static EmisoraList sInstance = null;

	/**
	 * Lista de emisoras
	 */
	private List<Emisora> mListEmisora = null;

	/**
	 * Método para obtener la instancia del singleton
	 */
	public static EmisoraList getInstance() {
		if (sInstance == null) {
			sInstance = new EmisoraList();
		}

		return sInstance;
	}


	public EmisoraList() {
		super();
		this.mListEmisora = new LinkedList<>();
	}
 
	
	public void setEmisora(TipoEmision tipo, String emisoraNombre, String url, Bitmap logo){
		mListEmisora.add( new Emisora(tipo, emisoraNombre, url, logo));
	}

	
	
	public void cargar(Resources resource){
		setEmisora(TipoEmision.NOTICIAS,"Es Radio.","http://91.121.68.52:8054/",BitmapFactory.decodeResource(resource, R.drawable.esradio));
	    setEmisora(TipoEmision.MUSICA,"40 Principales.","http://4613.live.streamtheworld.com:80/LOS40_SC", BitmapFactory.decodeResource(resource, R.drawable.principales40));
	    setEmisora(TipoEmision.MUSICA, "Kiss FM","http://kissfm.es.audio1.glb.ipercast.net:8000/kissfm.es/mp3", BitmapFactory.decodeResource(resource, R.drawable.kiss));
	}
	public List<Emisora> cloneEmisorasList() {

		List<Emisora> copy = new LinkedList<Emisora>();

		for (Emisora rem : mListEmisora) {
			copy.add(rem);
		}

		return copy;
	}
	
	public Emisora getEmisora(int cual){
		
	    for (Emisora rem :mListEmisora){
	    if (cual == 0)
	    	return rem;
	    cual--;
	    }
	return null;
	}
}



