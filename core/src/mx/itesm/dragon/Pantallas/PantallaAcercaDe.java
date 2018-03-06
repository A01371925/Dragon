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

public class PantallaAcercaDe extends Pantalla {

    private final Juego juego;

    // Escena para el menu.
    private Stage stageAcercaDe;

    // Fondo.
    private Fondo fondo;

    public PantallaAcercaDe(Juego juego) {
        this.juego = juego;

    }

    @Override
    public void show() {
        crearAcercaDe();
    }

    private void crearAcercaDe() {
        // Creación escena Acerca De.
        stageAcercaDe = new Stage(vista);
        fondo = new Fondo(new Texture("FondoAcercaDe.png"));

        // Creación de los botones a la Pantalla Acerca De.
        ImageButton btnRegresar = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("BotonRegresar.png"))));
        ImageButton btnAna = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("Boton Acerca de Charlotte.png"))));
        ImageButton btnCharlotte = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("Boton Acerca de Ana.png"))));
        ImageButton btnJorge = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("Boton Acerca de Jorge.png"))));
        ImageButton btnLuis = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("Boton Acerca de Luis.png"))));
        ImageButton btnMarco = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("Boton Acerca de Marco.png"))));

        // Posición de los botones.
        btnRegresar.setPosition(0,ALTO - btnRegresar.getHeight());
        btnAna.setPosition(220,400);
        btnCharlotte.setPosition(220,800);
        btnJorge.setPosition(120,200);
        btnLuis.setPosition(120,1000);
        btnMarco.setPosition(120,600);

        // Detecta si el usuario hace click en algún actor.
        btnRegresar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                // Cambia de pantalla, solo lo puede hacerlo 'juego'.
                juego.setScreen(new PantallaMenuPrincipal(juego));
            }
        });

        //TODO FALTA HABILITAR LOS BOTONES CON SU RESPECTIVA DESCRIPCION.

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
    }
}
