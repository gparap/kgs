/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling.enemies.jumpers

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.RandomXS128
import gparap.games.falling.utils.GameConstants
import gparap.games.falling.enemies.Enemy
import gparap.games.falling.enemies.EnemyState
import gparap.games.falling.enemies.EnemyType
import gparap.games.falling.enemies.MovementDirection

class FrogEnemy(enemySprite: Sprite) : Enemy() {
    private var isAbleToJump = false
    private var jumpDirection = MovementDirection.LEFT
    private var spriteLeft = GameConstants.ENEMY_FROG_LEFT
    private var spriteRight = GameConstants.ENEMY_FROG_RIGHT
    private var spriteJumpLeft = GameConstants.ENEMY_FROG_JUMP_LEFT
    private var spriteJumpRight = GameConstants.ENEMY_FROG_JUMP_RIGHT

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

                //randomize in which direction the enemy will jump
                val random = RandomXS128().nextInt(2)
                jumpDirection = if (random == 0) {
                    MovementDirection.LEFT
                } else {
                    MovementDirection.RIGHT
                }
            }

            if (isAbleToJump) {
                //jump enemy left or right
                if (jumpDirection == MovementDirection.LEFT) {
                    position.x -= speed + delta
                    sprite.x = position.x
                    sprite.texture = Texture(spriteJumpLeft)
                } else {
                    position.x += speed + delta
                    sprite.x = position.x
                    sprite.texture = Texture(spriteJumpRight)
                }
            }

            //handle the enemy when is off-screen
            if ((sprite.x > Gdx.graphics.width) || (sprite.x + sprite.width < 0)) {
                setDestroyed()
            }

            //don't fall of the ground
            if (sprite.y < GameConstants.GROUND_ZERO) {
                enemyState = EnemyState.FALLING
                sprite.y = GameConstants.GROUND_ZERO
                position.y = GameConstants.GROUND_ZERO

                //animate slightly enemy jump
                if (jumpDirection == MovementDirection.LEFT) {
                    sprite.texture = Texture(spriteLeft)
                } else {
                    sprite.texture = Texture(spriteRight)
                }

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