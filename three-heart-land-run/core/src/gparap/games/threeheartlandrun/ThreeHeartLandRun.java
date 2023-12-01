package gparap.games.threeheartlandrun;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class ThreeHeartLandRun extends Game {
	private SpriteBatch spriteBatch;
	private Texture img;
	private OrthographicCamera camera;

	//game dimensions
	public static final int MIN_WIDTH = 640;
	public static final int MIN_HEIGHT = 360;
	public static final int MAX_WIDTH = 1920;
	public static final int MAX_HEIGHT = 1080;

	@Override
	public void create () {
		spriteBatch = new SpriteBatch();
		img = new Texture("background-empty.png");

		//setup the game camera
		camera = new OrthographicCamera(MIN_WIDTH, MIN_HEIGHT);
		camera.setToOrtho(false);
		camera.position.set(MIN_WIDTH/2F, MIN_HEIGHT/2F,0F);
		camera.update();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.begin();
		spriteBatch.draw(img, 0, 0, camera.viewportWidth, camera.viewportHeight);
		spriteBatch.end();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);

		//update the game camera
		camera.viewportWidth = width;
		camera.viewportHeight = height;
		camera.position.set(width/2F, height/2F,0F);
		camera.update();
	}

	@Override
	public void dispose () {
		spriteBatch.dispose();
		img.dispose();
	}
}
