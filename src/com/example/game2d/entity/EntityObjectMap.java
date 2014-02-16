package com.example.game2d.entity;

import java.util.HashMap;
import java.util.SortedMap;

public class EntityObjectMap extends HashMap<String, Entity> {

	protected boolean _isSorted = false;
	
	public void preDrawProcess() {
		
		//list를 소트 한다.
		if(_isSorted)
		{
		}
	}
	
	public void setSortDraw(boolean isSort)
	{
		_isSorted = isSort;
	}
}
