/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling.enemies.flyers

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import gparap.games.falling.utils.GameConstants
import gparap.games.falling.enemies.Enemy
import gparap.games.falling.enemies.EnemyState
import gparap.games.falling.enemies.EnemyType

class BatEnemy(enemySprite: Sprite) : Enemy() {

    init {
        speed = 1.33F
        enemyType = EnemyType.FLYER
        sprite = enemySprite
        position = randomizePosition(sprite.width)
        sprite.setPosition(position.x, position.y)
    }

    override fun isActiveInGame(): Boolean {
        return isActive
    }

    override fun setActiveInGame(active: Boolean) {
        isActive = active
    }

    override fun update(delta: Float) {
        if (isActive) {
            //enemy is falling
            position.y -= speed + delta
            sprite.y = position.y

            //fly above the ground
            if (sprite.y < GameConstants.GROUND_ZERO + sprite.height * 2) {
                sprite.y = GameConstants.GROUND_ZERO + sprite.height * 2
                position.y = GameConstants.GROUND_ZERO + sprite.height * 2
                if (enemyState == EnemyState.FALLING) {
                    enemyState = EnemyState.IDLE
                }
                super.moveSideways(delta)
            }
        }
    }

    override fun draw(spriteBatch: SpriteBatch) {
        sprite.draw(spriteBatch)
    }
}