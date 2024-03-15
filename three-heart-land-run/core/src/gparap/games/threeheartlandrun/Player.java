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

import static gparap.games.threeheartlandrun.ThreeHeartLandRun.MIN_HEIGHT;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Player {
    private final ThreeHeartLandRun game;
    private final Sprite sprite;
    private final Vector2 position = new Vector2(0, 0);
    private float speed;
    private final float groundPosition;

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getGroundPosition() {
        return groundPosition;
    }

    public Player(ThreeHeartLandRun game) {
        this.game = game;

        //get the selected player from game preferences
        Preferences preferences = Gdx.app.getPreferences("preferences");
        int playerSelected = preferences.getInteger("player_selected");

        //create the texture based on the player selected
        Texture playerSelectedTexture = null;
        switch (playerSelected) {
            case 1:
                playerSelectedTexture = new Texture(Gdx.files.internal("players/adventurer_idle.png"));
                break;
            case 2:
                playerSelectedTexture = new Texture(Gdx.files.internal("players/female_idle.png"));
                break;
            case 3:
                playerSelectedTexture = new Texture(Gdx.files.internal("players/player_idle.png"));
                break;
            case 4:
                playerSelectedTexture = new Texture(Gdx.files.internal("players/soldier_idle.png"));
                break;
            case 5:
                playerSelectedTexture = new Texture(Gdx.files.internal("players/zombie_idle.png"));
                break;
        }

        //setup the player sprite
        if (playerSelectedTexture != null) {
            sprite = new Sprite(playerSelectedTexture);
        }else {
            sprite = new Sprite(new Texture(Gdx.files.internal("players/adventurer_idle.png")));
        }
        sprite.setRegionWidth((int) sprite.getWidth());
        sprite.setRegionHeight((int) sprite.getHeight());

        //setup player starting position on the ground
        groundPosition = MIN_HEIGHT / 10f;
        position.y =  groundPosition;
    }

    public void update(float delta) {
        //TODO: player animation
        //TODO: player jumping
        //TODO: gravity simulation
    }

    public void draw() {
        game.getSpriteBatch().draw(sprite.getTexture(), position.x, position.y);
    }
}
