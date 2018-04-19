package mx.itesm.dragon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import mx.itesm.dragon.Main;
import mx.itesm.dragon.States.ScreenState;
import mx.itesm.dragon.Utils.BackGround;

public class SettingsScreen extends GenericScreen {

    // Escena para el menu.
    private Stage stageConfiguracion;

    private ImageButton btnRegresar;
    private ImageButton btnSFX;
    private ImageButton btnMusic;
    private ImageButton btnReset;

    // BackGround.
    private BackGround backGround;
    private Texture textureBackground;

    private Texture textureBtnReturn;
    private Texture textureBtnPressReturn;
    private Texture textureBtnSFX;
    private Texture textureBtnPressSFX;
    private Texture textureBtnMusic;
    private Texture textureBtnPressMusic;
    private Texture textureBtnReset;
    private Texture textureBtnPressReset;
    private Music musicSettings;
    private Sound soundReturn;

    SettingsScreen(Main game) {
        super(game);
    }

    @Override
    public void show() {
        crearConfiguracion();
    }

    private void crearConfiguracion() {
        // Creación escena GenericScreen configuración.
        stageConfiguracion = new Stage(vista);

        //
        textureBackground = assetManager.get("backgrounds/settings.png");
        backGround = new BackGround(textureBackground);
        textureBtnReturn = assetManager.get("buttons/return.png");
        textureBtnPressReturn = assetManager.get("buttons/returnPressed.png");
        textureBtnSFX = assetManager.get("buttons/sfx.png");
        textureBtnPressSFX = assetManager.get("buttons/sfxPressed.png");
        textureBtnMusic = assetManager.get("buttons/music.png");
        textureBtnPressMusic = assetManager.get("buttons/musicPressed.png");
        textureBtnReset = assetManager.get("buttons/reset.png");
        textureBtnPressReset = assetManager.get("buttons/resetPressed.png");

        btnRegresar = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnReturn)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPressReturn)));
        btnSFX = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnSFX)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPressSFX)));
        btnMusic = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnMusic)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPressMusic)));
        btnReset = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnReset)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPressReset)));

        musicSettings = assetManager.get("music/Kevin MacLeod _ Bumbly March_preconfig.mp3");
        soundReturn = assetManager.get("music/regresar.wav");
        musicSettings.setVolume(1);
        musicSettings.play();
        musicSettings.setLooping(true);

        // Posición de los botones.
        btnReset.setPosition(ANCHO / 2 - btnReset.getWidth() / 2, ALTO / 2 + btnReset.getHeight() / 2);
        btnRegresar.setPosition(ANCHO - btnRegresar.getWidth() - 20,20);
        btnMusic.setPosition(40, ALTO / 3.5f);
        btnSFX.setPosition(ANCHO - btnSFX.getWidth() * 1.3f, btnMusic.getY());

        // Detecta si el usuario hace click en algún actor.
        btnRegresar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                soundReturn.play();
                // Cambia de pantalla, solo lo puede hacerlo 'game'.
                game.setScreen(new LoadingScreen(game, ScreenState.MENU));
            }
        });


        btnSFX.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                // Cambia de pantalla, solo lo puede hacerlo 'game'.
            }
        });
        btnMusic.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                // Cambia de pantalla, solo lo puede hacerlo 'game'.
            }
        });

        btnReset.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                // Cambia de pantalla, solo puede hacerlo 'game'.
            }
        });

        // Se agregan elementos a la GenericScreen Configuración.
        stageConfiguracion.addActor(btnRegresar);
        stageConfiguracion.addActor(btnSFX);
        stageConfiguracion.addActor(btnMusic);
        stageConfiguracion.addActor(btnMusic);
        stageConfiguracion.addActor(btnReset);


        // Indica quién escucha y atiende eventos.
        Gdx.input.setInputProcessor(stageConfiguracion);
    }


    @Override
    public void render(float delta) {
        // DIBUJAR.
        borrarPantalla();
        batch.begin();
            // Dibujar elementos de la pantalla.
            backGround.render(batch);
        batch.end();
        stageConfiguracion.draw();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        stageConfiguracion.dispose();
        batch.dispose();
        assetManager.unload("backgrounds/settings.png");
        assetManager.unload("buttons/return.png");
        assetManager.unload("buttons/returnPressed.png");
        assetManager.unload("buttons/sfx.png");
        assetManager.unload("buttons/sfxPressed.png");
        assetManager.unload("buttons/music.png");
        assetManager.unload("buttons/musicPressed.png");
        assetManager.unload("buttons/reset.png");
        assetManager.unload("buttons/resetPressed.png");
        assetManager.unload("music/Kevin MacLeod _ Bumbly March_preconfig.mp3");
        assetManager.unload("music/regresar.wav");
    }
}
