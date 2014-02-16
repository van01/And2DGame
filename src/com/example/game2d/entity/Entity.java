package com.example.game2d.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import com.example.game2d.R;
import com.example.game2d.Utils.Util;
import com.example.game2d.camera.Camera;
import com.example.game2d.camera.CameraManager;
import com.example.game2d.entity.CharectorEntity.ACTION;
import com.example.game2d.entity.CharectorEntity.DIRECTION;
import com.example.game2d.move.MovableListener;
import com.example.game2d.move.MovePattern;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public abstract class Entity {
	protected String _name;	
	protected HashMap<String , MovePattern> movePatternList = new HashMap<String , MovePattern>();

	protected MovePattern currentMove = null;
	protected String currentMoveName;
	protected MovableListener _movableListener = null;
	public int _x, _y;
	protected int _offsetX, _offsetY;
	protected Rect _rtValidArea = new Rect(0,0,1,1);
	
	//for test
	protected Bitmap _backrect;
	protected Paint testPaint = new Paint();
	
	
	public Entity(String name) {
		// TODO Auto-generated constructor stub
		_name = name;
		_backrect = BitmapFactory.decodeResource(Util.getContext().getResources(), R.drawable.test_rect); 
	}
	
	public void processOneFrame()
	{
		if(currentMove == null)
			return;
		
		currentMove.moveCheck();		
	}
	
	public void draw(Canvas canvas)
	{
//		if(currentMove == null)
//			return;
//		
//		currentMove.moveCheck();
	}
	
	public MovePattern setCurrentMovePattner(String moveName) {
		MovePattern pattern = movePatternList.get(moveName);
		
		if(pattern != null) {
			currentMoveName = moveName;
			currentMove  = pattern;
		}
		return currentMove;
	}

	public void moveTo(int x, int y)
	{
		if(_movableListener != null) {
			_movableListener.onStart(this ,currentMoveName);
		}
	}

	public void addMovePattern(String tag, MovePattern pattern) {
		// TODO Auto-generated method stub
		movePatternList.put(tag, pattern);
		pattern.setEntity(this);
	}

	public void addMovableListener(MovableListener movableListener) {
		// TODO Auto-generated method stub
		_movableListener = movableListener;
	}

	public void onArrived() {
		// TODO Auto-generated method stub
		if(_movableListener != null) {
			_movableListener.onStop(this, currentMoveName);
		}
	}

	HashMap<ACTION, HashMap> _actionMap = new HashMap<ACTION, HashMap>();
	public void addResource(ACTION action, DIRECTION dir, Bitmap bitmap) {
		LinkedList<Bitmap> list = getBitmapList(action, dir);
		list.add(bitmap);
	}
	
	LinkedList<Bitmap> getBitmapList (ACTION action, DIRECTION dir)
	{
		HashMap<DIRECTION, LinkedList> dirMap = _actionMap.get(action);
		if(dirMap == null)
		{
			dirMap = new HashMap<DIRECTION, LinkedList>();
		}
		
		LinkedList<Bitmap> list = dirMap.get(dir);
		if (list == null)
		{
			list = new LinkedList<Bitmap>();
		}
		return list;
	}

	public MovePattern getMovePattern(String moveName) {
		// TODO Auto-generated method stub
		return 	movePatternList.get(moveName);
	}

	public void setPosition(int x, int y) {
		// TODO Auto-generated method stub
		_x = x;
		_y = y;
	}

	public int getX() {
		// TODO Auto-generated method stub
		return _x;
	}

	public int getY() {
		return _y;
	}

	public void setOffset(int x, int y) {
		// TODO Auto-generated method stub
		_offsetX = x;
		_offsetY = y;
	}

	public void draw(Canvas canvas, Bitmap bitmap, int drawX, int drawY) {
		// TODO Auto-generated method stub
		Camera camera = CameraManager.getSingletone().getCurrentCamera();
		int x = camera.getDrawScreenX(drawX);
		int y = camera.getDrawScreenY(drawY);
		canvas.drawBitmap(bitmap, x,y, null);
		
		drawOutLine(canvas, drawX, drawY);
	}

	public void draw(Canvas canvas, Matrix mMat, Bitmap bitmap) {
		// TODO Auto-generated method stub
		Camera camera = CameraManager.getSingletone().getCurrentCamera();
		mMat = camera.getDrawScreenMatrix(mMat);
		canvas.drawBitmap(bitmap, mMat, null);
	}

	protected void drawOutLine(Canvas canvas, int drawX, int drawY) {
		Camera camera = CameraManager.getSingletone().getCurrentCamera();
		int x = camera.getDrawScreenX(drawX);
		int y = camera.getDrawScreenY(drawY);
		int x1 = x + _rtValidArea.left;
		int y1 = y + _rtValidArea.top;
		int x2 = x + _rtValidArea.right;
		int y2 = y + _rtValidArea.bottom;
		testPaint.setColor(Color.CYAN);

		canvas.drawLine(x1, y1, x2, y1, testPaint);
		canvas.drawLine(x2, y1, x2, y2, testPaint);
		canvas.drawLine(x2, y2, x1, y2, testPaint);
		canvas.drawLine(x1, y2, x1, y1, testPaint);
	}

	protected void drawOutLine(Canvas canvas, Matrix matrix) {
		// TODO Auto-generated method stub
//		float[] values = new float[9];
//		matrix.getValues(values);
//		float x  = values[Matrix.MTRANS_X] + _rtValidArea.left;
//		float y = values[Matrix.MTRANS_Y] + _rtValidArea.top;
//		float width = values[Matrix.MSCALE_X];
//		float height = values[Matrix.MSCALE_Y];
//		float width = _rtValidArea.right;
//		float height = _rtValidArea.bottom;
//		testPaint.setColor(Color.CYAN);
//		Log.d("ENTITY", x +", " + y  +", " + width + ", " + height);
		//canvas.drawRect(x, y, x+width, y+height, testPaint);
		canvas.drawBitmap(_backrect,  mMat, null);
	}

	Matrix mMat = new Matrix();
	public void draw(Canvas canvas, Bitmap bitmap, int x, int y, float angle) {
		// TODO Auto-generated method stub
		//for test code
		drawOutLine(canvas, mMat);
		
		Camera camera = CameraManager.getSingletone().getCurrentCamera();

		mMat.reset();
		mMat = camera.getDrawScreenMatrix(mMat);
		mMat.preTranslate(x, y);
		mMat.preRotate(angle);
		mMat.preTranslate(-bitmap.getWidth()/2, -bitmap.getHeight()/2);			
		canvas.drawBitmap(bitmap, mMat, null);
		
	}

	public String getName() {
		// TODO Auto-generated method stub
		return _name;
	}

	public boolean isInPoint(int x, int y) {
		// TODO Auto-generated method stub
		Camera camera = CameraManager.getSingletone().getCurrentCamera();
		int screenX = camera.getDrawScreenX(_x);
		int screenY = camera.getDrawScreenY(_y);

		int left	= screenX + _rtValidArea.left;
		int right	= screenX + _rtValidArea.right;
		int top		= screenY + _rtValidArea.top;
		int bottom	= screenY + _rtValidArea.bottom;
		
		Log.d("TOUCH", "Point : " + x + " ," + y + " , Entity : " + screenX + ", " + screenY);
		Log.d("TOUCH", "Rectangle : " + left + " ," + right + " , " + top + ", " + bottom + " Size : " + _rtValidArea.right + ", " + _rtValidArea.bottom);

		
		if ( x >= left && x < right
				&& y >= top && y < bottom) {
			return true;
		}
		return false;
	}

}
