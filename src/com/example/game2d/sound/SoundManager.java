package com.example.game2d.sound;

import java.util.HashMap;
import java.util.Iterator;

import com.example.game2d.Utils.Util;
import com.example.game2d.sound.SoundManager.TYPE;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class SoundManager {

	public enum TYPE{
		BGM,
		MUSIC
	};
	private static SoundManager _instance = new SoundManager();
	protected SoundPool _soundPool;
	protected HashMap<String, SoundObject> _soundMap = new HashMap<String, SoundObject>();
	protected boolean _isInit = false;
	
	public static SoundManager getSingletone() {
		// TODO Auto-generated method stub
		return _instance;
	}

	public void initailize(int bgmCnt) {
		// TODO Auto-generated method stub
		_soundPool = new SoundPool(bgmCnt, AudioManager.STREAM_MUSIC, 0);
		_isInit = true;
	}

	public void stopAllSound() {
		// TODO Auto-generated method stub
		if(!_isInit)	return;
		
		Iterator<SoundObject> iter = _soundMap.values().iterator();
		while(iter.hasNext())
		{
			SoundObject obj = iter.next();
			obj.stop();
		}
	}

	public SoundObject getResource(String key) {
		// TODO Auto-generated method stub
		if(!_isInit)	return null;
		return _soundMap.get(key);
	}

	public SoundObject addResource(String key, int resId, TYPE bgm) {
		// TODO Auto-generated method stub
		if(!_isInit)	return null;
		
		SoundObject obj = null;
		switch(bgm)
		{
		case BGM:
			{
				int id = _soundPool.load(Util.getContext(), resId, 1);
				obj = new BMGSound(key, id, _soundPool);
			}
			break;
		case MUSIC:
			{
				MediaPlayer mp = MediaPlayer.create(Util.getContext(), resId);
				obj = new MusicSound(key, mp);
			}
			break;
		}	
		_soundMap.put(key, obj );
		
		return obj;
	}
	
}
