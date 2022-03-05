/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling.tokens

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2

open class Token(protected val sprite: Sprite) {
    protected var position = Vector2(0F, 0F)

    open fun update(delta: Float) {
        position.y -= 1F + delta
        sprite.y = position.y
    }

    open fun draw(spriteBatch: SpriteBatch) {
        sprite.draw(spriteBatch)
    }
}