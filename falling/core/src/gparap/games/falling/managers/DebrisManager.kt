/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling.managers

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.RandomXS128
import gparap.games.falling.debris.Debris
import gparap.games.falling.debris.RockDebris
import gparap.games.falling.utils.GameConstants

/**
 * This manager class is responsible for the lifecycle of debris in the game
 */
class DebrisManager {
    private var debris: MutableList<Debris> = ArrayList()
    private lateinit var sprite: Sprite
    private var sprites: MutableList<Sprite> = ArrayList()

    init {
        createDebrisSprites()
    }

    fun createDebris(): Debris {
        //set the debris sprite
        randomizeDebrisSprite()

        //create the debris
        val rock = RockDebris(sprite)

        //add the debris to active debris list
        rock.setActiveInGame(false)
        debris.add(rock)

        return rock
    }

    fun updateDebris(delta: Float) {
        for (rock in debris) {
            if (rock.isActiveInGame()) {
                rock.update(delta)
            }
            //rock hits the player
            if (rock.isHitInGame()) {
                rock.setActiveInGame(false)
                rock.randomizePosition()
                rock.setHitInGame(false)
            }
        }
    }

    fun drawDebris(spriteBatch: SpriteBatch) {
        for (rock in debris) {
            if (rock.isActiveInGame()) {
                rock.draw(spriteBatch)
            }
        }
    }

    //Randomizes the sprite of the active debris
    private fun randomizeDebrisSprite() {
        val seed = RandomXS128()
        val random: Int = seed.nextInt(sprites.size)
        sprite = sprites[random]
    }

    //Creates the debris sprites and adds them to the sprite list
    private fun createDebrisSprites() {
        sprites.add(Sprite(Texture(GameConstants.DEBRIS_ROCK_LARGE_DARK)))
        sprites.add(Sprite(Texture(GameConstants.DEBRIS_ROCK_LARGE_LIGHT)))
        sprites.add(Sprite(Texture(GameConstants.DEBRIS_ROCK_SMALL_DARK)))
        sprites.add(Sprite(Texture(GameConstants.DEBRIS_ROCK_SMALL_LIGHT)))
    }
}