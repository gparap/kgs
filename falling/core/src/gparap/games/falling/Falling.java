package gparap.games.falling;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import gparap.games.falling.screens.GameScreen;

public class Falling extends Game {

	@Override
	public void create () {
		//create a new SpriteBatch
		SpriteBatch spriteBatch = new SpriteBatch();

		//create a new game screen
		setScreen(new GameScreen(spriteBatch));
	}
}
