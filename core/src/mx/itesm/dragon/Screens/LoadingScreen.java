package mx.itesm.dragon.Screens;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import mx.itesm.dragon.Levels.LevelThree;
import mx.itesm.dragon.Levels.LevelTwo;
import mx.itesm.dragon.States.ScreenState;
import mx.itesm.dragon.Main;
import mx.itesm.dragon.Levels.LevelOne;

public class LoadingScreen extends GenericScreen {

    private ScreenState gameState;

    private Texture loadingTexture;

    public LoadingScreen(Main game, ScreenState gameState) {
        super(game);
        this.gameState = gameState;
    }

    @Override
    public void show() {
        borrarPantalla();
        loadingTexture = new Texture("backgrounds/loading.jpg");
        load();
    }

    private void load() {
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
                assetManager.load("backgrounds/about.png", Texture.class);
                assetManager.load("buttons/return.png", Texture.class);
                assetManager.load("buttons/returnPressed.png", Texture.class);
                assetManager.load("buttons/aboutAna.png", Texture.class);
                assetManager.load("buttons/aboutCharlotte.png", Texture.class);
                assetManager.load("buttons/aboutJorge.png", Texture.class);
                assetManager.load("buttons/aboutLuis.png", Texture.class);
                assetManager.load("buttons/aboutMarco.png", Texture.class);
                assetManager.load("music/preacerca.mp3", Music.class);
                assetManager.load("music/regresar.wav", Sound.class);
                break;
            case LEVELS:
                assetManager.load("backgrounds/loading.jpg", Texture.class);
                assetManager.load("buttons/resume.png", Texture.class);
                assetManager.load("buttons/resumePressed.png", Texture.class);
                break;
            case LVL_ONE:
                assetManager.load("backgrounds/level1.png", Texture.class);
                assetManager.load("textures/healthBar.png", Texture.class);
                assetManager.load("textures/heart.png", Texture.class);
                assetManager.load("buttons/pause.png", Texture.class);
                assetManager.load("frames/dragon.png", Texture.class);
                assetManager.load("textures/potion.png", Texture.class);
                assetManager.load("backgrounds/pause.png", Texture.class);
                assetManager.load("buttons/resume.png", Texture.class);
                assetManager.load("buttons/resumePressed.png", Texture.class);
                assetManager.load("buttons/music.png", Texture.class);
                assetManager.load("buttons/musicPressed.png", Texture.class);
                assetManager.load("buttons/sfx.png", Texture.class);
                assetManager.load("buttons/sfxPressed.png", Texture.class);
                assetManager.load("buttons/mainMenu.png", Texture.class);
                assetManager.load("buttons/mainMenuPressed.png", Texture.class);
                assetManager.load("backgrounds/win.png", Texture.class);
                assetManager.load("backgrounds/gameOver.png", Texture.class);
                assetManager.load("buttons/reset.png", Texture.class);
                assetManager.load("buttons/resetPressed.png", Texture.class);
                assetManager.load("music/Hyrule Field - The Legend of Zelda Twilight Princess.mp3", Music.class);
                assetManager.load("music/flecha.wav", Sound.class);
                assetManager.load("music/colision.wav", Sound.class);
                assetManager.load("music/fuego.wav", Sound.class);
                assetManager.load("music/pausa.wav", Sound.class);
                assetManager.load("music/reanudar.wav", Sound.class);
                assetManager.load("music/impacto.wav", Sound.class);
                break;
            case LVL_TWO:
                assetManager.load("backgrounds/level1.png", Texture.class);
                assetManager.load("textures/healthBar.png", Texture.class);
                assetManager.load("textures/heart.png", Texture.class);
                assetManager.load("buttons/pause.png", Texture.class);
                assetManager.load("frames/dragon.png", Texture.class);
                assetManager.load("textures/potion.png", Texture.class);
                assetManager.load("backgrounds/pause.png", Texture.class);
                assetManager.load("buttons/resume.png", Texture.class);
                assetManager.load("buttons/resumePressed.png", Texture.class);
                assetManager.load("buttons/music.png", Texture.class);
                assetManager.load("buttons/musicPressed.png", Texture.class);
                assetManager.load("buttons/sfx.png", Texture.class);
                assetManager.load("buttons/sfxPressed.png", Texture.class);
                assetManager.load("buttons/mainMenu.png", Texture.class);
                assetManager.load("buttons/mainMenuPressed.png", Texture.class);
                assetManager.load("backgrounds/win.png", Texture.class);
                assetManager.load("backgrounds/gameOver.png", Texture.class);
                assetManager.load("buttons/reset.png", Texture.class);
                assetManager.load("buttons/resetPressed.png", Texture.class);
                assetManager.load("music/Hyrule Field - The Legend of Zelda Twilight Princess.mp3", Music.class);
                assetManager.load("music/flecha.wav", Sound.class);
                assetManager.load("music/colision.wav", Sound.class);
                assetManager.load("music/fuego.wav", Sound.class);
                assetManager.load("music/pausa.wav", Sound.class);
                assetManager.load("music/reanudar.wav", Sound.class);
                assetManager.load("music/impacto.wav", Sound.class);
                break;
            case LVL_THREE:
                assetManager.load("backgrounds/level1.png", Texture.class);
                assetManager.load("textures/healthBar.png", Texture.class);
                assetManager.load("textures/heart.png", Texture.class);
                assetManager.load("buttons/pause.png", Texture.class);
                assetManager.load("frames/dragon.png", Texture.class);
                assetManager.load("textures/potion.png", Texture.class);
                assetManager.load("backgrounds/pause.png", Texture.class);
                assetManager.load("buttons/resume.png", Texture.class);
                assetManager.load("buttons/resumePressed.png", Texture.class);
                assetManager.load("buttons/music.png", Texture.class);
                assetManager.load("buttons/musicPressed.png", Texture.class);
                assetManager.load("buttons/sfx.png", Texture.class);
                assetManager.load("buttons/sfxPressed.png", Texture.class);
                assetManager.load("buttons/mainMenu.png", Texture.class);
                assetManager.load("buttons/mainMenuPressed.png", Texture.class);
                assetManager.load("backgrounds/win.png", Texture.class);
                assetManager.load("backgrounds/gameOver.png", Texture.class);
                assetManager.load("buttons/reset.png", Texture.class);
                assetManager.load("buttons/resetPressed.png", Texture.class);
                assetManager.load("music/Hyrule Field - The Legend of Zelda Twilight Princess.mp3", Music.class);
                assetManager.load("music/flecha.wav", Sound.class);
                assetManager.load("music/colision.wav", Sound.class);
                assetManager.load("music/fuego.wav", Sound.class);
                assetManager.load("music/pausa.wav", Sound.class);
                assetManager.load("music/reanudar.wav", Sound.class);
                assetManager.load("music/impacto.wav", Sound.class);
                break;
            default:
        }
    }

    @Override
    public void render(float delta) {
        updateLoad();
        batch.begin();
            batch.draw(loadingTexture,0,0);
        batch.end();
    }

    private void updateLoad() {
        if (assetManager.update()) {
            switch (gameState) {
                case MENU:
                    game.setScreen(new MenuScreen(game, ScreenState.MENU));
                    break;
                case SETTINGS:
                    game.setScreen(new SettingsScreen(game));
                    break;
                case ABOUT:
                    game.setScreen(new AboutScreen(game));
                    break;
                case LEVELS:
                    game.setScreen(new LevelsScreen(game, ScreenState.LEVELS));
                    break;
                case LVL_ONE:
                    game.setScreen(new LevelOne(game, ScreenState.LVL_ONE));
                    break;
                case LVL_TWO:
                    game.setScreen(new LevelTwo(game, ScreenState.LVL_TWO));
                    break;
                case LVL_THREE:
                    game.setScreen(new LevelThree(game, ScreenState.LVL_THREE));
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
