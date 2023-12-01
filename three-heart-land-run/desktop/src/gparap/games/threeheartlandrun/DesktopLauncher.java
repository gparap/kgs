package gparap.games.threeheartlandrun;

import static gparap.games.threeheartlandrun.ThreeHeartLandRun.MAX_HEIGHT;
import static gparap.games.threeheartlandrun.ThreeHeartLandRun.MAX_WIDTH;
import static gparap.games.threeheartlandrun.ThreeHeartLandRun.MIN_HEIGHT;
import static gparap.games.threeheartlandrun.ThreeHeartLandRun.MIN_WIDTH;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(60);
        config.setTitle("Three-Heart Land Run");
        config.setWindowedMode(MIN_WIDTH, MIN_HEIGHT);
        config.setWindowSizeLimits(MIN_WIDTH, MIN_HEIGHT, MAX_WIDTH, MAX_HEIGHT);
        new Lwjgl3Application(new ThreeHeartLandRun(), config);
    }
}
