/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling.managers

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import gparap.games.falling.utils.GameConstants

/**
 * This manager class is responsible for the game's main music theme
 */
object MusicManager {
    private lateinit var music: Music

    /** Loads the main music theme. */
    fun loadTheme() {
        music = Gdx.audio.newMusic(Gdx.files.internal(GameConstants.MUSIC_THEME_PATH))
    }

    /** Plays back the music instance. */
    fun playTheme() {
        if (!music.isPlaying) {
            music.volume = GameConstants.MUSIC_DEFAULT_VOL
            music.isLooping = true
            music.play()
        }
    }

    /** Stops the playback of the music instance. */
    fun stopTheme() {
        if (music.isPlaying) {
            music.stop()
        }
    }

    /** Clears audio resources. */
    fun dispose() {
        music.dispose()
    }
}