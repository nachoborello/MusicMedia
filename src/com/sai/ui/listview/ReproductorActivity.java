package com.sai.ui.listview;

import java.io.IOException;
import java.util.Random;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class ReproductorActivity extends Activity {

	private MediaPlayer mp;
	private Bundle savedInstanceState2;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reproductor);
		Boolean aleatorio = ProfeList.esAleatorio();
		Boolean repetir = ProfeList.seRepite();
		CheckBox cboxAleatorio = (CheckBox) findViewById(R.id.aleatorio);
		CheckBox cboxRepetir = (CheckBox) findViewById(R.id.cbox_repetir);
		cboxAleatorio.setChecked(aleatorio);
		cboxRepetir.setChecked(repetir);
		
		String name = ProfeList.getNombre();
		
		/**poner el nombre del tema en la actividad*/
		
		TextView txtNombre = (TextView) findViewById(R.id.txtNombre);
		txtNombre.setText(name);
		
		/**reproducir el tema*/
		
		/*String path = extras.getString("path");
		mp = MediaPlayer.create(this, Uri.parse(path));
		mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mp.start();*/
		mp = new MediaPlayer();
		
		 mp.setOnCompletionListener(new  MediaPlayer.OnCompletionListener() { 
	            public  void  onCompletion(MediaPlayer mediaPlayer) { 
	            	
	            	CheckBox aleatorio = (CheckBox) findViewById(R.id.aleatorio);
	            	CheckBox repetir = (CheckBox) findViewById(R.id.cbox_repetir);
	        		if (repetir.isChecked() == true){
	        			mp.stop();
	        			mp.release();
	        			ProfeList.setRepetir(true);
	        			onCreate(savedInstanceState2);	
	        		}
	        		if (repetir.isChecked() == false){
	        			
	        		
	        			if (aleatorio.isChecked() == true){
	        				Random r = new Random();
	        				int num=r.nextInt(ProfeList.getSongs().size()-0) + 0;
	        				ProfeList.setActualSongId(num);
	        				ProfeList.setAleatorio(true);
	        				mp.stop();
	        				mp.release();
	        				ProfeList.setRepetir(false);
	        				onCreate(savedInstanceState2);
	        			}
	        			else {
	        				ProfeList.setAleatorio(false);
	        				ProfeList.siguienteCancion();
	        				mp.stop();
	        				mp.release();
	        				ProfeList.setRepetir(false);
	        				onCreate(savedInstanceState2);
	        			}
	        		}
	            } 
	        }); 
		
		empezar();
		
		
		
		
		
	       
		
	}

	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reproductor, menu);
				return true;
	}

	
	public void reproducir (View v) {
		 // check for already playing
		ImageButton btnPlay = (ImageButton) findViewById(R.id.imgbPlay);
		if(mp.isPlaying()){
            if(mp!=null){
            	btnPlay.setImageResource(R.drawable.btn_play);
            	mp.pause();
               
               
            }
        }else{
            // Resume song
            if(mp!=null){
            	
            	btnPlay.setImageResource(R.drawable.btn_pause);
            	mp.start();
            }
        }
	
			
		
	}
	

	public void atras (View v) {
		/** vuelve la cancion 6 segundos */
		
		
		
		int tiempoActual = mp.getCurrentPosition();
		if (mp.isPlaying() == true){
		mp.seekTo(tiempoActual-6000);
		}
		
		
		}
	
	public void adelante (View v){
		int tiempoActual = mp.getCurrentPosition();
		if (mp.isPlaying() == true){
		mp.seekTo(tiempoActual+6000);
		}
		
	}
	public void empezar () {
		
		try {
			Bundle extras = getIntent().getExtras();
			mp.reset();
            mp.setDataSource(ProfeList.getDireccion());
            mp.prepare();
            mp.start();
            
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		
	
	
	}
	@SuppressLint("NewApi")
	public void cambio_adelante(View v){
		CheckBox aleatorio = (CheckBox) findViewById(R.id.aleatorio);
		
		if (aleatorio.isChecked() == true){
			Random r = new Random();
			int num=r.nextInt(ProfeList.getSongs().size()-0) + 0;
			ProfeList.setActualSongId(num);
			ProfeList.setAleatorio(true);
			mp.stop();
			mp.release();
			onCreate(savedInstanceState2);
		}
		else {
			ProfeList.setAleatorio(false);
			ProfeList.siguienteCancion();
			mp.stop();
			mp.release();
			onCreate(savedInstanceState2);
		}
		
	}
	
	@SuppressLint("NewApi")
	public void cambio_atras(View v){
		CheckBox aleatorio = (CheckBox) findViewById(R.id.aleatorio);
		if (aleatorio.isChecked() == true){
			ProfeList.setAleatorio(true);
			Random r = new Random();
			int num=r.nextInt(ProfeList.getSongs().size()-0) + 0;
			ProfeList.setActualSongId(num);
			mp.stop();
			mp.release();
			onCreate(savedInstanceState2);
		}
		else{
			ProfeList.setAleatorio(false);
			ProfeList.anteriorCancion();
			mp.stop();
			mp.release();
			onCreate(savedInstanceState2);
		}
	}
        
	

}


