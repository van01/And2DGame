package com.example.game2d.Utils;

public class Device {

	static public int _lcdWidth, _lcdHeight;
	
	public static void setScreenSize(int width, int height) {
		_lcdWidth = width;
		_lcdHeight = height;
	}
	
	public static int getWidth() {
		// TODO Auto-generated method stub
		return _lcdWidth;
	}

	public static int getHeight() {
		// TODO Auto-generated method stub
		return _lcdHeight;
	}
	
}
