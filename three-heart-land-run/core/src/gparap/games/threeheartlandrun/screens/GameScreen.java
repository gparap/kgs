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
package gparap.games.threeheartlandrun.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import gparap.games.threeheartlandrun.Background;
import gparap.games.threeheartlandrun.Ground;
import gparap.games.threeheartlandrun.HUD;
import gparap.games.threeheartlandrun.ThreeHeartLandRun;

public class GameScreen implements Screen {
    private final ThreeHeartLandRun game;
    private Background background;
    private Ground ground;
    private Viewport viewport;
    private boolean isGameRunning;
    private float gameStartTimer;
    private BitmapFont font;
    private HUD hud;

    public GameScreen(ThreeHeartLandRun game) {
        this.game = game;

        //init game start timer
        isGameRunning = false;
        gameStartTimer = 4.0f;
    }

    @Override
    public void show() {
        //create the background
        background = new Background(game);
        background.setSpeed(0);

        //create the ground
        ground = new Ground(game);

        //create the game viewport
        viewport = new FillViewport(ThreeHeartLandRun.MIN_WIDTH, ThreeHeartLandRun.MIN_HEIGHT, game.getCamera());

        //create a starter font for countdown
        font = new BitmapFont(Gdx.files.internal("fonts/test-font.fnt"));

        //create the heads up display
        hud = new HUD(game);
    }

    @Override
    public void render(float delta) {
        //clear screen
        ScreenUtils.clear(0, 0, 0, 1);

        //BEGIN drawing
        //-------------
        game.getSpriteBatch().setProjectionMatrix(game.getCamera().combined);
        game.getSpriteBatch().begin();

        //draw background
        background.update(delta);
        background.draw();

        //draw ground
        ground.update();
        ground.draw();

        //game has stopped
        if (!isGameRunning) {
            gameStartTimer -= delta;
            if (gameStartTimer <= 1) {
                isGameRunning = true;
            }
            background.setSpeed(0);
            font.draw(game.getSpriteBatch(), String.valueOf((int) gameStartTimer), game.getCamera().viewportWidth / 2f, game.getCamera().viewportHeight / 2f);
        }

        //game has started
        else {
            background.setSpeed(100);
        }

        //END drawing
        //-----------
        game.getSpriteBatch().end();

        //draw heads up display
        hud.render();

    }

    @Override
    public void resize(int width, int height) {
        //update the game camera viewport
        game.getCamera().viewportWidth = width;
        game.getCamera().viewportHeight = height;
        game.getCamera().position.set(width / 2F, height / 2F, 0F);
        game.getCamera().update();

        //update the game viewport
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
