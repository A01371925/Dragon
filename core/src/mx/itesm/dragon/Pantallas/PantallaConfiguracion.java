package mx.itesm.dragon.Pantallas;

import com.badlogic.gdx.Gdx;
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

    private final Juego juego;

    // Escena para el menu.
    private Stage stageConfiguracion;

    // Fondo.
    private Fondo fondo;

    public PantallaConfiguracion(Juego juego) {
        this.juego = juego;
    }

    @Override
    public void show() {
        crearConfiguracion();
    }

    private void crearConfiguracion() {
        // Creación escena Pantalla configuración.
        stageConfiguracion = new Stage(vista);
        fondo = new Fondo(new Texture("FondoConfiguracion.png"));

        // Creación de los botones a la Pantalla Acerca De.
        ImageButton btnRegresar = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("BotonRegresar.png"))));

        /* TODO INCORPORAR LAS TEXTURAS DE LOS BOTONTES DE EFECTOS Y MUSICA.
        ImageButton btnSFX = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture(""))));
        ImageButton btnMusic = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture(""))));
        */

        // Posición de los botones.
        btnRegresar.setPosition(0,ALTO - btnRegresar.getHeight());
        // TODO btnSFX.setPosition(); POR DEFINIR.
        // TODO btnMusic.setPosition(); POR DEFINIR.

        // Detecta si el usuario hace click en algún actor.
        btnRegresar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                // Cambia de pantalla, solo lo puede hacerlo 'juego'.
                juego.setScreen(new PantallaMenuPrincipal(juego));
            }
        });

        /* TODO FALTA HABILITAR LOS BOTONES DE EFECTOS Y MUSICA.
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
        */

        // Se agregan elementos a la Pantalla Configuración.
        stageConfiguracion.addActor(btnRegresar);

        /* TODO DESCOMENTE SOLO CUANDO SE TENGA LA POSICION Y TEXTURA DEL BOTON.
        stageConfiguracion.addActor(btnSFX);
        stageConfiguracion.addActor(btnMusic);
        */

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
    }
}
