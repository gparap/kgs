/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling.managers

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import gparap.games.falling.GameConstants

/**
 * This manager class is responsible for the lifecycle of an enemy in the game
 */
class EnemyManager {
    private var flyers: MutableList<Sprite> = ArrayList()
    private var jumpers: MutableList<Sprite> = ArrayList()
    private var walkers: MutableList<Sprite> = ArrayList()
    private var crawlers: MutableList<Sprite> = ArrayList()

    init {
        createEnemySprites()
    }

    private fun createEnemySprites() {
        //create the enemy flyers sprites and add them to their list
        flyers.add(Sprite(Texture(GameConstants.ENEMY_BAT)))
        flyers.add(Sprite(Texture(GameConstants.ENEMY_BEE)))

        //create the enemy jumper sprites and add them to their list
        jumpers.add(Sprite(Texture(GameConstants.ENEMY_BLOCKER)))
        jumpers.add(Sprite(Texture(GameConstants.ENEMY_FROG)))

        //create the enemy walker sprites and add them to their list
        walkers.add(Sprite(Texture(GameConstants.ENEMY_SNAKE)))
        walkers.add(Sprite(Texture(GameConstants.ENEMY_SPIDER)))

        //create the enemy crawler sprites and add them to their list
        crawlers.add(Sprite(Texture(GameConstants.ENEMY_SLIME_WALK1)))
        crawlers.add(Sprite(Texture(GameConstants.ENEMY_SNAIL_WALK1)))
        crawlers.add(Sprite(Texture(GameConstants.ENEMY_SNAKE)))
    }
}