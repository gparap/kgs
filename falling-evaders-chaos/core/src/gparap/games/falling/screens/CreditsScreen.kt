/*******************************
 * Katocheánian Gaming Studios *
 * Falling Evaders Chaos       *
 * created by gparap           *
 *******************************/
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
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import com.badlogic.gdx.scenes.scene2d.ui.Table
import gparap.games.falling.Falling
import gparap.games.falling.utils.GameConstants
import gparap.games.falling.utils.GameConstants.BUTTON_HEIGHT
import gparap.games.falling.utils.GameConstants.BUTTON_WIDTH
import gparap.games.falling.utils.GameConstants.TABLE_CELL_PAD

class CreditsScreen(spriteBatch: SpriteBatch, private val game: Falling) : Screen(spriteBatch) {
    private var buttonMenu: Image? = null
    private var labelCredits: Label? = null
    private var isPressedMenu = false
    private lateinit var stage: Stage

    override fun show() {
        super.show()
        createFont()
        createLabel()
        createMenuButton()
        stage = Stage(viewport, spriteBatch)    //create a stage to add actors
        stage.addActor(createTable())           //add table actor to stage
        Gdx.input.inputProcessor = stage        //receive all touch events
    }

    override fun render(delta: Float) {
        super.render(delta)

        //draw stage
        stage.draw()

        //go back to menu
        if (isPressedMenu) {
            dispose()
            game.screen = MenuScreen(spriteBatch, game)
        }
    }

    private fun createLabel() {
        labelCredits = Label(String.format("%s", GameConstants.CREDITS.trimIndent()), LabelStyle(font, Color.BLACK))
    }

    private fun createTable(): Table {
        val table = Table()
        table.center()
        table.setFillParent(true)
        table.add(labelCredits).center()
        table.row()
        table.add(buttonMenu).size(BUTTON_WIDTH, BUTTON_HEIGHT).pad(TABLE_CELL_PAD)
        table.row()
        return table
    }

    private fun createMenuButton() {
        buttonMenu = Image(Texture(GameConstants.BUTTON_MENU))
        buttonMenu!!.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                isPressedMenu = true
                return true
            }

            override fun touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int) {
                isPressedMenu = false
            }
        })
    }
}