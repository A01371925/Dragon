package mx.itesm.dragon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;

import mx.itesm.dragon.Screens.LoadingIntroScreen;

public class Main extends Game {

    private final AssetManager assetManager = new AssetManager();
    private Preferences preferences;

    @Override
    public void create() {
        // Pone la pantalla inicial.
        setScreen(new LoadingIntroScreen(this));
        Gdx.input.setCatchBackKey(true);
        Gdx.input.setCatchMenuKey(true);
        Preferences prefs = Gdx.app.getPreferences("preferenceS");
        Preferences prefm = Gdx.app.getPreferences("preferenceM");
        Preferences prefprog = Gdx.app.getPreferences("preferenceProg");
        prefprog.putInteger("progress", 1);
        prefs.putBoolean("onSound", true);
        prefm.putBoolean("onMusic", true);
        prefprog.flush();
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

