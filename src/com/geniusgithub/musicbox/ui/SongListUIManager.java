package com.geniusgithub.musicbox.ui;

import com.geniusgithub.musicbox.R;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class SongListUIManager implements IBaseUIManager, SliderDrawerUIManager.ISliderStatuListener, OnItemClickListener{

	public View 				mRootView;

	public ListView				mListView;

	public SongListUIManager(){
		
	}

	@Override
	public void setupViews(View rootViews) {
		mRootView = rootViews;
		
		mListView = (ListView) rootViews.findViewById(R.id.lv_listview);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
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
	
}
