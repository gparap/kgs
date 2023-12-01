/*
 * Copyright 2023 gparap
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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import gparap.games.threeheartlandrun.ThreeHeartLandRun;

public class MenuScreen implements Screen {
    private final ThreeHeartLandRun game;
    private Texture background;
    private Stage stage;
    private Image buttonStart, buttonPlayer, buttonOptions, buttonCredits, buttonExit;
    private boolean isButtonStartPressed, isButtonPlayerPressed, isButtonOptionsPressed,
            isButtonCreditsPressed, isButtonExitPressed;

    public MenuScreen(ThreeHeartLandRun game) {
        this.game = game;
        isButtonStartPressed = false;
        isButtonPlayerPressed = false;
        isButtonOptionsPressed = false;
        isButtonCreditsPressed = false;
        isButtonExitPressed = false;
    }

    @Override
    public void show() {
        //create the background
        background = new Texture("background-empty.png");

        //create the stage
        Viewport viewport = new FillViewport(ThreeHeartLandRun.MIN_WIDTH, ThreeHeartLandRun.MIN_HEIGHT, game.getCamera());
        stage = new Stage(viewport);

        //create actors and add them to stage
        createButtons();
        stage.addActor(createTable());

        //receive all touch events
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        //clear screen
        ScreenUtils.clear(0, 0, 0, 1);

        //draw background
        game.getSpriteBatch().setProjectionMatrix(game.getCamera().combined);
        game.getSpriteBatch().begin();
        game.getSpriteBatch().draw(background, 0, 0, game.getCamera().viewportWidth, game.getCamera().viewportHeight);
        game.getSpriteBatch().end();

        //draw stage
        stage.draw();

        //start game
        if (isButtonStartPressed) {
            this.dispose();
            //TODO: start game
        }
        //select player
        if (isButtonPlayerPressed) {
            this.dispose();
            //TODO: show the player screen
        }
        //view options
        if (isButtonOptionsPressed) {
            this.dispose();
            //TODO: show the options screen
        }
        //view credits
        if (isButtonCreditsPressed) {
            this.dispose();
            //TODO: show the credits screen
        }
        //view credits
        if (isButtonCreditsPressed) {
            this.dispose();
            //TODO: show the credits screen
        }
        //exit game
        if (isButtonExitPressed) {
            this.dispose();
            game.dispose();
            Gdx.app.exit();
        }
    }

    @Override
    public void resize(int width, int height) {
        //update the game camera viewport
        game.getCamera().viewportWidth = width;
        game.getCamera().viewportHeight = height;
        game.getCamera().position.set(width / 2F, height / 2F, 0F);
        game.getCamera().update();

        //update the stage viewport
        stage.getViewport().update(width, height, true);
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
        stage.dispose();
    }

    /**
     * Creates the menu buttons.
     */
    private void createButtons() {
        //start button
        buttonStart = new Image(new Texture("buttons/button_start.png"));
        buttonStart.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isButtonStartPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isButtonStartPressed = false;
            }
        });
        //player button
        buttonPlayer = new Image(new Texture("buttons/button_player.png"));
        buttonPlayer.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isButtonPlayerPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isButtonPlayerPressed = false;
            }
        });
        //options button
        buttonOptions = new Image(new Texture("buttons/button_options.png"));
        buttonOptions.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isButtonOptionsPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isButtonOptionsPressed = false;
            }
        });
        //credits button
        buttonCredits = new Image(new Texture("buttons/button_credits.png"));
        buttonCredits.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isButtonCreditsPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isButtonCreditsPressed = false;
            }
        });
        //exit button
        buttonExit = new Image(new Texture("buttons/button_exit.png"));
        buttonExit.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isButtonExitPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isButtonExitPressed = false;
            }
        });
    }

    private Table createTable() {   //TODO: sizes depend upon the final images
        Table table = new Table();
        table.center();
        table.setFillParent(true);
        float PAD = 8;
        table.add(buttonStart).size(buttonStart.getWidth() / 2, buttonStart.getHeight() / 2).pad(PAD);
        table.row();
        table.add(buttonPlayer).size(buttonPlayer.getWidth() / 2, buttonPlayer.getHeight() / 2).pad(PAD);
        table.row();
        table.add(buttonOptions).size(buttonOptions.getWidth() / 2, buttonOptions.getHeight() / 2).pad(PAD);
        table.row();
        table.add(buttonCredits).size(buttonCredits.getWidth() / 2, buttonCredits.getHeight() / 2).pad(PAD);
        table.row();
        table.add(buttonExit).size(buttonExit.getWidth() / 2, buttonExit.getHeight() / 2).pad(PAD);
        table.row();
        return table;
    }
}
