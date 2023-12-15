/*******************************
 * Katocheánian Gaming Studios *
 * Falling Evaders Chaos       *
 * created by gparap           *
 *******************************/
package gparap.games.falling.utils

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Rectangle

object GameUtils {

    /** Returns the name of a friend sprite from its filepath. */
    fun getFriendFromFilePath(filePath: String): String {
        var friend = filePath
        friend = friend.substringAfter("/")
        friend = friend.substringBefore(".")
        return friend
    }

    /** Returns the collision boundaries for a sprite. */
    fun getCollisionBounds(sprite: Sprite): Rectangle {
        val rectangle = Rectangle()
        rectangle.width = sprite.width - (sprite.width / GameConstants.COLLISION_RECT_BOUNDS_SCALE_FACTOR)
        rectangle.height = sprite.height - (sprite.height / GameConstants.COLLISION_RECT_BOUNDS_SCALE_FACTOR)
        rectangle.setPosition(sprite.x, sprite.y)
        return rectangle
    }

    /** Returns the game scale factor for the target device. */
    fun getScaleFactor() : Float {
        var width: Int = 0
        var height:Int = 0
        var scaleFactor = 0f

        //get device dimensions
        width = Gdx.graphics.width
        height = Gdx.graphics.height

        //compute scale factor based on design height
        scaleFactor = height / GameConstants.GAME_DESIGN_HEIGHT

        return scaleFactor
    }

    /** Return the ground zero point, scaled for all devices. */
    fun getGroundZero(): Float {
        return GameConstants.GROUND_ZERO * GameUtils.getScaleFactor()
    }

}