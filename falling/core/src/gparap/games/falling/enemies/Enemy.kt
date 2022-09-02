/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling.enemies

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.RandomXS128
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array
import gparap.games.falling.utils.GameConstants
import kotlin.properties.Delegates

abstract class Enemy {
    protected var position = Vector2(GameConstants.OFF_SCREEN_X, GameConstants.OFF_SCREEN_Y)
    protected var speed by Delegates.notNull<Float>()
    protected var isActive = false
    protected lateinit var enemyType: EnemyType
    protected lateinit var sprite: Sprite
    protected var enemyState: EnemyState = EnemyState.FALLING
    private var movementDirection: MovementDirection = MovementDirection.LEFT
    protected var animationLeft: Animation<Texture>? = null
    protected var animationRight: Animation<Texture>? = null
    protected var framesLeft: Array<Texture>? = null
    protected var framesRight: Array<Texture>? = null
    protected var stateTime = 0f
    protected var frameDuration = 0.1f

    abstract fun isActiveInGame(): Boolean
    abstract fun setActiveInGame(active: Boolean)

    abstract fun update(delta: Float)
    abstract fun draw(spriteBatch: SpriteBatch)

    /**
     * Randomizes X position
     *
     * (x > 0 && x < screen_width - enemy_width)
     */
    fun randomizePosition(spriteWidth: Float): Vector2 {
        val random = RandomXS128().nextInt((Gdx.graphics.width - spriteWidth).toInt())
        return Vector2(random.toFloat(), Gdx.graphics.height.toFloat())
    }

    /**
     *  Returns the collision boundaries for this enemy
     */
    fun getCollisionBounds(): Rectangle {
        val rectangle = Rectangle()
        rectangle.width = sprite.width - (sprite.width / 10)
        rectangle.height = sprite.height - (sprite.height / 10)
        rectangle.setPosition(sprite.x, sprite.y)
        return rectangle
    }

    /**
     * Remove enemy from the screen
     */
    fun setDestroyed() {
        isActive = false
        enemyState = EnemyState.IDLE
        position = randomizePosition(sprite.width)
        sprite.setPosition(position.x, position.y)
    }

    /**
     * Moves enemy left or right when they stop falling (a.k.a. by default entered the game)
     */
    fun moveSideways(delta: Float) {
        if (enemyState == EnemyState.IDLE) {
            enemyState = EnemyState.MOVING
            //randomize in which direction the enemy will move
            val random = RandomXS128().nextInt(2)
            movementDirection = if (random == 0) {
                MovementDirection.LEFT
            } else {
                MovementDirection.RIGHT
            }
        }

        //move enemy left or right
        if (movementDirection == MovementDirection.LEFT) {
            position.x -= speed + delta
            sprite.x = position.x
        } else {
            position.x += speed + delta
            sprite.x = position.x
        }

        //handle the enemy when is off-screen
        if (isOffScreenBoundaries()) {
            setDestroyed()
        }
    }

    /* Checks if the enemy is off-screen to the left or right */
    private fun isOffScreenBoundaries(): Boolean {
        if (sprite.x > Gdx.graphics.width) {
            return true
        }
        if (sprite.x + sprite.width < 0) {
            return true
        }
        return false
    }

    /** Returns an array that contains the animation frames based on facing. */
    fun getAnimationFrames(isFacingLeft: Boolean = false, isFacingRight: Boolean = false): Array<Texture>? {
        if (isFacingLeft) return framesLeft
        if (isFacingRight) return framesRight
        return null
    }

    /** Creates the enemy left & right animations by storing the frame duration and key frames. */
    fun createAnimations() {
        animationLeft = Animation(frameDuration, getAnimationFrames(isFacingLeft = true))
        animationRight = Animation(frameDuration, getAnimationFrames(isFacingRight = true))
    }

    /** Animates the enemy sprites based on facing. */
    fun animate() {
        //increase the amount of seconds the bat has spent in current animation state
        stateTime += frameDuration.div(GameConstants.FRAME_DURATION_DIVIDER)

        //animate
        if (movementDirection == MovementDirection.LEFT) {
            sprite.texture = animationLeft?.getKeyFrame(stateTime, true)
        } else {
            sprite.texture = animationRight?.getKeyFrame(stateTime, true)
        }
    }
}