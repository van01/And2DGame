package com.example.game2d.Utils;

import android.content.Context;

public class Util {
	static float degToRad(float d){
		return d*0.017453292519943f; // Math.PI/180
	}
	public static float radToDeg(float r){
		return r*57.295779513082321f; // 180/Math.PI
	}
	
	/**
	 * 부호를 바꾸지 않고 램덤 값을 생성
	 * @param deltaY
	 * @param i
	 * @return
	 */
	public static int getRandom(int sign, int max) {
		// TODO Auto-generated method stub
		if(sign>0)
			sign = 1;
		else
			sign = -1;
		
		return (int) (Math.random() * max) * sign;
	}
	public static boolean isDistance(int n, int start, int end) {
		// TODO Auto-generated method stub
		return (n>=start&&n<end)?true:false;
	}
	
	private static Context _context;
	public static void setContext(Context cxt) {
		_context = cxt;
	}
	public static Context getContext() {
		// TODO Auto-generated method stub
		return _context;
	}
}
