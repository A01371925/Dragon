package mx.itesm.dragon.Pantallas;

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

import mx.itesm.dragon.Juego;
import mx.itesm.dragon.Objetos.Fondo;
import mx.itesm.dragon.Objetos.Pantalla;

public class PantallaConfiguracion extends Pantalla {

    // Escena para el menu.
    private Stage stageConfiguracion;

    // Fondo.
    private Fondo fondo;

    PantallaConfiguracion(Juego juego) {
        super(juego);
    }

    @Override
    public void show() {
        crearConfiguracion();
    }

    private void crearConfiguracion() {
        // Creación escena Pantalla configuración.
        stageConfiguracion = new Stage(vista);

        Texture textureBackground = assetManager.get("backgrounds/settings.png");
        fondo = new Fondo(textureBackground);
        // Creación de los botones a la Pantalla Acerca De.
        Texture textureBtnReturn = assetManager.get("buttons/return.png");
        Texture textureBtnPressReturn = assetManager.get("buttons/returnPressed.png");
        Texture textureBtnSFX = assetManager.get("buttons/sfx.png");
        Texture textureBtnPressSFX = assetManager.get("buttons/sfxPressed.png");
        Texture textureBtnMusic = assetManager.get("buttons/music.png");
        Texture textureBtnPressMusic = assetManager.get("buttons/musicPressed.png");
        Texture textureBtnReset = assetManager.get("buttons/reset.png");
        Texture textureBtnPressReset = assetManager.get("buttons/resetPressed.png");
        ImageButton btnRegresar = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnReturn)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPressReturn)));
        ImageButton btnSFX = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnSFX)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPressSFX)));
        ImageButton btnMusic = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnMusic)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPressMusic)));
        ImageButton btnReset = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnReset)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPressReset)));
        Music musicSettings = assetManager.get("music/Kevin MacLeod _ Bumbly March_preconfig.mp3");
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
                Sound soundReturn = assetManager.get("music/regresar.wav");
                soundReturn.play();
                // Cambia de pantalla, solo lo puede hacerlo 'juego'.
                juego.setScreen(new LoadingScreen(juego, GameState.MENU));
            }
        });


        btnSFX.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                // Cambia de pantalla, solo lo puede hacerlo 'juego'.
            }
        });
        btnMusic.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                // Cambia de pantalla, solo lo puede hacerlo 'juego'.
            }
        });

        btnReset.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                // Cambia de pantalla, solo puede hacerlo 'juego'.
            }
        });

        // Se agregan elementos a la Pantalla Configuración.
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
            fondo.render(batch);
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
