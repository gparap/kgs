package gparap.games.falling.managers

import gparap.games.falling.HUD
import gparap.games.falling.player.Player

/**
 * This manager class is responsible for handling the collisions of the player with the other game objects
 */
class CollisionManager(private val player: Player, private val spawnManager: SpawnManager, private val hud: HUD) {
    fun update() {
        handleCollisionWithToken()
    }

    private fun handleCollisionWithToken() {
        for (token in spawnManager.getTokenPool()) {
            if (token.isActiveInGame() && player.getCollisionBounds().overlaps(token.getCollisionBounds())) {
                token.setCollectedInGame(true)
                hud.setScore(token.getScorePoints())
            }
        }
    }
}