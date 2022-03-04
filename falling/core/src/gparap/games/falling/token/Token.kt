/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling.token

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.RandomXS128
import com.badlogic.gdx.math.Vector2
import gparap.games.falling.GameConstants

class Token {
    private var token: Sprite? = null
    private var tokens: MutableList<Sprite> = ArrayList()
    private var position = Vector2(400F, 240F)

    init {
        //create the gem sprites and add them to the list
        tokens.add(Sprite(Texture(GameConstants.TOKEN_GEM_BLUE)))
        tokens.add(Sprite(Texture(GameConstants.TOKEN_GEM_GREEN)))
        tokens.add(Sprite(Texture(GameConstants.TOKEN_GEM_RED)))
        tokens.add(Sprite(Texture(GameConstants.TOKEN_GEM_YELLOW)))

        //set up the active token
        token = getTokenAtRandom()
        token?.setPosition(position.x, position.y)
    }

    fun update(delta: Float) {
        position.y -= 1F + delta
        token?.y = position.y
    }

    fun draw(spriteBatch: SpriteBatch) {
        token?.draw(spriteBatch)
    }

    //Randomizes one of the tokens to be the active token
    private fun getTokenAtRandom(): Sprite {
        val seed = RandomXS128()
        val random = seed.nextInt(GameConstants.TOKENS_COUNT)
        return tokens[random]
    }
}