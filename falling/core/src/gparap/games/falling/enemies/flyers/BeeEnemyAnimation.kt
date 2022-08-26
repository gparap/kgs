/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling.enemies.flyers

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.Array
import gparap.games.falling.utils.GameConstants

class BeeEnemyAnimation() {
    private val framesLeft: Array<Texture>?
    private val framesRight: Array<Texture>?

    init {
        framesLeft = createAnimationFrames(isFacingLeft = true)
        framesRight = createAnimationFrames(isFacingRight = true)
    }

    fun getAnimationFrames(isFacingLeft: Boolean = false, isFacingRight: Boolean = false): Array<Texture>? {
        if (isFacingLeft) return framesLeft
        if (isFacingRight) return framesRight
        return null
    }

    /* Creates a list of textures containing the animation frames for the bee */
    private fun createAnimationFrames(
        isFacingLeft: Boolean = false,
        isFacingRight: Boolean = false
    ): Array<Texture>? {
        val frames = Array<Texture>()
        if (isFacingLeft) {
            frames.add(Texture(GameConstants.ENEMY_BEE))
            frames.add(Texture(GameConstants.ENEMY_BEE_FLY))
            return frames
        }
        if (isFacingRight) {
            frames.add(Texture(GameConstants.ENEMY_BEE_RIGHT))
            frames.add(Texture(GameConstants.ENEMY_BEE_FLY_RIGHT))
            return frames
        }
        return null
    }
}
