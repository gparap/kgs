package gparap.games.falling.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Table
import gparap.games.falling.GameConstants.MENU_BUTTON_HEIGHT
import gparap.games.falling.GameConstants.MENU_BUTTON_PAD
import gparap.games.falling.GameConstants.MENU_BUTTON_WIDTH

class MenuScreen(spriteBatch: SpriteBatch) : Screen(spriteBatch) {
    private var isStartPressed = false
    private var isCreditsPressed = false
    private var isExitPressed = false
    private lateinit var stage: Stage

    override fun show() {
        super.show()
        stage = Stage(viewport, spriteBatch)    //create a stage to add actors
        stage.addActor(createTableActor())      //add table actor to stage
        Gdx.input.inputProcessor = stage        //receive all touch events
    }

    override fun render(delta: Float) {
        super.render(delta)

        //draw stage
        stage.draw()

        //redirect to target screen based on user input
        if (isStartPressed) {
            this.dispose();
            this.hide();
            TODO("Not yet implemented - redirect to game screen")
        } else if (isCreditsPressed) {
            this.dispose();
            this.hide();
            TODO("Not yet implemented - redirect to credints screen")
        } else if (isExitPressed) {
            Gdx.app.exit();
        }
    }

    override fun dispose() {
        super.dispose()
        stage.dispose()
    }

    private fun createTableActor() : Table {
        //create a table actor
        val table = Table()
        table.center();
        table.setFillParent(true);

        //add buttons to table
        val buttonStart = createImageButton(isStartButton = true, isCreditsButton = false, isExiButton = false)
        table.add(buttonStart).size(MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT).pad(MENU_BUTTON_PAD)
        table.row();
        val buttonCredits = createImageButton(isStartButton = false, isCreditsButton = true, isExiButton = false)
        table.add(buttonCredits).size(MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT).pad(MENU_BUTTON_PAD)
        table.row();
        val buttonExit = createImageButton(isStartButton = false, isCreditsButton = false, isExiButton = true)
        table.add(buttonExit).size(MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT).pad(MENU_BUTTON_PAD)

        return table
    }

    private fun createImageButton(isStartButton: Boolean, isCreditsButton: Boolean, isExiButton: Boolean) : Image? {
        var button: Image? = null

        //create an image button
        if (isStartButton) {
            button = Image(Texture("button_start.png"))
        } else if (isCreditsButton) {
            button = Image(Texture("button_credits.png"))
        } else if (isExiButton) {
            button = Image(Texture("button_exit.png"))
        }

        button?.setSize(MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT)

        //add a click listener to button
        button?.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                if (isStartButton) {
                    isStartPressed = true
                } else if (isCreditsButton) {
                    isCreditsPressed = true
                } else if (isExiButton) {
                    isExitPressed = true
                }
                return true
            }

            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                if (isStartButton) {
                    isStartPressed = false
                } else if (isCreditsButton) {
                    isCreditsPressed = false
                } else if (isExiButton) {
                    isExitPressed = false
                }
            }
        })

        return button
    }
}