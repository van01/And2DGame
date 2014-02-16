package com.example.game2d.move;

import com.example.game2d.entity.Entity;

public abstract class MovableListener {
	public void onStart(Entity entity, String moveName) {};
	public void onStop(Entity entity, String moveName) {};
}
