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
import gparap.games.falling.utils.GameConstants
import gparap.games.falling.enemies.Enemy
import gparap.games.falling.enemies.EnemyType
import gparap.games.falling.enemies.crawlers.SlimeEnemy
import gparap.games.falling.enemies.crawlers.SnailEnemy
import gparap.games.falling.enemies.flyers.bat.BatEnemy
import gparap.games.falling.enemies.flyers.bee.BeeEnemy
import gparap.games.falling.enemies.flyers.fly.FlyEnemy
import gparap.games.falling.enemies.flyers.lady_bug.LadyBugEnemy
import gparap.games.falling.enemies.jumpers.BlockerEnemy
import gparap.games.falling.enemies.jumpers.FrogEnemy
import gparap.games.falling.enemies.walkers.MouseEnemy
import gparap.games.falling.enemies.walkers.SpiderEnemy

/**
 * This manager class is responsible for the lifecycle of an enemy in the game
 */
class EnemyManager {
    private var flyers: MutableList<Sprite> = ArrayList()
    private var jumpers: MutableList<Sprite> = ArrayList()
    private var walkers: MutableList<Sprite> = ArrayList()
    private var crawlers: MutableList<Sprite> = ArrayList()
    private lateinit var enemyType: EnemyType
    private lateinit var enemySprite: Sprite
    private var enemies: MutableList<Enemy> = ArrayList()

    init {
        createEnemySprites()
    }

    fun createEnemy(): Enemy {
        //set the enemy
        randomizeEnemyType()
        val random = randomizeEnemySprite()

        //create the enemy
        val enemy = when (enemyType) {
            EnemyType.FLYER -> {
                when (random) {
                    0 -> BatEnemy(enemySprite)
                    1 -> BeeEnemy(enemySprite)
                    2 -> FlyEnemy(enemySprite)
                    3 -> LadyBugEnemy(enemySprite)
                    else -> {
                        BatEnemy(enemySprite)
                    }
                }
            }
            EnemyType.JUMPER -> {
                if (random == 0) {
                    BlockerEnemy(enemySprite)
                } else {
                    FrogEnemy(enemySprite)
                }
            }
            EnemyType.WALKER -> {
                if (random == 0) {
                    MouseEnemy(enemySprite)
                } else {
                    SpiderEnemy(enemySprite)
                }
            }
            EnemyType.CRAWLER -> {
                if (random == 0) {
                    SlimeEnemy(enemySprite)
                } else {
                    SnailEnemy(enemySprite)
                }
            }
        }

        //add the enemy to active enemies list
        enemy.setActiveInGame(false)
        enemies.add(enemy)

        return enemy
    }

    fun updateEnemies(delta: Float) {
        for (enemy in enemies) {
            if (enemy.isActiveInGame()) {
                enemy.update(delta)
            }
        }
    }

    fun drawEnemies(spriteBatch: SpriteBatch) {
        for (enemy in enemies) {
            if (enemy.isActiveInGame()) {
                enemy.draw(spriteBatch)
            }
        }
    }

    private fun randomizeEnemySprite(): Int {
        val seed = RandomXS128()
        val random: Int
        when (enemyType) {
            EnemyType.FLYER -> {
                random = seed.nextInt(flyers.size)
                enemySprite = flyers[random]
            }
            EnemyType.JUMPER -> {
                random = seed.nextInt(jumpers.size)
                enemySprite = jumpers[random]
            }
            EnemyType.WALKER -> {
                random = seed.nextInt(walkers.size)
                enemySprite = walkers[random]
            }
            EnemyType.CRAWLER -> {
                random = seed.nextInt(crawlers.size)
                enemySprite = crawlers[random]
            }
        }
        return random
    }

    private fun randomizeEnemyType() {
        val seed = RandomXS128()
        @Suppress("MoveVariableDeclarationIntoWhen") val random = seed.nextInt(GameConstants.ENEMIES_TYPES_COUNT)
        when (random) {
            0 -> enemyType = EnemyType.FLYER
            1 -> enemyType = EnemyType.JUMPER
            2 -> enemyType = EnemyType.WALKER
            3 -> enemyType = EnemyType.CRAWLER
        }
    }

    private fun createEnemySprites() {
        //create the enemy flyers sprites and add them to their list
        flyers.add(Sprite(Texture(GameConstants.ENEMY_BAT)))
        flyers.add(Sprite(Texture(GameConstants.ENEMY_BEE)))
        flyers.add(Sprite(Texture(GameConstants.ENEMY_FLY)))
        flyers.add(Sprite(Texture(GameConstants.ENEMY_LADY_BUG_FLY)))

        //create the enemy jumper sprites and add them to their list
        jumpers.add(Sprite(Texture(GameConstants.ENEMY_BLOCKER)))
        jumpers.add(Sprite(Texture(GameConstants.ENEMY_FROG)))

        //create the enemy walker sprites and add them to their list
        walkers.add(Sprite(Texture(GameConstants.ENEMY_MOUSE)))
        walkers.add(Sprite(Texture(GameConstants.ENEMY_SPIDER)))

        //create the enemy crawler sprites and add them to their list
        crawlers.add(Sprite(Texture(GameConstants.ENEMY_SLIME_WALK1)))
        crawlers.add(Sprite(Texture(GameConstants.ENEMY_SNAIL_WALK1)))
    }
}