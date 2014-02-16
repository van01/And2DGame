package com.example.game2d;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;

public class MainActivity extends Activity {

	Panel panel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		panel = new Panel(this);
		setContentView(panel);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onPause() {
		panel.onPause();
		
		super.onPause();
	}
	
	public void onResume() {
		panel.onResume();
		
		super.onResume();
	}

}
