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

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import gparap.games.threeheartlandrun.tiles.Grass;

/**
 * Ground will be consisted of many grass tiles.
 */
public class Ground implements Disposable {
    private final Grass[] grass = new Grass[10];

    public Ground(ThreeHeartLandRun game) {
        //create the grass tiles
        for (int i = 0; i < 10; i++) {
            grass[i] = new Grass(game);

            //every grass tile will be next to the previous one
            if (i > 0) {
                Vector2 position = new Vector2();
                position.x = grass[i - 1].getPosition().x + grass[i].getWidth();
                position.y = 0;
                grass[i].setPosition(position);
            }
        }
    }

    public void update() {
        for (Grass g: grass) {
            g.update(new Vector2(g.getPosition()));
        }
    }

    public void draw() {
        for (Grass g: grass) {
            g.draw();
        }
    }

    @Override
    public void dispose() {

    }
}
