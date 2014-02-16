package com.example.game2d.move;

import com.example.game2d.Utils.Device;
import com.example.game2d.Utils.Util;

public class BresenhamMovePattern extends MovePattern {

	protected int ex, ey, dx, dy, p, spd, direction;
	
	public BresenhamMovePattern() {
		// TODO Auto-generated constructor stub
		super();
	}

	public BresenhamMovePattern(int speed) {
		super();
		// TODO Auto-generated constructor stub
		spd = speed;
	}

	public void moveSet(int x, int y, int speed) {
		// TODO Auto-generated method stub
		_entity._x = x;
		_entity._y = y;
		spd = speed;
	}
	

	public void moveTo(int x, int y)
	{
		ex = x;
		ey = y;

		dx = ex - _entity._x;
		dy = ey - _entity._y;
		p = 0;

		if ( dx >=0 && dy >= 0) {
			if(abs(dx) > abs(dy)) direction = 0;
			else	direction = 1;
		}
		else if ( dx <=0 && dy <= 0) {
			if(abs(dx) > abs(dy)) direction = 2;
			else	direction = 3;
		}
		else if ( dx >=0 && dy <= 0) {
			if(abs(dx) > abs(dy)) direction = 4;
			else	direction = 5;
		}
		else if ( dx <=0 && dy >= 0) {
			if(abs(dx) > abs(dy)) direction = 6;
			else	direction = 7;
		}
		_isMove = true;	
	}
	
	public void moveInit() {
		
		int x = (int)(Math.random()*Device.getWidth());
		int y = (int)(Math.random()*Device.getHeight());
		
		moveTo(x, y);
	}
	
	

	@Override
	protected boolean _moveCheckImpl() {
		//if( _x == ex && _y == ey) {
		if(ex-spd<=_entity._x && _entity._x<=ex+spd && ey-spd<=_entity._y && _entity._y<=ey+spd)
		{
			//onArrived()d에서 다시 moveTo 항경우 위해
			_isMove = false;
			//listener.onArrived();
			if(_entity != null) {
				_entity.onArrived();
			}
			
			return false;
		}
		else 
		{
			move();
			return true;
		}
	}
	
	private int abs(int n) {
		// TODO Auto-generated method stub
		return (n<0)?-n:n;
	}


	float _prevAngleX = 0;
	float _prevAngleY= 0;
	float _prevAngle= 0;
	public float getAngle(){
		float fRad, fAng;
		
		
		if(dx == _prevAngleX && dy == _prevAngleY) {
			return _prevAngle;
		}

		// arrow.png
		fRad=(float) Math.acos(dx/Math.sqrt(dx*dx + dy*dy));
		fAng=(dy<0)? -Util.radToDeg(fRad) : Util.radToDeg(fRad);

		// arrow0.png
		//dRad = Math.atan2(dx, -dy);
		//dAng=radToDeg(dRad);

		// arrow1.png
		//fRad = (float) Math.atan2(-dx, dy);
		//fAng = Util.radToDeg(fRad);
		
		_prevAngleX = dx;
		_prevAngleY = dy;
		_prevAngle = fAng;
		
		return (float)fAng;
	}	

	protected void move() {
		// TODO Auto-generated method stub

		for (int i=0; i<spd; i++) {
			switch(direction) {
			case 0:
				_entity._x++;
				p+=dy;
				if(p>dx/2){_entity._y++; p-=dx;}
				break;
			case 1:
				_entity._y++;
				p+=dx;
				if(p>dy/2){_entity._x++; p-=dy;}
				break;
			case 2:
				_entity._x--;
				p-=dy;
				if(p>-dx/2){_entity._y--; p+=dx;}
				break;
			case 3:
				_entity._y--;
				p-=dx;
				if(p>-dy/2){_entity._x--; p+=dy;}
				break;
			case 4:
				_entity._x++;
				p-=dy;
				if(p>dx/2){_entity._y--; p-=dx;}
				break;
			case 5:
				_entity._y--;
				p+=dx;
				if(p>-dy/2){_entity._x++; p+=dy;}
				break;
			case 6:
				_entity._x--;
				p+=dy;
				if(p>-dx/2){_entity._y++; p+=dx;}
				break;
			case 7:
				_entity._y++;
				p-=dx;
				if(p>dy/2){_entity._x--; p-=dy;}
				break;
			}
		}
	}

}
