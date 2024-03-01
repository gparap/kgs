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
package gparap.games.threeheartlandrun.tiles;

import static gparap.games.threeheartlandrun.ThreeHeartLandRun.MIN_HEIGHT;
import static gparap.games.threeheartlandrun.ThreeHeartLandRun.MIN_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

import gparap.games.threeheartlandrun.ThreeHeartLandRun;

public class Grass implements Disposable {
    private final ThreeHeartLandRun game;
    private final Sprite sprite;
    private Vector2 position = new Vector2(0, 0);

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public int getWidth() {
        return (int) sprite.getWidth();
    }

    public Grass(ThreeHeartLandRun game) {
        this.game = game;

        //setup the grass sprite
        sprite = new Sprite(new Texture(Gdx.files.internal("tiles/grass.png")));
        sprite.setSize(MIN_WIDTH / 10f, MIN_HEIGHT / 10f);
        sprite.setRegionWidth((int) sprite.getWidth());
        sprite.setRegionHeight((int) sprite.getHeight());
    }

    public void update(Vector2 position) {
        //update the sprite's position along with the exposed one
        sprite.setPosition(position.x, position.y);
        this.position.x = position.x;
        this.position.y = position.y;
    }

    public void draw() {
        game.getSpriteBatch().draw(sprite, sprite.getX(),sprite.getY());
    }

    @Override
    public void dispose() {

    }
}
