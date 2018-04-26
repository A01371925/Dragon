package mx.itesm.dragon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;

import mx.itesm.dragon.Screens.LoadingIntroScreen;

public class Main extends Game {

    private final AssetManager assetManager = new AssetManager();

    @Override
    public void create() {
        // Pone la pantalla inicial.
        setScreen(new LoadingIntroScreen(this));
        Preferences prefs = Gdx.app.getPreferences("preferenceS");
        Preferences prefm = Gdx.app.getPreferences("preferenceM");
        prefs.putBoolean("onSound", true);
        prefm.putBoolean("onMusic", true);
        prefs.flush();
        prefm.flush();
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }
}

