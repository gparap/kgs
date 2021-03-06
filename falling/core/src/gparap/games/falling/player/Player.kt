/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling.player

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import gparap.games.falling.GameConstants.GROUND_ZERO
import gparap.games.falling.GameConstants.PLAYER_SCALE_FACTOR
import kotlin.math.abs

class Player(filePath: String) {
    //creates player sprite based on user selection of friend
    private var sprite: Sprite = Sprite(Texture(filePath))
    private var spriteIdle: Sprite = Sprite(Texture(filePath))
    private val speed = 7.5F
    private var state: PlayerState
    private var velocity = 0F
    private var velocityUpdateFactor = 1.5F
    private var velocityUpdateMax = velocityUpdateFactor * 10
    private var life = 5
    private val animationLeft: Animation<Texture>
    private val animationRight: Animation<Texture>
    private var facing: PlayerFacing
    private var stateTime = 0f
    private var frameDuration = 0.1f

    fun getLife(): Int {
        return life
    }

    init {
        sprite.setPosition(0F, GROUND_ZERO)
        sprite.setSize(sprite.width * PLAYER_SCALE_FACTOR, sprite.height * PLAYER_SCALE_FACTOR)

        state = PlayerState.IDLE
        facing = PlayerFacing.NONE

        //create player walking animations (left/right)
        animationLeft = Animation(frameDuration, PlayerAnimation(filePath).getAnimationFrames(isFacingLeft = true))
        animationRight = Animation(frameDuration, PlayerAnimation(filePath).getAnimationFrames(isFacingRight = true))
    }

    fun update(delta: Float) {
        updatePlayerMovement(delta)
        updatePlayerState()
        checkIfPlayerShouldJump()
        updatePlayerJumping(delta)
        updateSpriteTexture()
    }

    fun draw(spriteBatch: SpriteBatch) {
        sprite.draw(spriteBatch)
    }

    private fun updateSpriteTexture() {
        //player is idle
        if (state == PlayerState.IDLE) {
            sprite.texture = spriteIdle.texture
            stateTime = 0F

            //player is walking
        } else {
            if (state == PlayerState.WALK) {
                if (facing == PlayerFacing.LEFT) {
                    sprite.texture = animationLeft.getKeyFrame(stateTime, true)
                } else if (facing == PlayerFacing.RIGHT) {
                    sprite.texture = animationRight.getKeyFrame(stateTime, true)
                }

                //increase the amount of seconds player has spent in current animation state
                stateTime += frameDuration
            }
        }
    }

    private fun updatePlayerJumping(delta: Float) {
        //handle jumping of falling
        if (state == PlayerState.JUMP) {
            velocity += velocityUpdateFactor
            sprite.y += velocity + delta

        } else if (state == PlayerState.FALL) {
            velocity -= velocityUpdateFactor
            sprite.y += velocity + delta
        }

        //don't fall of the ground
        if (sprite.y < GROUND_ZERO) {
            sprite.y = GROUND_ZERO
            state = PlayerState.WALK
        }

        //reset velocity
        if (abs(velocity) > velocityUpdateMax) {
            velocity = 0F

            //reverse player state
            if (state == PlayerState.JUMP) {
                state = PlayerState.FALL
            }
        }
    }

    private fun checkIfPlayerShouldJump() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && ((state != PlayerState.JUMP) && (state != PlayerState.FALL))) {
            state = PlayerState.JUMP
            sprite.y = GROUND_ZERO
        }
    }

    private fun updatePlayerState() {
        if (sprite.y == GROUND_ZERO) {
            state = PlayerState.IDLE
            if (Gdx.input.isTouched) {
                state = PlayerState.WALK
            }
        } else if (sprite.y > GROUND_ZERO && velocity > 0) {
            state = PlayerState.JUMP
        } else if (sprite.y > GROUND_ZERO && velocity < 0) {
            state = PlayerState.FALL
        }
    }

    private fun updatePlayerMovement(delta: Float) {
        if (Gdx.input.isTouched) {
            //right
            if (Gdx.input.x > sprite.x + sprite.width) {
                facing = PlayerFacing.RIGHT
                sprite.x += speed + delta
                //keep inside screen
                if (sprite.x + sprite.width > Gdx.graphics.width) {
                    sprite.x = Gdx.graphics.width - sprite.width
                }
            }
            //left
            else if (Gdx.input.x < sprite.x) {
                facing = PlayerFacing.LEFT
                sprite.x -= speed + delta
                //keep inside screen
                if (sprite.x < 0) {
                    sprite.x = 0F
                }
            }
            //idle
            else {
                facing = PlayerFacing.NONE
            }
        }
    }

    /**
     * Returns the collision boundaries for the player
     */
    fun getCollisionBounds(): Rectangle {
        val rectangle = Rectangle()
        rectangle.width = sprite.width
        rectangle.height = sprite.height
        rectangle.width = sprite.width - (sprite.width / 10F)
        rectangle.height = sprite.height - (sprite.height / 10F)
        rectangle.setPosition(sprite.x, sprite.y)
        return rectangle
    }

    /**
     * Makes the player lose 1 life
     */
    fun loseLife() {
        life -= 1

        //don't let life go bellow zero
        if (life < 0) {
            life = 0
        }
    }
}