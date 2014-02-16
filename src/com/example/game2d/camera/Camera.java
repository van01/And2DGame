package com.example.game2d.camera;

import android.graphics.Matrix;

import com.example.game2d.Utils.Device;

public class Camera {
	
	protected int _wx, _wy;

	public void setPosition(int x, int y) {
		// TODO Auto-generated method stub
		_wx = x;
		_wy = y;
	}
	
	public int getWX()
	{
		return _wx;
	}
	
	public int getWY()
	{
		return _wy;
	}

	public void setWorldXY(int x, int y) {
		// TODO Auto-generated method stub
		_wx = x;
		_wy = y;
	}

	public int getDrawScreenX(int drawX) {
		// TODO Auto-generated method stub
		return drawX + (Device._lcdWidth/2) - _wx;
	}

	public int getDrawScreenY(int drawY) {
		// TODO Auto-generated method stub
		return drawY + (Device._lcdHeight/2) - _wy;
	}

	public Matrix getDrawScreenMatrix(Matrix mMat) {
		// TODO Auto-generated method stub
		int dx = getDrawScreenX(0);
		int dy = getDrawScreenY(0);
		mMat.preTranslate(dx, dy);
		return mMat;
	}

	public void translate(int dx, int dy) {
		// TODO Auto-generated method stub
		_wx += dx;
		_wy += dy;
	}
	
	
}
