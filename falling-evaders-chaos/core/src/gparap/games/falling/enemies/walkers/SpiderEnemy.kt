/*******************************
 * Katocheánian Gaming Studios *
 * Falling Evaders Chaos       *
 * created by gparap           *
 *******************************/
package gparap.games.falling.enemies.walkers

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Array
import gparap.games.falling.enemies.Enemy
import gparap.games.falling.enemies.EnemyState
import gparap.games.falling.enemies.EnemyType
import gparap.games.falling.utils.GameConstants
import gparap.games.falling.utils.GameUtils

class SpiderEnemy(enemySprite: Sprite) : Enemy() {

    init {
        super.init(EnemyType.WALKER, enemySprite)

        //create walking animations (left/right)
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
            setFalling(delta)

            //don't fall of the ground
            if (sprite.y < GameUtils.getGroundZero()) {
                setGroundedPosition()
                setGroundedState()
                moveSideways(delta)
            }

            //animate walking enemy
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
            frames.add(Texture(GameConstants.ENEMY_SPIDER_LEFT))
            frames.add(Texture(GameConstants.ENEMY_SPIDER_WALK1_LEFT))
            frames.add(Texture(GameConstants.ENEMY_SPIDER_WALK2_LEFT))
            return frames
        }
        if (isFacingRight) {
            frames.add(Texture(GameConstants.ENEMY_SPIDER_RIGHT))
            frames.add(Texture(GameConstants.ENEMY_SPIDER_WALK1_RIGHT))
            frames.add(Texture(GameConstants.ENEMY_SPIDER_WALK2_RIGHT))
            return frames
        }
        return null
    }
}