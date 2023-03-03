/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling.managers

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.audio.Sound
import gparap.games.falling.hud.HUD
import gparap.games.falling.player.Player

/**
 * This manager class is responsible for handling the collisions of the player with the other game objects
 */
class CollisionManager(
    private val player: Player,
    private val spawnManager: SpawnManager,
    private val hud: HUD,
    assetManager: AssetManager
) {
    private val sfxPlayerHitDebris: Sound
    private val sfxPlayerHitEnemy: Sound
    private val sfxPlayerPickToken: Sound

    init {
        //get SFX from AssetManager
        sfxPlayerHitDebris = assetManager.get<Sound>("sfx/hit_debris.wav")
        sfxPlayerHitEnemy = assetManager.get<Sound>("sfx/hit_enemy.wav")
        sfxPlayerPickToken = assetManager.get<Sound>("sfx/pick_token.wav")
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
                sfxPlayerPickToken.play(0.5f)
            }
        }
    }

    private fun handleCollisionWithEnemy() {
        for (enemy in spawnManager.getEnemyPool()) {
            if (enemy.isActiveInGame() && player.getCollisionBounds().overlaps(enemy.getCollisionBounds())) {
                enemy.setDestroyed()
                player.loseLife()
                hud.setLife(player.getLife())
                sfxPlayerHitEnemy.play(0.5f)
            }
        }
    }

    private fun handleCollisionWithDebris() {
        for (debris in spawnManager.getDebrisPool()) {
            if (debris.isActiveInGame() && player.getCollisionBounds().overlaps(debris.getCollisionBounds())) {
                debris.setHitInGame(true)
                player.loseLife()
                hud.setLife(player.getLife())
                sfxPlayerHitDebris.play(0.5f)
            }
        }
    }
}