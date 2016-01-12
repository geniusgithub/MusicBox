package com.geniusgithub.musicbox.ui;

import com.geniusgithub.musicbox.MBApplication;
import com.geniusgithub.musicbox.R;
import com.geniusgithub.musicbox.brower.MediaItem;
import com.geniusgithub.musicbox.control.MusicControlCenter;
import com.geniusgithub.musicbox.util.TimeExUtils;
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


public class SliderDrawerUIManager implements IBaseUIManager, OnClickListener, OnSeekBarChangeListener, 
						OnDrawerOpenListener, OnDrawerCloseListener, IOnSliderHandleViewClickListener{

	public static interface ISliderStatuListener{
		public void onStatus(boolean isOpen);
	}
	
	public SlidingDrawerEx mSlidingDrawer;
	public ISliderStatuListener mChangeListener;

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
	
	private boolean isSeekbarTouch = false;	
	
	private MusicControlCenter mMusicControlCenter;
	
	public SliderDrawerUIManager()
	{
		mMusicControlCenter = MusicControlCenter.getInstance();

	}
	
	@Override
	public void setupViews(View sliderView) {
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

	public void addSliderStatusListener(ISliderStatuListener listener){
		mChangeListener = listener;
	}
	
	public void removeSliderStatusListener(){
		mChangeListener = null;
	}
	
	public boolean isSliderOpen(){
		return mSlidingDrawer.isOpened();
	}
	
	public void closeSlider(){
		mSlidingDrawer.close();
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.btn_Play:
			mMusicControlCenter.replay();
			break;
		case R.id.btn_Pause:
			mMusicControlCenter.pause();
			break;
		case R.id.btn_Stop:
			mMusicControlCenter.stop();
			break;
		case R.id.btn_PlayPre:
			mMusicControlCenter.prev();
			break;
		case R.id.btn_PlayNext:
			mMusicControlCenter.next();
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

		setcurTime(progress);
		setSeekbarProgress(progress);
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		isSeekbarTouch = true;
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		isSeekbarTouch = false;
		mMusicControlCenter.skipTo(mPlayProgress.getProgress());
	}


	@Override
	public void onDrawerOpened() {

		mBtnHandle.setImageResource(R.drawable.handle_down);
		
		if (mChangeListener != null){
			mChangeListener.onStatus(true);
		}

	}

	@Override
	public void onDrawerClosed() {

		mBtnHandle.setImageResource(R.drawable.handle_up);

		if (mChangeListener != null){
			mChangeListener.onStatus(false);
		}
	}

	@Override
	public void onViewClick(View view) {
		switch(view.getId())
		{
		case R.id.handler_play:
			mMusicControlCenter.replay();
			break;
		case R.id.handler_pause:
			mMusicControlCenter.pause();
			break;
		default:
			break;
		}
	}
	

	public void setSeekbarMax(int max){
		mPlayProgress.setMax(max);
	}
	
	public void setTotalTime(int totalTime){
		String timeString = TimeExUtils.formateTime(totalTime);
		mtotaltimeTextView.setText(timeString);
	}
	
	public void setSongName(String name){
		mPlaySongTextView.setText(name);
	}
	
	private void setcurTime(int curTime){
		String timeString = TimeExUtils.formateTime(curTime);
		mcurtimeTextView.setText(timeString);
	}
	
	public void setSeekbarProgress(int time)
	{
		if (!isSeekbarTouch)
		{
			mPlayProgress.setProgress(time);	
		}
	}
	
	public void updateMediaInfo(MediaItem item){
 
		if (item != null){
			setTotalTime(item.getDuration());
			setSeekbarMax(item.getDuration());
			setSeekbarProgress(0);

			setSongName(item.getTitle());
		}
	}

	public void setSongNumInfo(int curPlayIndex, int totalSongNum)
	{
		String str = String.valueOf(curPlayIndex + 1) + "/" + String.valueOf(totalSongNum);
		mSongNumTextView.setText(str);
	}
	
	
	public void showPlay(boolean flag)
	{
		if (flag)
		{
			mBtnPlay.setVisibility(View.VISIBLE);
			mBtnPause.setVisibility(View.GONE);
			mBtnHandlePlay.setVisibility(View.VISIBLE);
			mBtnHandlePause.setVisibility(View.INVISIBLE);
    		
		}else{
			mBtnPlay.setVisibility(View.GONE);
			mBtnPause.setVisibility(View.VISIBLE);
			mBtnHandlePlay.setVisibility(View.INVISIBLE);
			mBtnHandlePause.setVisibility(View.VISIBLE);
		}
		
	}
	
	public void ShowHandlePanel(boolean flag)
	{
		if (flag)
		{
			mHandlePane.setVisibility(View.VISIBLE);
		}else{
			mHandlePane.setVisibility(View.INVISIBLE);
		}
		
	}

	public void showPlayErrorTip(){
		Toast.makeText(MBApplication.getInstance(), R.string.toast_musicplay_fail, Toast.LENGTH_SHORT).show();
	}

}
