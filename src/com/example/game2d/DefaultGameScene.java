package com.example.game2d;

import com.example.game2d.entity.Entity;
import com.example.game2d.entity.ObjectManager;
import com.example.game2d.sound.SoundManager;
import com.example.game2d.sound.SoundObject;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;

public class DefaultGameScene implements GameScene, GameTouchListener{

	protected Context _ctx;
	protected ObjectManager objMgr = new ObjectManager();
	
	
	public DefaultGameScene(Context ctx) {
		// TODO Auto-generated constructor stub
		_ctx = ctx;
		objMgr.setTouchListener(this);
	}

	
	public void oneDrawFrame(Canvas canvas)
	{
		objMgr.oneDrawFrame(canvas);
	}
	
	public void onPause()
	{
		SoundManager.getSingletone().stopAllSound();
			
	}
	
	public void onResume()
	{
		SoundObject sound = SoundManager.getSingletone().getResource("song");
		if(null != sound) {
			sound.play(5, 0);
		}
	}
	
	public boolean onTouchEvent(MotionEvent event) 
	{
		int action = event.getAction();
		int x = (int) event.getX();
		int y = (int) event.getY();
		
		switch(action)
		{
		case MotionEvent.ACTION_DOWN:
			objMgr.onTouchDown(x, y);
			break;
		case MotionEvent.ACTION_UP:
			objMgr.onTouchUp(x, y);
			break;
		case MotionEvent.ACTION_MOVE:
			objMgr.onTouchMove(x, y);
			break;
		}
		
		return true;
	}


	public void finalize() 
	{
		// TODO Auto-generated method stub
		
	}


	public void initailize() 
	{
		// TODO Auto-generated method stub
		
	}
	
	//interface of GameTouchListener
	public boolean onTouchDown(Entity entity, int x, int y)
	{
		return true;
	}
	
	public boolean onTouchUp(Entity entity, int x, int y)
	{
		return true;
	}
	
	public boolean onTouchMove(Entity entity, int x, int y)
	{
		return true;
	}

	
}
