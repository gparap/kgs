/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling

import com.badlogic.gdx.graphics.Color

object GameConstants {
    const val BUTTON_WIDTH = 128F
    const val BUTTON_HEIGHT = 64F
    const val TABLE_CELL_PAD = 8F
    const val DEFAULT_FONT_SIZE = 24
    const val TEXT_TAP = "Tap!"
    const val PLAYER_SCALE_FACTOR = 1 / 1.5F
    const val GROUND_ZERO = 40F

    //custom colors
    val COLOR_VANILLA = Color(0.95F, 0.90F, 0.67F, 1F)
    val COLOR_PINK = Color(0.100F, 0.75F, 0.80F, 1F)

    //preferences
    const val PREFERENCES = "jnf_preferences"
    const val PREFERENCES_FRIEND = "friend"
    const val PREFERENCES_FRIEND_DEFAULT = "friend_green"

    //friends .png
    const val FRIEND_BEIGE = "friends/alien_beige.png"
    const val FRIEND_BLUE = "friends/alien_blue.png"
    const val FRIEND_GREEN = "friends/alien_green.png"
    const val FRIEND_PINK = "friends/alien__pink.png"
    const val FRIEND_YELLOW = "friends/alien_yellow.png"

    //tokens
    const val TOKEN_TYPES_COUNT = 3
    const val TOKEN_GEM_BLUE = "tokens/gem_blue.png"
    const val TOKEN_GEM_GREEN = "tokens/gem_green.png"
    const val TOKEN_GEM_RED = "tokens/gem_red.png"
    const val TOKEN_GEM_YELLOW = "tokens/gem_yellow.png"
    const val TOKEN_COIN_BRONZE = "tokens/coin_bronze.png"
    const val TOKEN_COIN_GOLD = "tokens/coin_gold.png"
    const val TOKEN_COIN_SILVER = "tokens/coin_silver.png"
    const val TOKEN_STAR = "tokens/star.png"
    const val TOKEN_MIN_SPEED = 1.25F
    const val TOKEN_GEM_MAX_SPEED = 2.5F
    const val TOKEN_COIN_MAX_SPEED = 5F
    const val TOKEN_STAR_MAX_SPEED = 10F
}