/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling.hud

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.viewport.FitViewport
import gparap.games.falling.utils.GameConstants
import gparap.games.falling.utils.GameConstants.PREFERENCES_HIGH_SCORE
import gparap.games.falling.utils.GameConstants.SCORE_DEFAULT
import java.util.*

/**
 * Heads-up display (presents game data to the user)
 */
class HUD(spriteBatch: SpriteBatch?) : Disposable {
    private val stage: Stage
    private var font: BitmapFont
    private var highScore: Int = 0
    private var score = 0
    private var life = 0
    private var labelHighScoreL: Label? = null  //displays the label "HIGH SCORE:
    private var labelHighScoreR: Label? = null  //displays the actual high score
    private var labelScoreL: Label? = null  //displays the label "SCORE:
    private var labelScoreR: Label? = null  //displays the actual score
    private var labelLifeL: Label? = null   //displays the label "LIFE: "
    private var labelLifeR: Label? = null   //displays the actual life
    private var preferences: Preferences? = null

    fun setScore(score: Int) {
        this.score += score
    }

    fun setLife(life: Int) {
        this.life = life
    }

    init {
        val viewport = FitViewport(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat(), OrthographicCamera())
        stage = Stage(viewport, spriteBatch)
        font = BitmapFont(Gdx.files.internal(GameConstants.DEFAULT_FONT))
        preferences = Gdx.app.getPreferences(GameConstants.PREFERENCES).apply {
            highScore = this.getInteger(PREFERENCES_HIGH_SCORE, SCORE_DEFAULT)
        }
        score = SCORE_DEFAULT
        life = GameConstants.LIFE_DEFAULT
        createFont()
        createLabels()
        createLabelsTables().forEach {
            stage.addActor(it)
        }
    }

    fun update() {
        labelHighScoreR?.setText(highScore)
        labelScoreR?.setText(score)
        labelLifeR?.setText(life)

        //check for high score
        if (score > highScore) {
            highScore = score
            preferences?.putInteger(PREFERENCES_HIGH_SCORE, highScore)
            preferences?.flush()
        }
    }

    fun draw() {
        stage.draw()
    }

    /* Creates the font that will be shown in the heads-up display */
    private fun createFont() {
        val generator = FreeTypeFontGenerator(Gdx.files.internal(GameConstants.OPEN_TYPE_FONT))
        val parameter = FreeTypeFontParameter()
        parameter.size = getFontSize()
        parameter.color = Color.GOLD
        parameter.shadowColor = Color.BLACK
        parameter.shadowOffsetX = 1
        parameter.shadowOffsetY = 1
        font = generator.generateFont(parameter)
        generator.dispose()
    }

    /* Creates all the labels that will be shown in the heads-up display */
    private fun createLabels() {
        labelHighScoreL = Label(
            String.format(
                Locale.getDefault(),
                GameConstants.HUD_LABEL_FORMAT_STRING,
                GameConstants.HUD_LABEL_HIGH_SCORE
            ),
            LabelStyle(font, Color.GOLD)
        )
        labelHighScoreR = Label(
            String.format(Locale.getDefault(), GameConstants.HUD_LABEL_FORMAT_INTEGER, highScore),
            LabelStyle(font, Color.GOLD)
        )
        labelScoreL = Label(
            String.format(Locale.getDefault(), GameConstants.HUD_LABEL_FORMAT_STRING, GameConstants.HUD_LABEL_SCORE),
            LabelStyle(font, Color.GOLD)
        )
        labelScoreR = Label(
            String.format(Locale.getDefault(), GameConstants.HUD_LABEL_FORMAT_INTEGER, score),
            LabelStyle(font, Color.GOLD)
        )
        labelLifeL = Label(
            String.format(Locale.getDefault(), GameConstants.HUD_LABEL_FORMAT_STRING, GameConstants.HUD_LABEL_LIFE),
            LabelStyle(font, Color.GOLD)
        )
        labelLifeR = Label(
            String.format(Locale.getDefault(), GameConstants.HUD_LABEL_FORMAT_INTEGER, life),
            LabelStyle(font, Color.GOLD)
        )
    }

    /* Creates the table that will hold all labels that will be shown in the heads-up display */
    private fun createLabelsTables(): ArrayList<Table> {
        val tables = kotlin.collections.ArrayList<Table>()

        //create score and life table and add them to the list
        var table = Table()
        table.left().top()
        table.setFillParent(true)
        table.row()
        table.add(labelScoreL).align(Align.right).padLeft(GameConstants.HUD_TABLE_PADDING)
        table.add(labelScoreR).align(Align.left)
        tables.add(table)

        //create high score and add it to the list
        table = Table()
        table.center().top()
        table.setFillParent(true)
        table.row()
        table.add(labelHighScoreL).align(Align.right)
        table.add(labelHighScoreR).align(Align.left)
        tables.add(table)

        //create life table and add it to the list
        table = Table()
        table.right().top()
        table.setFillParent(true)
        table.row()
        table.add(labelLifeL).align(Align.right)
        table.add(labelLifeR).align(Align.left).padRight(GameConstants.HUD_TABLE_PADDING)
        tables.add(table)

        return tables
    }

    /* Returns the font size in pixels */
    private fun getFontSize(): Int {
        var size = GameConstants.DEFAULT_FONT_SIZE

        //check for lower resolutions and adjust the size
        val width = Gdx.graphics.width.toFloat()
        val height = Gdx.graphics.height.toFloat()
        if (width < Gdx.graphics.width.toFloat() || width < Gdx.graphics.height.toFloat() || height < Gdx.graphics.width.toFloat() || height < Gdx.graphics.height.toFloat()) {
            size = GameConstants.LOW_RESOLUTION_FONT_SIZE
        }

        return size
    }

    override fun dispose() {
        stage.dispose()
        font.dispose()
    }
}