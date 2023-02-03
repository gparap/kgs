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
import gparap.games.falling.debris.DebrisObject
import gparap.games.falling.utils.GameConstants

/**
 * This manager class is responsible for the lifecycle of debris in the game
 */
class DebrisManager {
    private var debrisList: MutableList<Debris> = ArrayList()
    private lateinit var sprite: Sprite
    private var sprites: MutableList<Sprite> = ArrayList()

    init {
        createDebrisSprites()
    }

    fun createDebris(): Debris {
        //set the debris sprite
        randomizeDebrisSprite()

        //create the debris
        val debris = DebrisObject(sprite)

        //add the debris to active debris list
        debris.setActiveInGame(false)
        debrisList.add(debris)

        return debris
    }

    fun updateDebris(delta: Float) {
        for (debris in debrisList) {
            if (debris.isActiveInGame()) {
                debris.update(delta)
            }
            //debris hits the player
            if (debris.isHitInGame()) {
                debris.setActiveInGame(false)
                debris.randomizePosition()
                debris.setHitInGame(false)
            }
        }
    }

    fun drawDebris(spriteBatch: SpriteBatch) {
        for (debris in debrisList) {
            if (debris.isActiveInGame()) {
                debris.draw(spriteBatch)
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
        sprites.add(Sprite(Texture(GameConstants.DEBRIS_SPIKES_LONG)))
        sprites.add(Sprite(Texture(GameConstants.DEBRIS_SPIKES_SHORT)))
        sprites.add(Sprite(Texture(GameConstants.DEBRIS_WEIGHT)))
    }
}