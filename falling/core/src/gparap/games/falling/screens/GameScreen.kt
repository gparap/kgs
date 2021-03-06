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
import gparap.games.falling.HUD
import gparap.games.falling.managers.CollisionManager
import gparap.games.falling.managers.EnemyManager
import gparap.games.falling.managers.SpawnManager
import gparap.games.falling.managers.TokenManager
import gparap.games.falling.player.Player

class GameScreen(spriteBatch: SpriteBatch, private val game: Falling) : Screen(spriteBatch) {
    private lateinit var player: Player
    private lateinit var enemyManager: EnemyManager
    private lateinit var tokenManager: TokenManager
    private lateinit var spawnManager: SpawnManager
    private lateinit var collisionManager: CollisionManager
    private lateinit var hud: HUD

    override fun show() {
        super.show()

        //create player object
        val friendPref: String = Gdx.app.getPreferences(GameConstants.PREFERENCES)
            .getString(GameConstants.PREFERENCES_FRIEND, GameConstants.PREFERENCES_FRIEND_DEFAULT)
        player = Player(friendPref)

        //create the heads-up display object
        hud = HUD(spriteBatch)

        //create manager objects
        enemyManager = EnemyManager()
        tokenManager = TokenManager()
        spawnManager = SpawnManager(tokenManager, enemyManager)
        collisionManager = CollisionManager(player, spawnManager, hud)
    }

    override fun render(delta: Float) {
        super.render(delta)

        //check if game is over
        if (player.getLife() == 0) {
            this.dispose()
            this.hide()
            game.screen = GameOverScreen(spriteBatch, game)
        }

        //update game
        player.update(delta)
        tokenManager.updateTokens(delta)
        enemyManager.updateEnemies(delta)
        spawnManager.update(delta)
        collisionManager.update()
        hud.update()

        //draw game
        spriteBatch.begin()
        player.draw(spriteBatch)
        tokenManager.drawTokens(spriteBatch)
        enemyManager.drawEnemies(spriteBatch)
        spriteBatch.end()

        //draw heads-up display
        hud.draw()
    }
}