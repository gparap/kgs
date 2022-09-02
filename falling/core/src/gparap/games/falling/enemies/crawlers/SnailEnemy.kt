/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling.enemies.crawlers

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Array
import gparap.games.falling.enemies.Enemy
import gparap.games.falling.enemies.EnemyState
import gparap.games.falling.enemies.EnemyType
import gparap.games.falling.utils.GameConstants

class SnailEnemy(enemySprite: Sprite) : Enemy() {

    init {
        speed = 1.33F
        enemyType = EnemyType.CRAWLER
        sprite = enemySprite
        position = randomizePosition(sprite.width)
        sprite.setPosition(position.x, position.y)

        //create crawling animations (left/right)
        framesLeft = createAnimationFrames(isFacingLeft = true)
        framesRight = createAnimationFrames(isFacingRight = true)
        createAnimations()
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

            //don't fall of the ground
            if (sprite.y < GameConstants.GROUND_ZERO) {
                sprite.y = GameConstants.GROUND_ZERO
                position.y = GameConstants.GROUND_ZERO
                if (enemyState == EnemyState.FALLING) {
                    enemyState = EnemyState.IDLE
                    stateTime = 0F
                }
                super.moveSideways(delta)
            }

            //animate crawling enemy
            if (enemyState == EnemyState.MOVING) {
                animate()
            }
        }
    }

    override fun draw(spriteBatch: SpriteBatch) {
        sprite.draw(spriteBatch)
    }

    /* Creates an array of textures that contains the animation frames for this enemy */
    private fun createAnimationFrames(
        isFacingLeft: Boolean = false,
        isFacingRight: Boolean = false
    ): Array<Texture>? {
        val frames = Array<Texture>()
        if (isFacingLeft) {
            frames.add(Texture(GameConstants.ENEMY_SNAIL_WALK1_LEFT))
            frames.add(Texture(GameConstants.ENEMY_SNAIL_WALK2_LEFT))
            return frames
        }
        if (isFacingRight) {
            frames.add(Texture(GameConstants.ENEMY_SNAIL_WALK1_RIGHT))
            frames.add(Texture(GameConstants.ENEMY_SNAIL_WALK2_RIGHT))
            return frames
        }
        return null
    }
}