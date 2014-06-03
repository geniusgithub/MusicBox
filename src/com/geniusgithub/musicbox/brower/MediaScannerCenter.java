package com.geniusgithub.musicbox.brower;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.geniusgithub.musicbox.MBApplication;
import com.geniusgithub.musicbox.util.CommonLog;
import com.geniusgithub.musicbox.util.LogFactory;



public class MediaScannerCenter {

	private static final CommonLog log = LogFactory.createLog();

	
	private static  MediaScannerCenter mInstance;
	private Context mContext;
	
	private ScanMediaThread mediaThread;
	
	private MediaScannerCenter(Context context) {
		mContext = context;
		
		initData();
	}

	public static synchronized MediaScannerCenter getInstance() {
		if (mInstance == null){
			mInstance  = new MediaScannerCenter(MBApplication.getInstance());
		}
		return mInstance;
	}

	private void initData(){


	}
	
	
	public synchronized boolean startScanThread(IMediaScanListener listener){
		if (mediaThread == null || !mediaThread.isAlive()){
			mediaThread = new ScanMediaThread(listener);
			mediaThread.start();
		}
		
		return true;
	}
	
	public synchronized void stopScanThread(){
		if (mediaThread != null){
			if (mediaThread.isAlive()){
				mediaThread.exit();
			}
			mediaThread = null;
		}
	}
	
	public synchronized boolean isThreadOver(){
		if (mediaThread != null && mediaThread.isAlive()){
			return false;
		}
		
		return true;
	}
	
	private  void scanMusic(IMediaScanListener listener, ICancelScanMedia cancelObser) throws Exception {
		
		Cursor cursor = mContext.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, 
				IConstansMusicColume.AUDIO_COLUMN_STRS, 
				null, 
				null,
				IConstansMusicColume.AUDIO_DISPLAYHNAME);				

		if (cursor != null)
		{
			int count = cursor.getCount();
			if (count != 0)
			{
	    		if (cursor.moveToFirst()) {  
	         		do { 
	         			if (cancelObser.ifCancel()){
	         				listener.scanCancel();
	         				return ;
	         			}
	         		
	         			MediaItem item = MediaItemFactory.buildItem(cursor);
	         			if (item != null){
	         				listener.mediaScan(item);
	         			}
	         	
	         		} while (cursor.moveToNext());  
	         	}  			
			}		
			cursor.close();
		}

		listener.scanComplete();
	}
	
	
	public class ScanMediaThread extends Thread implements ICancelScanMedia{
		
		IMediaScanListener mListener;
		boolean exitFlag = false;
		
		public ScanMediaThread(IMediaScanListener listener){
			mListener = listener;
		}

		public void exit(){
			exitFlag = true;
		}
		
		@Override
		public void run() {

			try {
				scanMusic(mListener, this);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			super.run();
		}

		@Override
		public boolean ifCancel() {
			return exitFlag;
		}	
	}
	
	
	public  interface ICancelScanMedia{
		public boolean ifCancel();
	}
	
	
}
