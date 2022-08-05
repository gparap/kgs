/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling.managers

import gparap.games.falling.hud.HUD
import gparap.games.falling.player.Player

/**
 * This manager class is responsible for handling the collisions of the player with the other game objects
 */
class CollisionManager(private val player: Player, private val spawnManager: SpawnManager, private val hud: HUD) {
    fun update() {
        handleCollisionWithToken()
        handleCollisionWithEnemy()
    }

    private fun handleCollisionWithToken() {
        for (token in spawnManager.getTokenPool()) {
            if (token.isActiveInGame() && player.getCollisionBounds().overlaps(token.getCollisionBounds())) {
                token.setCollectedInGame(true)
                hud.setScore(token.getScorePoints())
            }
        }
    }

    private fun handleCollisionWithEnemy() {
        for (enemy in spawnManager.getEnemyPool()) {
            if (enemy.isActiveInGame() && player.getCollisionBounds().overlaps(enemy.getCollisionBounds())) {
                enemy.setDestroyed()
                player.loseLife()
                hud.setLife(player.getLife())
            }
        }
    }
}