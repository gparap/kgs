/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling.managers

import gparap.games.falling.GameConstants
import gparap.games.falling.enemies.Enemy
import gparap.games.falling.tokens.Token

/**
 * This manager class is responsible for the spawning of tokens and enemies in the game
 */
class SpawnManager(private val tokenManager: TokenManager, private val enemyManager: EnemyManager) {
    private var tokenSpawnTimer = 0F
    private var enemySpawnTimer = 0F
    private var tokenPool = ArrayList<Token>()
    private var enemyPool = ArrayList<Enemy>()

    fun getTokenPool(): ArrayList<Token> {
        return tokenPool
    }

    fun getEnemyPool(): ArrayList<Enemy> {
        return enemyPool
    }

    init {
        initTokenPool()
        initEnemyPool()
    }

    fun update(delta: Float) {
        spawnToken()
        spawnEnemy()

        //update the timers
        tokenSpawnTimer += delta
        enemySpawnTimer += delta
    }

    /* Creates requested number of tokens and fills up the pool */
    private fun initTokenPool() {
        for (i in 0 until GameConstants.TOKEN_POOL_SIZE) {
            val token = tokenManager.createToken()
            tokenPool.add(token)
        }
    }

    /* Creates requested number of enemies and fills up the pool */
    private fun initEnemyPool() {
        for (i in 0 until GameConstants.ENEMY_POOL_SIZE) {
            val enemy = enemyManager.createEnemy()
            enemyPool.add(enemy)
        }
    }

    /* Makes a token from the pool active in the game based on a time interval */
    private fun spawnToken() {
        if (tokenSpawnTimer >= GameConstants.TOKEN_SPAWN_TIMER) {
            //retrieve the first active token from the pool
            for (t: Token in tokenPool) {
                if (!t.isActiveInGame()) {
                    t.setActiveInGame(true)
                    break
                }
            }
            //reset the token timer
            if (tokenSpawnTimer >= GameConstants.TOKEN_SPAWN_TIMER) {
                tokenSpawnTimer = 0F
            }
        }
    }

    /* Makes an enemy from the pool active in the game based on a time interval */
    private fun spawnEnemy() {
        if (enemySpawnTimer >= GameConstants.ENEMY_SPAWN_TIMER) {
            //retrieve the first active enemy from the pool
            for (e: Enemy in enemyPool) {
                if (!e.isActiveInGame()) {
                    e.setActiveInGame(true)
                    break
                }
            }
            //reset the enemy timer
            if (enemySpawnTimer >= GameConstants.ENEMY_SPAWN_TIMER) {
                enemySpawnTimer = 0F
            }
        }
    }
}