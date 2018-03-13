package mx.itesm.dragon.Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

import mx.itesm.dragon.Estados.Estado;
import mx.itesm.dragon.Estados.EstadoJuego;
import mx.itesm.dragon.Juego;
import mx.itesm.dragon.Objetos.Boton;
import mx.itesm.dragon.Objetos.Pantalla;
import mx.itesm.dragon.Objetos.Personaje;
import mx.itesm.dragon.Objetos.Fondo;

public class PantallaJugar extends Pantalla {

    private static final float ALTO_MAPA = 2560;
    private final Juego juego;

    private EstadoJuego estadoJuego;

    // Fondo.
    private Fondo fondo;

    // Objetos.
    private Personaje dragon;
    private Boton botonRegresar;

    // Multiplexor.
    private InputMultiplexer multiplexer;

    // InputProcessor.
    private InputProcessor procesadorDragon;
    private InputProcessor processorPausa;

    public PantallaJugar(Juego juego) {
        this.juego = juego;
    }

    @Override
    public void show() {
        stageJuego();
    }

    private void stageJuego() {
        // Creación de los botones a la Pantalla Acerca De.
        multiplexer = new InputMultiplexer();
        estadoJuego = new EstadoJuego();
        fondo = new Fondo(new Texture("fondoNivel1.png"));
        dragon = new Personaje(new Texture("Dragon.png"),ANCHO * .3f,0);
        botonRegresar = new Boton(new Texture("BotonRegresar.png"), 0, 0);


        procesadorDragon = new InputAdapter() {
            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                Vector3 v = new Vector3(screenX, screenY, 0);
                camara.unproject(v);
                dragon.sprite.setX(v.x - dragon.sprite.getWidth() / 2);
                if (dragon.sprite.getY() <= ALTO / 3){
                    dragon.sprite.setY(v.y - dragon.sprite.getHeight() / 1.5f);
                }
                if (dragon.sprite.getY() >= ALTO / 3){
                    dragon.sprite.setY(ALTO / 3);
                }


                return true;
            }
        } ;
        processorPausa = new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                Vector3 v = new Vector3(screenX, screenY, 0);
                camara.unproject(v);
                if (botonRegresar.sprite.getY() <= v.y && botonRegresar.sprite.getX() <= v.x) {
                    juego.setScreen(new PantallaMenuPrincipal(juego));
                }
                return true;
            }
        };
        multiplexer.addProcessor(procesadorDragon);
        multiplexer.addProcessor(processorPausa);
        Gdx.input.setInputProcessor(multiplexer);
    }


    @Override
    public void render(float delta) {
        // ACTUALIZAR.
        actualizarObjetos(delta);
        actualizarCamara();
        moverCamara();
        // DIBUJAR.
        borrarPantalla();
        batch.begin();
            // Dibujar elementos de la pantalla.
            fondo.render(batch);
            dragon.render(batch);
            botonRegresar.render(batch);
        batch.end();
    }

    private void actualizarCamara() {
        // Depende de la posición del personaje. Siempre sigue al personaje
        float posY = dragon.sprite.getY();
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
        dragon.sprite.setY(dragon.sprite.getY());
        botonRegresar.sprite.setY(ALTO - botonRegresar.sprite.getHeight());
    }

    @Override
    public void pause() {
        estadoJuego.setEstado(Estado.PAUSA);
    }

    @Override
    public void resume() {
        estadoJuego.setEstado(Estado.JUGANDO);
    }

    @Override
    public void dispose() {

    }

}
