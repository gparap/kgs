/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling.enemies

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.Array

open class EnemyAnimation {
    protected var framesLeft: Array<Texture>? = null
    protected var framesRight: Array<Texture>? = null

    /** Returns an array that contains the animation frames based on facing. */
    fun getAnimationFrames(isFacingLeft: Boolean = false, isFacingRight: Boolean = false): Array<Texture>? {
        if (isFacingLeft) return framesLeft
        if (isFacingRight) return framesRight
        return null
    }
}