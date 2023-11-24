/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.ScreenViewport
import gparap.games.falling.utils.GameConstants

/**
 * Base class for all screens of the game.
 */
open class Screen(protected val spriteBatch: SpriteBatch) : Screen {
    private lateinit var background: Texture
    private lateinit var camera: OrthographicCamera
    protected lateinit var viewport: ScreenViewport
    protected lateinit var font: BitmapFont
    protected lateinit var preferences: Preferences

    override fun show() {
        //set up how the game world coordinates are mapped to and from the screen
        camera = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        camera.setToOrtho(false)
        viewport = ScreenViewport(camera)

        //create background
        background = Texture(GameConstants.BACKGROUND)

        //get the preferences of this game
        preferences = Gdx.app.getPreferences(GameConstants.PREFERENCES)
    }

    override fun render(delta: Float) {
        //clear the screen before drawing
        ScreenUtils.clear(0F, 0F, 0F, 1F)

        //draw background
        spriteBatch.projectionMatrix = camera.combined
        spriteBatch.begin()
        spriteBatch.draw(background, 0F, 0F, viewport.screenWidth.toFloat(), viewport.screenHeight.toFloat())
        spriteBatch.end()
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun pause() {}

    override fun resume() {}

    override fun hide() {}

    override fun dispose() {
        background.dispose()
    }

    /** Creates the default font that will be used throughout the screens */
    protected open fun createFont(fontSize: Int = GameConstants.DEFAULT_FONT_SIZE) {
        font = BitmapFont(Gdx.files.internal(GameConstants.DEFAULT_FONT))
        val generator = FreeTypeFontGenerator(Gdx.files.internal(GameConstants.OPEN_TYPE_FONT))
        val parameter = FreeTypeFontParameter()
        parameter.size = fontSize
        font = generator.generateFont(parameter)
        generator.dispose()
    }
}