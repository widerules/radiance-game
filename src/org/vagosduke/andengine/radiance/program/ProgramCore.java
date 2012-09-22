package org.vagosduke.andengine.radiance.program;


import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.util.FPSLogger;
import org.vagosduke.andengine.radiance.tests.BaseExample;


public class ProgramCore extends BaseExample {
	// ===========================================================
		// Constants
		// ===========================================================

		private static final int CAMERA_WIDTH = 480;
		private static final int CAMERA_HEIGHT = 320;

		// ===========================================================
		// Fields
		// ===========================================================

		private Camera mCamera;

		

		// ===========================================================
		// Constructors
		// ===========================================================

		// ===========================================================
		// Getter & Setter
		// ===========================================================

		// ===========================================================
		// Methods for/from SuperClass/Interfaces
		// ===========================================================

		@Override
		public Engine onLoadEngine() {
			this.mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
			final Engine engine = new Engine(new EngineOptions(true, ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mCamera));

			return engine;
		}

		@Override
		public void onLoadResources() {
			
			Init.init_All(this);
			
		}

		@Override
		public Scene onLoadScene() {
			this.mEngine.registerUpdateHandler(new FPSLogger());

			final Scene scene = new Scene();
			

			return scene;
		}

		@Override
		public void onLoadComplete() {

		}

		// ===========================================================
		// Methods
		// ===========================================================

		// ===========================================================
		// Inner and Anonymous Classes
		// ===========================================================
	

}
