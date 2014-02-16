package com.example.game2d.entity;

import com.example.game2d.Timer.TimerListener;
import com.example.game2d.Timer.TimerManager;
import com.example.game2d.camera.Camera;
import com.example.game2d.camera.CameraManager;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;

public class BitmapEntity extends Entity{

	protected Bitmap _bitmap;
	protected int _width, _height;
	protected boolean _isAutoAngle = false;
	protected Matrix mMat = new Matrix();
	
	public BitmapEntity(String name, Bitmap bitmap) {
		// TODO Auto-generated constructor stub
		super(name);
		if(null != bitmap) {
			_bitmap = bitmap;
			_width = bitmap.getWidth();
			_height = bitmap.getHeight();
			_rtValidArea.set(0, 0, _width, _height);
			_offsetX = _width/2;
			_offsetY = _height/2;
		}
		
		TimerManager.getSingletone().addTimer(new TimerListener(null, 10, true) {
			@Override
			public void expiredTimerImpl(Object obj) {
				// TODO Auto-generated method stub
				if(currentMove != null) {
					currentMove.moveCheck();
				}				
			}
		});
	}

	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		draw(canvas, _bitmap,_x, _y);
	}
	
	public void draw(Canvas canvas, Bitmap bitmap, int ix, int iy)
	{
		int x = ix-_offsetX;
		int y = iy-_offsetY;
		
		if(null == currentMove || !currentMove._isAutoAngle()) {
			if(null != bitmap) {
				super.draw(canvas, bitmap, x, y);
			}		
		}
		else 
		{
			float angle = currentMove.getAngle();
//			canvas.drawBitmap(_bitmap, mMat, null);
			super.draw(canvas, bitmap, x, y, angle);
		}		
	}

	private Object angleCheck() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void moveTo(int x, int y) {
		// TODO Auto-generated method stub
		super.moveTo(x, y);
		
		if(null != currentMove) {
			currentMove.moveTo(x,y);
		}
	}
	

	public void setAutoAngle(boolean isAngle) {
		// TODO Auto-generated method stub
		_isAutoAngle = isAngle;
	}

}
