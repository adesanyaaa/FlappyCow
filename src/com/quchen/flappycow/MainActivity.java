package com.quchen.flappycow;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	public static final String medaille_save = "medaille_save";
	public static final String medaille_key = "medaille_key";
	public static final float DEFAULT_VOLUME = 0.3f;
	public static float volume = DEFAULT_VOLUME;
	
	ImageButton muteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ((ImageButton)findViewById(R.id.play_button)).setImageBitmap(Sprite.createBitmap(getResources().getDrawable(R.drawable.play_button), this));
        ((ImageButton)findViewById(R.id.play_button)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent("com.quchen.flappycow.Game"));
			}
		});
        
        muteButton = ((ImageButton)findViewById(R.id.mute_button));
        muteButton.setImageBitmap(Sprite.createBitmap(getResources().getDrawable(R.drawable.speaker), this));
        muteButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(volume != 0){
					volume = 0;
					muteButton.setImageBitmap(Sprite.createBitmap(getResources().getDrawable(R.drawable.speaker_mute), MainActivity.this));
				}else{
					volume = DEFAULT_VOLUME;
					muteButton.setImageBitmap(Sprite.createBitmap(getResources().getDrawable(R.drawable.speaker), MainActivity.this));
				}
			}
		});
        
        ((Button)findViewById(R.id.about_button)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent("com.quchen.flappycow.About"));
			}
		});
        
        setSocket();
    }
    
	
	private void setSocket(){
		SharedPreferences saves = this.getSharedPreferences(medaille_save, 0);
        switch(saves.getInt(medaille_key, 0)){
        	case 1:
        		((ImageView)findViewById(R.id.medaille_socket)).setImageBitmap(Sprite.createBitmap(getResources().getDrawable(R.drawable.socket_bronce), this));
        		break;
        	case 2:
        		((ImageView)findViewById(R.id.medaille_socket)).setImageBitmap(Sprite.createBitmap(getResources().getDrawable(R.drawable.socket_silver), this));
        		break;
        	case 3:
        		((ImageView)findViewById(R.id.medaille_socket)).setImageBitmap(Sprite.createBitmap(getResources().getDrawable(R.drawable.socket_gold), this));
        		break;
        }
	}


	@Override
	protected void onResume() {
		super.onResume();
		setSocket();
	}
	
	
    
}
