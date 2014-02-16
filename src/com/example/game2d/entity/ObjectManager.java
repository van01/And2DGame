package com.example.game2d.entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import com.example.game2d.GameTouchListener;


import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;

public class ObjectManager {

	private final static int DEFAULT_DRAW_LEVEL = 3;
	
	private int screenWidth, screenHeight;
	protected HashMap<String, Entity> _map = new HashMap<String, Entity>();
	protected HashMap<Integer, EntityObjectMap> _priorityDrawMap = new HashMap<Integer, EntityObjectMap>();
	protected GameTouchListener _touchListener;
	
	public ObjectManager() {
		_priorityDrawMap.put(0, new EntityObjectMap());
		_priorityDrawMap.put(1, new EntityObjectMap());
		_priorityDrawMap.put(2, new EntityObjectMap());
		_priorityDrawMap.put(3, new EntityObjectMap());
		_priorityDrawMap.put(4, new EntityObjectMap());

	}

	private HashMap<String, Entity> getMap(int level) {
		return _priorityDrawMap.get(level);
	}
	
	
	/**
	 * create the BitmapEntity
	 * @param name
	 * @param bm
	 * @return
	 */
	public Entity createBitmapEntity(String name, Bitmap bm) {
		// TODO Auto-generated method stub
		return createBitmapEntity(name,  bm, DEFAULT_DRAW_LEVEL);
	}
	

	public Entity createBitmapEntity(String name, Bitmap bm, int drawLevel) {
		// TODO Auto-generated method stub
		BitmapEntity entity = new BitmapEntity(name, bm); 
		_map.put(name, entity);
		getMap(drawLevel).put(name, entity);

		return entity;
	}
	
	
	/**
	 * createLineEntity
	 * @param name
	 * @param list
	 * @return
	 */
	public Entity createLineEntity(String name, LinkedList<Point> list) {
		// TODO Auto-generated method stub
		
		LineEntity entity = new LineEntity(name, list);
		_map.put(name, entity);
		getMap(DEFAULT_DRAW_LEVEL).put(name, entity);

		return entity;
	}
	
	/**
	 * createAnimationEntity
	 * @param name
	 * @param idList
	 * @return
	 */
	public Entity createAnimationEntity(String name, LinkedList<Bitmap> idList) {
		// TODO Auto-generated method stub
		AnimationEntity entity = new AnimationEntity(name, idList);
		_map.put(name, entity);
		getMap(DEFAULT_DRAW_LEVEL).put(name, entity);
		
		return entity;
	}

	/**
	 * create the AnimationEntity
	 * @param name
	 * @param idList
	 * @param zorder
	 * @return
	 */
	public Entity createAnimationEntity(String name,
			LinkedList<Bitmap> idList, int zorder) {
		// TODO Auto-generated method stub
		AnimationEntity entity = new AnimationEntity(name, idList);
		_map.put(name, entity);
		getMap(zorder).put(name, entity);
		
		return entity;		
	}


	/** 
	 * create the ScrollImage
	 * @param string
	 * @param background
	 * @param srcRect
	 * @param destRect
	 * @return
	 */
	public ScrollEntity createScrollEntity(String name, Bitmap bitmap,
			Rect srcRect, Rect destRect, int zorder) {
		// TODO Auto-generated method stub
		ScrollEntity entity = new ScrollEntity(name, bitmap, srcRect, destRect);
		_map.put(name, entity);
		getMap(zorder).put(name, entity);
		
		return entity;
	}

	public Entity createCharectorEntity(String name, int zorder) {
		// TODO Auto-generated method stub
		CharectorEntity entity = new CharectorEntity(name);
		_map.put(name, entity);
		getMap(zorder).put(name, entity);
		
		return entity;
	}


	
	public void setScreenSize(int lcdWidth, int lcdHeight) {
		// TODO Auto-generated method stub
		screenWidth = lcdWidth;
		screenHeight = lcdHeight;
	}

	public void oneDrawFrame(Canvas canvas) {
		// TODO Auto-generated method stub
		
		
		Iterator<EntityObjectMap> priorityItor = _priorityDrawMap.values().iterator();
		
		while(priorityItor.hasNext()) {
			EntityObjectMap map = priorityItor.next();
			//Object 그리기 전에 sort
			map.preDrawProcess();
			Iterator<Entity> itor = map.values().iterator();
			while(itor.hasNext()) {
				Entity entity = itor.next();
				entity.processOneFrame();
				entity.draw(canvas);
			}
		}
		
	}

	public Iterator<Entity> getAllEntity() {
		// TODO Auto-generated method stub
		Iterator<Entity> iter = _map.values().iterator();
		return iter;
	}
	public Iterator<Entity> getAllEntityWithLevel(int i) {
		// TODO Auto-generated method stub
		Iterator<Entity> iter = _priorityDrawMap.get(i).values().iterator();
		return iter;
	}
	
	public Entity getEntity(String name) {
		return _map.get(name);
	}

	public void setSortDraw(int i, boolean isSort) {
		// TODO Auto-generated method stub
		_priorityDrawMap.get(i).setSortDraw(isSort);
	}

	public void setTouchListener(GameTouchListener listener) {
		// TODO Auto-generated method stub
		_touchListener = listener;
	}

	
	LinkedList<Entity> _selectedList = new LinkedList<Entity>();
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	public void onTouchDown(int x, int y) {
		// TODO Auto-generated method stub
		Iterator<Entity> iter = getAllEntity();
		while(iter.hasNext())
		{
			Entity entity = iter.next();
			if(true == entity.isInPoint(x, y))
			{
				_selectedList.push(entity);
				_touchListener.onTouchDown(entity, x, y);
			}
		}
	}

	public void onTouchMove(int x, int y) {
		// TODO Auto-generated method stub
		Iterator<Entity> iter = _selectedList.iterator();
		while(iter.hasNext())
		{
			Entity entity = iter.next();
			_touchListener.onTouchMove(entity, x, y);
		}
	}

	public void onTouchUp(int x, int y) {
		// TODO Auto-generated method stub
		Iterator<Entity> iter = _selectedList.iterator();
		while(iter.hasNext())
		{
			Entity entity = iter.next();
			_touchListener.onTouchUp(entity, x, y);
		}
		_selectedList.clear();
	}

}
