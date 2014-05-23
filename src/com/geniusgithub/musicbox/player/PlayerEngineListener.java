package com.geniusgithub.musicbox.player;

import com.geniusgithub.musicbox.brower.MediaItem;



public interface PlayerEngineListener {
	
	public void onTrackPlay(MediaItem itemInfo); 

	public void onTrackStop(MediaItem itemInfo);
	
	public void onTrackPause(MediaItem itemInfo);	

	public void onTrackPrepareSync(MediaItem itemInfo);
	
	public void onTrackPrepareComplete(MediaItem itemInfo);
	
	public void onTrackStreamError(MediaItem itemInfo);
	
	public void onTrackPlayComplete(MediaItem itemInfo);
}
