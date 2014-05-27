package com.geniusgithub.musicbox.brower;

public class MediaItem {

	private String path = "";
	private String title = "";				
	private String artist = "";
	private String album = "";
	private String albumiconuri = "";
	private int    totalTime = 0;
	

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = (title != null ? title : "");
	}
	
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = (artist != null ? artist : "");
	}
	
	public void setAlbum(String album) {
		this.album = (album != null ? album : "");
	}
	public String getAlbum() {
		return album;
	}
	
	public void setPath(String uri) {
		this.path = (uri != null ? uri : "");
	}
	public String getPath() {
		return path;
	}
	
	public String getAlbumUri(){
		return albumiconuri;
	}
	public void setAlbumUri(String albumiconuri){
		this.albumiconuri = (albumiconuri != null ? albumiconuri : "");
	}
	
	public int getDuration(){
		return totalTime;
	}
	public void setDuration(int duration){
		totalTime = duration;
	}
}
