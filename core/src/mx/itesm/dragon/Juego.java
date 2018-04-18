package mx.itesm.dragon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;

import mx.itesm.dragon.Pantallas.GameState;
import mx.itesm.dragon.Pantallas.LoadingIntroScreen;
import mx.itesm.dragon.Pantallas.PantallaMenuPrincipal;

public class Juego extends Game{

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

