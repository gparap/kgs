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
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import gparap.games.threeheartlandrun.Background;
import gparap.games.threeheartlandrun.ThreeHeartLandRun;

public class PlayerScreen implements Screen {
    private final ThreeHeartLandRun game;
    private Background background;
    private Stage stage;
    private Image buttonPlayer1, buttonPlayer2, buttonPlayer3, buttonPlayer4, buttonPlayer5, buttonMenu;
    private final Sprite spritePlayer1, spritePlayer2, spritePlayer3, spritePlayer4, spritePlayer5;
    private final Texture textureIdlePlayer1, textureIdlePlayer2, textureIdlePlayer3, textureIdlePlayer4, textureIdlePlayer5;
    private final Texture textureSelectedPlayer1, textureSelectedPlayer2, textureSelectedPlayer3, textureSelectedPlayer4, textureSelectedPlayer5;
    private boolean isPlayer1Pressed, isPlayer2Pressed, isPlayer3Pressed, isPlayer4Pressed, isPlayer5Pressed, isMenuPressed;
    private final Preferences preferences;

    public PlayerScreen(ThreeHeartLandRun game) {
        this.game = game;
        isPlayer1Pressed = false;
        isPlayer2Pressed = false;
        isPlayer3Pressed = false;
        isPlayer4Pressed = false;
        isPlayer5Pressed = false;
        isMenuPressed = false;

        //create player character textures
        textureIdlePlayer1 = new Texture("players/adventurer_idle.png");
        textureSelectedPlayer1 = new Texture("players/adventurer_cheer1.png");
        textureIdlePlayer2 = new Texture("players/female_idle.png");
        textureSelectedPlayer2 = new Texture("players/female_cheer1.png");
        textureIdlePlayer3 = new Texture("players/player_idle.png");
        textureSelectedPlayer3 = new Texture("players/player_cheer1.png");
        textureIdlePlayer4 = new Texture("players/soldier_idle.png");
        textureSelectedPlayer4 = new Texture("players/soldier_cheer1.png");
        textureIdlePlayer5 = new Texture("players/zombie_idle.png");
        textureSelectedPlayer5 = new Texture("players/zombie_cheer1.png");

        //initialize player sprites
        spritePlayer1 = new Sprite(textureIdlePlayer1);
        spritePlayer2 = new Sprite(textureIdlePlayer2);
        spritePlayer3 = new Sprite(textureIdlePlayer3);
        spritePlayer4 = new Sprite(textureIdlePlayer4);
        spritePlayer5 = new Sprite(textureIdlePlayer5);

        //get the game preferences
        preferences = Gdx.app.getPreferences("preferences");
    }

    @Override
    public void show() {
        //create the background
        background = new Background(game);

        //create the stage
        Viewport viewport = new FillViewport(ThreeHeartLandRun.MIN_WIDTH, ThreeHeartLandRun.MIN_HEIGHT, game.getCamera());
        stage = new Stage(viewport);

        //create actors and add them to stage   //TODO: add info label
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
        background.update(delta);
        background.draw();
        game.getSpriteBatch().end();

        //draw stage
        stage.draw();

        //select player character
        if (isPlayer1Pressed) {
            spritePlayer1.setTexture(textureSelectedPlayer1);

            //reset other sprites
            spritePlayer2.setTexture(textureIdlePlayer2);
            spritePlayer3.setTexture(textureIdlePlayer3);
            spritePlayer4.setTexture(textureIdlePlayer4);
            spritePlayer5.setTexture(textureIdlePlayer5);
        }
        if (isPlayer2Pressed) {
            spritePlayer2.setTexture(textureSelectedPlayer2);

            //reset other sprites
            spritePlayer1.setTexture(textureIdlePlayer1);
            spritePlayer3.setTexture(textureIdlePlayer3);
            spritePlayer4.setTexture(textureIdlePlayer4);
            spritePlayer5.setTexture(textureIdlePlayer5);
        }
        if (isPlayer3Pressed) {
            spritePlayer3.setTexture(textureSelectedPlayer3);

            //reset other sprites
            spritePlayer1.setTexture(textureIdlePlayer1);
            spritePlayer2.setTexture(textureIdlePlayer2);
            spritePlayer4.setTexture(textureIdlePlayer4);
            spritePlayer5.setTexture(textureIdlePlayer5);
        }
        if (isPlayer4Pressed) {
            spritePlayer4.setTexture(textureSelectedPlayer4);

            //reset other sprites
            spritePlayer1.setTexture(textureIdlePlayer1);
            spritePlayer2.setTexture(textureIdlePlayer2);
            spritePlayer3.setTexture(textureIdlePlayer3);
            spritePlayer5.setTexture(textureIdlePlayer5);
        }
        if (isPlayer5Pressed) {
            spritePlayer5.setTexture(textureSelectedPlayer5);

            //reset other sprites
            spritePlayer1.setTexture(textureIdlePlayer1);
            spritePlayer2.setTexture(textureIdlePlayer2);
            spritePlayer3.setTexture(textureIdlePlayer3);
            spritePlayer4.setTexture(textureIdlePlayer4);
        }

        //goto main menu
        if (isMenuPressed) {
            savePlayerPreferences();
            this.dispose();
            game.setScreen(new MenuScreen(game));
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
        //TODO: dispose the other player textures
    }

    /**
     * Creates the menu buttons.
     */
    private void createButtons() {
        //player #1
        buttonPlayer1 = new Image(spritePlayer1);
        buttonPlayer1.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isPlayer1Pressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                //unselect other players
                isPlayer2Pressed = false;
                isPlayer3Pressed = false;
                isPlayer4Pressed = false;
                isPlayer5Pressed = false;
            }
        });
        //player #2
        buttonPlayer2 = new Image(spritePlayer2);
        buttonPlayer2.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isPlayer2Pressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                //unselect other players
                isPlayer1Pressed = false;
                isPlayer3Pressed = false;
                isPlayer4Pressed = false;
                isPlayer5Pressed = false;
            }
        });
        //player #3
        buttonPlayer3 = new Image(spritePlayer3);
        buttonPlayer3.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isPlayer3Pressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                //unselect other players
                isPlayer1Pressed = false;
                isPlayer2Pressed = false;
                isPlayer4Pressed = false;
                isPlayer5Pressed = false;
            }
        });
        //player #4
        buttonPlayer4 = new Image(spritePlayer4);
        buttonPlayer4.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isPlayer4Pressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                //unselect other players
                isPlayer1Pressed = false;
                isPlayer2Pressed = false;
                isPlayer3Pressed = false;
                isPlayer5Pressed = false;
            }
        });
        //player #5
        buttonPlayer5 = new Image(spritePlayer5);
        buttonPlayer5.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isPlayer5Pressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                //unselect other players
                isPlayer1Pressed = false;
                isPlayer2Pressed = false;
                isPlayer3Pressed = false;
                isPlayer4Pressed = false;
            }
        });
        //main menu
        buttonMenu = new Image(new Texture("buttons/button_menu.png"));
        buttonMenu.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                isMenuPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isMenuPressed = false;
            }
        });
    }

    private Table createTable() {   //TODO: sizes depend upon the final images
        Table table = new Table();
        table.center();
        table.setFillParent(true);
        float PAD = 16;
        table.add(buttonPlayer1).size(buttonPlayer1.getWidth() / 2, buttonPlayer1.getHeight() / 2).pad(PAD);
        table.add(buttonPlayer2).size(buttonPlayer2.getWidth() / 2, buttonPlayer2.getHeight() / 2).pad(PAD);
        table.add(buttonPlayer3).size(buttonPlayer3.getWidth() / 2, buttonPlayer3.getHeight() / 2).pad(PAD);
        table.add(buttonPlayer4).size(buttonPlayer4.getWidth() / 2, buttonPlayer4.getHeight() / 2).pad(PAD);
        table.add(buttonPlayer5).size(buttonPlayer5.getWidth() / 2, buttonPlayer5.getHeight() / 2).pad(PAD);
        table.row();
        //TODO: fix positioning
        table.add(buttonMenu).size(buttonMenu.getWidth() / 2, buttonMenu.getHeight() / 2).pad(PAD);
        return table;
    }

    private void savePlayerPreferences() {
        int value = 1;
        if (isPlayer2Pressed) value = 2;
        if (isPlayer3Pressed) value = 3;
        if (isPlayer4Pressed) value = 4;
        if (isPlayer5Pressed) value = 5;
        preferences.putInteger("player_selected", value);
        preferences.flush();
    }
}
