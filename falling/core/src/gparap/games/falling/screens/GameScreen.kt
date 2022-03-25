/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import gparap.games.falling.GameConstants
import gparap.games.falling.HUD
import gparap.games.falling.enemies.Enemy
import gparap.games.falling.managers.CollisionManager
import gparap.games.falling.managers.SpawnManager
import gparap.games.falling.managers.TokenManager
import gparap.games.falling.player.Player

class GameScreen(spriteBatch: SpriteBatch) : Screen(spriteBatch) {
    private lateinit var player: Player
    private lateinit var enemy: Enemy
    private lateinit var tokenManager: TokenManager
    private lateinit var spawnManager: SpawnManager
    private lateinit var collisionManager: CollisionManager
    private lateinit var hud: HUD

    override fun show() {
        super.show()

        //create player object
        player = Player(playerPref)

        //create enemy object
        enemy = Enemy()

        //create the heads-up display object
        hud = HUD(spriteBatch)

        //create manager objects
        tokenManager = TokenManager()
        spawnManager = SpawnManager(tokenManager)
        collisionManager = CollisionManager(player, spawnManager, hud)
    }

    override fun render(delta: Float) {
        super.render(delta)

        //update game
        player.update(delta)
        enemy.update(delta)
        tokenManager.updateTokens(delta)
        spawnManager.update(delta)
        collisionManager.update()
        hud.update()

        //draw game
        spriteBatch.begin()
        player.draw(spriteBatch)
        enemy.draw(spriteBatch)
        tokenManager.drawTokens(spriteBatch)
        spriteBatch.end()

        //draw heads-up display
        hud.draw()
    }

    companion object {
        val playerPref: String = Gdx.app.getPreferences(GameConstants.PREFERENCES)
            .getString(GameConstants.PREFERENCES_FRIEND, GameConstants.PREFERENCES_FRIEND_DEFAULT)
    }
}