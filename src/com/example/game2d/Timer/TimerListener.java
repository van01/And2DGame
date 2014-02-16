package com.example.game2d.Timer;

abstract public class TimerListener {
	
	private Object _obj;
	private boolean _isRepeat;
	private long _nCurrentTime;
	private long _mapKey;
	
	public TimerListener(Object obj, long delayMS, boolean isRepeat) {
		_obj = obj;
		_nCurrentTime = delayMS;
		_isRepeat = isRepeat;
	}
	
	public abstract void expiredTimerImpl(Object obj);
	
	public void expiredTimer() {
		expiredTimerImpl(_obj);
	}

	public boolean isRepeat() {
		// TODO Auto-generated method stub
		return _isRepeat;
	}

	public long getCurrentTime() {
		// TODO Auto-generated method stub
		return _nCurrentTime;
	}
	
	public void setKey(long key) {
		// TODO Auto-generated method stub
		_mapKey = key;
	}
	
	public long getKey() {
		return _mapKey;
	}
	
}
