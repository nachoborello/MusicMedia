package com.sai.ui.listview;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.FilenameFilter;
import android.app.ListActivity;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.SimpleAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.view.View;
import android.media.MediaPlayer;
import android.net.Uri;
import android.media.AudioManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;

public class MyCustomListView extends ListActivity {
    
	public ListaCanciones lista = new ListaCanciones();
    final String MEDIA_PATH = new String("/storage/extSdCard/Music/");
	
	
	public void onCreate(Bundle savedInstanceState) {
        
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_list_view);
		
		//ArrayList<HashMap<String, String>> list = mp3select();
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        //ArrayList<HashMap<String, String>> list = mp3select();
        
        String[] STAR = { "*" };

	    
	    Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
	    String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
	    Cursor cursor;

	    cursor = getContentResolver().query(uri, STAR, selection, null, null);

	    ProfeList.chargeList(cursor);
	    
	    SimpleAdapter adapter = new SimpleAdapter(
        		this,
        		ProfeList.getSongs(),
        		R.layout.custom_row_view,
        		new String[] {"songTitle","songPath"},
        		new int[] {R.id.text1,R.id.text2}
        		);
        
        setListAdapter(adapter);
		
		
		
		
		
		
    }
    
	protected void onListItemClick(ListView l, View v, int position, long id) {

		super.onListItemClick(l, v, position, id);
		Object o = this.getListAdapter().getItem(position);
		long text = this.getListAdapter().getItemId(position);
		
		
		ProfeList.setActualSongId((int) text);
		
		 Intent intent = new Intent(this, ReproductorActivity.class);

	       intent.putExtra("Id", (int) text);
	       startActivity(intent);
	}

}