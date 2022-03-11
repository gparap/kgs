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
import gparap.games.falling.GameConstants
import gparap.games.falling.tokens.*

/**
 * This manager class is responsible for the lifecycle of a token in the game
 */
class TokenManager {
    private var activeTokens: MutableList<Token> = ArrayList()
    private lateinit var tokenType: TokenType
    private lateinit var tokenSprite: Sprite
    private var gems: MutableList<Sprite> = ArrayList()
    private var coins: MutableList<Sprite> = ArrayList()
    private lateinit var starTokenSprite: Sprite

    init {
        createTokenSprites()
    }

    fun createToken() {
        //set the token sprite
        randomizeTokenType()
        randomizeTokenSprite()

        //create the token
        val token = when (tokenType) {
            TokenType.GEM -> GemToken(tokenSprite)
            TokenType.COIN -> CoinToken(tokenSprite)
            TokenType.STAR -> StarToken(tokenSprite)
        }

        //add the token to active tokens list
        activeTokens.add(token)
    }

    fun updateTokens(delta: Float) {
        for (token in activeTokens) {
            token.update(delta)
        }
    }

    fun drawTokens(spriteBatch: SpriteBatch) {
        for (token in activeTokens) {
            token.draw(spriteBatch)
        }
    }

    //Randomizes the sprite of the active token based on its type (if there are multiple sprites in the type)
    private fun randomizeTokenSprite() {
        val seed = RandomXS128()
        val random: Int
        when (tokenType) {
            TokenType.GEM -> {
                random = seed.nextInt(gems.size)
                tokenSprite = gems[random]
            }
            TokenType.COIN -> {
                random = seed.nextInt(coins.size)
                tokenSprite = coins[random]
            }
            TokenType.STAR -> tokenSprite = starTokenSprite
        }
    }

    //Randomizes what type of token should be the active one
    private fun randomizeTokenType() {
        val seed = RandomXS128()
        @Suppress("MoveVariableDeclarationIntoWhen") val random = seed.nextInt(GameConstants.TOKEN_TYPES_COUNT)
        when (random) {
            0 -> tokenType = TokenType.GEM
            1 -> tokenType = TokenType.COIN
            2 -> tokenType = TokenType.STAR
        }
    }

    private fun createTokenSprites() {
        //create the token sprites and add them to the appropriate list
        gems.add(Sprite(Texture(GameConstants.TOKEN_GEM_BLUE)))
        gems.add(Sprite(Texture(GameConstants.TOKEN_GEM_GREEN)))
        gems.add(Sprite(Texture(GameConstants.TOKEN_GEM_RED)))
        gems.add(Sprite(Texture(GameConstants.TOKEN_GEM_YELLOW)))

        //create the coin sprites and add them to the list
        coins.add(Sprite(Texture(GameConstants.TOKEN_COIN_BRONZE)))
        coins.add(Sprite(Texture(GameConstants.TOKEN_COIN_GOLD)))
        coins.add(Sprite(Texture(GameConstants.TOKEN_COIN_SILVER)))

        //create the star sprite
        starTokenSprite = Sprite(Texture(GameConstants.TOKEN_STAR))
    }
}