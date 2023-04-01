/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling.utils

import com.badlogic.gdx.graphics.Color

object GameConstants {
    const val BUTTON_WIDTH = 128F
    const val BUTTON_HEIGHT = 64F
    const val TABLE_CELL_PAD = 8F
    const val TEXT_TAP = "Tap!"
    const val TEXT_GAME_OVER = "Game Over!"
    const val TEXT_START = "start"
    const val TEXT_MENU = "menu"
    const val TEXT_EXIT = "exit"
    const val PLAYER_SCALE_FACTOR = 1 / 1.5F
    const val GROUND_ZERO = 40F
    const val OFF_SCREEN_X = -1600F
    const val OFF_SCREEN_Y = -960F
    const val FRAME_DURATION_DIVIDER = 10
    const val ENEMY_JUMP_FACTOR = 100F
    const val SETTINGS_SCREEN_PAD = 2F.times(BUTTON_HEIGHT)

    //fonts
    const val GAME_OVER_FONT_SIZE = 48
    const val DEFAULT_FONT_SIZE = 24
    const val LOW_RESOLUTION_FONT_SIZE = 32
    const val DEFAULT_FONT = "fonts/kenney_pixel.fnt"
    const val OPEN_TYPE_FONT = "fonts/kenney_pixel.otf"

    //custom colors
    val COLOR_VANILLA = Color(0.95F, 0.90F, 0.67F, 1F)
    val COLOR_PINK = Color(0.100F, 0.75F, 0.80F, 1F)

    //preferences
    const val PREFERENCES = "jnf_preferences"
    const val PREFERENCES_FRIEND = "friend"
    const val PREFERENCES_FRIEND_DEFAULT = "friend_green"
    const val PREFERENCES_HIGH_SCORE = "jnf_high_score"
    const val PREFERENCES_MUSIC = "jnf_music"
    const val PREFERENCES_MUSIC_DEFAULT = true
    const val PREFERENCES_SXF = "jnf_sfx"
    const val PREFERENCES_SXF_DEFAULT = true

    //friends .png
    const val FRIEND_BEIGE = "friends/alien_beige.png"
    const val FRIEND_BLUE = "friends/alien_blue.png"
    const val FRIEND_GREEN = "friends/alien_green.png"
    const val FRIEND_PINK = "friends/alien_pink.png"
    const val FRIEND_YELLOW = "friends/alien_yellow.png"

    //enemies
    const val ENEMIES_TYPES_COUNT = 4
    const val ENEMY_POOL_SIZE = 4
    const val ENEMY_SPAWN_TIMER = 5F
    const val ENEMY_BAT_LEFT = "enemies/bat.png"
    const val ENEMY_BAT_FLY_LEFT = "enemies/bat_fly.png"
    const val ENEMY_BAT_RIGHT = "enemies/bat_right.png"
    const val ENEMY_BAT_FLY_RIGHT = "enemies/bat_fly_right.png"
    const val ENEMY_BEE = "enemies/bee.png"
    const val ENEMY_BEE_FLY = "enemies/bee_fly.png"
    const val ENEMY_BEE_RIGHT = "enemies/bee_right.png"
    const val ENEMY_BEE_FLY_RIGHT = "enemies/bee_fly_right.png"
    const val ENEMY_FLY = "enemies/fly.png"
    const val ENEMY_FLY_FLY = "enemies/fly_fly.png"
    const val ENEMY_FLY_RIGHT = "enemies/fly_right.png"
    const val ENEMY_FLY_FLY_RIGHT = "enemies/fly_fly_right.png"
    const val ENEMY_LADY_BUG = "enemies/lady_bug.png"
    const val ENEMY_LADY_BUG_FLY = "enemies/lady_bug_fly.png"
    const val ENEMY_LADY_BUG_RIGHT = "enemies/lady_bug_right.png"
    const val ENEMY_LADY_BUG_FLY_RIGHT = "enemies/lady_bug_fly_right.png"
    const val ENEMY_BLOCKER = "enemies/blocker_mad.png"
    const val ENEMY_FROG_LEFT = "enemies/frog_left.png"
    const val ENEMY_FROG_RIGHT = "enemies/frog_right.png"
    const val ENEMY_FROG_JUMP_LEFT = "enemies/frog_jump_left.png"
    const val ENEMY_FROG_JUMP_RIGHT = "enemies/frog_jump_right.png"
    const val ENEMY_MOUSE_LEFT = "enemies/mouse_left.png"
    const val ENEMY_MOUSE_WALK_LEFT = "enemies/mouse_walk_left.png"
    const val ENEMY_MOUSE_RIGHT = "enemies/mouse_right.png"
    const val ENEMY_MOUSE_WALK_RIGHT = "enemies/mouse_walk_right.png"
    const val ENEMY_SPIDER_LEFT = "enemies/spider_left.png"
    const val ENEMY_SPIDER_WALK1_LEFT = "enemies/spider_walk1_left.png"
    const val ENEMY_SPIDER_WALK2_LEFT = "enemies/spider_walk2_left.png"
    const val ENEMY_SPIDER_RIGHT = "enemies/spider_right.png"
    const val ENEMY_SPIDER_WALK1_RIGHT = "enemies/spider_walk1_right.png"
    const val ENEMY_SPIDER_WALK2_RIGHT = "enemies/spider_walk2_right.png"
    const val ENEMY_SLIME_WALK1_LEFT = "enemies/slime_walk1_left.png"
    const val ENEMY_SLIME_WALK2_LEFT = "enemies/slime_walk2_left.png"
    const val ENEMY_SLIME_WALK1_RIGHT = "enemies/slime_walk1_right.png"
    const val ENEMY_SLIME_WALK2_RIGHT = "enemies/slime_walk2_right.png"
    const val ENEMY_SNAIL_WALK1_LEFT = "enemies/snail_walk1_left.png"
    const val ENEMY_SNAIL_WALK2_LEFT = "enemies/snail_walk2_left.png"
    const val ENEMY_SNAIL_WALK1_RIGHT = "enemies/snail_walk1_right.png"
    const val ENEMY_SNAIL_WALK2_RIGHT = "enemies/snail_walk2_right.png"

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
    const val TOKEN_SPAWN_TIMER = 5F
    const val TOKEN_POOL_SIZE = 5
    const val SCORE_POINTS_GEM = 100
    const val SCORE_POINTS_COIN = 10
    const val SCORE_POINTS_STAR = 1000

    //heads-up display
    const val HUD_LABEL_HIGH_SCORE = "HIGH SCORE: "
    const val HUD_LABEL_SCORE = "SCORE: "
    const val HUD_LABEL_LIFE = "LIFE: "
    const val HUD_LABEL_FORMAT_STRING = "%s"
    const val HUD_LABEL_FORMAT_INTEGER = "%3d"
    const val HUD_TABLE_PADDING = 16F

    //player animations
    const val PLAYER_ANIMATION_PATH = "friends/animations/"
    const val PLAYER_ANIMATION_WALK1_LEFT = "_walk1_left.png"
    const val PLAYER_ANIMATION_WALK2_LEFT = "_walk2_left.png"
    const val PLAYER_ANIMATION_WALK1_RIGHT = "_walk1_right.png"
    const val PLAYER_ANIMATION_WALK2_RIGHT = "_walk2_right.png"

    //debris
    const val DEBRIS_ROCK_LARGE_DARK = "debris/rock_large_dark.png"
    const val DEBRIS_ROCK_LARGE_LIGHT = "debris/rock_large_light.png"
    const val DEBRIS_ROCK_SMALL_DARK = "debris/rock_small_dark.png"
    const val DEBRIS_ROCK_SMALL_LIGHT = "debris/rock_small_light.png"
    const val DEBRIS_SPIKES_LONG = "debris/spikes_long.png"
    const val DEBRIS_SPIKES_SHORT = "debris/spikes_short.png"
    const val DEBRIS_WEIGHT = "debris/weight.png"
    const val DEBRIS_MIN_SPEED = 2.5F
    const val DEBRIS_MAX_SPEED = 5F
    const val DEBRIS_POOL_SIZE = 4
    const val DEBRIS_SPAWN_TIMER = 5F

    //SFX
    const val SFX_GAME_OVER = "sfx/game_over.wav"
    const val SFX_PLAYER_HIT_DEBRIS = "sfx/hit_debris.wav"
    const val SFX_PLAYER_HIT_ENEMY = "sfx/hit_enemy.wav"
    const val SFX_PLAYER_JUMP = "sfx/jump_player.wav"
    const val SFX_PLAYER_PICKUP_TOKEN = "sfx/pick_token.wav"
    const val SFX_VOLUME_DEFAULT = 0.5f
    const val SFX_VOLUME_MIN = 0.1f

    //music
    const val MUSIC_DEFAULT_VOL = 0.4F
    const val MUSIC_THEME_PATH = "music/theme.mp3"
}