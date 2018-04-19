package mx.itesm.dragon.Pantallas;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import mx.itesm.dragon.Juego;
import mx.itesm.dragon.Objetos.Pantalla;

public class LoadingScreen extends Pantalla {

    private GameState gameState;

    private Texture loadingTexture;

    public LoadingScreen(Juego juego, GameState gameState) {
        super(juego);
        this.gameState = gameState;
    }

    @Override
    public void show() {
        loadingTexture = new Texture("backgrounds/loading.jpg");
        loadResources();
    }

    private void loadResources() {
        switch (gameState) {
            case MENU:
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
                break;
            case SETTINGS:
                assetManager.load("backgrounds/settings.png", Texture.class);
                assetManager.load("buttons/return.png", Texture.class);
                assetManager.load("buttons/returnPressed.png", Texture.class);
                assetManager.load("buttons/sfx.png", Texture.class);
                assetManager.load("buttons/sfxPressed.png", Texture.class);
                assetManager.load("buttons/music.png", Texture.class);
                assetManager.load("buttons/musicPressed.png", Texture.class);
                assetManager.load("buttons/reset.png", Texture.class);
                assetManager.load("buttons/resetPressed.png", Texture.class);
                assetManager.load("music/Kevin MacLeod _ Bumbly March_preconfig.mp3", Music.class);
                assetManager.load("music/regresar.wav", Sound.class);
                break;
            case ABOUT:
                break;
            case LEVELS:
                break;
            case LVL_ONE:
                break;
            case LVL_TWO:
                break;
            case LVL_TRES:
                break;
            default:
        }
    }

    @Override
    public void render(float delta) {
        borrarPantalla();
        updateLoad();
        batch.begin();
            batch.draw(loadingTexture,0,0);
        batch.end();
    }

    private void updateLoad() {
        if (assetManager.update()) {
            switch (gameState) {
                case MENU:
                    juego.setScreen(new PantallaMenuPrincipal(juego));
                    break;
                case SETTINGS:
                    juego.setScreen(new PantallaConfiguracion(juego));
                    break;
                case ABOUT:
                    juego.setScreen(new PantallaAcercaDe(juego));
                    break;
                case LEVELS:
                    // TODO
                    break;
                case LVL_ONE:
                    juego.setScreen(new LevelOne(juego));
                    break;
                case LVL_TWO:
                    // TODO
                    break;
                case LVL_TRES:
                    // TODO
                    break;
                default:
            }
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
