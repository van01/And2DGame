package com.example.game2d;

import java.util.Iterator;
import java.util.LinkedList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.example.game2d.Timer.TimerListener;
import com.example.game2d.Timer.TimerManager;
import com.example.game2d.Utils.Device;
import com.example.game2d.Utils.Util;
import com.example.game2d.camera.Camera;
import com.example.game2d.camera.CameraManager;
import com.example.game2d.entity.AnimationEntity;
import com.example.game2d.entity.CharectorEntity;
import com.example.game2d.entity.Entity;
import com.example.game2d.entity.ObjectManager;
import com.example.game2d.entity.ScrollEntity;
import com.example.game2d.entity.CharectorEntity.DIRECTION;
import com.example.game2d.move.BresenhamMovePattern;
import com.example.game2d.move.MovableListener;
import com.example.game2d.move.StraightMovePattern;
import com.example.game2d.sound.SoundManager;
import com.example.game2d.sound.SoundObject;
import com.example.game2d.tile.TileData;
import com.example.game2d.tile.TileManager;

public class SelectStageScene extends DefaultGameScene {
	
	public SelectStageScene(Context _ctx) {
		super(_ctx);
		// TODO Auto-generated constructor stub
	}

	/**
	 * DefaultGameScene Interface
	 */
	public void initailize() 
	{
		
		createBackground();
		createSoundResource();
		createEntities();
		
		SoundObject obj = SoundManager.getSingletone().getResource("song");
		obj.play(5, 1);		
	}
	
	/**
	 * 
	 */

	public void createEntities() {
		/** entities */		
		Entity entity;
		
		LinkedList<Bitmap> idList = new LinkedList<Bitmap>();
		idList.add(BitmapFactory.decodeResource(_ctx.getResources(), R.drawable.arrow));
//		idList.add(BitmapFactory.decodeResource(getResources(), R.drawable.front02));
//		idList.add(BitmapFactory.decodeResource(getResources(), R.drawable.front03));
//		idList.add(BitmapFactory.decodeResource(getResources(), R.drawable.front04));

		MovableListener listener = new MovableListener() {
			@Override
			public void onStart(Entity entity, String moveName) {
				if(moveName == "2")
				{
				}
			}
			@Override
			public void onStop(Entity entity, String moveName) {
				// TODO Auto-generated method stub
				if(moveName == "1") {
					StraightMovePattern pattern = (StraightMovePattern)entity.getMovePattern("2");
					
					int x = entity.getX();
					int y = entity.getY();
					int dx;
					int dy;
					
					do { dx = (int)(Math.random()*10)-5;} while (dx == 0);
					do { dy = (int)(Math.random()*10)-5;} while (dy == 0);

					// Entity Postision 초기화
					if(x < 0) {
						x = 1;
					}
					else if(x >= Device.getWidth()) {
						x = Device.getWidth() - 1;
					}
					if(y < 0) {
						y = 1;
					}
					else if(y >= Device.getHeight()) {
						y = Device.getHeight() - 1;
					}
					entity.setPosition(x, y);
					pattern.setDeltaXY(dx, dy);
					entity.setCurrentMovePattner("2");
					entity.moveTo(0, 0);
				}
				else if(moveName == "2") {
//					StraightMovePattern pattern = (StraightMovePattern)entity.getMovePattern(moveName);
//					pattern.setToggleMove();
//					entity.moveTo(0, 0);
					SoundManager.getSingletone().getResource("ding").play(5,0);
					
					Entity mainEntity = objMgr.getEntity("mainEntity");
					entity.setCurrentMovePattner("1");
					entity.moveTo(mainEntity.getX(), mainEntity.getY());
					
				}
			}
			
		};
		for(int i=0; i<5; i++) {
			String name = "Entity" + i;
			int dep = (int)(Math.random() * 10)+5;
			entity = objMgr.createAnimationEntity(name, idList);
			BresenhamMovePattern pattern1 = new BresenhamMovePattern( dep );
			StraightMovePattern pattern2 = new StraightMovePattern((int)(Math.random()*10)-5, (int)(Math.random()*10)-5);
			pattern1.setAutoAngle(true);
			pattern2.setAutoAngle(true);
			entity.setPosition(Device.getWidth()/2, Device.getHeight()/2);
			entity.addMovePattern("1", pattern1);
			entity.addMovePattern("2", pattern2);
			entity.addMovableListener( listener );
			entity.setCurrentMovePattner("1");
			entity.moveTo(10, 10);
			((AnimationEntity)entity).startFrame((i+1)*500);
		}
		
		
		/** charecter */
		entity = objMgr.createCharectorEntity("mainEntity", 3);
		for(int i=R.drawable.user_move_0_0; i<=R.drawable.user_move_0_7; i++) {
			entity.addResource(CharectorEntity.ACTION.WALK, CharectorEntity.DIRECTION.UP,BitmapFactory.decodeResource(_ctx.getResources(), i));			
		}
		for(int i=R.drawable.user_move_2_0; i<=R.drawable.user_move_2_7; i++) {
			entity.addResource(CharectorEntity.ACTION.WALK, CharectorEntity.DIRECTION.LEFT,BitmapFactory.decodeResource(_ctx.getResources(), i));			
		}
		for(int i=R.drawable.user_move_4_0; i<=R.drawable.user_move_4_7; i++) {
			entity.addResource(CharectorEntity.ACTION.WALK, CharectorEntity.DIRECTION.DOWN,BitmapFactory.decodeResource(_ctx.getResources(), i));			
		}
		for(int i=R.drawable.user_move_6_0; i<=R.drawable.user_move_6_7; i++) {
			entity.addResource(CharectorEntity.ACTION.WALK, CharectorEntity.DIRECTION.RIGHT,BitmapFactory.decodeResource(_ctx.getResources(), i));			
		}
		Bitmap bm = BitmapFactory.decodeResource(_ctx.getResources(), R.drawable.user_move_0_0);
		entity.setOffset(bm.getWidth()/2, bm.getHeight()/2);
		entity.setPosition(Device.getWidth()/2, Device.getHeight()/2);
		((CharectorEntity)entity).setAction(CharectorEntity.ACTION.WALK);
		((CharectorEntity)entity).setDirection(CharectorEntity.DIRECTION.LEFT);
		((CharectorEntity)entity).startFrame(200);
		((CharectorEntity)entity).addMovePattern("WALK", new StraightMovePattern(4,2));
		((CharectorEntity)entity).setCurrentMovePattner("WALK");
		((CharectorEntity)entity).moveTo(10, 10);
		MovableListener listener2 = new MovableListener() {
			@Override
			public void onStart(Entity entity, String moveName) {}
			@Override
			public void onStop(Entity entity, String moveName) {
				if(moveName == "WALK") {
					StraightMovePattern pattern = (StraightMovePattern)entity.getMovePattern(moveName);
					int dx = Util.getRandom(pattern.getDeltaX(), 10);
					int dy = Util.getRandom(pattern.getDeltaY(), 10);
					pattern.setDeltaXY(dx,dy);
					pattern.setToggleMove();
					entity.moveTo(0, 0);
					
					dx = pattern.getDeltaX();
					dy = pattern.getDeltaY();
					CharectorEntity chr = (CharectorEntity)entity;
					if(Math.abs(dx) > Math.abs(dy)) {
						chr.setDirection((dx > 0)?DIRECTION.LEFT:DIRECTION.RIGHT);
					}
					else {
						chr.setDirection((dy > 0)?DIRECTION.DOWN:DIRECTION.UP);
					}
					
				}
			}
		};
		((CharectorEntity)entity).addMovableListener(listener2);
		
		//Object들은 sorting하여 그려준다.
		objMgr.setSortDraw(3, true);
	}
	
	public void createBackground() {
		////////////////////////
		Entity entity;
		
		MovableListener cloudListener = new MovableListener() {
			public void onStart(Entity entity, String moveName) {
			}
			@Override
			public void onStop(Entity entity, String moveName) {
				SoundObject sound = SoundManager.getSingletone().getResource("ding");
				sound.play(5, 0);

				entity.setPosition(entity.getX(), entity.getY() - (Device.getHeight()*2));
				//entity.setPosition(entity.getX(), entity.getY() - (Device.getHeight()));
				entity.moveTo(entity.getX(), entity.getY() + (Device.getHeight()*2));
			}
		};
		
		/** small cloud */
		for(int i=0; i<20; i++) {
			String name = "smallCloud" + i;
			int speed = (int)(Math.random() * 3)+3;
			entity = objMgr.createBitmapEntity(name, BitmapFactory.decodeResource(_ctx.getResources(), R.drawable.small_cloud1+(i%3)), 2);
			BresenhamMovePattern pattern1 = new BresenhamMovePattern( speed );
			entity.addMovePattern("background", pattern1);
			entity.addMovableListener( cloudListener );
			entity.setCurrentMovePattner("background");
			entity.setPosition((int)(Math.random()*Device.getWidth()), -(int)(Math.random()*Device.getHeight()));
			entity.moveTo(entity.getX(), entity.getY() + (Device.getHeight()*2));
		}
		for(int i=0; i<10; i++) {
			String name = "bigCloud" + i;
			int speed = (int)(Math.random() * 3)+7;
			entity = objMgr.createBitmapEntity(name, BitmapFactory.decodeResource(_ctx.getResources(), R.drawable.big_cloud1+(i%3)), 4);
			BresenhamMovePattern pattern1 = new BresenhamMovePattern( speed );
			entity.addMovePattern("background", pattern1);
			entity.addMovableListener( cloudListener );
			entity.setCurrentMovePattner("background");
			entity.setPosition((int)(Math.random()*Device.getWidth()), -(int)(Math.random()*Device.getHeight()));
			entity.moveTo(entity.getX(), entity.getY() + (Device.getHeight()*2));
		}	
		

		//background scroll
		Bitmap background = BitmapFactory.decodeResource(_ctx.getResources(), R.drawable.background);
		Rect destRect = new Rect(0,0, Device.getWidth(), Device.getHeight());
		Rect srcRect = new Rect(0,0, background.getWidth(), background.getHeight());
		ScrollEntity scroll = objMgr.createScrollEntity("background", background, srcRect, destRect, 0);

		int mx=0, my=5;
		int ms = 20;
		scroll.startFrame(ms, mx, my);

	}
	
	public void createTileBackground() {
		byte firstStage[][]={
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
				{1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
				{1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
				{1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
				{1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
				{1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
				{1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
				{1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
				{1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
				{1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
		};
		byte secondStage[][]={
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
				{1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
				{1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
				{1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
				{1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
				{1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
				{1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
				{1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
				{1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
				{1,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		};
			
		Camera camera = CameraManager.getSingletone().createCamera("playerCam");
		camera.setPosition(0,0);

		CameraManager.getSingletone().setCurrentCamera("playerCam");
	}
	
	public void createSoundResource()
	{
		SoundManager.getSingletone().initailize(3);
		SoundManager.getSingletone().addResource("ding", R.raw.ding, SoundManager.TYPE.BGM);
		SoundManager.getSingletone().addResource("walk", R.raw.walk, SoundManager.TYPE.BGM);
		SoundManager.getSingletone().addResource("song", R.raw.song, SoundManager.TYPE.MUSIC);
	}
	
	
	int mouseX, mouseY;
	int moveX, moveY;
	boolean bMove;	
	public boolean onTouchEvent(MotionEvent event) 
	{
	    int keyAction = event.getAction();
	    int x = (int)event.getX();
	    int y = (int)event.getY();
	    switch (keyAction){
	    case MotionEvent.ACTION_MOVE:
	        if (bMove){
	        	
	        	int dx = moveX - x;
	        	int dy = moveY - y;
	        	
	        	if(moveX != 0 && moveY != 0) {
		        	Camera camera = CameraManager.getSingletone().getCurrentCamera();
		        	camera.translate(-dx, -dy);
					Log.d("AKIRA", "dx : " + dx + ", dy : " + dy);
	        	}
	        	
	            moveX = x;
	            moveY = y;
	        }
	        //moveEntity(x, y);
	        
	        break;
	    case MotionEvent.ACTION_UP:
		    mouseX = 0;
		    mouseY = 0;
		    moveX = moveY = 0;
	        bMove = false;
	        break;
	    case MotionEvent.ACTION_DOWN:
		    {
			    mouseX = x;
			    mouseY = y;
		        bMove = true;
		        moveEntity(x, y);
		        break;
		    }
	    }
	    // 함수 override 해서 사용하게 되면  return  값이  super.onTouchEvent(event) 되므로
	    // MOVE, UP 관련 이벤트가 연이어 발생하게 할려면 true 를 반환해주어야 한다.	
		return true;
	}

	private void moveEntity(int x, int y) {
		// TODO Auto-generated method stub
        Iterator<Entity> iter = objMgr.getAllEntityWithLevel(3);
		
		while(iter.hasNext()) {
			Entity entity = iter.next();
			entity.setCurrentMovePattner("1");
			entity.moveTo(x, y);
		}
	}
	
	public void oneDrawFrame (Canvas canvas) {
		
		super.oneDrawFrame(canvas);
	}
}
