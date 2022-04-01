/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling.enemies.jumpers

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import gparap.games.falling.GameConstants
import gparap.games.falling.enemies.Enemy
import gparap.games.falling.enemies.EnemyType

class FrogEnemy(enemySprite: Sprite) : Enemy() {

    init {
        speed = 1.33F
        enemyType = EnemyType.JUMPER
        sprite = enemySprite
        sprite.setPosition(GameConstants.OFF_SCREEN_X, GameConstants.OFF_SCREEN_Y)
    }

    override fun isActiveInGame(): Boolean {
        return isActive
    }

    override fun setActiveInGame(active: Boolean) {
        isActive = active
    }

    override fun update(delta: Float) {
        if (isActive) {
            //enemy is falling
            position.y -= speed + delta
            sprite.y = position.y

            //don't fall of the ground
            if (sprite.y < GameConstants.GROUND_ZERO) {
                sprite.y = GameConstants.GROUND_ZERO
            }
        }
    }

    override fun draw(spriteBatch: SpriteBatch) {
        sprite.draw(spriteBatch)
    }
}