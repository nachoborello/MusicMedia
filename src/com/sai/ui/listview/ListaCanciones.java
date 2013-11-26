package com.sai.ui.listview;

import java.util.ArrayList;
import java.util.HashMap;

import android.provider.MediaStore;
import android.net.Uri;
import android.content.Context;
import android.database.Cursor;

public class ListaCanciones {
  private static ArrayList<HashMap<String, String>> listaC = new ArrayList<HashMap<String, String>>(); 
   
 public ListaCanciones(){
	Lista();
}


Context context;

	private void Lista(){
       try{
    	   
       
		ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
	    //String[] STAR = { "*" };
	    //Cursor cursor;
	    //Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
	    //String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";

	    //cursor = context.getContentResolver().query(uri, STAR, selection, null, null);
	    
		System.out.println("1");
		
	    Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] cursor_cols = { MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.ALBUM_ID,
                MediaStore.Audio.Media.DURATION };
        String where = MediaStore.Audio.Media.IS_MUSIC + "=1";
        Cursor cursor = context.getContentResolver().query(uri,
                cursor_cols, where, null, null);
	    
        
	    
	    System.out.println("de locos");
	    
	    while (cursor.moveToNext()) {
	    	String track = cursor.getString(cursor
                    .getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
            String data = cursor.getString(cursor
                    .getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
            
            
            HashMap<String, String> audioListModel = new HashMap<String, String>();
            audioListModel.put("songTitle", track);
            audioListModel.put("songPath", data);
            songsList.add(audioListModel);
	    }
	    
	    
	    /*if (cursor != null) {
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
	                song.put("songTitle",albumName);
	                song.put("songPath",path );
	                songsList.add(song);

	            } while (cursor.moveToNext());


	        }

	    }
	    cursor.close();
	    listaC = songsList;*/
	    
       } catch (Exception ex){
    	   System.out.println("SUPER ERROR!!!!" + ex);
       }
		
	}
	
	
	public String getName(int i){
		String name = "";
		ArrayList<HashMap<String, String>> lista = listaC;
		HashMap<String, String> cancion = lista.get(i);
		name = cancion.get("songTitle");
		return name;
		
	}
	public String getPath(int i){
		String path = "";
		ArrayList<HashMap<String, String>> lista = listaC;
		HashMap<String, String> cancion = lista.get(i);
		path = cancion.get("songPath");
		return path;
		
	}
	public int getCantidad(){
		
		ArrayList<HashMap<String, String>> canciones = listaC;
		int cantidadCanciones = canciones.size();
		
		
		return cantidadCanciones;
		
	}
	
	public String toString () {
		String string = "lalalalala";
		return string;
	}
	
	public ArrayList<HashMap<String, String>> getLista(){
		return listaC;
	}
}
		
		





	/*public ArrayList<HashMap<String, String>> mp3select(){
	       
		ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
	    String[] STAR = { "*" };

	    
	    Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
	    String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
	    Cursor cursor;

	    cursor = managedQuery(uri, STAR, selection, null, null);

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
	                song.put("songTitle",albumName+" "+songName+"___"+albumId);
	                song.put("songPath",path );
	                songsList.add(song);

	            } while (cursor.moveToNext());


	        }

	    }
		return songsList;
	}*/
