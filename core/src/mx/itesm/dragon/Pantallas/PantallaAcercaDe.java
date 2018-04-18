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

public class PantallaAcercaDe extends Pantalla {

    // Escena para el menu.
    private Stage stageAcercaDe;

    // Fondo.
    private Fondo fondo;

    // Musica de fondo y sonidos
    private Music musica_f;
    private Sound regresar;

    PantallaAcercaDe(Juego juego) {
       super(juego);
    }

    @Override
    public void show() {
        crearAcercaDe();
    }

    private void crearAcercaDe() {
        // Creación escena Acerca De.
        stageAcercaDe = new Stage(vista);
        fondo = new Fondo(new Texture("backgrounds/about.png"));

        // Creacion de musica y sonido
        musica_f =  Gdx.audio.newMusic(Gdx.files.internal("music/preacerca.mp3"));
        regresar =  Gdx.audio.newSound(Gdx.files.internal("music/regresar.wav"));

        musica_f.setVolume(1);
        musica_f.play();
        musica_f.setLooping(true);

        // Creación de los botones a la Pantalla Acerca De.
        ImageButton btnRegresar = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("buttons/return.png"))),
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("buttons/returnPressed.png"))));
        ImageButton btnAna = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("buttons/aboutAna.png"))));
        ImageButton btnCharlotte = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("buttons/aboutCharlotte.png"))));
        ImageButton btnJorge = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("buttons/aboutJorge.png"))));
        ImageButton btnLuis = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("buttons/aboutLuis.png"))));
        ImageButton btnMarco = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("buttons/aboutMarco.png"))));

        // Posición de los botones.
        btnLuis.setPosition(85,900);
        btnCharlotte.setPosition(350,650);
        btnJorge.setPosition(85,400);
        btnAna.setPosition(350,200);
        btnMarco.setPosition(85,50);
        btnRegresar.setPosition(ANCHO - btnRegresar.getWidth() - 20,20);

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

        // TODO FALTA HABILITAR LOS BOTONES CON SU RESPECTIVA DESCRIPCION.

        // Se agregan elementos a la Pantalla Acerca De.
        stageAcercaDe.addActor(btnRegresar);
        stageAcercaDe.addActor(btnAna);
        stageAcercaDe.addActor(btnCharlotte);
        stageAcercaDe.addActor(btnJorge);
        stageAcercaDe.addActor(btnLuis);
        stageAcercaDe.addActor(btnMarco);

        // Indica quién escucha y atiende eventos.
        Gdx.input.setInputProcessor(stageAcercaDe);
    }


    @Override
    public void render(float delta) {
        // DIBUJAR.
        borrarPantalla();
        batch.begin();
            // Dibujar elementos de la pantalla.
            fondo.render(batch);
        batch.end();
        stageAcercaDe.draw();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        stageAcercaDe.dispose();
        batch.dispose();
        musica_f.dispose();
        regresar.dispose();
    }
}
