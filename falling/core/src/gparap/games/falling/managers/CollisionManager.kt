/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
@file:Suppress("JoinDeclarationAndAssignment")

package gparap.games.falling.managers

import com.badlogic.gdx.audio.Sound
import gparap.games.falling.hud.HUD
import gparap.games.falling.player.Player
import gparap.games.falling.utils.GameConstants

/**
 * This manager class is responsible for handling the collisions of the player with the other game objects
 */
class CollisionManager(private val player: Player, private val spawnManager: SpawnManager, private val hud: HUD) {
    private val sfxPlayerHitDebris: Sound
    private val sfxPlayerHitEnemy: Sound
    private val sfxPlayerPickToken: Sound

    init {
        //get SFX from AssetManager
        sfxPlayerHitDebris = SfxManager.getInstance().getSFX(GameConstants.SFX_PLAYER_HIT_DEBRIS)
        sfxPlayerHitEnemy = SfxManager.getInstance().getSFX(GameConstants.SFX_PLAYER_HIT_ENEMY)
        sfxPlayerPickToken = SfxManager.getInstance().getSFX(GameConstants.SFX_PLAYER_PICKUP_TOKEN)
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
                sfxPlayerPickToken.play(GameConstants.SFX_VOLUME_DEFAULT)
            }
        }
    }

    private fun handleCollisionWithEnemy() {
        for (enemy in spawnManager.getEnemyPool()) {
            if (enemy.isActiveInGame() && player.getCollisionBounds().overlaps(enemy.getCollisionBounds())) {
                enemy.setDestroyed()
                player.loseLife()
                hud.setLife(player.getLife())
                sfxPlayerHitEnemy.play(GameConstants.SFX_VOLUME_DEFAULT)
            }
        }
    }

    private fun handleCollisionWithDebris() {
        for (debris in spawnManager.getDebrisPool()) {
            if (debris.isActiveInGame() && player.getCollisionBounds().overlaps(debris.getCollisionBounds())) {
                debris.setHitInGame(true)
                player.loseLife()
                hud.setLife(player.getLife())
                sfxPlayerHitDebris.play(GameConstants.SFX_VOLUME_DEFAULT)
            }
        }
    }
}