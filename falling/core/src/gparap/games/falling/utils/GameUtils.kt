/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling.utils

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

}