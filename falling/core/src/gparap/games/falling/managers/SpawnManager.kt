/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling.managers

import gparap.games.falling.GameConstants
import gparap.games.falling.tokens.Token

/**
 * This manager class is responsible for the spawning of tokens and enemies in the game
 */
class SpawnManager(private val tokenManager: TokenManager) {
    private var tokenSpawnTimer = 0F
    private var tokenPool = ArrayList<Token>()

    init {
        initTokenPool()
    }

    fun update(delta: Float) {
        spawnToken()

        //update the token timer
        tokenSpawnTimer += delta
        println(tokenSpawnTimer)
    }

    /* Creates requested number of tokens and fills up the pool */
    private fun initTokenPool() {
        for (i in 0 until GameConstants.TOKEN_POOL_SIZE) {
            val token = tokenManager.createToken()
            tokenPool.add(token)
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
}