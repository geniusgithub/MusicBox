package com.geniusgithub.musicbox.brower;

import android.database.Cursor;
import android.provider.MediaStore;

import com.geniusgithub.musicbox.util.CommonLog;
import com.geniusgithub.musicbox.util.LogFactory;


public class MediaItemFactory {

	private static final CommonLog log = LogFactory.createLog();

	public static MediaItem  buildItem(Cursor cursor){
		if (cursor != null)
		{
			int _name_index = cursor.getColumnIndexOrThrow(IConstansMusicColume.AUDIO_DISPLAYHNAME);
     		int _dir_index = cursor.getColumnIndexOrThrow(IConstansMusicColume.AUDIO_PATH);
     		int _time_index = cursor.getColumnIndexOrThrow(IConstansMusicColume.AUDIO_DURATION);
     		int _artist_index = cursor.getColumnIndexOrThrow(IConstansMusicColume.AUDIO_ARTIST);
     		
 			String srcpath = cursor.getString(_dir_index);
 			String name = cursor.getString(_name_index);
 			int duration = cursor.getInt(_time_index);
 			String artist = cursor.getString(_artist_index);
 			 			
 			MediaItem item = new MediaItem();
 			item.setPath(srcpath);
 			item.setTitle(name);	
 			item.setDuration(duration);
 			item.setArtist(artist);
 			
 			return item;
		}

		return null;
	}

}



