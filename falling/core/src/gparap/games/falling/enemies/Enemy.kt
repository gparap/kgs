package gparap.games.falling.enemies

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.RandomXS128
import com.badlogic.gdx.math.Vector2
import gparap.games.falling.GameConstants
import kotlin.properties.Delegates

abstract class Enemy {
    protected var position = Vector2(GameConstants.OFF_SCREEN_X, GameConstants.OFF_SCREEN_Y)
    protected var speed by Delegates.notNull<Float>()
    protected var isActive = false
    protected lateinit var enemyType: EnemyType
    protected lateinit var sprite: Sprite

    abstract fun isActiveInGame(): Boolean
    abstract fun setActiveInGame(active: Boolean)

    abstract fun update(delta: Float)
    abstract fun draw(spriteBatch: SpriteBatch)

    /**
     * Randomizes X position
     *
     * (x > 0 && x < screen_width - enemy_width)
     */
    fun randomizePosition(spriteWidth: Float) : Vector2 {
        val random = RandomXS128().nextInt((Gdx.graphics.width - spriteWidth).toInt())
        return Vector2(random.toFloat(), Gdx.graphics.height.toFloat())
        //sprite.setPosition(position.x, position.y)
    }
}