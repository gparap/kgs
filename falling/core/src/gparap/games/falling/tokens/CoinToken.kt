/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling.tokens

import com.badlogic.gdx.graphics.g2d.Sprite
import gparap.games.falling.GameConstants

class CoinToken(sprite: Sprite) : Token(sprite) {

    init {
        speed = 1.25F
        super.randomizeSpeed(GameConstants.TOKEN_COIN_MAX_SPEED)
    }

    override fun update(delta: Float) {
        position.y -= speed + delta
        sprite.y = position.y
    }
}