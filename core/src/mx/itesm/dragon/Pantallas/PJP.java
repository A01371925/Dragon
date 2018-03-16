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
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;

import mx.itesm.dragon.Juego;
import mx.itesm.dragon.Objetos.Fondo;
import mx.itesm.dragon.Objetos.Pantalla;
import mx.itesm.dragon.Objetos.Proyectil;


public class PJP extends Pantalla {

    private final Juego juego;

    private ArrayList<Proyectil> listaProyectil;

    private static final float ALTO_MAPA = 2560;

    private Stage stageJuego;

    private Texture proyectil;

    private Fondo fondo;

    private Image dragon;
    private ImageButton btnRegresar;

    private InputMultiplexer multiplexer;

    public PJP(Juego juego) {
        this.juego = juego;
    }

    @Override
    public void show() {
        cargarStage();
    }

    private void cargarStage() {
        multiplexer = new InputMultiplexer();
        listaProyectil = new ArrayList<Proyectil>();
        stageJuego = new Stage(vista);
        fondo = new Fondo(new Texture("fondoNivel1.png"));
        btnRegresar = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(
                        new Texture("BotonRegresar.png"))));
        dragon = new Image(new Texture("Dragon.png"));
        proyectil = new Texture("BolaFuego.png");

        btnRegresar.setPosition(0,ALTO - btnRegresar.getHeight());
        dragon.setPosition(ANCHO / 2 - dragon.getWidth() / 2, 0);

        btnRegresar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                juego.setScreen(new PantallaMenuPrincipal(juego));
            }
        });



        dragon.addListener(new DragListener() {



            @Override
            public void touchDragged (InputEvent event, float x, float y, int pointer) {
                listaProyectil.add(new Proyectil(proyectil, dragon.getX() + dragon.getWidth() / 2 - proyectil.getWidth() / 2, dragon.getY() + dragon.getHeight()));
                // example code below for origin and position
                Vector3 v = new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
                camara.unproject(v);
                if (v.y <= ALTO - btnRegresar.getHeight() - dragon.getImageHeight() / 2) {
                    dragon.setPosition(v.x - dragon.getImageWidth() / 2, v.y - dragon.getImageHeight() / 2);
                } else {
                    dragon.setX(v.x - dragon.getImageWidth() / 2);
                }
            }
        });



        stageJuego.addActor(btnRegresar);
        stageJuego.addActor(dragon);

        multiplexer.addProcessor(stageJuego);

        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render(float delta) {
        borrarPantalla();
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
    }

    private void actualizarCamara() {
        // Depende de la posición del personaje. Siempre sigue al personaje
        float posY = dragon.getImageY();
        // Primera mitad de la pantalla.
        if (posY < ALTO/2 ) {
            camara.position.set(ANCHO / 2, ALTO / 2, 0);
        } else if (posY > ALTO_MAPA - ANCHO / 2) {   // Última mitad de la pantalla
            camara.position.set(camara.position.x,ALTO_MAPA - ANCHO/2,0);
        } /*else if (posY < ALTO_MAPA - ANCHO / 2){    // En 'medio' del mapa
            camara.position.set(camara.position.x, posY,0);
        }*/
        camara.update();
    }

    private void actualizarObjetos(float delta) {
        for (int i = 0; i < listaProyectil.size(); i++) {
            listaProyectil.get(i).mover(delta);
        }

        for (int i = 0; i < listaProyectil.size(); i++) {
            if (listaProyectil.get(i).sprite.getY() >= ALTO) {
                listaProyectil.remove(i);
            }
        }

        System.out.println(listaProyectil.size());

        fondo.mover(delta);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        stageJuego.dispose();
        proyectil.dispose();
    }
}