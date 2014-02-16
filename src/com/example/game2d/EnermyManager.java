package com.example.game2d;

import java.util.Iterator;

import com.example.game2d.Timer.TimerListener;
import com.example.game2d.Timer.TimerManager;

public class EnermyManager{
	
	final static int DEFAULT_TIMER = 100;
	
	protected PlaceList _placeList;
	protected Iterator<PlaceList> _iterPlace = null;
	protected TimerListener _timerListner = new TimerListener(null, DEFAULT_TIMER, true) {

		
		@Override
		public void expiredTimerImpl(Object obj) {
			// TODO Auto-generated method stub
			
			
		}
		
	};

	public void setData(PlaceList placeList) {
		// TODO Auto-generated method stub
		_placeList = placeList;
	}

	public void start() 
	{
		// TODO Auto-generated method stub
		TimerManager.getSingletone().addTimer(_timerListner);
	}
	
	public void stop() 
	{
		TimerManager.getSingletone().removeTimer(_timerListner);
	}

}
