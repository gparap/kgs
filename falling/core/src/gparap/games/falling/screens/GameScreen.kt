/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import gparap.games.falling.Falling
import gparap.games.falling.GameConstants
import gparap.games.falling.Player

class GameScreen(spriteBatch: SpriteBatch, game: Falling) : Screen(spriteBatch){
    private lateinit var player: Player

    override fun show() {
        super.show()

        //create player object
        player = Player(playerPref)
    }

    override fun render(delta: Float) {
        super.render(delta)
        spriteBatch.begin()
        player.draw(spriteBatch)
        spriteBatch.end()
    }

    companion object{
        val playerPref: String = Gdx.app.getPreferences(GameConstants.PREFERENCES)
            .getString(GameConstants.PREFERENCES_FRIEND, GameConstants.PREFERENCES_FRIEND_DEFAULT)
    }
}