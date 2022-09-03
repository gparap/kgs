/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling.enemies.jumpers

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import gparap.games.falling.utils.GameConstants
import gparap.games.falling.enemies.Enemy
import gparap.games.falling.enemies.EnemyState
import gparap.games.falling.enemies.EnemyType

class FrogEnemy(enemySprite: Sprite) : Enemy() {
    private var isAbleToJump = false

    init {
        speed = 1.33F
        enemyType = EnemyType.JUMPER
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

            //handle when enemy is able to jump
            if (enemyState == EnemyState.IDLE) {
                isAbleToJump = false
            }
            if (isAbleToJump) {
                super.moveSideways(delta)
            }

            //don't fall of the ground
            if (sprite.y < GameConstants.GROUND_ZERO) {
                enemyState = EnemyState.FALLING
                sprite.y = GameConstants.GROUND_ZERO
                position.y = GameConstants.GROUND_ZERO

                //jump
                if (enemyState != EnemyState.JUMPING) {
                    position.y += speed * 100 + delta
                    sprite.y = position.y
                    enemyState = EnemyState.JUMPING
                    isAbleToJump = true
                }

                //end the jump
                if (enemyState == EnemyState.JUMPING) {
                    enemyState = EnemyState.FALLING
                }
            }
        }
    }

    override fun draw(spriteBatch: SpriteBatch) {
        sprite.draw(spriteBatch)
    }
}