/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling.debris

import com.badlogic.gdx.graphics.g2d.Sprite
import gparap.games.falling.utils.GameConstants

class DebrisObject (sprite: Sprite) : Debris(sprite) {

    init {
        super.randomizeSpeed(GameConstants.DEBRIS_MAX_SPEED)
        sprite.setPosition(GameConstants.OFF_SCREEN_X, GameConstants.OFF_SCREEN_Y)
    }

    override fun isActiveInGame(): Boolean {
        return isActive
    }

    override fun setActiveInGame(active: Boolean) {
        isActive = active
    }

    override fun isHitInGame(): Boolean {
        return isHit
    }

    override fun setHitInGame(hit: Boolean) {
        isHit = hit
    }
}