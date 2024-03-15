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
import gparap.games.threeheartlandrun.tiles.Dirt;

/**
 * Ground will be consisted of many grass & dirt tiles.
 */
public class Ground implements Disposable {
    private final Grass[] grass = new Grass[10];    //we need game's width tiles
    private final Dirt[] dirt = new Dirt[11];       //we need game's width div 10 plus 1 tile
    private float speed;

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Ground(ThreeHeartLandRun game) {
        //ground not moving
        speed = 0;

        //create the grass tiles
        for (int i = 0; i < 10; i++) {
            grass[i] = new Grass(game);

            //every grass tile will be next to the previous one
            if (i > 0) {
                float x = grass[i - 1].getPosition().x + grass[i].getWidth();
                float y = 0;
                grass[i].setPosition(new Vector2(x, y));
                grass[i].update(grass[i].getPosition());
            }
        }

        //create the dirt tiles
        for (int i = 0; i < 11; i++) {
            dirt[i] = new Dirt(game);

            //every dirt tile will be next to the previous one
            if (i > 0) {
                float x = dirt[i - 1].getPosition().x + dirt[i].getWidth();
                float y = 0;
                dirt[i].setPosition(new Vector2(x, y));
                dirt[i].update(dirt[i].getPosition());
            }
        }
    }

    public void update(float delta) {
        Vector2 position;

        //update ground dirt tiles to create parallax scrolling effect
        for (Dirt dirt : this.dirt) {
            position = new Vector2(dirt.getPosition());

            //move the dirt tiles leftwards
            position.x -= speed * delta;

            //reset position if tile is off the screen
            if (position.x + dirt.getWidth() < 0) {
                position.x = MIN_WIDTH;
            }

            //set the final position for the dirt tile
            dirt.update(position);
        }
    }

    public void draw() {
        for (Grass grass : grass) {
            grass.draw();
        }
        for (Dirt dirt : this.dirt) {
            dirt.draw();
        }
    }

    @Override
    public void dispose() {

    }
}
