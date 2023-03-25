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
import gparap.games.falling.utils.GameConstants.SETTINGS_SCREEN_PAD
import gparap.games.falling.utils.GameConstants.TABLE_CELL_PAD

class SettingsScreen(spriteBatch: SpriteBatch, private val game: Falling) : Screen(spriteBatch) {
    private var isMenuPressed = false
    private var buttonToggleMusic: Image? = null
    private var buttonToggleSFX: Image? = null
    private var labelMusic: Label? = null
    private var labelSFX: Label? = null
    private var isMusicPressed = false
    private var isSFXPressed = false
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

        //toggle music/SFX
        if (isMusicPressed) {
            println("DEBUG: Music toggle button pressed...")
        }
        if (isSFXPressed) {
            println("DEBUG: SFX toggle button pressed...")
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

        //create table for music toggle and add to list
        var table = Table()
        table.center().padBottom(SETTINGS_SCREEN_PAD)
        table.setFillParent(true)
        table.add(buttonToggleMusic).size(buttonToggleMusic?.width!!, buttonToggleMusic?.width!!).pad(TABLE_CELL_PAD)
        table.add(buttonToggleSFX).size(buttonToggleSFX?.width!!, buttonToggleSFX?.width!!).pad(TABLE_CELL_PAD)
        table.row()
        table.add(labelMusic).pad(TABLE_CELL_PAD)
        table.add(labelSFX).pad(TABLE_CELL_PAD)
        tables.add(table)

        //create table for SFX toggle and add to list
        table = Table()
        table.center().padTop(SETTINGS_SCREEN_PAD)
        table.setFillParent(true)
        table.add(createToggleButton("button_menu.png")).size(
            GameConstants.BUTTON_WIDTH,
            GameConstants.BUTTON_HEIGHT
        ).pad(TABLE_CELL_PAD)
        tables.add(table)

        return tables
    }

    private fun createButtons() {
        buttonToggleMusic = createToggleButton("music/on.png")   //create toggle button for music
        buttonToggleSFX = createToggleButton("sfx/on.png")       //create toggle button for SFX
    }

    private fun createToggleButton(filePath: String): Image {
        val button = Image(Texture(filePath))
        button.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                if (filePath.contains("music")) {
                    isMusicPressed = true
                } else if (filePath.contains("sfx")) {
                    isSFXPressed = true
                } else if (filePath.contains(GameConstants.TEXT_MENU)) {
                    isMenuPressed = true
                }
                return true
            }

            override fun touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int) {
                if (filePath.contains("music")) {
                    isMusicPressed = false
                } else if (filePath.contains("sfx")) {
                    isSFXPressed = false
                } else if (filePath.contains(GameConstants.TEXT_MENU)) {
                    isMenuPressed = false
                }
            }
        })

        return button
    }
}