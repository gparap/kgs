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
import gparap.games.falling.utils.GameUtils
import kotlin.properties.Delegates

abstract class Enemy {
    protected var position = Vector2(GameConstants.OFF_SCREEN_X, GameConstants.OFF_SCREEN_Y)
    protected var playerPosition = Vector2(0f, 0f)
    protected var speed by Delegates.notNull<Float>()
    protected var isActive = false
    protected lateinit var enemyType: EnemyType
    protected lateinit var sprite: Sprite
    protected var enemyState: EnemyState = EnemyState.FALLING
    private var facingDirection: FacingDirection = FacingDirection.LEFT
    private var animationLeft: Animation<Texture>? = null
    private var animationRight: Animation<Texture>? = null
    protected var framesLeft: Array<Texture>? = null
    protected var framesRight: Array<Texture>? = null
    private var stateTime = 0f
    private var frameDuration = 0.1f

    abstract fun isActiveInGame(): Boolean
    abstract fun setActiveInGame(active: Boolean)

    abstract fun update(delta: Float)
    abstract fun draw(spriteBatch: SpriteBatch)

    fun init(type: EnemyType, sprite: Sprite) {
        speed = GameConstants.ENEMY_SPEED_DEFAULT * GameUtils.getScaleFactor()
        enemyType = type
        this.sprite = sprite
        this.sprite.setScale(GameUtils.getScaleFactor())
        position = randomizePosition(sprite.width)
        this.sprite.setPosition(position.x, position.y)
    }

    /** Handles the enemy positioning when it reaches or approaches the ground */
    fun setGroundedPosition() {
        if (enemyType == EnemyType.CRAWLER || enemyType == EnemyType.JUMPER || enemyType == EnemyType.WALKER) {
            sprite.y = GameUtils.getGroundZero()
            position.y = GameUtils.getGroundZero()

        } else if (enemyType == EnemyType.FLYER) {
            sprite.y = GameUtils.getGroundZero() + sprite.height * GameUtils.getScaleFactor() * 2
            position.y = GameUtils.getGroundZero() + sprite.height * GameUtils.getScaleFactor() * 2
        }
    }

    /** Handles the enemy state when it reaches or approaches the ground */
    fun setGroundedState() {
        if (enemyState == EnemyState.FALLING) {
            enemyState = EnemyState.IDLE
            stateTime = 0F
        }
    }

    /** Makes enemy falling downwards simulating gravity */
    fun setFalling(delta: Float) {
        position.y -= speed + delta
        sprite.y = position.y
    }

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
        return GameUtils.getCollisionBounds(sprite)
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
            //set which direction the enemy will move to
            facingDirection = if (position.x > playerPosition.x) {
                FacingDirection.LEFT
            } else {
                FacingDirection.RIGHT
            }
        }

        //move enemy left or right
        if (facingDirection == FacingDirection.LEFT) {
            moveLeft(delta)
        } else {
            moveRight(delta)
        }

        //handle the enemy when is off-screen
        if (isOffScreenBoundaries()) {
            setDestroyed()
        }
    }

    /** Moves enemy left */
    fun moveLeft(delta: Float) {
        position.x -= speed + delta
        sprite.x = position.x
    }

    /** Moves enemy right */
    fun moveRight(delta: Float) {
        position.x += speed + delta
        sprite.x = position.x
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
    private fun getAnimationFrames(isFacingLeft: Boolean = false, isFacingRight: Boolean = false): Array<Texture>? {
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
        stateTime += frameDuration.div(GameConstants.ENEMY_FRAME_DURATION_DIVIDER)

        //animate
        if (facingDirection == FacingDirection.LEFT) {
            sprite.texture = animationLeft?.getKeyFrame(stateTime, true)
        } else {
            sprite.texture = animationRight?.getKeyFrame(stateTime, true)
        }
    }

    /** Synchronizes its information about the player's current position in the game. */
    fun syncPlayerPosition(position: Vector2) {
        playerPosition = position
    }
}