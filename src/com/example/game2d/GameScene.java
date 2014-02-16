package com.example.game2d;

import android.graphics.Canvas;
import android.view.MotionEvent;

public interface GameScene {
	public void initailize();
	public void finalize();
	public void oneDrawFrame(Canvas canvas);
	public void onPause();
	public void onResume();
	public boolean onTouchEvent(MotionEvent event);

}
