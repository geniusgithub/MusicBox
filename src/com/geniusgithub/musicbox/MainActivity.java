package com.geniusgithub.musicbox;

import com.geniusgithub.musicbox.brower.MediaItem;
import com.geniusgithub.musicbox.brower.MediaStoreCenter;
import com.geniusgithub.musicbox.control.MusicControlCenter;
import com.geniusgithub.musicbox.player.MusicPlayEngineImpl;
import com.geniusgithub.musicbox.player.PlayerEngineListener;
import com.geniusgithub.musicbox.ui.SliderDrawerUIManager;
import com.geniusgithub.musicbox.ui.SongListUIManager;
import com.geniusgithub.musicbox.util.CommonLog;
import com.geniusgithub.musicbox.util.LogFactory;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnErrorListener;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements MediaStoreCenter.IScanObser,
														OnBufferingUpdateListener, OnErrorListener{

	private final static CommonLog log = LogFactory.createLog();
	
	private MediaStoreCenter mMediaStoreCenter;
	
	private SliderDrawerUIManager mSliderDrawerUIManager;
	private SongListUIManager mSongListUIManager;
	
	private MusicControlCenter mMusicControlCenter;
	private MusicPlayEngineListener mMusicPlayEngineListener;
	
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
		
		mSongListUIManager = new SongListUIManager(MBApplication.getInstance());
		View songView = findViewById(R.id.rl_background);
		mSongListUIManager.setupViews(songView);
		
		mSliderDrawerUIManager.addSliderStatusListener(mSongListUIManager);
	
	}
	
	
	
	private void initData(){
		mMediaStoreCenter = MediaStoreCenter.getInstance();
		mMediaStoreCenter.registerScanObser(this);
		mMusicControlCenter = MusicControlCenter.getInstance();
		mMusicPlayEngineListener = new MusicPlayEngineListener();
		MBApplication.getInstance().setPlayerListener(mMusicPlayEngineListener);
		
		if (mMediaStoreCenter.isMusicEmpty()){
			mMediaStoreCenter.doScanMedia();
		}else{
			mSongListUIManager.refreshList(mMediaStoreCenter.getMusicMedia());
			mSongListUIManager.setPlayState( mMusicControlCenter.getCurPlayIndex(), mMusicControlCenter.getCurPlayState());
		}
		
	
	}
	
	private void unInitData(){
		mMediaStoreCenter.unRegisterScanObser(this);
		MBApplication.getInstance().setPlayerListener(null);
	}

	@Override
	public void scanComplete() {
		mSongListUIManager.refreshList(mMediaStoreCenter.getMusicMedia());
		mMusicControlCenter.updateMediaInfo(0, mMediaStoreCenter.getMusicMedia());
	}

	@Override
	public void scanCancel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onError(MediaPlayer arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void onBufferingUpdate(MediaPlayer arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	private class MusicPlayEngineListener implements PlayerEngineListener
	{

		@Override
		public void onTrackPlay(MediaItem itemInfo) {
		
//			mPlayPosTimer.startTimer();
//			LoaderHelper.syncDownLoadDrawable(mMediaInfo.getAlbumUri(), mHandler, LOAD_DRAWABLE_COMPLETE);
//			mUIManager.showPlay(false);
//			mUIManager.showPrepareLoadView(false);
//			mUIManager.showControlView(true);
		}

		@Override
		public void onTrackStop(MediaItem itemInfo) {

//			mPlayPosTimer.stopTimer();
//			mUIManager.showPlay(true);
//			mUIManager.updateMediaInfoView(mMediaInfo);
//			mUIManager.showLoadView(false);
		}

		@Override
		public void onTrackPause(MediaItem itemInfo) {
	
//			mPlayPosTimer.stopTimer();
//			mUIManager.showPlay(true);
		}

		@Override
		public void onTrackPrepareSync(MediaItem itemInfo) {

//			mPlayPosTimer.stopTimer();
//			mUIManager.updateMediaInfoView(itemInfo);
//			mUIManager.showPlay(false);
//			mUIManager.showPrepareLoadView(true);
//			mUIManager.showControlView(false);
//			
//			mMediaInfo = itemInfo;
//			boolean need = checkNeedDownLyric(itemInfo);
//			log.e("checkNeedDownLyric need = " + need);
//			if (need){
//				mLrcDownLoadHelper.syncDownLoadLRC(itemInfo.title, itemInfo.artist, MusicPlayerActivity.this);
//			}			
//			mUIManager.updateLyricView(itemInfo);	
		}

		@Override
		public void onTrackPrepareComplete(MediaItem itemInfo) {

//			mPlayPosTimer.stopTimer();
//			int duration = mPlayerEngineImpl.getDuration();
//			mUIManager.setSeekbarMax(duration);
//			mUIManager.setTotalTime(duration);
			
		

		}
		
		@Override
		public void onTrackStreamError(MediaItem itemInfo) {
			log.e("onTrackStreamError");
//			mPlayPosTimer.stopTimer();		
//			mMusicControlCenter.stop();	
//			mUIManager.showPlayErrorTip();
		}

		@Override
		public void onTrackPlayComplete(MediaItem itemInfo) {
			log.e("onTrackPlayComplete");
//			boolean ret = mMusicControlCenter.next();
//			if (!ret){
//				mUIManager.showPlayErrorTip();
//				mUIManager.updateMediaInfoView(itemInfo);
//				mUIManager.showPlay(false);
//				mUIManager.showPrepareLoadView(false);
//				mUIManager.showControlView(true);
//				
//		
//				
//			}
		}

	

	}

}
