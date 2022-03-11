/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling.tokens

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.RandomXS128
import com.badlogic.gdx.math.Vector2
import gparap.games.falling.GameConstants
import java.util.*
import kotlin.properties.Delegates

abstract class Token(protected val sprite: Sprite) {
    protected var position = Vector2(0F, 480F)
    protected var speed by Delegates.notNull<Float>()
    protected var isActive = false

    abstract fun isActiveInGame(): Boolean
    abstract fun setActiveInGame(active: Boolean)
    abstract fun update(delta: Float)

    open fun draw(spriteBatch: SpriteBatch) {
        sprite.draw(spriteBatch)
    }

    open fun randomizeSpeed(maxSpeed: Float) {
        RandomXS128()
        speed = Random().nextFloat() * (maxSpeed - GameConstants.TOKEN_MIN_SPEED) + GameConstants.TOKEN_MIN_SPEED
    }
}