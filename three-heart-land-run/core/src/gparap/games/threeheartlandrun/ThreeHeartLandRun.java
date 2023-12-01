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
package gparap.games.threeheartlandrun;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import gparap.games.threeheartlandrun.screens.MenuScreen;

public class ThreeHeartLandRun extends Game {
    private SpriteBatch spriteBatch;
    private OrthographicCamera camera;

    //game dimensions
    public static final int MIN_WIDTH = 640;
    public static final int MIN_HEIGHT = 360;
    public static final int MAX_WIDTH = 1920;
    public static final int MAX_HEIGHT = 1080;

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();

        //setup the game camera
        camera = new OrthographicCamera(MIN_WIDTH, MIN_HEIGHT);
        camera.setToOrtho(false);
        camera.position.set(MIN_WIDTH / 2F, MIN_HEIGHT / 2F, 0F);
        camera.update();

        //goto main menu
        setScreen(new MenuScreen(this));
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
    }
}
