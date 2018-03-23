package mx.itesm.dragon.Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;

import mx.itesm.dragon.Estados.Estado;
import mx.itesm.dragon.Juego;
import mx.itesm.dragon.Objetos.Enemigos;
import mx.itesm.dragon.Objetos.Fondo;
import mx.itesm.dragon.Objetos.Pantalla;
import mx.itesm.dragon.Objetos.Proyectil;
import mx.itesm.dragon.Objetos.Texto;


public class PJP extends Pantalla {

    private final Juego juego;

    private static final float ALTO_MAPA = 2560;
    private float timer;
    private Stage stageJuego;
    private Stage stagePausa;

    private Texture proyectil;

    private ArrayList<Proyectil> listaProyectil;
    private ArrayList<Enemigos> listaEnemigos;

    private Fondo fondo;
    private Fondo fondoPausa;

    private Image dragon;
    private ImageButton btnPausa;
    private ImageButton btnReanudar;

    private Estado estado;

    private InputMultiplexer multiplexer;

    //Marcador
    private int puntosJugador = 0;
    private String letras = "Score";
    private Texto puntos;  // Muestra los valores en pantalla
    private Texto texto;

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
        timer = 0;
        multiplexer = new InputMultiplexer();
        listaProyectil = new ArrayList<Proyectil>();
        stageJuego = new Stage(vista);
        stagePausa = new Stage(vista);
        fondo = new Fondo(new Texture("fondoNivel1.png"));
        fondoPausa = new Fondo(new Texture("cuadro transp.png"));
                btnPausa = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(
                        new Texture("BotonRegresar.png"))));
        btnReanudar = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(
                        new Texture("BotonRegresar.png"))));
        dragon = new Image(new Texture("Dragon.png"));

        proyectil = new Texture("BolaFuego.png");

        // Se anexan las Escenas al Multiplexor.
        multiplexer.addProcessor(stageJuego);
        multiplexer.addProcessor(stagePausa);
        Gdx.input.setInputProcessor(multiplexer);

        // Objeto que dibuja al texto
        puntos = new Texto();
        texto = new Texto();


    }

    public void setStagePausa() {
        // Posisción inicial de los elementos
        btnReanudar.setPosition(0,ALTO - btnReanudar.getHeight() - btnReanudar.getHeight());

        // Se anexan los Actores a la Escena.
        stagePausa.addActor(btnReanudar);

    }

    public void setStageJuego() {

        // Posisción inicial de los elementos
        btnPausa.setPosition(0,ALTO - btnPausa.getHeight());
        dragon.setPosition(ANCHO / 2 - dragon.getWidth() / 2, 0);

        dragon.addListener(new DragListener() {



           @Override
           public void touchDragged(InputEvent event, float x, float y, int pointer) {


               Vector3 v = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
               camara.unproject(v);
               if (v.y <= ALTO - btnPausa.getHeight() - dragon.getImageHeight() / 2) {
                   dragon.setPosition(v.x - dragon.getWidth() / 2  ,v.y - dragon.getHeight() / 2);
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
                    puntosJugador += 10; // incrementa los puntos del jugagor conforme pasa el tiempo;
                    fondo.render(batch);

                    //Marcador
                    texto.mostrarMensaje(batch,letras,ANCHO - ANCHO/8, ALTO);
                    puntos.mostrarMensaje(batch, Integer.toString(puntosJugador), ANCHO - ANCHO/8, ALTO-50);


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
                    fondoPausa.render(batch);
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
        timer += delta;
        if (timer >= 1){
            listaProyectil.add(new Proyectil(proyectil, dragon.getX() + dragon.getWidth() / 2 - proyectil.getWidth() / 2, dragon.getY() + dragon.getHeight()));
            timer = 0;
        }


        for (int i = 0; i < listaProyectil.size(); i++) {
            listaProyectil.get(i).mover();
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