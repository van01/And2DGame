package com.example.game2d;

import com.example.game2d.entity.Entity;

public interface GameTouchListener 
{
	public boolean onTouchDown(Entity entity, int x, int y);
	public boolean onTouchUp(Entity entity, int x, int y);
	public boolean onTouchMove(Entity entity, int x, int y);
	
}
