/*******************************
 * Katocheánian Gaming Studios *
 * Falling Evaders Chaos       *
 * created by gparap           *
 *******************************/
package gparap.games.falling.enemies.jumpers

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import gparap.games.falling.enemies.Enemy
import gparap.games.falling.enemies.EnemyState
import gparap.games.falling.enemies.EnemyType
import gparap.games.falling.enemies.FacingDirection
import gparap.games.falling.utils.GameConstants
import gparap.games.falling.utils.GameUtils

class FrogEnemy(enemySprite: Sprite) : Enemy() {
    private var isAbleToJump = false
    private var jumpDirection = FacingDirection.LEFT
    private var spriteLeft = GameConstants.ENEMY_FROG_LEFT
    private var spriteRight = GameConstants.ENEMY_FROG_RIGHT
    private var spriteJumpLeft = GameConstants.ENEMY_FROG_JUMP_LEFT
    private var spriteJumpRight = GameConstants.ENEMY_FROG_JUMP_RIGHT

    init {
        super.init(EnemyType.JUMPER, enemySprite)
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

            //handle when enemy is able to jump
            if (enemyState == EnemyState.IDLE) {
                isAbleToJump = false

                //set which direction the enemy will jump to
                jumpDirection = if (position.x > playerPosition.x) {
                    FacingDirection.LEFT
                } else {
                    FacingDirection.RIGHT
                }
            }

            if (isAbleToJump) {
                //jump enemy left or right
                if (jumpDirection == FacingDirection.LEFT) {
                    moveLeft(delta)
                    sprite.texture = Texture(spriteJumpLeft)
                } else {
                    moveRight(delta)
                    sprite.texture = Texture(spriteJumpRight)
                }
            }

            //handle the enemy when is off-screen
            if ((sprite.x > Gdx.graphics.width) || (sprite.x + sprite.width < 0)) {
                setDestroyed()
            }

            //don't fall of the ground
            if (sprite.y < GameUtils.getGroundZero()) {
                enemyState = EnemyState.FALLING
                setGroundedPosition()

                //animate slightly enemy jump
                if (jumpDirection == FacingDirection.LEFT) {
                    sprite.texture = Texture(spriteLeft)
                } else {
                    sprite.texture = Texture(spriteRight)
                }

                //jump
                if (enemyState != EnemyState.JUMPING) {
                    position.y += speed * GameConstants.ENEMY_JUMP_FACTOR + delta
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