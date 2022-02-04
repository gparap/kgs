package gparap.games.falling.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.ScreenViewport

class GameScreen(private val spriteBatch: SpriteBatch) : Screen {
    private lateinit var background: Texture
    private lateinit var camera: OrthographicCamera
    private lateinit var viewport: ScreenViewport

    override fun show() {
        //set up how the game world coordinates are mapped to and from the screen
        camera = OrthographicCamera(Gdx.graphics.width * 2F, Gdx.graphics.height * 2F)
        viewport = ScreenViewport(camera)

        //create background
        background = Texture("background.png")

    }

    override fun render(delta: Float) {
        ScreenUtils.clear(0F, 0F, 0F, 1F)
        spriteBatch.projectionMatrix = camera.combined
        spriteBatch.begin()
        spriteBatch.draw(background, 0F, 0F)
        spriteBatch.end()
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun pause() {
        TODO("Not yet implemented")
    }

    override fun resume() {
        TODO("Not yet implemented")
    }

    override fun hide() {
        TODO("Not yet implemented")
    }

    override fun dispose() {
        TODO("Not yet implemented")
    }
}