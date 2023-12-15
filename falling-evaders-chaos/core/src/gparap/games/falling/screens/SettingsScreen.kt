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
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import gparap.games.falling.Falling
import gparap.games.falling.utils.GameConstants
import gparap.games.falling.utils.GameConstants.PREFERENCES_MUSIC
import gparap.games.falling.utils.GameConstants.PREFERENCES_MUSIC_DEFAULT
import gparap.games.falling.utils.GameConstants.PREFERENCES_SXF
import gparap.games.falling.utils.GameConstants.PREFERENCES_SXF_DEFAULT
import gparap.games.falling.utils.GameConstants.SETTINGS_SCREEN_PAD
import gparap.games.falling.utils.GameConstants.TABLE_CELL_PAD
import gparap.games.falling.utils.GameConstants.TEXT_MUSIC
import gparap.games.falling.utils.GameConstants.TEXT_OFF
import gparap.games.falling.utils.GameConstants.TEXT_ON
import gparap.games.falling.utils.GameConstants.TEXT_SFX

class SettingsScreen(spriteBatch: SpriteBatch, private val game: Falling) : Screen(spriteBatch) {
    private var isMenuPressed = false
    private var buttonMainMenu: Image? = null
    private var buttonToggleMusic: ImageButton? = null
    private var buttonToggleSFX: ImageButton? = null
    private var labelMusic: Label? = null
    private var labelSFX: Label? = null
    private lateinit var stage: Stage
    private var tables = ArrayList<Table>()

    override fun show() {
        super.show()
        super.createFont(GameConstants.DEFAULT_FONT_SIZE)
        createLabels()
        createButtons()
        stage = Stage(viewport, spriteBatch)
        tables = createTables().onEach { stage.addActor(it) }
        Gdx.input.inputProcessor = stage
    }

    override fun render(delta: Float) {
        super.render(delta)
        stage.draw()

        //return to main menu
        if (isMenuPressed) {
            this.dispose()
            this.hide()
            game.screen = MenuScreen(spriteBatch, game)
        }
    }

    override fun dispose() {
        super.dispose()
        stage.dispose()
    }

    private fun createLabels() {
        labelMusic = Label(GameConstants.LABEL_MUSIC, Label.LabelStyle(font, Color.GREEN))
        labelSFX = Label(GameConstants.LABEL_SOUNDS, Label.LabelStyle(font, Color.GREEN))
    }

    private fun createTables(): ArrayList<Table> {
        tables = ArrayList()

        //create table for music & SFX toggles and add them to list
        var table = Table()
        table.center().padBottom(SETTINGS_SCREEN_PAD)
        table.setFillParent(true)
        table.add(buttonToggleMusic).size(buttonToggleMusic?.width!!, buttonToggleMusic?.width!!).pad(TABLE_CELL_PAD)
        table.add(buttonToggleSFX).size(buttonToggleSFX?.width!!, buttonToggleSFX?.width!!).pad(TABLE_CELL_PAD)
        table.row()
        table.add(labelMusic).pad(TABLE_CELL_PAD)
        table.add(labelSFX).pad(TABLE_CELL_PAD)
        tables.add(table)

        //create table for menu redirection and add it to list
        table = Table()
        table.center().padTop(SETTINGS_SCREEN_PAD)
        table.setFillParent(true)
        table.add(buttonMainMenu).size(GameConstants.BUTTON_WIDTH, GameConstants.BUTTON_HEIGHT).pad(TABLE_CELL_PAD)
        tables.add(table)

        return tables
    }

    private fun createButtons() {
        buttonToggleMusic = createToggleButton(GameConstants.MUSIC_ON, GameConstants.MUSIC_OFF) //create toggle button for music
        buttonToggleSFX = createToggleButton(GameConstants.SFX_ON, GameConstants.SFX_OFF)       //create toggle button for SFX
        buttonMainMenu = createMenuButton()                                                     //create button for main menu
    }

    private fun updateImageButtonStyle(style: ImageButtonStyle, drawable: Drawable) {
        style.up = drawable
        style.down = drawable
        style.checked = drawable
        style.checkedDown = drawable
    }

    private fun createToggleButton(filePathToggleOn: String, filePathToggleOff: String): ImageButton {
        //create a skin and add drawables for on/off states
        val skin = Skin()
        val textureRegionON = TextureRegion(Texture(filePathToggleOn))
        val textureRegionOFF = TextureRegion(Texture(filePathToggleOff))
        skin.add(TEXT_ON, textureRegionON)
        skin.add(TEXT_OFF, textureRegionOFF)

        //create the button style using the on/off state's drawables
        val drawableON: Drawable = skin.getDrawable(TEXT_ON)
        val drawableOFF: Drawable = skin.getDrawable(TEXT_OFF)
        val imageButtonStyle = createImageButtonStyle(drawableON)

        //set the button ON/OFF state based on preferences
        var isToggleOn: Boolean
        if (filePathToggleOn.contains(TEXT_MUSIC)) {
            isToggleOn = preferences.getBoolean(PREFERENCES_MUSIC, PREFERENCES_MUSIC_DEFAULT)
            if (isToggleOn) {
                updateImageButtonStyle(imageButtonStyle, drawableON)
            } else {
                updateImageButtonStyle(imageButtonStyle, drawableOFF)
            }
        } else if (filePathToggleOn.contains(TEXT_SFX)) {
            isToggleOn = preferences.getBoolean(PREFERENCES_SXF, PREFERENCES_SXF_DEFAULT)
            if (isToggleOn) {
                updateImageButtonStyle(imageButtonStyle, drawableON)
            } else {
                updateImageButtonStyle(imageButtonStyle, drawableOFF)
            }
        }

        //create an image button and set listener based on its state
        val imageButton = ImageButton(imageButtonStyle)
        imageButton.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                if (filePathToggleOn.contains(TEXT_MUSIC)) {
                    //get the button ON/OFF state based on preferences
                    isToggleOn = preferences.getBoolean(PREFERENCES_MUSIC, PREFERENCES_MUSIC_DEFAULT)

                    //reverse the button state and save the preference
                    if (isToggleOn) {
                        reverseState(imageButton, drawableOFF, PREFERENCES_MUSIC, false)
                    } else {
                        reverseState(imageButton, drawableON, PREFERENCES_MUSIC, true)
                    }
                } else if (filePathToggleOn.contains(TEXT_SFX)) {
                    //get the button ON/OFF state based on preferences
                    isToggleOn = preferences.getBoolean(PREFERENCES_SXF, PREFERENCES_SXF_DEFAULT)

                    //reverse the button ON/OFF state and save the preference
                    if (isToggleOn) {
                        reverseState(imageButton, drawableOFF, PREFERENCES_SXF, false)
                    } else {
                        reverseState(imageButton, drawableON, PREFERENCES_SXF, true)
                    }
                }
                return true
            }
        })

        return imageButton
    }

    private fun createMenuButton(): Image {
        val button = Image(Texture(GameConstants.BUTTON_MENU))
        button.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                isMenuPressed = true
                return true
            }

            override fun touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int) {
                isMenuPressed = false
            }
        })
        return button
    }

    /** Reverses an image button's state and saves the preference. */
    private fun reverseState(imageButton: ImageButton, drawable: Drawable, prefName: String, prefValue: Boolean) {
        imageButton.style = createImageButtonStyle(drawable)
        preferences.putBoolean(prefName, prefValue)
        preferences.flush()
    }

    private fun createImageButtonStyle(drawable: Drawable): ImageButtonStyle {
        val style = ImageButtonStyle()
        style.up = drawable
        style.down = drawable
        style.checked = drawable
        style.checkedDown = drawable
        return style
    }
}