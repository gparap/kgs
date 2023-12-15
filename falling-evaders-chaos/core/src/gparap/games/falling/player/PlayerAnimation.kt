/*******************************
 * Katocheánian Gaming Studios *
 * Falling Evaders Chaos       *
 * created by gparap           *
 *******************************/
package gparap.games.falling.player

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.Array
import gparap.games.falling.utils.GameConstants.PLAYER_ANIMATION_PATH
import gparap.games.falling.utils.GameConstants.PLAYER_ANIMATION_WALK1_LEFT
import gparap.games.falling.utils.GameConstants.PLAYER_ANIMATION_WALK1_RIGHT
import gparap.games.falling.utils.GameConstants.PLAYER_ANIMATION_WALK2_LEFT
import gparap.games.falling.utils.GameConstants.PLAYER_ANIMATION_WALK2_RIGHT
import gparap.games.falling.utils.GameUtils

class PlayerAnimation(filePath: String) {
    private val friend: String?
    private val framesLeft: Array<Texture>?
    private val framesRight: Array<Texture>?

    init {
        friend = GameUtils.getFriendFromFilePath(filePath)
        framesLeft = createAnimationFrames(isFacingLeft = true)
        framesRight = createAnimationFrames(isFacingRight = true)
    }

    fun getAnimationFrames(isFacingLeft: Boolean = false, isFacingRight: Boolean = false): Array<Texture>? {
        if (isFacingLeft) return framesLeft
        if (isFacingRight) return framesRight
        return null
    }

    /* Creates a list of textures containing the animation frames for the current friend */
    private fun createAnimationFrames(
        isFacingLeft: Boolean = false,
        isFacingRight: Boolean = false
    ): Array<Texture>? {
        val frames = Array<Texture>()
        if (isFacingLeft) {
            frames.add(Texture("$PLAYER_ANIMATION_PATH$friend/$friend$PLAYER_ANIMATION_WALK1_LEFT"))
            frames.add(Texture("$PLAYER_ANIMATION_PATH$friend/$friend$PLAYER_ANIMATION_WALK2_LEFT"))
            return frames
        }
        if (isFacingRight) {
            frames.add(Texture("$PLAYER_ANIMATION_PATH$friend/$friend$PLAYER_ANIMATION_WALK1_RIGHT"))
            frames.add(Texture("$PLAYER_ANIMATION_PATH$friend/$friend$PLAYER_ANIMATION_WALK2_RIGHT"))
            return frames
        }
        return null
    }
}
