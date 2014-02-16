package com.example.game2d.sound;

import android.media.SoundPool;

public class BMGSound extends SoundObject {

	int _id;
	SoundPool _soundPool;
	private int _priority = 1;
	
	public BMGSound(String key, int id, SoundPool pool) {
		// TODO Auto-generated constructor stub
		super(key);
		_id = id;
		_soundPool = pool;
	}

	@Override
	public void play(int volume, int isLoop) {
		// TODO Auto-generated method stub
		_soundPool.play(_id,  volume, volume, _priority , isLoop, 1);
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		_soundPool.release();
	}

}
