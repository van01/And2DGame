package com.example.game2d.sound;

import android.media.MediaPlayer;

public class MusicSound extends SoundObject {

	MediaPlayer _mediaPlayer;
	
	public MusicSound(String key, MediaPlayer mp) {
		// TODO Auto-generated constructor stub
		super(key);
		_mediaPlayer = mp;
	}

	@Override
	public void play(int volume, int nLoop) {
		// TODO Auto-generated method stub
		_mediaPlayer.setLooping(nLoop==0?true:false);
		_mediaPlayer.start();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		_mediaPlayer.stop();
	}

}
