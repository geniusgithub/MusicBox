package com.geniusgithub.musicbox.brower;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.geniusgithub.musicbox.MBApplication;
import com.geniusgithub.musicbox.util.CommonLog;
import com.geniusgithub.musicbox.util.LogFactory;


public class MediaStoreCenter implements IMediaScanListener{
	
	private final static CommonLog log = LogFactory.createLog();
	
	public static interface IScanObser{
		public void scanComplete();
		public void scanCancel();
	}

	
	private static  MediaStoreCenter mInstance;
	private Context mContext;

	private MediaScannerCenter mMediaScannerCenter;
	private List<MediaItem> mMusicList = new ArrayList<MediaItem>();
	
	private List<IScanObser> mObsers = new ArrayList<MediaStoreCenter.IScanObser>();
	
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
	
	public boolean isMusicEmpty(){
		if (mMusicList != null && mMusicList.size() > 0){
			return false;
		}
		
		return true;
	}
	
	public void registerScanObser(IScanObser object){
		synchronized(mObsers){
			mObsers.add(object);
		}
	}
	
	public void unRegisterScanObser(IScanObser object){
		synchronized(mObsers){
			mObsers.remove(object);
		}
	}
	
	public void clearScanObser(){
		synchronized(mObsers){
			mObsers.clear();
		}
	}
	

	
	public void doScanMedia(){
		log.e("doScanMedia...");
		mMediaScannerCenter.startScanThread(this);
	}
	
	public void stopScanMedia(){
		log.e("stopScanMedia...");
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
		log.e("scanComplete...");
		performScanComplete();
	}

	@Override
	public void scanCancel() {
		log.e("scanCancel...");
		performScanCancel();
	}
	
	private void performScanComplete(){
		synchronized(mObsers){
			int size = mObsers.size();
			for(int i = 0; i < size; i++){
				mObsers.get(i).scanComplete();
			}
		}
	}

	private void performScanCancel(){
		synchronized(mObsers){
			int size = mObsers.size();
			for(int i = 0; i < size; i++){
				mObsers.get(i).scanCancel();
			}
		}
	}
	
	@Override
	public void mediaScan(MediaItem item) {
		// TODO Auto-generated method stub
		if (item != null){
			mMusicList.add(item);
		}
	
	}
	
}
