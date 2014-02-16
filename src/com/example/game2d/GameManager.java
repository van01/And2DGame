package com.example.game2d;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import com.example.game2d.GameManager.GAME_STATE;
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

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class GameManager {
	
	enum GAME_STATE{
		INTRO,
		PLAY
	}
	
	private static GameManager _instance = new GameManager();

	private Paint _TextPaint = new Paint();
	private int lcdWidth;
	private int lcdHeight;
	private ObjectManager objMgr = new ObjectManager();
	private Context _ctx;
	private int _nFrameCount=0;
	private int _nFrameCoutOut=0;
	
	private HashMap<GAME_STATE, DefaultGameScene> _sceneMap = new HashMap<GAME_STATE, DefaultGameScene>();
	private GameScene _currentScene = null;
	
	protected GameManager() {
		// TODO Auto-generated constructor stub
	}


	public void surfaceChanged(int format, int width, int height) {
		// TODO Auto-generated method stub
		lcdWidth = width;
		lcdHeight = height;
		Device.setScreenSize(width, height);
		objMgr.setScreenSize(lcdWidth, lcdHeight);
		
	}
	
	private void printFrame(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawText(""+_nFrameCoutOut, 20, 20, _TextPaint);
	}
	
	// GameManager Initailize
	public void initailize(Context context) {
		
		TimerManager.getSingletone().initailize();
		_TextPaint.setColor(Color.BLACK);
		Util.setContext(context);
		_ctx = context;
		
		_sceneMap.put(GAME_STATE.INTRO, new IntroScene(_ctx));
		_sceneMap.put(GAME_STATE.PLAY, new PlayScene(_ctx));

		
		//frame check timer
		TimerManager.getSingletone().addTimer(new TimerListener(null, 100, true) {
			@Override
			public void expiredTimerImpl(Object obj) {
				// TODO Auto-generated method stub
				_nFrameCoutOut = _nFrameCount;
				_nFrameCount = 0;
			}
		});
	}
	
	/**
	 * GameScene Interface
	 */

	public void oneDrawFrame(Canvas canvas) {
		
		//for frame check
		_nFrameCount++;
		printFrame(canvas);
		
		TimerManager.getSingletone().processTimer();
		canvas.drawColor(Color.BLACK);
		
		_currentScene.oneDrawFrame(canvas);
	}	
	public void onPause() {
		// TODO Auto-generated method stub
		_currentScene.onPause();
	}

	public void onResume() {
		// TODO Auto-generated method stub
		if( null != _currentScene)
		{
			_currentScene.onResume();
		}
	}	
	
	public boolean onTouchEvent(MotionEvent event) {
	    return _currentScene.onTouchEvent(event);
	}


	public void changeState(GAME_STATE state) {
		// TODO Auto-generated method stub
		
		if( null != _currentScene)
		{
			_currentScene.finalize();
		}	
		_currentScene = _sceneMap.get(state);
		
		_currentScene.initailize();
	}


	public static GameManager getSingletone() {
		// TODO Auto-generated method stub
		return _instance;
	}


	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
		{
			
		}
		return false;
	}


}
