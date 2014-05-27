package com.geniusgithub.musicbox.ui;

import java.util.ArrayList;
import java.util.List;

import com.geniusgithub.musicbox.R;
import com.geniusgithub.musicbox.adapter.ListViewAdapter;
import com.geniusgithub.musicbox.brower.MediaItem;
import com.geniusgithub.musicbox.brower.MediaStoreCenter;
import com.geniusgithub.musicbox.util.CommonLog;
import com.geniusgithub.musicbox.util.LogFactory;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class SongListUIManager implements IBaseUIManager, SliderDrawerUIManager.ISliderStatuListener, OnItemClickListener,
												MediaStoreCenter.IScanObser{
	
	private final static CommonLog log = LogFactory.createLog();
	
	private final static int REFRESH_SONGLIST = 0x0001;

	
	private Context 			mContext;
	public View 				mRootView;

	private Handler 			mHandler;
	public ListView				mListView;
	public ListViewAdapter 		mAdapter;
	public List<MediaItem>		mMediaItems = new ArrayList<MediaItem>();

	public SongListUIManager(Context context){
		mContext = context;
	}

	@Override
	public void setupViews(View rootViews) {
		mRootView = rootViews;
		
		mListView = (ListView) rootViews.findViewById(R.id.lv_listview);	
		mAdapter = new ListViewAdapter(mContext, mMediaItems);
		mListView.setAdapter(mAdapter);
		
		mHandler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				switch(msg.what){
					case REFRESH_SONGLIST:
						refresh();
				}
			}
			
		};
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
	
	public void refreshList(List<MediaItem> items){
		mMediaItems = items;
		
		notifyRefreshHandler();
	}
	public void showUI(boolean bShow){
		if (bShow){
			mRootView.setVisibility(View.VISIBLE);
		}else{
			mRootView.setVisibility(View.GONE);
		}
	}

	@Override
	public void onStatus(boolean isOpen) {
		showUI(!isOpen);
	}

	@Override
	public void scanComplete() {
		
		mMediaItems = MediaStoreCenter.getInstance().getMusicMedia();
		
		notifyRefreshHandler();
	}

	@Override
	public void scanCancel() {
		
	}
	
	private void notifyRefreshHandler(){
		mHandler.removeMessages(REFRESH_SONGLIST);
		Message msg = mHandler.obtainMessage(REFRESH_SONGLIST);
		msg.sendToTarget();
	}
	private void refresh(){
		log.e("SongListUIManager refresh");
		mAdapter.refreshAdapter(mMediaItems);
	}
	
}
