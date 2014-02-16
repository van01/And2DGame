package com.example.game2d.camera;

import java.util.HashMap;

public class CameraManager {

	static protected CameraManager _instance = new CameraManager();
	protected HashMap<String, Camera> _cameraMap = new HashMap<String, Camera>();
	protected Camera _currentCamera;
	protected String _currentCameraName;
	
	private CameraManager() {
	}
	
	public static CameraManager getSingletone() {
		return _instance;
	}
	
	public Camera createCamera(String name) {
		// TODO Auto-generated method stub
		Camera camera = new Camera();
		_cameraMap.put(name, camera);
		return camera;
	}

	public Camera setCurrentCamera(String name) {
		// TODO Auto-generated method stub

		Camera camera = _cameraMap.get(name);
		
		if ( null != camera)
		{
			_currentCamera = camera;
			_currentCameraName = name;
		}
		
		return _currentCamera;
	}

	public Camera getCurrentCamera() {
		// TODO Auto-generated method stub
		return _currentCamera;
	}

}
