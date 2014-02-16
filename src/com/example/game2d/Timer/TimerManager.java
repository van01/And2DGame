package com.example.game2d.Timer;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeMap;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class TimerManager {
/**
	TreeMap<Long, LinkedList<TimerListener>> treeMap = new TreeMap<Long, LinkedList<TimerListener>>();
	static private TimerManager _instnace = new TimerManager();
	
	public static TimerManager getSingletone() {
		// TODO Auto-generated method stub
		return _instnace;
	}

	public void initailize() {
		// TODO Auto-generated method stub
		
	}

	
	long firstExpiredTime = 0;
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	public void processTimer() {
		// TODO Auto-generated method stub
		
		Log.d( "AKIRA_TIMER", "Timer Count: " + treeMap.size());
		if(treeMap.size() == 0) 
			return;
		
		long now = System.currentTimeMillis();

		
		//long firedTime = (Long) treeMap.firstKey();
		
		if ( firstExpiredTime <= now )
		{
			LinkedList<TimerListener> list = treeMap.firstEntry().getValue();
			Iterator<TimerListener> iter = list.iterator();
			while(iter.hasNext()) {
				TimerListener listener = iter.next();
				treeMap.remove(firstExpiredTime);
				listener.expiredTimer();
				
				if(listener.isRepeat()) {
					addTimer(listener);
				}
			}
			firstExpiredTime = (Long) treeMap.firstKey();
		}
	}
	
	public void addTimer(TimerListener listener) {
		
		long time = listener.getCurrentTime();
		long now = System.currentTimeMillis();
		Long nextTime = now + time;
		
		//Integer freq = (Integer) m.get(args[i]);
		listener.setKey(nextTime);
		
		LinkedList<TimerListener> list = treeMap.get(nextTime);
		if(null == list) {
			list = new LinkedList<TimerListener>();
	        treeMap.put(nextTime, list);
		}
		if(treeMap.size() == 0) {
			firstExpiredTime = nextTime;
		}
		list.push(listener);
	}

	public void removeTimer(TimerListener timer) {
		// TODO Auto-generated method stub
		if(null == timer) {
			return;
		}
		long key = timer.getKey();
		LinkedList<TimerListener> list = treeMap.get(key);
		if(null != list) {
			list.remove(timer);
			if(list.size() == 0) {
				treeMap.remove(key);
			}
		}
	}
	
*/
	static private TimerManager _instnace = new TimerManager();
	LinkedList<TimerListener> _timerList = new LinkedList<TimerListener>();
	
	public static TimerManager getSingletone() {
		// TODO Auto-generated method stub
		return _instnace;
	}

	public void initailize() {
		// TODO Auto-generated method stub
		
	}

	
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	public void processTimer() {
		// TODO Auto-generated method stub
		
		if(_timerList.size() == 0) 
			return;
		
		long now = System.currentTimeMillis();

		
		//long firedTime = (Long) treeMap.firstKey();
		Iterator<TimerListener> iter = _timerList.iterator();
		
		while(iter.hasNext()) {
			TimerListener listener = iter.next();
			long expiredTime = listener.getKey();
			if ( expiredTime <= now )
			{
				listener.expiredTimer();
				
				if(listener.isRepeat()) {
					listener.setKey(now + listener.getCurrentTime());
				}
				else {
					iter.remove();
				}
			}
		}
	}
	
	public void addTimer(TimerListener listener) {
		
		long time = listener.getCurrentTime();
		long now = System.currentTimeMillis();
		Long nextTime = now + time;
		
		//Integer freq = (Integer) m.get(args[i]);
		listener.setKey(nextTime);
		_timerList.push(listener);
	}

	public void removeTimer(TimerListener timer) {
		// TODO Auto-generated method stub
		if(null == timer) {
			return;
		}
		_timerList.remove(timer);
	}
	

}
