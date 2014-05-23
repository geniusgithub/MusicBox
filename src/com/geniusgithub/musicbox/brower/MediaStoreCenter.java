package com.geniusgithub.musicbox.brower;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.geniusgithub.musicbox.MBApplication;
import com.geniusgithub.musicbox.util.CommonLog;
import com.geniusgithub.musicbox.util.LogFactory;


public class MediaStoreCenter implements IMediaScanListener{


	private static final CommonLog log = LogFactory.createLog();
	
	private static  MediaStoreCenter mInstance;
	private Context mContext;

	private MediaScannerCenter mMediaScannerCenter;
	private List<MediaItem> mMusicList = new ArrayList<MediaItem>();
	
	
	private MediaStoreCenter(Context context) {
		mContext = context;
		
		initData();
	}

	public static synchronized MediaStoreCenter getInstance() {
		if (mInstance == null){
			mInstance  = new MediaStoreCenter(MBApplication.getInstance());
		}
		return mInstance;
	}

	private void initData(){
		mMediaScannerCenter = MediaScannerCenter.getInstance();
	}
	
	
	public void clearAllData(){
		stopScanMedia();
		clearMediaCache();
	}
	

	public void clearMediaCache(){
		mMusicList = new ArrayList<MediaItem>();
	}
	
	public List<MediaItem> getMusicMedia(){
		return mMusicList;
	}
	
	public void doScanMedia(){
		mMediaScannerCenter.startScanThread(this);
	}
	
	public void stopScanMedia(){
		mMediaScannerCenter.stopScanThread();
		while(!mMediaScannerCenter.isThreadOver()){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}



	@Override
	public void scanComplete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scanCancel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mediaScan(MediaItem item) {
		// TODO Auto-generated method stub
		if (item != null){
			mMusicList.add(item);
		}
	
	}
	
}
