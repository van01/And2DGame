package com.example.game2d.tile;

public class TileData {

	protected String _name;
	protected byte[][] _stageData;
	
	public TileData(String name, byte[][] stageData) {
		// TODO Auto-generated constructor stub
		_name		= name;
		_stageData	= stageData;
	}

	public byte[][] getData() {
		// TODO Auto-generated method stub
		return _stageData;
	}
	

}
