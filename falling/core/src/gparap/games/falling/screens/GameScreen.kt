/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import gparap.games.falling.GameConstants
import gparap.games.falling.managers.TokenManager
import gparap.games.falling.player.Player

class GameScreen(spriteBatch: SpriteBatch) : Screen(spriteBatch) {
    private lateinit var player: Player
    private lateinit var tokenManager: TokenManager

    override fun show() {
        super.show()

        //create manager objects
        tokenManager = TokenManager()

        //create game objects
        player = Player(playerPref)
        for (i in 0..2) {
            tokenManager.createToken()
        }
    }

    override fun render(delta: Float) {
        super.render(delta)

        //update game
        player.update(delta)
        tokenManager.updateTokens(delta)

        //draw game
        spriteBatch.begin()
        player.draw(spriteBatch)
        tokenManager.drawTokens(spriteBatch)
        spriteBatch.end()
    }

    companion object {
        val playerPref: String = Gdx.app.getPreferences(GameConstants.PREFERENCES)
            .getString(GameConstants.PREFERENCES_FRIEND, GameConstants.PREFERENCES_FRIEND_DEFAULT)
    }
}