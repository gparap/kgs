/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling.player

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import gparap.games.falling.GameConstants.GROUND_ZERO
import gparap.games.falling.GameConstants.PLAYER_SCALE_FACTOR
import kotlin.math.abs

class Player(filePath: String) {
    //creates player sprite based on user selection of friend
    private var player: Sprite = Sprite(Texture(filePath))
    private val speed = 7.5F
    private var state = getPlayerState()
    private var velocity = 0F
    private var velocityUpdateFactor = 1.5F
    private var velocityUpdateMax = velocityUpdateFactor * 10

    init {
        player.setPosition(0F, GROUND_ZERO)
        player.setSize(player.width * PLAYER_SCALE_FACTOR, player.height * PLAYER_SCALE_FACTOR)
    }

    fun update(delta: Float) {
        updatePlayerMovement(delta)
        checkIfPlayerShouldJump()
        updatePlayerJumping(delta)
    }

    fun draw(spriteBatch: SpriteBatch) {
        player.draw(spriteBatch)
    }

    private fun updatePlayerJumping(delta: Float) {
        //handle jumping of falling
        if (state == PlayerState.JUMP) {
            velocity += velocityUpdateFactor
        } else if (state == PlayerState.FALL) {
            velocity -= velocityUpdateFactor
        }
        player.y += velocity + delta

        //don't fall of the ground
        if (player.y < GROUND_ZERO) {
            player.y = GROUND_ZERO
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
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && ((getPlayerState() != PlayerState.JUMP) && (getPlayerState() != PlayerState.FALL))) {
            state = PlayerState.JUMP
            player.y = GROUND_ZERO
        }
    }

    private fun getPlayerState(): PlayerState {
        if (player.y == GROUND_ZERO) {
            return PlayerState.WALK
        } else if (player.y > GROUND_ZERO && velocity > 0) {
            return PlayerState.JUMP
        } else if (player.y > GROUND_ZERO && velocity < 0) {
            return PlayerState.FALL
        }
        return PlayerState.WALK
    }

    private fun updatePlayerMovement(delta: Float) {
        if (Gdx.input.isTouched) {
            //right
            if (Gdx.input.x > player.x + player.width) {
                player.x += speed + delta
                //keep inside screen
                if (player.x + player.width > Gdx.graphics.width) {
                    player.x = Gdx.graphics.width - player.width
                }
            }
            //left
            if (Gdx.input.x < player.x) {
                player.x -= speed + delta
                //keep inside screen
                if (player.x < 0) {
                    player.x = 0F
                }
            }
        }
    }
}