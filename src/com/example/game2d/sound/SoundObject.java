package com.example.game2d.sound;

public abstract class SoundObject {

	String _name;
	
	public SoundObject(String key) {
		// TODO Auto-generated constructor stub
		_name = key;
	}

	abstract public void play(int volume, int nLoop);
	abstract public void stop();

}
