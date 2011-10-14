package org.anddev.tests;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.anddev.andengine.engine.camera.hud.controls.DigitalOnScreenControl;
import org.anddev.andengine.engine.camera.hud.controls.BaseOnScreenControl.IOnScreenControlListener;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.IEntity;
import org.anddev.andengine.entity.modifier.PathModifier;
import org.anddev.andengine.entity.modifier.PathModifier.IPathModifierListener;
import org.anddev.andengine.entity.modifier.PathModifier.Path;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.RepeatingSpriteBackground;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.atlas.bitmap.source.AssetBitmapTextureAtlasSource;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import android.util.Log;
import android.widget.Toast;

/**
 * @author Nicolas Gramlich
 * @since 11:54:51 - 03.04.2010
 */
public class PathModifierExample2 extends BaseExample {
	// ===========================================================
	// Constants
	// ===========================================================
	
	private static final int CAMERA_WIDTH = 720;
	private static final int CAMERA_HEIGHT = 480;
	
	// ===========================================================
	// Fields
	// ===========================================================

	private Camera mCamera;

	private RepeatingSpriteBackground mGrassBackground;

	private BitmapTextureAtlas mBitmapTextureAtlas;
	private TiledTextureRegion mPlayerTextureRegion;
	
	
	
	private BitmapTextureAtlas mOnScreenControlTexture;
	private TextureRegion mOnScreenControlBaseTextureRegion;
	private TextureRegion mOnScreenControlKnobTextureRegion;
	
	private DigitalOnScreenControl mDigitalOnScreenControl;
	
	
	int playerX = 0;
	int playerY = 0;
	

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	public Engine onLoadEngine() {
		Toast.makeText(this, "You move my sprite right round, right round...", Toast.LENGTH_LONG).show();
		this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		return new Engine(new EngineOptions(true, ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mCamera));
	}

	
//	public void onLoadResources() {
//		this.mBitmapTextureAtlas = new BitmapTextureAtlas(64, 128, TextureOptions.DEFAULT);
//		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
//		this.mPlayerTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "player.png", 0, 0, 3, 4);
//		this.mGrassBackground = new RepeatingSpriteBackground(CAMERA_WIDTH, CAMERA_HEIGHT, this.mEngine.getTextureManager(), new AssetBitmapTextureAtlasSource(this, "background_grass.png"));
//
//		this.mEngine.getTextureManager().loadTextures(this.mBitmapTextureAtlas);
//	}
	
	public void onLoadResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		this.mBitmapTextureAtlas = new BitmapTextureAtlas(64, 128, TextureOptions.DEFAULT);
		//this.mPlayerTextureRegion = TextureRegionFactory.createTiledFromAsset(this.mTexture, this, "gfx/duke2.png", 0, 0, 2, 4);
		this.mPlayerTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "duke2.png", 0, 0, 2, 4);
		
		this.mGrassBackground = new RepeatingSpriteBackground(CAMERA_WIDTH, CAMERA_HEIGHT, this.mEngine.getTextureManager(), new AssetBitmapTextureAtlasSource(this, "gfx/background_grass.png"));
		
		//this.mOnScreenControlTexture = new BitmapTextureAtlas(256, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		//this.mOnScreenControlBaseTextureRegion = TextureRegionFactory.createFromAsset(this.mOnScreenControlTexture, this, "gfx/onscreen_control_base.png", 0, 0);
		//this.mOnScreenControlKnobTextureRegion = TextureRegionFactory.createFromAsset(this.mOnScreenControlTexture, this, "gfx/onscreen_control_knob.png", 128, 0);
		this.mOnScreenControlTexture = new BitmapTextureAtlas(256, 128, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.mOnScreenControlBaseTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mOnScreenControlTexture, this, "onscreen_control_base.png", 0, 0);
		this.mOnScreenControlKnobTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.mOnScreenControlTexture, this, "onscreen_control_knob.png", 128, 0);
		
		//this.mEngine.getTextureManager().loadTextures(this.mTexture, this.mOnScreenControlTexture);
		this.mEngine.getTextureManager().loadTextures(this.mBitmapTextureAtlas, this.mOnScreenControlTexture);
	}

	public Scene onLoadScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());

		final Scene scene = new Scene();
		scene.setBackground(this.mGrassBackground);

		/* Create the face and add it to the scene. */
		final AnimatedSprite player = new AnimatedSprite(0, 0, 64, 64, this.mPlayerTextureRegion);
	
		/* Add the proper animation when a waypoint of the path is passed. */
		

		
		
		mDigitalOnScreenControl = new DigitalOnScreenControl(0, CAMERA_HEIGHT - this.mOnScreenControlBaseTextureRegion.getHeight(), this.mCamera, this.mOnScreenControlBaseTextureRegion, this.mOnScreenControlKnobTextureRegion, 0.1f, new IOnScreenControlListener() {
			
			Boolean movable = true;
			@Override
			public void onControlChange(final BaseOnScreenControl pBaseOnScreenControl, final float pValueX, final float pValueY) {
				
				Path path;
				PathModifier pathMod;
				IPathModifierListener ipathlistener = new IPathModifierListener() {
					@Override
					public void onPathStarted(PathModifier pPathModifier,IEntity pEntity) {
						Log.i("CONTROL", "Path started");
					}
					@Override
					public void onPathWaypointStarted(PathModifier pPathModifier, IEntity pEntity,int pWaypointIndex) {
						// TODO Auto-generated method stub
						
					}
					@Override
					public void onPathWaypointFinished(PathModifier pPathModifier, IEntity pEntity,int pWaypointIndex) {

					}
					@Override
					public void onPathFinished(PathModifier pPathModifier,IEntity pEntity) {
						Log.i("CONTROL", "Path ended");
						movable = true;
						//pEntity.unregisterEntityModifier(pPathModifier);
					}
				};
				
				
				
				if(movable) {
					if(pValueX > 0) {		// control right
						Log.i("CONTROL", "right");
						player.animate(new long[]{200, 200}, 2, 3, true);
						path = new Path(2).to(playerX, playerY).to(playerX+64, playerY);
						playerX += 64;
						pathMod = new PathModifier((float) 0.4 , path, null, ipathlistener);
						player.registerEntityModifier(pathMod);
						Log.i("CONTROL", "removed when finished = " + String.valueOf(pathMod.isRemoveWhenFinished()));
						movable = false;
					}
					if(pValueX < 0) {		// control left
						Log.i("CONTROL", "left");
						player.animate(new long[]{200, 200}, 4, 5, true);
						path = new Path(2).to(playerX, playerY).to(playerX-64, playerY);
						playerX -= 64;
						pathMod = new PathModifier(2, path, null, ipathlistener);
						player.registerEntityModifier(pathMod);
						movable = false;
					}
					if(pValueY > 0) {		// control down
						Log.i("CONTROL", "down");
						player.animate(new long[]{200, 200}, 0, 1, true);
						path = new Path(2).to(playerX, playerY).to(playerX, playerY+64);
						playerY += 64;
						pathMod = new PathModifier(2, path, null, ipathlistener);
						player.registerEntityModifier(pathMod);
						movable = false;
					}
					if(pValueY < 0) {		// control up
						Log.i("CONTROL", "up");
						player.animate(new long[]{200, 200}, 6, 7, true);
						path = new Path(2).to(playerX, playerY).to(playerX, playerY-64);
						playerY -= 64;
						pathMod = new PathModifier(2, path, null, ipathlistener);
						player.registerEntityModifier(pathMod);
						movable = false;
					}
				}
			}
		});
		mDigitalOnScreenControl.getControlBase().setBlendFunction(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		mDigitalOnScreenControl.getControlBase().setAlpha(0.5f);
		mDigitalOnScreenControl.getControlBase().setScaleCenter(0, 128);
		mDigitalOnScreenControl.getControlBase().setScale(1.25f);
		mDigitalOnScreenControl.getControlKnob().setScale(1.25f);
		mDigitalOnScreenControl.refreshControlKnobPosition();

		
		scene.attachChild(player);
		scene.setChildScene(mDigitalOnScreenControl);

		return scene;
	}

	public void onLoadComplete() {

	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
