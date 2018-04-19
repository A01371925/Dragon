package mx.itesm.dragon.Pantallas;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import mx.itesm.dragon.Juego;
import mx.itesm.dragon.Objetos.Pantalla;

public class LoadingIntroScreen extends Pantalla {

    private Texture loadingTexture;

    public LoadingIntroScreen(Juego juego) {
        super(juego);
    }

    @Override
    public void show() {
        loadingTexture = new Texture("tecLogo.jpg");
        loadResources();
    }

    private void loadResources() {
        assetManager.load("backgrounds/mainMenu.jpg", Texture.class);
        assetManager.load("textures/dragonPlay1.png", Texture.class);
        assetManager.load("buttons/play.png", Texture.class);
        assetManager.load("buttons/playPressed.png", Texture.class);
        assetManager.load("buttons/about.png", Texture.class);
        assetManager.load("buttons/aboutPressed.png", Texture.class);
        assetManager.load("buttons/settings.png", Texture.class);
        assetManager.load("buttons/settingsPressed.png", Texture.class);
        assetManager.load("music/premenu.mp3", Music.class);
        assetManager.load("music/jugar.wav", Sound.class);
        assetManager.load("music/config.wav", Sound.class);
    }


    @Override
    public void render(float delta) {
        borrarPantalla(1,1,1,1);
        updateLoad();
        batch.begin();
            batch.draw(loadingTexture, ANCHO / 2 - loadingTexture.getWidth() / 2, ALTO / 2 - loadingTexture.getHeight() / 2);

        batch.end();
    }

    private void updateLoad() {
        if (assetManager.update()) {
            juego.setScreen(new PantallaMenuPrincipal(juego));
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {


    }
}
