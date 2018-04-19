package mx.itesm.dragon.Screens;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import mx.itesm.dragon.Main;

public class LoadingIntroScreen extends GenericScreen {

    private Texture textureLogo;

    public LoadingIntroScreen(Main game) {
        super(game);
    }

    @Override
    public void show() {
        textureLogo = new Texture("textures/tecLogo.jpg");
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
            batch.draw(textureLogo, ANCHO / 2 - textureLogo.getWidth() / 2, ALTO / 2 - textureLogo.getHeight() / 2);
        batch.end();
    }

    private void updateLoad() {
        if (assetManager.update()) {
            game.setScreen(new MenuScreen(game));
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
        textureLogo.dispose();
    }
}
