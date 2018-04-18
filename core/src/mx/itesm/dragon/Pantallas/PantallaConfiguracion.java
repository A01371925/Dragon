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

    // Musica de fondo
    private Music musica_f;
    private Sound regresar;

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
        fondo = new Fondo(new Texture("backgrounds/settings.png"));

        // Creación de los botones a la Pantalla Acerca De.
        ImageButton btnRegresar = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("buttons/return.png"))),
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("buttons/returnPressed.png"))));
        ImageButton btnSFX = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("buttons/sfx.png"))),
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("buttons/sfxPressed.png"))));
        ImageButton btnMusic = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("buttons/music.png"))),
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("buttons/musicPressed.png"))));
        ImageButton btnReset = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("buttons/reset.png"))),
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("buttons/resetPressed.png"))));

        musica_f = Gdx.audio.newMusic(Gdx.files.internal("music/Kevin MacLeod _ Bumbly March_preconfig.mp3"));
        regresar = Gdx.audio.newSound(Gdx.files.internal("music/regresar.wav"));

        musica_f.setVolume(1);
        musica_f.play();
        musica_f.setLooping(true);


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
                regresar.play();
                // Cambia de pantalla, solo lo puede hacerlo 'juego'.
                juego.setScreen(new PantallaMenuPrincipal(juego));
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
        musica_f.dispose();
        regresar.dispose();
    }
}
