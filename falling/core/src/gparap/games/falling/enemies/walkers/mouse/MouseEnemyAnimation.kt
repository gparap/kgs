/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling.enemies.walkers.mouse

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.Array
import gparap.games.falling.enemies.EnemyAnimation
import gparap.games.falling.utils.GameConstants

class MouseEnemyAnimation : EnemyAnimation() {

    init {
        framesLeft = createAnimationFrames(isFacingLeft = true)
        framesRight = createAnimationFrames(isFacingRight = true)
    }

    /* Creates an array of textures that contains the animation frames for this enemy */
    private fun createAnimationFrames(
        isFacingLeft: Boolean = false,
        isFacingRight: Boolean = false
    ): Array<Texture>? {
        val frames = Array<Texture>()
        if (isFacingLeft) {
            frames.add(Texture(GameConstants.ENEMY_MOUSE_LEFT))
            frames.add(Texture(GameConstants.ENEMY_MOUSE_WALK_LEFT))
            return frames
        }
        if (isFacingRight) {
            frames.add(Texture(GameConstants.ENEMY_MOUSE_RIGHT))
            frames.add(Texture(GameConstants.ENEMY_MOUSE_WALK_RIGHT))
            return frames
        }
        return null
    }
}
