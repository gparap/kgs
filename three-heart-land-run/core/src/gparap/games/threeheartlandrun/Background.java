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

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

public class Background implements Disposable {
    private final ThreeHeartLandRun game;
    private final Texture texture1, texture2;
    private float x1, x2;
    private final float width;
    private float speed;

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Background(ThreeHeartLandRun game) {
        this.game = game;

        //init parallax scrolling
        texture1 = new Texture("background-empty.png"); //front
        texture2 = new Texture("background-empty.png"); //back
        speed = 30;

        //init starting positions
        width = ThreeHeartLandRun.MIN_WIDTH;
        x1 = 0;
        x2 = width;
    }

    public void update(float delta) {
        //parallax scrolling
        x1 -= speed * delta;
        x2 -= speed * delta;

        //reset starting positions
        if (x1 + width < 0) {
            x1 = x2 + width;
        }
        if (x2 + width < 0) {
            x2 = x1 + width;
        }
    }

    public void draw() {
        game.getSpriteBatch().draw(texture1, x1, 0, game.getCamera().viewportWidth, game.getCamera().viewportHeight);
        game.getSpriteBatch().draw(texture2, x2, 0, game.getCamera().viewportWidth, game.getCamera().viewportHeight);
    }

    @Override
    public void dispose() {
        texture1.dispose();
        texture2.dispose();
    }
}
