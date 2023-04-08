/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
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
import com.badlogic.gdx.scenes.scene2d.ui.Table
import gparap.games.falling.Falling
import gparap.games.falling.utils.GameConstants
import gparap.games.falling.utils.GameConstants.COLOR_PINK
import gparap.games.falling.utils.GameConstants.COLOR_VANILLA
import gparap.games.falling.utils.GameConstants.FRIEND_BEIGE
import gparap.games.falling.utils.GameConstants.FRIEND_BLUE
import gparap.games.falling.utils.GameConstants.FRIEND_GREEN
import gparap.games.falling.utils.GameConstants.FRIEND_PINK
import gparap.games.falling.utils.GameConstants.FRIEND_YELLOW
import gparap.games.falling.utils.GameConstants.PREFERENCES_FRIEND
import gparap.games.falling.utils.GameConstants.TABLE_CELL_PAD
import gparap.games.falling.utils.GameConstants.TEXT_BEIGE
import gparap.games.falling.utils.GameConstants.TEXT_BLUE
import gparap.games.falling.utils.GameConstants.TEXT_GREEN
import gparap.games.falling.utils.GameConstants.TEXT_PINK
import gparap.games.falling.utils.GameConstants.TEXT_TAP
import gparap.games.falling.utils.GameConstants.TEXT_YELLOW

class FriendsScreen(spriteBatch: SpriteBatch, private val game: Falling) : Screen(spriteBatch) {
    private var buttonBeige: Image? = null
    private var buttonBlue: Image? = null
    private var buttonGreen: Image? = null
    private var buttonPink: Image? = null
    private var buttonYellow: Image? = null
    private var labelTapBeige: Label? = null
    private var labelTapBlue: Label? = null
    private var labelTapGreen: Label? = null
    private var labelTapPink: Label? = null
    private var labelTapYellow: Label? = null
    private var isPressedBeige = false
    private var isPressedBlue = false
    private var isPressedGreen = false
    private var isPressedPink = false
    private var isPressedYellow = false
    private lateinit var stage: Stage

    override fun show() {
        super.show()
        super.createFont(GameConstants.DEFAULT_FONT_SIZE)
        createLabels()
        createFriendsButtons()
        stage = Stage(viewport, spriteBatch)
        stage.addActor(createTable())
        Gdx.input.inputProcessor = stage
    }

    override fun render(delta: Float) {
        super.render(delta)

        //draw stage
        stage.draw()

        //select friend and return to main menu
        if (isPressedBeige) {
            selectFriend(FRIEND_BEIGE)
        }
        if (isPressedBlue) {
            selectFriend(FRIEND_BLUE)
        }
        if (isPressedGreen) {
            selectFriend(FRIEND_GREEN)
        }
        if (isPressedPink) {
            selectFriend(FRIEND_PINK)
        }
        if (isPressedYellow) {
            selectFriend(FRIEND_YELLOW)
        }
    }

    override fun dispose() {
        super.dispose()
        stage.dispose()
    }

    private fun createLabels() {
        labelTapBeige = Label(TEXT_TAP, Label.LabelStyle(font, COLOR_VANILLA))
        labelTapBlue = Label(TEXT_TAP, Label.LabelStyle(font, Color.BLUE))
        labelTapGreen = Label(TEXT_TAP, Label.LabelStyle(font, Color.GREEN))
        labelTapPink = Label(TEXT_TAP, Label.LabelStyle(font, COLOR_PINK))
        labelTapYellow = Label(TEXT_TAP, Label.LabelStyle(font, Color.YELLOW))
    }

    private fun createTable(): Table {
        val table = Table()
        table.center()
        table.setFillParent(true)
        table.add(buttonBeige).size(buttonBeige?.width!!, buttonBeige?.width!!).pad(TABLE_CELL_PAD)
        table.add(buttonBlue).size(buttonBlue?.width!!, buttonBlue?.width!!).pad(TABLE_CELL_PAD)
        table.add(buttonGreen).size(buttonGreen?.width!!, buttonGreen?.width!!).pad(TABLE_CELL_PAD)
        table.add(buttonPink).size(buttonPink?.width!!, buttonPink?.width!!).pad(TABLE_CELL_PAD)
        table.add(buttonYellow).size(buttonYellow?.width!!, buttonYellow?.width!!).pad(TABLE_CELL_PAD)
        table.row()
        table.add(labelTapBeige).pad(TABLE_CELL_PAD)
        table.add(labelTapBlue).pad(TABLE_CELL_PAD)
        table.add(labelTapGreen).pad(TABLE_CELL_PAD)
        table.add(labelTapPink).pad(TABLE_CELL_PAD)
        table.add(labelTapYellow).pad(TABLE_CELL_PAD)
        return table
    }

    private fun createFriendsButtons() {
        buttonBeige = createImageButton(FRIEND_BEIGE)   //create beige friend
        buttonBlue = createImageButton(FRIEND_BLUE)     //create blue friend
        buttonGreen = createImageButton(FRIEND_GREEN)   //create green friend (aka Jerry!!!)
        buttonPink = createImageButton(FRIEND_PINK)     //create pink friend
        buttonYellow = createImageButton(FRIEND_YELLOW) //create yellow friend
    }

    private fun selectFriend(friend: String) {
        //save preferences
        preferences.putString(PREFERENCES_FRIEND, friend)
        preferences.flush()

        //goto menu
        this.dispose()
        game.screen = MenuScreen(spriteBatch, game)
    }

    private fun createImageButton(filePath: String): Image {
        val button = Image(Texture(filePath))
        button.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                if (filePath.contains(TEXT_BEIGE)) {
                    isPressedBeige = true
                } else if (filePath.contains(TEXT_BLUE)) {
                    isPressedBlue = true
                } else if (filePath.contains(TEXT_GREEN)) {
                    isPressedGreen = true
                } else if (filePath.contains(TEXT_PINK)) {
                    isPressedPink = true
                } else if (filePath.contains(TEXT_YELLOW)) {
                    isPressedYellow = true
                }

                return true
            }

            override fun touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int) {
                if (filePath.contains(TEXT_BEIGE)) {
                    isPressedBeige = false
                } else if (filePath.contains(TEXT_BLUE)) {
                    isPressedBlue = false
                } else if (filePath.contains(TEXT_GREEN)) {
                    isPressedGreen = false
                } else if (filePath.contains(TEXT_PINK)) {
                    isPressedPink = false
                } else if (filePath.contains(TEXT_YELLOW)) {
                    isPressedYellow = false
                }
            }
        })

        return button
    }

}