package mx.itesm.dragon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;

import mx.itesm.dragon.Screens.SplashScreen;

public class Main extends Game {

    private final AssetManager assetManager = new AssetManager();
    private Preferences preferences;

    @Override
    public void create() {
        // Pone la pantalla inicial.
        Gdx.input.setCatchBackKey(true);
        Gdx.input.setCatchMenuKey(true);

        preferences = Gdx.app.getPreferences("Preferences");

        Preferences prefs = Gdx.app.getPreferences("preferenceS");
        Preferences prefm = Gdx.app.getPreferences("preferenceM");
        Preferences prefprog = Gdx.app.getPreferences("preferenceProg");
        prefprog.getInteger("progress", 1);
        prefs.getBoolean("onSound", true);
        prefm.getBoolean("onMusic", true);
        prefprog.flush();
        prefs.flush();
        prefm.flush();
        setScreen(new SplashScreen(this));

    }



    public AssetManager getAssetManager() {
        return assetManager;
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }
}

