/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import gparap.games.falling.screens.MenuScreen;

public class Falling extends Game {
	private AssetManager assetManager;

	public AssetManager getAssetManager() {
		return assetManager;
	}

	@Override
	public void create () {
		//load SFX
		assetManager = new AssetManager();
		assetManager.load("sfx/hit_debris.wav", Sound.class);
		assetManager.load("sfx/hit_enemy.wav", Sound.class);
		assetManager.load("sfx/jump_player.wav", Sound.class);
		assetManager.load("sfx/pick_token.wav", Sound.class);
		assetManager.finishLoading();

		//create a new SpriteBatch
		SpriteBatch spriteBatch = new SpriteBatch();

		//create the main menu screen
		setScreen(new MenuScreen(spriteBatch, this));
	}

	@Override
	public void dispose() {
		super.dispose();
		assetManager.dispose();
	}
}
