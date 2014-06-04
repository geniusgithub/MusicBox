package com.geniusgithub.musicbox.control;

import java.util.ArrayList;
import java.util.List;



import com.geniusgithub.musicbox.MBApplication;
import com.geniusgithub.musicbox.brower.MediaItem;
import com.geniusgithub.musicbox.brower.MediaScannerCenter;
import com.geniusgithub.musicbox.player.IMediaOperator;
import com.geniusgithub.musicbox.player.MusicPlayEngineImpl;

import android.content.Context;


public class MusicControlCenter implements IMediaOperator{

	private Context mContext;
	private int mCurPlayIndex = 0;
	private List<MediaItem> mMusicList = new ArrayList<MediaItem>();
	private MusicPlayEngineImpl mPlayerEngineImpl;
	private long playNextTimeMill = 0;
	
	private static  MusicControlCenter mInstance;
	
	
	private MusicControlCenter(Context context){
		mContext = context;
	}
	

	public static synchronized MusicControlCenter getInstance() {
		if (mInstance == null){
			mInstance  = new MusicControlCenter(MBApplication.getInstance());
		}
		return mInstance;
	}

	
	
	public void updateMediaInfo(int index, List<MediaItem> list){
		mCurPlayIndex = index;
		mMusicList = list;
	}
	
	public void bindMusicPlayEngine(MusicPlayEngineImpl object){
		mPlayerEngineImpl = object;
	}
	
	public int getCurPlayIndex(){
		return mCurPlayIndex;
	}
	
	public int getCurPlayState(){
		return mPlayerEngineImpl.getPlayState();
	}
	
	public int getCurPosition(){
		return mPlayerEngineImpl.getCurPosition();
	}
	
	public int getDuration(){
		return mPlayerEngineImpl.getDuration();
	}
	
	public String getPlaySong(){
		return mPlayerEngineImpl.getPlaySong();
	}
	
	public boolean playIndex(int index){
		if (!isHaveFile())
		{
			return false;
		}
		mCurPlayIndex = index;
	
		long curTimeMill =  System.currentTimeMillis();
		long timeInterfal = Math.abs(curTimeMill - playNextTimeMill);
		if (timeInterfal < 1000){
			return false;
		}
		
		playNextTimeMill = curTimeMill;	
		mCurPlayIndex = reviceIndex(mCurPlayIndex);
		mPlayerEngineImpl.playMedia(mMusicList.get(mCurPlayIndex));
		return true;
	}

	
	@Override
	public void exit() {
		mPlayerEngineImpl.exit();
	}

	@Override
	public void replay() {
		mPlayerEngineImpl.play();
	}

	@Override
	public void pause() {
		mPlayerEngineImpl.pause();
	}

	@Override
	public void stop() {
		mPlayerEngineImpl.stop();
	}

	@Override
	public void prev() {
		if (!isHaveFile())
		{
			return ;
		}
		
		mCurPlayIndex--;
		mCurPlayIndex = reviceIndex(mCurPlayIndex);
	
		mPlayerEngineImpl.playMedia(mMusicList.get(mCurPlayIndex));
	}

	@Override
	public boolean next() {
		if (!isHaveFile())
		{
			return false;
		}
		long curTimeMill =  System.currentTimeMillis();
		long timeInterfal = Math.abs(curTimeMill - playNextTimeMill);
		if (timeInterfal < 1000){
			return false;
		}
		playNextTimeMill = curTimeMill;
		
		mCurPlayIndex++;
		mCurPlayIndex = reviceIndex(mCurPlayIndex);
	
		mPlayerEngineImpl.playMedia(mMusicList.get(mCurPlayIndex));
		return true;
	}

	@Override
	public void skipTo(int time) {
		mPlayerEngineImpl.skipTo(time);
	}
	
	
	private boolean isHaveFile(){
		if (mMusicList != null && mMusicList.size() > 0){
			return true;
		}
		
		return false;
	}
	
	private int reviceIndex(int index)
	{
		if (index < 0)
		{
			index = mMusicList.size() - 1;
		}
		
		if (index >= mMusicList.size())
		{
			index = 0;
		}
		
		return index;
	}

}
