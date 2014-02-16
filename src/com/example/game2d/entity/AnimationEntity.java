package com.example.game2d.entity;

import java.util.LinkedList;

import com.example.game2d.Timer.TimerListener;
import com.example.game2d.Timer.TimerManager;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

public class AnimationEntity extends BitmapEntity {

	LinkedList<Bitmap> _spliteList;
	int _nFrame = 0;
	TimerListener _timer;

	
	public AnimationEntity(String name, LinkedList<Bitmap> idList) {
		super(name, null);
		_spliteList = idList;

		Bitmap bitmap = idList.getFirst();
		if (_width==0 || _height==0)
		{
			_width = bitmap.getWidth();
			_height = bitmap.getHeight();
			_rtValidArea.set(0, 0, _width, _height);
		}
		
		
	}
	
	public void startFrame(long nTime) {
		
		if(null != _timer) {
			TimerManager.getSingletone().removeTimer(_timer);
		}
		
		_timer = new TimerListener(null, nTime,true) {
			@Override
			public void expiredTimerImpl(Object obj) {
				// TODO Auto-generated method stub
				if(++_nFrame >= _spliteList.size()) {
					_nFrame = 0;
				}
			}
		};
		
		TimerManager.getSingletone().addTimer(_timer);
	}
	
	public void stopFrame() {
		TimerManager.getSingletone().removeTimer(_timer);		
	}
	

	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		//canvas.drawBitmap(_spliteList.get(_nFrame), _x, _y, null);		
		_bitmap = _spliteList.get(_nFrame);
		super.draw(canvas, _bitmap, _x, _y);
	}


}
