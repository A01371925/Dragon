package mx.itesm.dragon.Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;

import mx.itesm.dragon.Estados.Estado;
import mx.itesm.dragon.Juego;
import mx.itesm.dragon.Objetos.Fondo;
import mx.itesm.dragon.Objetos.Pantalla;
import mx.itesm.dragon.Objetos.Proyectil;


public class PJP extends Pantalla {

    private final Juego juego;

    private static final float ALTO_MAPA = 2560;
    private static final float HOLA = 1;
    private Stage stageJuego;
    private Stage stagePausa;

    private Texture proyectil;

    private ArrayList<Proyectil> listaProyectil;

    private Fondo fondo;

    private Image dragon;
    private Image trasparencia;
    private ImageButton btnPausa;
    private ImageButton btnReanudar;

    private Estado estado;

    private InputMultiplexer multiplexer;

    public PJP(Juego juego) {
        this.juego = juego;
        estado = Estado.JUGANDO;
    }

    @Override
    public void show() {
        borrarPantalla();
        inicializacion();
        setStagePausa();
        setStageJuego();
    }

    private void inicializacion() {
        // INICIALIZACIÓN DE COMPONENTES.
        multiplexer = new InputMultiplexer();
        listaProyectil = new ArrayList<Proyectil>();
        stageJuego = new Stage(vista);
        stagePausa = new Stage(vista);
        fondo = new Fondo(new Texture("fondoNivel1.png"));
        btnPausa = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(
                        new Texture("BotonRegresar.png"))));
        btnReanudar = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(
                        new Texture("BotonRegresar.png"))));
        dragon = new Image(new Texture("Dragon.png"));
        trasparencia = new Image(new Texture("fondoNegro.jpg"));
        proyectil = new Texture("BolaFuego.png");

        // Se anexan las Escenas al Multiplexor.
        multiplexer.addProcessor(stageJuego);
        multiplexer.addProcessor(stagePausa);
        Gdx.input.setInputProcessor(multiplexer);
    }

    public void setStagePausa() {
        trasparencia.setColor(0,0,0,0.05f);
        // Posisción inicial de los elementos
        trasparencia.setPosition(0,0);
        btnReanudar.setPosition(0,ALTO - btnReanudar.getHeight() - btnReanudar.getHeight());

        btnReanudar.addListener(new ClickListener() {
            @Override
            public boolean isPressed() {

                return false;
            }
        });

        // Se anexan los Actores a la Escena.
        stagePausa.addActor(trasparencia);
        stagePausa.addActor(btnReanudar);
    }

    public void setStageJuego() {

        // Posisción inicial de los elementos
        btnPausa.setPosition(0,ALTO - btnPausa.getHeight());
        dragon.setPosition(ANCHO / 2 - dragon.getWidth() / 2, 0);

        dragon.addListener(new DragListener() {
            @Override
            public void touchDragged (InputEvent event, float x, float y, int pointer) {
                // example code below for origin and position
                Vector3 v = new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
                camara.unproject(v);
                if (v.y <= ALTO - btnPausa.getHeight() - dragon.getImageHeight() / 2) {
                    dragon.setPosition(v.x - dragon.getImageWidth() / 2, v.y - dragon.getImageHeight() / 2);
                } else {
                    dragon.setX(v.x - dragon.getImageWidth() / 2);
                }
            }
        });

        // Se anexan los Actores a la Escena.
        stageJuego.addActor(btnPausa);
        stageJuego.addActor(dragon);
    }

    @Override
    public void render(float delta) {

        switch (estado) {
            case JUGANDO:
                actualizarObjetos(delta);
                actualizarCamara();
                moverCamara();
                batch.begin();
                    fondo.render(batch);
                    for (Proyectil p: listaProyectil) {
                        p.render(batch);
                    }
                batch.end();
                stageJuego.draw();

                if (btnPausa.isPressed()) {
                    estado = Estado.PAUSA;
                }

                break;
            case PAUSA:
                batch.begin();
                batch.end();
                stagePausa.draw();
                if (btnReanudar.isPressed()) {
                    estado = Estado.JUGANDO;
                }
                break;
        }
    }

    private void actualizarCamara() {
        // Depende de la posición del personaje. Siempre sigue al personaje
        float posY = dragon.getImageY();
        // Primera mitad de la pantalla.
        if (posY < ALTO/2 ) {
            camara.position.set(ANCHO / 2, ALTO / 2, 0);
        } else if (posY > ALTO_MAPA - ANCHO / 2) {   // Última mitad de la pantalla
            camara.position.set(camara.position.x,ALTO_MAPA - ANCHO/2,0);
        }
        camara.update();
    }

    private void actualizarObjetos(float delta) {
        listaProyectil.add(new Proyectil(proyectil, dragon.getX() + dragon.getWidth() / 2 - proyectil.getWidth() / 2, dragon.getY() + dragon.getHeight()));

        for (int i = 0; i < listaProyectil.size(); i++) {
            listaProyectil.get(i).mover(delta - 10);
        }

        for (int i = 0; i < listaProyectil.size(); i++) {
            if (listaProyectil.get(i).sprite.getY() >= ALTO) {
                listaProyectil.remove(i);
            }
        }
        fondo.mover(delta);
    }


    @Override
    public void pause() {
        estado = Estado.PAUSA;
    }

    @Override
    public void resume() {
        estado = Estado.PAUSA;
    }

    @Override
    public void dispose() {
        stageJuego.dispose();
        stagePausa.dispose();
        proyectil.dispose();
    }
}