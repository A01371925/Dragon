package mx.itesm.dragon.Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.AddAction;
import com.badlogic.gdx.scenes.scene2d.actions.AddListenerAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import mx.itesm.dragon.Juego;
import mx.itesm.dragon.Objetos.Fondo;
import mx.itesm.dragon.Objetos.Pantalla;


public class PJP extends Pantalla {

    private final Juego juego;

    private static final float ALTO_MAPA = 2560;

    private Stage stageJuego;

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
        stageJuego = new Stage(vista);
        fondo = new Fondo(new Texture("fondoNivel1.png"));
        btnRegresar = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(
                        new Texture("BotonRegresar.png"))));
        dragon = new Image(new Texture("Dragon.png"));
        //fuego = new Image(new Texture("BolaFuego.png"));


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
        //stageJuego.addActor(proyectil);

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
        fondo.mover(delta * 100);
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
    }
}
/*
if (dragon.sprite.getY() <= ALTO / 3){
                    dragon.sprite.setY(v.y - dragon.sprite.getHeight() / 1.5f);
                }
                if (dragon.sprite.getY() >= ALTO / 3){
                    dragon.sprite.setY(ALTO / 3);
                }


                return true;
 */