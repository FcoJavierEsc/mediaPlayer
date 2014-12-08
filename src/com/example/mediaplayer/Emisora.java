package com.example.mediaplayer;

import android.graphics.Bitmap;

public class Emisora {

	private TipoEmision mTipo;
	private String mEmisoraNombre;
	private String mUrl;
	private Bitmap mLogo;

	public TipoEmision getTipo() {
		return mTipo;
	}

	public String getTipoStr() {
		switch (mTipo) {
		case CLASICA:
			return "Clásica";
		case GENERALISTA:
			return "Generalista";
		case MUSICA:
			return "Música";
		case NOTICIAS:
			return "Noticias";
		default:
			return "Alto se olvida";
		}
	}

	public void setTipo(TipoEmision tipo) {
		this.mTipo = tipo;
	}

	public String getEmisoraNombre() {
		return mEmisoraNombre;
	}

	public void setEmisoraNombre(String emisoraNombre) {
		this.mEmisoraNombre = emisoraNombre;
	}

	public String getUrl() {
		return mUrl;
	}

	public void setUrl(String url) {
		this.mUrl = url;
	}

	public Emisora(TipoEmision tipo, String emisoraNombre, String url,
			Bitmap logo) {
		super();
		this.mTipo = tipo;
		this.mEmisoraNombre = emisoraNombre;
		this.mUrl = url;
		this.mLogo = logo;
	}

	public Bitmap getLogo() {
		return mLogo;
	}

	public void setLogo(Bitmap logo) {
		this.mLogo = logo;
	}

}
