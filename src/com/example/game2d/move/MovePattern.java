package com.example.game2d.move;

import com.example.game2d.entity.Entity;

public abstract class MovePattern {

	boolean _isMove = false;
	Entity _entity;
	protected boolean _isAutoAngle = false;

	
	public MovePattern() {
		// TODO Auto-generated constructor stub
	}
//
//	public MovePattern(int x, int y) {
//		// TODO Auto-generated constructor stub
//		_x = x;
//		_y = y;
//	}
	
	public void setEntity(Entity entity) {
		_entity = entity;
	}
	
	public void moveStop() {
		_isMove = false;
	}

	abstract public void moveInit();
	public boolean moveCheck() {
		if(_isMove)
		{
			return _moveCheckImpl();
		}
		return false;
	}
	abstract protected boolean _moveCheckImpl();
	abstract public void moveTo(int x, int y);

	
	public boolean _isAutoAngle() {
		// TODO Auto-generated method stub
		return _isAutoAngle;
	}
	
	public void setAutoAngle(boolean isAutoAngle) {
		_isAutoAngle = isAutoAngle;
	}
	public abstract float getAngle();
	


}
