/*
 * Copyright 2024 gparap
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gparap.games.threeheartlandrun;

import static com.badlogic.gdx.utils.Align.left;
import static com.badlogic.gdx.utils.Align.right;
import static java.lang.String.format;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Locale;

public class HUD implements Disposable {
    private final Stage stage;
    private int distance, score, highScore;
    private Label labelHighScoreL, labelHighScoreR, labelScoreL, labelScoreR, labelDistanceL, labelDistanceR;
    private final BitmapFont font;

    public HUD(ThreeHeartLandRun game) {
        Viewport viewport = new FillViewport(ThreeHeartLandRun.MIN_WIDTH, ThreeHeartLandRun.MIN_HEIGHT, game.getCamera());
        stage = new Stage(viewport, game.getSpriteBatch());
        font = new BitmapFont(Gdx.files.internal("fonts/test-font.fnt"));
        font.getData().setScale(0.5f);
        init();
        createLabels();
        stage.addActor(createTable());
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    /**
     * Initializes all heads up display's variables.
     */
    private void init() {
        score = 0;
        distance = 0;
        highScore = 0;    //TODO: handle with preferences
    }

    /**
     * Creates all the labels that will be shown in the heads up display.
     */
    private void createLabels() {
        labelHighScoreL = new Label(format(Locale.getDefault(), "%s", "HIGH SCORE: "),
                new Label.LabelStyle(font, Color.WHITE));
        labelHighScoreR = new Label(format(Locale.getDefault(), "%3d", 0),
                new Label.LabelStyle(font, Color.WHITE));
        labelScoreL = new Label(format(Locale.getDefault(), "%s", "SCORE: "),
                new Label.LabelStyle(font, Color.WHITE));
        labelScoreR = new Label(format(Locale.getDefault(), "%3d", 0),
                new Label.LabelStyle(font, Color.WHITE));
        labelDistanceL = new Label(format(Locale.getDefault(), "%s", "DISTANCE: "),
                new Label.LabelStyle(font, Color.WHITE));
        labelDistanceR = new Label(format(Locale.getDefault(), "%3d%s", 0, " m"),
                new Label.LabelStyle(font, Color.WHITE));
    }

    /**
     * Create the table that will hold all labels that will be shown in the heads up display.
     *
     * @return table
     */
    private Table createTable() {
        Table table = new Table();
        float padLeft = 0.0f;
        float padTop = 0.0f;

        //adjust the table padding for different sizes
        switch(Gdx.app.getType()) {
            case Android:
                padLeft = 8;
                padTop = 8;
                if (Gdx.graphics.getDensity() < 2.5) {
                    padLeft = 32;
                    padTop = 12;
                }
                break;
            case Desktop:
                padLeft = 16;
                padTop = 16;
            case WebGL:
                //TODO("Not implemented yet.")
        }

        table.left().top().padLeft(padLeft).padTop(padTop);
        table.setFillParent(true);
        table.add(labelHighScoreL).align(right);
        table.add(labelHighScoreR).align(left);
        table.row();
        table.add(labelScoreL).align(right);
        table.add(labelScoreR).align(left);
        table.row();
        table.add(labelDistanceL).align(right);
        table.add(labelDistanceR).align(left);
        return table;
    }

    public void update() {
        //update display
        labelScoreR.setText(format(Locale.getDefault(), "%3d", score));
        labelDistanceR.setText(format(Locale.getDefault(), "%3d m", distance));
        labelHighScoreR.setText(format(Locale.getDefault(), "%3d", highScore));
    }

    public void render() {
        stage.draw();
    }

    /**
     * Releases all resources of the heads up display.
     */
    @Override
    public void dispose() {
        stage.dispose();
        font.dispose();
    }
}
