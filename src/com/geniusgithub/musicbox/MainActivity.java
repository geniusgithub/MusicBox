package com.geniusgithub.musicbox;

import com.geniusgithub.musicbox.brower.MediaStoreCenter;
import com.geniusgithub.musicbox.ui.SliderDrawerUIManager;
import com.geniusgithub.musicbox.ui.SongListUIManager;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends Activity{

	
	private MediaStoreCenter mMediaStoreCenter;
	
	private SliderDrawerUIManager mSliderDrawerUIManager;
	private SongListUIManager mSongListUIManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setupViews();
		initData();
	}
	
	@Override
	protected void onResume() {


		
		super.onResume();
	}


	@Override
	protected void onPause() {


		
		super.onPause();
	}




	@Override
	protected void onDestroy() {

		unInitData();
		
		super.onDestroy();
	}



	private void setupViews(){
		mSliderDrawerUIManager = new SliderDrawerUIManager();
		View slView = findViewById(R.id.slidingDrawer);
		mSliderDrawerUIManager.setupViews(slView);
		
		mSongListUIManager = new SongListUIManager();
		View songView = findViewById(R.id.rl_background);
		mSongListUIManager.setupViews(songView);
		
		mSliderDrawerUIManager.addSliderStatusListener(mSongListUIManager);
	}
	
	
	
	private void initData(){
		mMediaStoreCenter = MediaStoreCenter.getInstance();
	
		if (mMediaStoreCenter.isMusicEmpty()){
		//	mMediaStoreCenter.doScanMedia();
		}else{
			
		}
	}
	
	private void unInitData(){
		
	}

}
