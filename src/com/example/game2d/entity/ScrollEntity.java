package com.example.game2d.entity;

import com.example.game2d.Timer.TimerListener;
import com.example.game2d.Timer.TimerManager;
import com.example.game2d.camera.CameraManager;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class ScrollEntity extends BitmapEntity{

	TimerListener timer;
	long _delay;
	int _moveX, _moveY;
	Rect _srcRect;
	Rect _destRect;
	int srcHeight, srcWidth;
	int destHeight, destWidth;

	public ScrollEntity(String name, Bitmap bitmap, Rect srcRect, Rect destRect) {
		super(name, bitmap);
		// TODO Auto-generated constructor stub
		_srcRect	= srcRect;
		_destRect	= destRect;
		
		srcHeight = srcRect.height();
		srcWidth = srcRect.width();
		
		destHeight = destRect.height();
		destWidth = destRect.width();
	}
	
	public void startFrame(long ms, int mx, int my) {
		// TODO Auto-generated method stub
		TimerManager.getSingletone().removeTimer(timer);
		
		_moveX = mx;
		_moveY = my;
		_delay = ms;
		timer = new TimerListener(null, ms, true) {
			@Override
			public void expiredTimerImpl(Object obj) {
				// TODO Auto-generated method stub
				_x += _moveX;
				_y += _moveY;
				if(_y >= srcHeight || _y + srcHeight < 0) {
					_y=0; 
				}
					
				if(_x >= srcWidth || _x + srcWidth < 0) {
					_x=0; 
				}
			}
		};
		TimerManager.getSingletone().addTimer(timer);
		
	}
	
	final static int UP			= 1;
	final static int LEFT		= 2;
	final static int RIGHT		= 4;
	final static int DOWN		= 8;
	final static int UP_LEFT	= UP|LEFT;
	final static int UP_RIGHT 	= UP|RIGHT;
	final static int DOWN_LEFT	= DOWN|LEFT;
	final static int DOWN_RIGHT	= DOWN|RIGHT;

	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		if(null != _bitmap) {

			int y = (_y - srcHeight + destHeight) + _offsetY;
			int x = _x  + _offsetX;
			int flag = 0;
			
//			canvas.drawBitmap(_bitmap, x, y, null);
			super.draw(canvas, _bitmap, x, y);

			if(y > 0) 
			{
				flag |= UP;
			}
			else if( y+srcHeight < destHeight )
			{
				flag |= DOWN;
			}
			
			if(x > 0) 
			{
				flag |= LEFT;
			}else if( x+srcWidth < destWidth )
			{
				flag |= RIGHT;
			}
			
			int drawX = 0, drawY = 0;
			if( (UP_LEFT & flag) == UP_LEFT) {
				drawX = x - srcWidth;
				drawY = y - srcHeight;
//				canvas.drawBitmap(_bitmap, drawX, drawY, null);								
				super.draw(canvas, _bitmap, drawX, drawY);
			}
			if( (UP & flag) == UP) {
				drawX = x;
				drawY = y - srcHeight;
//				canvas.drawBitmap(_bitmap, drawX, drawY, null);								
				super.draw(canvas, _bitmap, drawX, drawY);
			}
			if( (UP_RIGHT & flag) == UP_RIGHT) {
				drawX = x + srcWidth;
				drawY = y - srcHeight;
//				canvas.drawBitmap(_bitmap, drawX, drawY, null);								
				super.draw(canvas, _bitmap, drawX, drawY);
			}
			if( (LEFT & flag) == LEFT) {
				drawX = x - srcWidth;
				drawY = y;
//				canvas.drawBitmap(_bitmap, drawX, drawY, null);								
				super.draw(canvas, _bitmap, drawX, drawY);
			}
			if( (RIGHT & flag) == RIGHT) {
				drawX = x + srcWidth;
				drawY = y;
//				canvas.drawBitmap(_bitmap, drawX, drawY, null);								
				super.draw(canvas, _bitmap, drawX, drawY);
			}
			if( (DOWN_LEFT & flag) == DOWN_LEFT) {
				drawX = x - srcWidth;
				drawY = y + srcHeight;
//				canvas.drawBitmap(_bitmap, drawX, drawY, null);								
				super.draw(canvas, _bitmap, drawX, drawY);
			}
			if( (DOWN & flag) == DOWN) {
				drawX = x;
				drawY = y + srcHeight;
//				canvas.drawBitmap(_bitmap, drawX, drawY, null);								
				super.draw(canvas, _bitmap, drawX, drawY);
			}
			if( (DOWN_RIGHT & flag) == DOWN_RIGHT) {
				drawX = x + srcWidth;
				drawY = y + srcHeight;
//				canvas.drawBitmap(_bitmap, drawX, drawY, null);								
				super.draw(canvas, _bitmap, drawX, drawY);
			}
		}		
	}

	public void startFrame(int dx, int dy) {
		// TODO Auto-generated method stub
		startFrame(_delay, dx,dy);
	}	
}
