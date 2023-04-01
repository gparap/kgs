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
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle
import com.badlogic.gdx.scenes.scene2d.utils.Drawable
import gparap.games.falling.Falling
import gparap.games.falling.utils.GameConstants
import gparap.games.falling.utils.GameConstants.SETTINGS_SCREEN_PAD
import gparap.games.falling.utils.GameConstants.TABLE_CELL_PAD

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
        tables = createTables().apply {
            forEach {
                stage.addActor(it)
            }
        }
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
        labelMusic = Label("Music", Label.LabelStyle(font, Color.GREEN))
        labelSFX = Label("Sounds", Label.LabelStyle(font, Color.GREEN))
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
        buttonToggleMusic = createToggleButton("music/on.png", "music/off.png") //create toggle button for music
        buttonToggleSFX = createToggleButton("sfx/on.png", "sfx/off.png")       //create toggle button for SFX
        buttonMainMenu = createMenuButton() //create button for main menu
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
        skin.add("on", textureRegionON)
        skin.add("off", textureRegionOFF)

        //create the button style using the on/off state's drawables
        val drawableON: Drawable = skin.getDrawable("on")
        val drawableOFF: Drawable = skin.getDrawable("off")
        val imageButtonStyle = createImageButtonStyle(drawableON)

        //set the button ON/OFF state based on preferencesThe style for an image button
        var isToggleOn: Boolean
        if (filePathToggleOn.contains("music")) {
            isToggleOn = preferences.getBoolean(GameConstants.PREFERENCES_MUSIC, GameConstants.PREFERENCES_MUSIC_DEFAULT)
            if (isToggleOn) {
                updateImageButtonStyle(imageButtonStyle, drawableON)
            } else {
                updateImageButtonStyle(imageButtonStyle, drawableOFF)
            }
        } else if (filePathToggleOn.contains("sfx")) {
            isToggleOn = preferences.getBoolean(GameConstants.PREFERENCES_SXF, GameConstants.PREFERENCES_SXF_DEFAULT)
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
                if (filePathToggleOn.contains("music")) {
                    //get the button ON/OFF state based on preferences
                    isToggleOn =
                        preferences.getBoolean(GameConstants.PREFERENCES_MUSIC, GameConstants.PREFERENCES_MUSIC_DEFAULT)

                    //reverse the button state and save the preference
                    if (isToggleOn) {
                        imageButton.style = createImageButtonStyle(drawableOFF)
                        preferences.putBoolean(GameConstants.PREFERENCES_MUSIC, false)
                        preferences.flush()
                    } else {
                        imageButton.style = createImageButtonStyle(drawableON)
                        preferences.putBoolean(GameConstants.PREFERENCES_MUSIC, true)
                        preferences.flush()
                    }
                } else if (filePathToggleOn.contains("sfx")) {
                    //get the button ON/OFF state based on preferences
                    isToggleOn =
                        preferences.getBoolean(GameConstants.PREFERENCES_SXF, GameConstants.PREFERENCES_SXF_DEFAULT)

                    //reverse the button ON/OFF state and save the preference
                    if (isToggleOn) {
                        imageButton.style = createImageButtonStyle(drawableOFF)
                        preferences.putBoolean(GameConstants.PREFERENCES_SXF, false)
                        preferences.flush()
                    } else {
                        imageButton.style = createImageButtonStyle(drawableON)
                        preferences.putBoolean(GameConstants.PREFERENCES_SXF, true)
                        preferences.flush()
                    }
                }
                return true
            }
        })

        return imageButton
    }

    private fun createMenuButton(): Image {
        val button = Image(Texture("button_menu.png"))
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

    private fun createImageButtonStyle(drawable: Drawable): ImageButtonStyle {
        val style = ImageButtonStyle()
        style.up = drawable
        style.down = drawable
        style.checked = drawable
        style.checkedDown = drawable
        return style
    }
}