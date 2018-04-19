package mx.itesm.dragon;

import com.badlogic.gdx.assets.AssetManager;

import mx.itesm.dragon.Screens.LoadingIntroScreen;

public class Main extends com.badlogic.gdx.Game {

    private final AssetManager assetManager = new AssetManager();

    @Override
    public void create() {
        // Pone la pantalla inicial.
        setScreen(new LoadingIntroScreen(this));
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }
}

