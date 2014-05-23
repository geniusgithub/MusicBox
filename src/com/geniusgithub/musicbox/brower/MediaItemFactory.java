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
     		
 			String srcpath = cursor.getString(_dir_index);
 			String name = cursor.getString(_name_index);
 			 			
 			MediaItem item = new MediaItem();
 			item.setPath(srcpath);
 			item.setTitle(name);	
 			
 			return item;
		}

		return null;
	}

}



