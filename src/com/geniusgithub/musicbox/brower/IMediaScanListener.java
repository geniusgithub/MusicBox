package com.geniusgithub.musicbox.brower;


public interface IMediaScanListener {

	public void mediaScan(MediaItem item);
	public void scanComplete();
	public void scanCancel();
}
