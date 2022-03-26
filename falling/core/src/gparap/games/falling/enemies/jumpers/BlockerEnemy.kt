/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling.enemies.jumpers

import com.badlogic.gdx.graphics.g2d.Sprite
import gparap.games.falling.GameConstants
import gparap.games.falling.enemies.Enemy
import gparap.games.falling.enemies.EnemyType

class BlockerEnemy(sprite: Sprite) : Enemy(sprite) {

    init {
        speed = 1.33F
        enemyType = EnemyType.JUMPER
        sprite.setPosition(GameConstants.OFF_SCREEN_X, GameConstants.OFF_SCREEN_Y)
    }

    override fun isActiveInGame(): Boolean {
        return isActive
    }

    override fun setActiveInGame(active: Boolean) {
        isActive = active
    }
}