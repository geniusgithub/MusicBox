package com.geniusgithub.musicbox;



import android.app.Application;

import com.geniusgithub.musicbox.brower.MediaItem;
import com.geniusgithub.musicbox.control.MusicControlCenter;
import com.geniusgithub.musicbox.player.MusicPlayEngineImpl;
import com.geniusgithub.musicbox.player.PlayerEngineListener;
import com.geniusgithub.musicbox.util.CommonLog;
import com.geniusgithub.musicbox.util.LogFactory;



public class MBApplication  extends Application implements PlayerEngineListener{

	private static final CommonLog log = LogFactory.createLog();

	private static MBApplication mInstance;

	private MusicControlCenter mMusicControlCenter;
	private MusicPlayEngineImpl mMusicPlayEngineImpl;
	private PlayerEngineListener mPlayerEngineListener;
	
	public synchronized static MBApplication getInstance(){
		return mInstance;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		log.e("MBApplication onCreate");
		
		mInstance = this;
		
		mMusicPlayEngineImpl = new MusicPlayEngineImpl(this);
		mMusicPlayEngineImpl.setPlayerListener(this);

		mMusicControlCenter = MusicControlCenter.getInstance();
		mMusicControlCenter.bindMusicPlayEngine(mMusicPlayEngineImpl);
	}
	
	
	public void setPlayerListener(PlayerEngineListener listener){
		mPlayerEngineListener = listener;
	}
	
	@Override
	public void onTrackPlay(MediaItem itemInfo) {
		if (mPlayerEngineListener != null){
			mPlayerEngineListener.onTrackPlay(itemInfo);
		}
	}
	@Override
	public void onTrackStop(MediaItem itemInfo) {
		if (mPlayerEngineListener != null){
			mPlayerEngineListener.onTrackStop(itemInfo);
		}
	}
	@Override
	public void onTrackPause(MediaItem itemInfo) {
		if (mPlayerEngineListener != null){
			mPlayerEngineListener.onTrackPause(itemInfo);
		}
	}
	@Override
	public void onTrackPrepareSync(MediaItem itemInfo) {
		if (mPlayerEngineListener != null){
			mPlayerEngineListener.onTrackPrepareSync(itemInfo);
		}
	}
	@Override
	public void onTrackPrepareComplete(MediaItem itemInfo) {
		if (mPlayerEngineListener != null){
			mPlayerEngineListener.onTrackPrepareComplete(itemInfo);
		}
	}
	@Override
	public void onTrackStreamError(MediaItem itemInfo) {
		mMusicControlCenter.stop();	
		if (mPlayerEngineListener != null){
			mPlayerEngineListener.onTrackStreamError(itemInfo);
		}
	}
	@Override
	public void onTrackPlayComplete(MediaItem itemInfo) {
		boolean ret = mMusicControlCenter.next();
		if (mPlayerEngineListener != null){
			mPlayerEngineListener.onTrackPlayComplete(itemInfo);
		}
	}

	
}
