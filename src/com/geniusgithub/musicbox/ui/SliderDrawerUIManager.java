package com.geniusgithub.musicbox.ui;


import com.geniusgithub.musicbox.R;
import com.geniusgithub.musicbox.widget.SlidingDrawerEx;
import com.geniusgithub.musicbox.widget.SlidingDrawerEx.IOnSliderHandleViewClickListener;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;


public class SliderDrawerUIManager implements OnClickListener, OnSeekBarChangeListener, 
						OnDrawerOpenListener, OnDrawerCloseListener, IOnSliderHandleViewClickListener{

	public SlidingDrawerEx mSlidingDrawer;
	
	public View mRootView;

	public ImageButton   mBtnHandle;
	public ImageButton   mBtnHandlePlay;
	public ImageButton   mBtnHandlePause;
	public TextView 	 mSongNumTextView;
	public View 		 mHandlePane;
	public TextView 	 mPlaySongTextView;

	public ImageButton mBtnModeSet;
	public ImageButton mBtnVolumnSet;
	public SeekBar mPlayProgress;
	public TextView mcurtimeTextView;
	public TextView mtotaltimeTextView;
	
	
	public ImageButton mBtnPlay;
	public ImageButton mBtnPause;
	public ImageButton mBtnStop;	    		    	
	public ImageButton mBtnPlayNext;
	public ImageButton mBtnPlayPre;
	public ImageButton mBtnMenu;    	  
	
	private boolean mPlayAuto = true;
	
	
	public SliderDrawerUIManager()
	{
	
	}
	
	private void setupViews(View sliderView)
	{
		mRootView = sliderView;
		
		mBtnPlay = (ImageButton) sliderView.findViewById(R.id.btn_Play);
    	mBtnPause = (ImageButton)  sliderView.findViewById(R.id.btn_Pause);
    	mBtnStop = (ImageButton)  sliderView.findViewById(R.id.btn_Stop);
    	mBtnPlayPre = (ImageButton)  sliderView.findViewById(R.id.btn_PlayPre);      	
    	mBtnPlayNext = (ImageButton)  sliderView.findViewById(R.id.btn_PlayNext);      	
        mBtnMenu = (ImageButton)  sliderView.findViewById(R.id.btn_Menu);
        mBtnModeSet = (ImageButton)  sliderView.findViewById(R.id.btn_Mode);
        mBtnVolumnSet = (ImageButton)  sliderView.findViewById(R.id.btn_Volumn);
    	mBtnHandle = (ImageButton)  sliderView.findViewById(R.id.handler_icon);
    	mBtnHandlePlay= (ImageButton)  sliderView.findViewById(R.id.handler_play);
    	mBtnHandlePause = (ImageButton)  sliderView.findViewById(R.id.handler_pause);
        mBtnPlay.setOnClickListener(this);
        mBtnPause.setOnClickListener(this);
        mBtnStop.setOnClickListener(this);
        mBtnPlayPre.setOnClickListener(this);
        mBtnPlayNext.setOnClickListener(this);
        mBtnStop.setOnClickListener(this);
        mBtnMenu.setOnClickListener(this);	        	
    	mBtnModeSet.setOnClickListener(this);
    	mBtnVolumnSet.setOnClickListener(this);
        
        
        mPlaySongTextView = (TextView)  sliderView.findViewById(R.id.tv_PlaySong);        	
    	mcurtimeTextView = (TextView)  sliderView.findViewById(R.id.tv_CurTime);
    	mtotaltimeTextView = (TextView)  sliderView.findViewById(R.id.tv_TotalTime);
    	mSongNumTextView = (TextView)  sliderView.findViewById(R.id.tv_SongNum);
    	
    		    
    	
    	mPlayProgress = (SeekBar) sliderView.findViewById(R.id.seekBar);
    	mPlayProgress.setOnSeekBarChangeListener(this);
    	
   
    	mSlidingDrawer = (SlidingDrawerEx) sliderView.findViewById(R.id.slidingDrawer);
    	mSlidingDrawer.setOnDrawerOpenListener(this);
    	mSlidingDrawer.setOnDrawerCloseListener(this);
    	mSlidingDrawer.setHandleId(R.id.handler_icon);
    	mSlidingDrawer.setTouchableIds(new int[]{R.id.handler_play, R.id.handler_pause});
    	mSlidingDrawer.setOnSliderHandleViewClickListener(this);
    	
    	mHandlePane = sliderView.findViewById(R.id.handle_panel);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.btn_Play:

			break;
		case R.id.btn_Pause:

			break;
		case R.id.btn_Stop:

			break;
		case R.id.btn_PlayPre:

			break;
		case R.id.btn_PlayNext:

			break;
		case R.id.btn_Menu:

			break;
		case R.id.btn_Mode:

			break;
		case R.id.btn_Volumn:

			break;
		default:
			break;
		}
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		if (mPlayAuto == false)
		{

		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		mPlayAuto = false;
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		mPlayAuto = true;
	}


	@Override
	public void onDrawerOpened() {

		mBtnHandle.setImageResource(R.drawable.handle_down);

	}

	@Override
	public void onDrawerClosed() {

		mBtnHandle.setImageResource(R.drawable.handle_up);

	}

	@Override
	public void onViewClick(View view) {
		switch(view.getId())
		{
		case R.id.handler_play:
			break;
		case R.id.handler_pause:
			break;
		default:
			break;
		}
	}

}
