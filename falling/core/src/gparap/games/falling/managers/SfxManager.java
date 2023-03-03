package gparap.games.falling.managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import gparap.games.falling.utils.GameConstants;

public class SfxManager {
    private static SfxManager instance;
    private AssetManager assetManager;
    private SfxManager() {}
    public static SfxManager getInstance() {
        if (instance == null) {
            instance = new SfxManager();
        }
        return instance;
    }

    /** Loads sound effects. */
    public void loadSFX() {
        assetManager = new AssetManager();
        assetManager.load(GameConstants.SFX_GAME_OVER, Sound.class);
        assetManager.load(GameConstants.SFX_PLAYER_HIT_DEBRIS, Sound.class);
        assetManager.load(GameConstants.SFX_PLAYER_HIT_ENEMY, Sound.class);
        assetManager.load(GameConstants.SFX_PLAYER_JUMP, Sound.class);
        assetManager.load(GameConstants.SFX_PLAYER_PICKUP_TOKEN, Sound.class);
        assetManager.finishLoading();
    }

    /** Returns a sound effect. */
    public Sound getSFX(String sfxPath) {
        return assetManager.get(sfxPath);
    }

    /** Clears sound effect resources. */
    public void dispose() {
        assetManager.dispose();
    }
}
