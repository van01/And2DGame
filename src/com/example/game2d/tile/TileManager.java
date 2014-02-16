package com.example.game2d.tile;

import java.util.HashMap;
import java.util.Iterator;

import com.example.game2d.Utils.Device;
import com.example.game2d.camera.Camera;
import com.example.game2d.camera.CameraManager;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

public class TileManager {
	
	protected HashMap<Integer, Bitmap> _tileResource = new HashMap<Integer, Bitmap>();
	protected HashMap<String, TileData> _stageDataMap = new HashMap<String, TileData>();
	protected TileData _currentStage;
	protected String _currentStageName;
	protected int _tileWidth, _tileHeight;
	protected Bitmap[] _resourceData;
	
	public TileManager()
	{
	}
	

	public void addResource(int index, Bitmap bitmap) {
		// TODO Auto-generated method stub
		_tileResource.put(index, bitmap);
		
		//default tile size
		if(_tileWidth == 0 || _tileHeight == 0) {
			_tileWidth	= bitmap.getWidth();
			_tileHeight = bitmap.getHeight();
		}
		
		int size = _tileResource.size();
		_resourceData = new Bitmap[size];
		int i = 0;
		Iterator<Bitmap> iter = _tileResource.values().iterator();
		while(iter.hasNext())
		{
			_resourceData[i++] = iter.next();
		}
	}

	public void setTileData(String name, byte[][] stageData) {
		// TODO Auto-generated method stub
		TileData data = new TileData(name, stageData);
		_stageDataMap.put( name, data);
	}

	public TileData setCurrentStage(String stageName) {
		// TODO Auto-generated method stub
		
		TileData data = _stageDataMap.get(stageName);
		
		if(null != data) {
			_currentStageName = stageName;
			_currentStage = data;
		}
		
		return data;
	}

	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		
		if(null == _currentStage)
		{
			return;
		}
		
		byte data[][] = _currentStage.getData();
		int row = data[0].length;
		int col = data.length;
		Camera camera = CameraManager.getSingletone().getCurrentCamera();

		int sx = camera.getWX() + Device._lcdWidth/2;
		int sy = camera.getWY() + Device._lcdHeight/2;
		
		for(int i=0; i<col; i++) 
		{
			int y=sy + (i*_tileHeight);
			
			//clip tile col
			if( y>= -_tileHeight  && y < Device._lcdHeight) {

				for (int j=0; j<row; j++)
				{
					//print bitmap
					int x=sx + (j*_tileWidth);
					
					//clip tile row
					if(x >= -_tileWidth && x < Device._lcdWidth)
					{
						int index = data[i][j];
						Bitmap bitTile = _tileResource.get(index);
						canvas.drawBitmap(bitTile, x, y,null);
					}
				}
			}
		}
	}

}
