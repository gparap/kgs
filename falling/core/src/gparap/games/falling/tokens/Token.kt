/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling.tokens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.RandomXS128
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import gparap.games.falling.GameConstants
import java.util.*
import kotlin.properties.Delegates

abstract class Token(private val sprite: Sprite) {
    private var position = Vector2(-100F, -100F)
    protected var speed by Delegates.notNull<Float>()
    protected var isActive = false
    protected var isCollected = false
    protected lateinit var tokenType: TokenType

    abstract fun isActiveInGame(): Boolean
    abstract fun setActiveInGame(active: Boolean)

    abstract fun isCollectedInGame(): Boolean
    abstract fun setCollectedInGame(collected: Boolean)

    abstract fun getScorePoints(): Int

    open fun update(delta: Float) {
        //token is falling
        position.y -= speed + delta
        sprite.y = position.y

        //token reaches the ground
        if (position.y < 0 - sprite.height) {
            setActiveInGame(false)
            randomizePosition()
            when (tokenType) {
                TokenType.GEM -> randomizeSpeed(GameConstants.TOKEN_GEM_MAX_SPEED)
                TokenType.COIN -> randomizeSpeed(GameConstants.TOKEN_COIN_MAX_SPEED)
                TokenType.STAR -> randomizeSpeed(GameConstants.TOKEN_STAR_MAX_SPEED)
            }
        }
    }

    open fun draw(spriteBatch: SpriteBatch) {
        sprite.draw(spriteBatch)
    }

    open fun randomizeSpeed(maxSpeed: Float) {
        speed = Random().nextFloat() * (maxSpeed - GameConstants.TOKEN_MIN_SPEED) + GameConstants.TOKEN_MIN_SPEED
    }

    /* Randomizes X position (x > 0 && x < screen_width - token_width) */
    open fun randomizePosition() {
        val random = RandomXS128().nextInt((Gdx.graphics.width - sprite.width).toInt())
        position = Vector2(random.toFloat(), Gdx.graphics.height.toFloat())
        sprite.setPosition(position.x, position.y)
    }

    /* Returns the collision boundaries for this token */
    open fun getCollisionBounds(): Rectangle {
        val rectangle = Rectangle()
        rectangle.width = sprite.width - (sprite.width / 10)
        rectangle.height = sprite.height - (sprite.height / 10)
        rectangle.setPosition(sprite.x, sprite.y)
        return rectangle
    }
}