package gparap.games.falling.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils

class GameScreen(private val spriteBatch: SpriteBatch) : Screen {
    private lateinit var background: Texture
    private lateinit var camera: OrthographicCamera

    override fun show() {
        //create background
        background = Texture("background.png")
        camera = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
    }

    override fun render(delta: Float) {
        ScreenUtils.clear(0F, 0F, 0F, 1F)
        spriteBatch.begin()
        spriteBatch.draw(background, 0F, 0F)
        spriteBatch.end()
    }

    override fun resize(width: Int, height: Int) {
        println("Game Screen_resize")
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