package com.example.game2d.entity;

import java.util.Iterator;
import java.util.LinkedList;

import com.example.game2d.move.BresenhamMovePattern;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

public class LineEntity extends Entity{

	LinkedList<Point> _list;
	BresenhamMovePattern pattern;
	
	public LineEntity(String name, LinkedList<Point> list) {
		super(name);
		// TODO Auto-generated constructor stub
		_list = list;
		pattern = new BresenhamMovePattern();
	}
	
	
	public void draw(Canvas canvas)
	{
		Paint myPaint = new Paint();
		myPaint.setColor(Color.parseColor("#ffffff00"));
		
		Iterator<Point> iter = _list.iterator();
		while(iter.hasNext()) {
			Point point1 = iter.next();
			if(iter.hasNext()) {
				Point point2 = iter.next();

				pattern.moveSet(point1.x, point1.y, 1);
				pattern.moveTo(point2.x, point2.y);
				
				while(true == pattern.moveCheck()) {
					canvas.drawPoint(_x, _y, myPaint);
				}				
			}
		}
	}



}
