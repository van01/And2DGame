package com.example.game2d.move;

import com.example.game2d.Utils.Device;
import com.example.game2d.Utils.Util;

public class StraightMovePattern extends MovePattern {

	protected int _rangeWidth, _rangeHeight;
	protected int _dx, _dy;
	
	public StraightMovePattern(int dx, int dy) {
		// TODO Auto-generated constructor stub
		super();
		_rangeWidth = Device.getWidth();
		_rangeHeight = Device.getHeight();
		
		_dx = dx;
		_dy = dy;
	}


	protected void move() {
		// TODO Auto-generated method stub
		_entity._x += _dx;
		_entity._y += _dy;
		
		if (_entity._x<0 || _entity._x>=_rangeWidth-_dx) 
		{
			_entity.onArrived();
//			 _dx = -_dx;
		}
		if (_entity._y<0 || _entity._y>=_rangeHeight-_dy)
		{
			_entity.onArrived();
//			_dy = -_dy;
		}

	}

	public void setToggleMove() {
		// TODO Auto-generated method stub
		int endX = _rangeWidth-_dx;
		int endY = _rangeHeight-_dy;
		if(_entity._x < 0) {
			_entity._x = 0;
			_dx = -_dx;
		}
		else if(_entity._x>=endX){
			_entity._x = _rangeWidth-_dx;				
			_dx = -_dx;
		}
		else if (_entity._y<0) {
			_entity._y = 0;
			_dy = -_dy;
		}
		else if (_entity._y>=endY) {
			_entity._y = endY;
			_dy = -_dy;
		}
		else {
			_dx = -_dx;
			_dy = -_dy;
		}
	}


	@Override
	public boolean _moveCheckImpl() {
		// TODO Auto-generated method stub
		move();
		return true; 
	}


	@Override
	public void moveInit() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void moveTo(int x, int y) {
		// TODO Auto-generated method stub
		_isMove = true;
	}
	

	public int getDeltaX() {
		// TODO Auto-generated method stub
		return _dx;
	}


	public int getDeltaY() {
		// TODO Auto-generated method stub
		return _dy;
	}

	public void setDeltaXY(int x, int y) {
		// TODO Auto-generated method stub
		_dx = x;
		_dy = y;
	}

	float _prevAngleX = 0;
	float _prevAngleY= 0;
	float _prevAngle= 0;
	public float getAngle(){
		float fRad, fAng;
		
		
		if(_dx == _prevAngleX && _dy == _prevAngleY) {
			return _prevAngle;
		}

		// arrow.png
		fRad=(float) Math.acos(_dx/Math.sqrt(_dx*_dx + _dy*_dy));
		fAng=(_dy<0)? -Util.radToDeg(fRad) : Util.radToDeg(fRad);

		// arrow0.png
		//dRad = Math.atan2(dx, -dy);
		//dAng=radToDeg(dRad);

		// arrow1.png
		//fRad = (float) Math.atan2(-dx, dy);
		//fAng = Util.radToDeg(fRad);
		
		_prevAngleX = _dx;
		_prevAngleY = _dy;
		_prevAngle = fAng;
		
		return (float)fAng;
	}		


}
