/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling.player

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.Array

class PlayerAnimation(private val filePath: String) {
    private val friend: String
    private val framesLeft: Array<Texture>?
    private val framesRight: Array<Texture>?

    init {
        friend = getFriendFromFilePath()
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
            frames.add(Texture("friends/animations/" + friend + "/" + friend + "_walk1_left.png"))
            frames.add(Texture("friends/animations/" + friend + "/" + friend + "_walk2_left.png"))
            return frames
        }
        if (isFacingRight) {
            frames.add(Texture("friends/animations/" + friend + "/" + friend + "_walk1_right.png"))
            frames.add(Texture("friends/animations/" + friend + "/" + friend + "_walk2_right.png"))
            return frames
        }
        return null
    }

    private fun getFriendFromFilePath(): String {
        var friend = filePath
        friend = friend.substringAfter("/")
        friend = friend.substringBefore(".")
        return friend
    }
}
