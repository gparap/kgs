/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import gparap.games.falling.GameConstants
import gparap.games.falling.player.Player
import gparap.games.falling.token.Token

class GameScreen(spriteBatch: SpriteBatch) : Screen(spriteBatch) {
    private lateinit var player: Player
    private lateinit var token: Token

    override fun show() {
        super.show()

        //create game objects
        player = Player(playerPref)
        token = Token()
    }

    override fun render(delta: Float) {
        super.render(delta)

        //update game
        player.update(delta)
        token.update(delta)

        //draw game
        spriteBatch.begin()
        player.draw(spriteBatch)
        token.draw(spriteBatch)
        spriteBatch.end()
    }

    companion object {
        val playerPref: String = Gdx.app.getPreferences(GameConstants.PREFERENCES)
            .getString(GameConstants.PREFERENCES_FRIEND, GameConstants.PREFERENCES_FRIEND_DEFAULT)
    }
}