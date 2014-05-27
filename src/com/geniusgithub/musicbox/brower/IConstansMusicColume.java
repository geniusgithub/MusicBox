package com.geniusgithub.musicbox.brower;

import android.provider.MediaStore;

public interface IConstansMusicColume {

	String AUDIO_PATH = MediaStore.Audio.AudioColumns.DATA;
	String AUDIO_DISPLAYHNAME = MediaStore.Audio.AudioColumns.DISPLAY_NAME;
	String AUDIO_DURATION = MediaStore.Audio.AudioColumns.DURATION;
	String AUDIO_ARTIST = MediaStore.Audio.AudioColumns.ARTIST;
	
	
	String AUDIO_COLUMN_STRS[] = {AUDIO_PATH, AUDIO_DISPLAYHNAME, AUDIO_DURATION, AUDIO_ARTIST};
}
