/*******************************
 * Katocheánian Gaming Studios *
 * Falling Evaders Chaos       *
 * created by gparap           *
 *******************************/
@file:Suppress("JoinDeclarationAndAssignment")

package gparap.games.falling.managers

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import com.badlogic.gdx.audio.Sound
import gparap.games.falling.hud.HUD
import gparap.games.falling.player.Player
import gparap.games.falling.utils.GameConstants
import gparap.games.falling.utils.GameConstants.SFX_VOLUME_DEFAULT

/**
 * This manager class is responsible for handling the collisions of the player with the other game objects
 */
class CollisionManager(private val player: Player, private val spawnManager: SpawnManager, private val hud: HUD) {
    private val sfxPlayerHitDebris: Sound
    private val sfxPlayerHitEnemy: Sound
    private val sfxPlayerPickToken: Sound
    private var preferences: Preferences
    private var areSFXOn: Boolean

    init {
        //get SFX from AssetManager
        sfxPlayerHitDebris = SfxManager.getInstance().getSFX(GameConstants.SFX_PLAYER_HIT_DEBRIS)
        sfxPlayerHitEnemy = SfxManager.getInstance().getSFX(GameConstants.SFX_PLAYER_HIT_ENEMY)
        sfxPlayerPickToken = SfxManager.getInstance().getSFX(GameConstants.SFX_PLAYER_PICKUP_TOKEN)

        //get the SFX preferences
        preferences = Gdx.app.getPreferences(GameConstants.PREFERENCES)
        areSFXOn = preferences.getBoolean(GameConstants.PREFERENCES_SXF, GameConstants.PREFERENCES_SXF_DEFAULT)
    }

    fun update() {
        handleCollisionWithToken()
        handleCollisionWithEnemy()
        handleCollisionWithDebris()
    }

    private fun handleCollisionWithToken() {
        for (token in spawnManager.getTokenPool()) {
            if (token.isActiveInGame() && player.getCollisionBounds().overlaps(token.getCollisionBounds())) {
                token.setCollectedInGame(true)
                hud.setScore(token.getScorePoints())
                if (areSFXOn){
                    sfxPlayerPickToken.play(SFX_VOLUME_DEFAULT)
                }
            }
        }
    }

    private fun handleCollisionWithEnemy() {
        for (enemy in spawnManager.getEnemyPool()) {
            if (enemy.isActiveInGame() && player.getCollisionBounds().overlaps(enemy.getCollisionBounds())) {
                enemy.setDestroyed()
                player.loseLife()
                hud.setLife(player.getLife())
                if (areSFXOn){
                    sfxPlayerHitEnemy.play(SFX_VOLUME_DEFAULT)
                }
            }
        }
    }

    private fun handleCollisionWithDebris() {
        for (debris in spawnManager.getDebrisPool()) {
            if (debris.isActiveInGame() && player.getCollisionBounds().overlaps(debris.getCollisionBounds())) {
                debris.setHitInGame(true)
                player.loseLife()
                hud.setLife(player.getLife())
                if (areSFXOn){
                    sfxPlayerHitDebris.play(SFX_VOLUME_DEFAULT)
                }
            }
        }
    }
}