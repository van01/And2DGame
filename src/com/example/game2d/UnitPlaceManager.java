package com.example.game2d;

public class UnitPlaceManager {

	static private UnitPlaceManager _instance = new UnitPlaceManager();
	
	public static UnitPlaceManager getSingletone() {
		// TODO Auto-generated method stub
		return _instance;
	}

	public UnitPlaceDataContent load(String string) {
		// TODO Auto-generated method stub
		
		UnitPlaceDataContent placeData = new UnitPlaceDataContent();
		
		placeData.setDummyData();
		
		return placeData;
	}

}
