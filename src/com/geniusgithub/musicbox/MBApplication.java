package com.geniusgithub.musicbox;



import android.app.Application;

import com.geniusgithub.musicbox.util.CommonLog;
import com.geniusgithub.musicbox.util.LogFactory;



public class MBApplication  extends Application{

	private static final CommonLog log = LogFactory.createLog();

	private static MBApplication mInstance;

	
	
	public synchronized static MBApplication getInstance(){
		return mInstance;
	}
	@Override
	public void onCreate() {
		super.onCreate();
		log.e("MBApplication onCreate");
		
		mInstance = this;

	}


	
}
