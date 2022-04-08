/*******************************
 * Katocheánian Gaming Studios *
 * Little Jerry's Friends      *
 * created by gparap           *
 *******************************/
package gparap.games.falling

import com.badlogic.gdx.Gdx
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
import java.util.*

/**
 * Heads-up display (presents game data to the user)
 */
class HUD(spriteBatch: SpriteBatch?) : Disposable {
    private val stage: Stage
    private var font: BitmapFont
    private var score = 0
    private var life = 0
    private var labelScoreL: Label? = null  //displays the label "SCORE:
    private var labelScoreR: Label? = null  //displays the actual score
    private var labelLifeL: Label? = null   //displays the label "LIFE: "
    private var labelLifeR: Label? = null   //displays the actual life

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
        score = 0
        life = 5
        createFont()
        createLabels()
        stage.addActor(createLabelsTable())
    }

    fun update() {
        labelScoreR?.setText(score)
        labelLifeR?.setText(life)
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
    private fun createLabelsTable(): Table {
        val table = Table()
        table.left().top()
        table.setFillParent(true)
        table.row()
        table.add(labelScoreL).align(Align.right)
        table.add(labelScoreR).align(Align.left)
        table.row()
        table.add(labelLifeL).align(Align.right)
        table.add(labelLifeR).align(Align.left)
        return table
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