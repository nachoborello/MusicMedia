package com.sai.ui.listview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

public abstract class ProfeList {
	
	static ArrayList<HashMap<String, String>> songsList;
	static int actualSongId;
	static boolean aleatorio = false;
	static boolean repetir = false;
	
	public static int getActualSongId(){
		return actualSongId;
	}
	
	public static void setActualSongId(int id){
		actualSongId = id;
	}
	
	public static void siguienteCancion(){
		if (songsList.size()>actualSongId)
			actualSongId++;
		else
			actualSongId=0;
		
	}
	
	public static void anteriorCancion(){
		if (0<actualSongId)
			actualSongId--;
		else
			actualSongId=songsList.size();
		
	}
	
	public static String getNombre(){
		String nombre = songsList.get(actualSongId).get("songTitle");
		return nombre;
		
	}
	
	public static String getDireccion(){
		String direccion = songsList.get(actualSongId).get("songPath");
		return direccion;
		
	}
	
	
	
	public static ArrayList<HashMap<String, String>> getSongs(){
		return songsList;
	}
	
	public static boolean esAleatorio(){
		return aleatorio;
	}
	
	public static void setAleatorio (Boolean boleano){
		aleatorio = boleano;
	}
	
	public static void setRepetir (Boolean boleano){
		repetir = boleano;
	}
	
	public static boolean seRepite(){
		return repetir;
	}
	
	
	public static void chargeList(Cursor cursor){
		songsList = new ArrayList<HashMap<String, String>>();

	    if (cursor != null) {
	        if (cursor.moveToFirst()) {
	            do {
	                String songName = cursor
	                        .getString(cursor
	                                .getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));


	                String path = cursor.getString(cursor
	                        .getColumnIndex(MediaStore.Audio.Media.DATA));


	                String albumName = cursor.getString(cursor
	                        .getColumnIndex(MediaStore.Audio.Media.ALBUM));
	                int albumId = cursor
	                        .getInt(cursor
	                                .getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));

	                HashMap<String, String> song = new HashMap<String, String>();
	                song.put("songTitle",songName);
	                song.put("songPath",path );
	                songsList.add(song);

	            } while (cursor.moveToNext());


	        }

	    }		
	}
	
	
}
