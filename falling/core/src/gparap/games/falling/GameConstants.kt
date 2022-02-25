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
    const val PLAYER_SCALE_FACTOR = 1 / 2F

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
}