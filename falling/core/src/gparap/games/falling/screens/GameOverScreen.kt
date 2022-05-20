package gparap.games.falling.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import gparap.games.falling.Falling
import gparap.games.falling.GameConstants

class GameOverScreen(spriteBatch: SpriteBatch, private val game: Falling) : Screen(spriteBatch) {
    private var labelGameOver: Label? = null
    private var isStartPressed = false
    private var isMenuPressed = false
    private var isExitPressed = false
    private lateinit var stage: Stage

    override fun show() {
        super.show()
        createFont(GameConstants.GAME_OVER_FONT_SIZE)
        createLabel()
        stage = Stage(viewport, spriteBatch)    //create a stage to add actors
        stage.addActor(createTable())           //add table actor to stage
        Gdx.input.inputProcessor = stage        //receive all touch events
    }

    override fun render(delta: Float) {
        super.render(delta)

        //draw stage
        stage.draw()

        //redirect to target screen based on user input
        if (isStartPressed) {
            this.dispose()
            this.hide()
            game.screen = GameScreen(spriteBatch)

        } else if (isMenuPressed) {
            this.dispose()
            this.hide()
            game.screen = MenuScreen(spriteBatch, game)

        } else if (isExitPressed) {
            this.dispose()
            Gdx.app.exit()
        }
    }

    private fun createLabel() {
        labelGameOver = Label(
            String.format(
                "%s", """ ${GameConstants.TEXT_GAME_OVER}
                
                """.trimIndent()
            ), Label.LabelStyle(font, Color.BLACK)
        )
    }

    private fun createTable(): Table {
        val table = Table()
        table.center()
        table.setFillParent(true)
        table.add(labelGameOver).center()
        table.row()
        table.add(createImageButton("button_start.png")).size(
            GameConstants.BUTTON_WIDTH,
            GameConstants.BUTTON_HEIGHT
        ).pad(GameConstants.TABLE_CELL_PAD)
        table.row()
        table.add(createImageButton("button_menu.png")).size(
            GameConstants.BUTTON_WIDTH,
            GameConstants.BUTTON_HEIGHT
        ).pad(GameConstants.TABLE_CELL_PAD)
        table.row()
        table.add(createImageButton("button_exit.png")).size(
            GameConstants.BUTTON_WIDTH,
            GameConstants.BUTTON_HEIGHT
        ).pad(GameConstants.TABLE_CELL_PAD)
        table.row()
        return table
    }

    private fun createImageButton(filePath: String): Image {
        val button = Image(Texture(filePath))
        button.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                if (filePath.contains(GameConstants.TEXT_START)) {
                    isStartPressed = true
                } else if (filePath.contains(GameConstants.TEXT_MENU)) {
                    isMenuPressed = true
                } else if (filePath.contains(GameConstants.TEXT_EXIT)) {
                    isExitPressed = true
                }
                return true
            }

            override fun touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int) {
                if (filePath.contains(GameConstants.TEXT_START)) {
                    isStartPressed = false
                } else if (filePath.contains(GameConstants.TEXT_MENU)) {
                    isMenuPressed = false
                } else if (filePath.contains(GameConstants.TEXT_EXIT)) {
                    isExitPressed = false
                }
            }
        })
        return button
    }
}