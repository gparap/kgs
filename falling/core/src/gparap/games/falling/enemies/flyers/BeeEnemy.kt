/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling.enemies.flyers

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import gparap.games.falling.utils.GameConstants
import gparap.games.falling.enemies.Enemy
import gparap.games.falling.enemies.EnemyState
import gparap.games.falling.enemies.EnemyType
import gparap.games.falling.enemies.MovementDirection

class BeeEnemy(enemySprite: Sprite) : Enemy() {
    private val animationLeft: Animation<Texture>
    private val animationRight: Animation<Texture>
    private var stateTime = 0f
    private var frameDuration = 0.1f

    init {
        speed = 1.33F
        enemyType = EnemyType.FLYER
        sprite = enemySprite
        position = randomizePosition(sprite.width)
        sprite.setPosition(position.x, position.y)

        //create flying animations (left/right)
        animationLeft = Animation(frameDuration, BeeEnemyAnimation().getAnimationFrames(isFacingLeft = true))
        animationRight = Animation(frameDuration, BeeEnemyAnimation().getAnimationFrames(isFacingRight = true))
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
                    stateTime = 0F
                }
                super.moveSideways(delta)
            }

            //increase the amount of seconds the bat has spent in current animation state
            stateTime += frameDuration / 10

            //animate
            if (movementDirection == MovementDirection.LEFT) {
                sprite.texture = animationLeft.getKeyFrame(stateTime, true)
            } else {
                sprite.texture = animationRight.getKeyFrame(stateTime, true)
            }
        }
    }

    override fun draw(spriteBatch: SpriteBatch) {
        sprite.draw(spriteBatch)
    }
}