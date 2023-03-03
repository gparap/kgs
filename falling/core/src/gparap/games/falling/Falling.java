/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import gparap.games.falling.managers.SfxManager;
import gparap.games.falling.screens.MenuScreen;

public class Falling extends Game {
    @Override
    public void create() {
        //load sound effects
        SfxManager.getInstance().loadSFX();

        //create a new SpriteBatch
        SpriteBatch spriteBatch = new SpriteBatch();

        //create the main menu screen
        setScreen(new MenuScreen(spriteBatch, this));
    }

    @Override
    public void dispose() {
        super.dispose();
        SfxManager.getInstance().dispose();
    }
}
