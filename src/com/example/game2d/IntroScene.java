package com.example.game2d;

import com.example.game2d.GameManager.GAME_STATE;
import com.example.game2d.Utils.Device;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

public class IntroScene extends DefaultGameScene {

	protected Paint _paint = new Paint();
	
	public IntroScene(Context _ctx) {
		super(_ctx);
		// TODO Auto-generated constructor stub
		_paint.setColor(Color.RED);
		_paint.setTextSize(10);
	}

	public void initailize() 
	{
		super.initailize();
	}
	
	public void finalize()
	{
		super.finalize();
	}
	
	public void oneDrawFrame(Canvas canvas)
	{
		super.oneDrawFrame(canvas);

		canvas.drawText("INTRO SCREEN", 50, Device._lcdHeight/2, _paint);
	}
	
	public void onPause()
	{
		super.onPause();
	}
	
	public void onResume()
	{
		super.onResume();
	}
	
	public boolean onTouchEvent(MotionEvent event)
	{
	    int keyAction = event.getAction();
	    int x = (int)event.getX();
	    int y = (int)event.getY();
	    switch (keyAction){
	    case MotionEvent.ACTION_UP:
	    	GameManager.getSingletone().changeState(GAME_STATE.PLAY);
	    	break;
	    }
		return super.onTouchEvent(event);
	}
	

	
}
