package com.example.game2d;

import java.util.Iterator;
import java.util.LinkedList;

import com.example.game2d.Timer.TimerListener;
import com.example.game2d.Timer.TimerManager;
import com.example.game2d.Utils.Device;
import com.example.game2d.Utils.Util;
import com.example.game2d.camera.Camera;
import com.example.game2d.camera.CameraManager;
import com.example.game2d.entity.AnimationEntity;
import com.example.game2d.entity.CharectorEntity;
import com.example.game2d.entity.Entity;
import com.example.game2d.entity.ObjectManager;
import com.example.game2d.entity.ScrollEntity;
import com.example.game2d.entity.CharectorEntity.DIRECTION;
import com.example.game2d.move.BresenhamMovePattern;
import com.example.game2d.move.MovableListener;
import com.example.game2d.move.StraightMovePattern;
import com.example.game2d.sound.SoundManager;
import com.example.game2d.sound.SoundObject;
import com.example.game2d.tile.TileData;
import com.example.game2d.tile.TileManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.text.method.MovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Panel extends SurfaceView implements SurfaceHolder.Callback{

	
	private TutorialThread _thread;
	private GameManager _gameMgr;
	private Context _context;
	
	public Panel(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		getHolder().addCallback(this);
		_thread = new TutorialThread(getHolder(), this);
		_context = context;
		
		_gameMgr = GameManager.getSingletone();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		_gameMgr.surfaceChanged(format, width, height);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		_thread.setRunning(true);
		_thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		boolean retry = true;
		_thread.setRunning(false);
		while(retry) {
			try {
				_thread.join();
				retry = false;
			} catch (InterruptedException e) {
				
			}
		}
	}
	
	
	
	public boolean onTouchEvent(MotionEvent event) {	   
	    return _gameMgr.onTouchEvent(event);
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (_gameMgr.onKeyDown(keyCode, event))
		{
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	public void draw(Canvas canvas) {
		_gameMgr.oneDrawFrame(canvas);
	}
	
	class TutorialThread extends Thread {
		
		private SurfaceHolder _surfaceHolder;
		private Panel _panel;
		private boolean _run = false;

		public TutorialThread(SurfaceHolder holder, Panel panel) {
			// TODO Auto-generated constructor stub
			_surfaceHolder = holder;
			_panel = panel;
		}

		public void setRunning(boolean b) {
			// TODO Auto-generated method stub
			_run = b;
		}
		
		private boolean _isInit = false;
		public void run() {
			Canvas c;
			
			while(_run) {
				c = null;
				
				try {
					c = _surfaceHolder.lockCanvas(null);
					if(c != null) {
						synchronized (_surfaceHolder) {
							if(!_isInit) 
							{
								_isInit = true;
								initailize();
							}
							else
							{
								//_panel.onDraw(c);
								_panel.draw(c);
							}
						}
					}
				} finally {
					if(c!=null) {
						_surfaceHolder.unlockCanvasAndPost(c);
					}
				}
			}
		}
	}

	public void onPause() {
		// TODO Auto-generated method stub
		_gameMgr.onPause();
	}

	public void onResume() {
		// TODO Auto-generated method stub
		_gameMgr.onResume();
	}
	
	public void initailize() {
		// TODO Auto-generated method stub
		_gameMgr.initailize(_context);
		_gameMgr.changeState(GameManager.GAME_STATE.INTRO);
//		_gameMgr.changeState(GameManager.GAME_STATE.PLAY);
	}
}
