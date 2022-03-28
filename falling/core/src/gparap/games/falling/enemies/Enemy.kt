package gparap.games.falling.enemies

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.RandomXS128
import com.badlogic.gdx.math.Vector2
import gparap.games.falling.GameConstants
import kotlin.properties.Delegates

abstract class Enemy(private var sprite: Sprite) {
    private var position = Vector2(GameConstants.OFF_SCREEN_X, GameConstants.OFF_SCREEN_Y)
    protected var speed by Delegates.notNull<Float>()
    protected var isActive = false
    protected lateinit var enemyType: EnemyType

    abstract fun isActiveInGame(): Boolean
    abstract fun setActiveInGame(active: Boolean)

    init {
        //choose a random enemy sprite
        when (RandomXS128().nextInt(GameConstants.ENEMIES_TYPES_COUNT)) {
            0 -> sprite = Sprite(Texture(GameConstants.ENEMY_BAT))
            1 -> sprite = Sprite(Texture(GameConstants.ENEMY_BEE))
            2 -> sprite = Sprite(Texture(GameConstants.ENEMY_BLOCKER))
            3 -> sprite = Sprite(Texture(GameConstants.ENEMY_SLIME_WALK1))
            4 -> sprite = Sprite(Texture(GameConstants.ENEMY_SNAIL_WALK1))
            5 -> sprite = Sprite(Texture(GameConstants.ENEMY_SNAKE))
        }

        //set the enemy at a temporary position and speed
        randomizePosition()
        //position= Vector2(GameConstants.OFF_SCREEN_X, GameConstants.OFF_SCREEN_Y)
        //sprite.setPosition(position.x, position.y)
        speed = 1F
    }

    fun update(delta: Float) {
        if (isActive) {
            //enemy is falling
            position.y -= speed + delta
            sprite.y = position.y

            //don't fall of the ground
            if (sprite.y < GameConstants.GROUND_ZERO) {
                sprite.y = GameConstants.GROUND_ZERO
            }
        }
    }

    fun draw(spriteBatch: SpriteBatch) {
        sprite.draw(spriteBatch)
    }

    /* Randomizes X position (x > 0 && x < screen_width - enemy_width) */
    open fun randomizePosition() {
        val random = RandomXS128().nextInt((Gdx.graphics.width - sprite.width).toInt())
        position = Vector2(random.toFloat(), Gdx.graphics.height.toFloat())
        sprite.setPosition(position.x, position.y)
    }
}