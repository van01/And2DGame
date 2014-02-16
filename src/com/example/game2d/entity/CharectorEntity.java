package com.example.game2d.entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import com.example.game2d.Timer.TimerListener;
import com.example.game2d.Timer.TimerManager;
import com.example.game2d.entity.CharectorEntity.ACTION;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class CharectorEntity extends BitmapEntity{

	public enum ACTION{
		WALK
	}
	
	public enum DIRECTION{
		UP,
		LEFT,
		DOWN,
		RIGHT
	}

	public CharectorEntity(String name) {
		super(name, null);
		// TODO Auto-generated constructor stub
	}
	
	protected TimerListener _timer;
	protected int _nFrame;
	HashMap<ACTION, HashMap> _actionMap = new HashMap<ACTION, HashMap>();
	protected LinkedList<Bitmap> currentList;
	protected Iterator<Bitmap> currentIterator;
	protected Bitmap currentBitmap;
	protected ACTION _currentAction = ACTION.WALK;
	protected DIRECTION _currentDirection = DIRECTION.LEFT;
	
	
	public void startFrame(long nTime) {
		
		if(null != _timer) {
			TimerManager.getSingletone().removeTimer(_timer);
		}
		
		_timer = new TimerListener(null, nTime,true) {
			@Override
			public void expiredTimerImpl(Object obj) {
				// TODO Auto-generated method stub
				if(currentList != null)
				{
					if(!currentIterator.hasNext())
					{
						currentIterator = currentList.iterator();
					}
					if(currentIterator.hasNext()) 
					{
						currentBitmap = currentIterator.next();
					}
				}
			}
		};
		
		TimerManager.getSingletone().addTimer(_timer);
	}
	
	public void stopFrame() {
		TimerManager.getSingletone().removeTimer(_timer);		
	}
	

	public void draw(Canvas canvas) 
	{
		// TODO Auto-generated method stub
		if(null != currentBitmap) {
			//canvas.drawBitmap(currentBitmap, _x-_offsetX, _y-_offsetY, null);
			super.draw(canvas, currentBitmap, _x-_offsetX, _y-_offsetY);
		}
	}
	


	public void addResource(ACTION action, DIRECTION dir, Bitmap bitmap) {
		LinkedList<Bitmap> list = getBitmapList(action, dir);
		list.add(bitmap);
		
		if (_width==0 || _height==0)
		{
			_width = bitmap.getWidth();
			_height = bitmap.getHeight();
			_rtValidArea.set(0, 0, _width, _height);
		}
	}
	
	LinkedList<Bitmap> getBitmapList (ACTION action, DIRECTION dir)
	{
		HashMap<DIRECTION, LinkedList> dirMap = _actionMap.get(action);
		if(dirMap == null)
		{
			dirMap = new HashMap<DIRECTION, LinkedList>();
			_actionMap.put(action, dirMap);
		}
		
		LinkedList<Bitmap> list = dirMap.get(dir);
		if (list == null)
		{
			list = new LinkedList<Bitmap>();
			dirMap.put(dir, list);
		}
		return list;
	}
	
	public void setAction(ACTION action) 
	{
		_currentAction  = action;		
	}
	
	public void setDirection(DIRECTION dir) 
	{
		currentList = getBitmapList(_currentAction, dir);
		currentIterator = currentList.iterator();
		if(currentIterator.hasNext()) {
			currentBitmap = currentIterator.next();
		}
	}	

}
