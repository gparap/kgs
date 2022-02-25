/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import gparap.games.falling.GameConstants.PLAYER_SCALE_FACTOR

class Player(filePath: String) {
    //creates player sprite based on user selection of friend
    private var player: Sprite = Sprite(Texture(filePath))

    init {
        player.setPosition(0F, 0F)
        player.setSize(player.width*PLAYER_SCALE_FACTOR, player.height* PLAYER_SCALE_FACTOR)
    }

    fun update(delta: Float) {
        TODO("Not yet implemented - update")
    }

    fun draw(spriteBatch: SpriteBatch) {
        player.draw(spriteBatch)
    }
}