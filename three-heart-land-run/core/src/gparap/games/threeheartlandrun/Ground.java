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

import static gparap.games.threeheartlandrun.ThreeHeartLandRun.MIN_WIDTH;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import gparap.games.threeheartlandrun.tiles.Grass;

/**
 * Ground will be consisted of many grass tiles.
 */
public class Ground implements Disposable {
    private final Grass[] ground = new Grass[11];    //we need game's width div 10 plus 1 tile
    private final float speed;

    public Ground(ThreeHeartLandRun game) {
        speed = 60;

        //create the grass tiles
        for (int i = 0; i < 11; i++) {
            ground[i] = new Grass(game);

            //every grass tile will be next to the previous one
            if (i > 0) {
                float x = ground[i - 1].getPosition().x + ground[i].getWidth();
                float y = 0;
                ground[i].setPosition(new Vector2(x, y));
            }
        }
    }

    public void update(float delta) {
        Vector2 position;

        for (Grass grass : ground) {
            position = new Vector2(grass.getPosition());

            //move the ground tiles using parallax scrolling
            position.x -= speed * delta;

            //reset position if tile is off the screen
            if (position.x + grass.getWidth() < 0) {
                position.x = MIN_WIDTH;
            }

            //set the final position for the grass tile
            grass.update(position); //TODO: fix minor positioning bug
        }
    }

    public void draw() {
        for (Grass grass : ground) {
            grass.draw();
        }
    }

    @Override
    public void dispose() {

    }
}
