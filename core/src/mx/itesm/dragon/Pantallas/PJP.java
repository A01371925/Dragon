package mx.itesm.dragon.Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;
import java.util.Random;

import mx.itesm.dragon.Estados.Estado;
import mx.itesm.dragon.Juego;
import mx.itesm.dragon.Objetos.Enemigos;
import mx.itesm.dragon.Objetos.Fondo;
import mx.itesm.dragon.Objetos.Pantalla;
import mx.itesm.dragon.Objetos.Proyectil;
import mx.itesm.dragon.Objetos.Texto;

import static com.badlogic.gdx.graphics.g2d.Batch.X1;
import static com.badlogic.gdx.graphics.g2d.Batch.X2;
import static com.badlogic.gdx.graphics.g2d.Batch.X3;
import static com.badlogic.gdx.graphics.g2d.Batch.X4;
import static com.badlogic.gdx.graphics.g2d.Batch.Y1;
import static com.badlogic.gdx.graphics.g2d.Batch.Y2;
import static com.badlogic.gdx.graphics.g2d.Batch.Y3;
import static com.badlogic.gdx.graphics.g2d.Batch.Y4;


public class PJP extends Pantalla {

    private final Juego juego;

    private static final float ALTO_MAPA = 2560;

    private float timerProyectil;
    private float timerFlecha;

    private Random random;

    private Stage stageJuego;
    private Stage stagePausa;

    private Texture proyectil;
    private Texture flecha;

    private ArrayList<Proyectil> listaProyectil;
    private ArrayList<Enemigos> listaFlechas;

    private Fondo fondo;
    private Fondo fondoPausa;

    private Image dragon;
    private ImageButton btnPausa;
    private ImageButton btnReanudar;
    private ImageButton btnMusica;
    private ImageButton btnMenu;

    private Estado estado;

    private InputMultiplexer multiplexer;

    private Music musica_f;
    private Sound colision;
    private Sound impacto;
    private Sound fuego;
    private Sound flecha_s;
    private Sound reanudar;
    private Sound pausa;

    // Marcador.
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
        timerProyectil = 0;
        timerFlecha = 0;
        multiplexer = new InputMultiplexer();
        listaProyectil = new ArrayList<Proyectil>();
        listaFlechas = new ArrayList<Enemigos>();
        random = new Random();

        // Musica
        musica_f = Gdx.audio.newMusic(Gdx.files.internal("Hyrule Field - The Legend of Zelda Twilight Princess.mp3"));
        flecha_s = Gdx.audio.newSound(Gdx.files.internal("flecha.wav"));
        colision = Gdx.audio.newSound(Gdx.files.internal("colision.wav"));
        fuego = Gdx.audio.newSound(Gdx.files.internal("fuego.wav"));
        pausa = Gdx.audio.newSound(Gdx.files.internal("pausa.wav"));
        reanudar = Gdx.audio.newSound(Gdx.files.internal("reanudar.wav"));
        impacto = Gdx.audio.newSound(Gdx.files.internal("impacto.wav"));

        musica_f.setVolume(.5f);
        musica_f.play();
        musica_f.setLooping(true);

        // Objeto que dibuja al texto
        puntos = new Texto();
        texto = new Texto();

        stageJuego = new Stage(vista);
        stagePausa = new Stage(vista);
        fondo = new Fondo(new Texture("fondoNivel1.png"));
        //Pausa
        fondoPausa = new Fondo(new Texture("cuadro transp.png"));
        btnPausa = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(
                        new Texture("BotonRegresar.png"))));
        btnReanudar = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(
                        new Texture("BotonReanudar1.png"))),
                new TextureRegionDrawable(new TextureRegion(
                        new Texture("BotonReanudar2.png"))));
        btnMusica = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(
                        new Texture("BotonMusica1.png"))),
                new TextureRegionDrawable(new TextureRegion(
                        new Texture("BotonMusica2.png"))));
        btnMenu = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(
                        new Texture("BotonMenu1.png"))));

        dragon = new Image(new Sprite(new Texture("Dragon.png")));

        proyectil = new Texture("BolaFuego.png");
        flecha = new Texture("Flecha.png");

        // Se anexan las Escenas al Multiplexor.
        multiplexer.addProcessor(stageJuego);
        multiplexer.addProcessor(stagePausa);
        Gdx.input.setInputProcessor(multiplexer);




    }

    private void setStagePausa() {
        // Posisción inicial de los elementos
        btnMenu.setPosition(ANCHO/3 - 10,ALTO/ 8);
        btnReanudar.setPosition(ANCHO/3,ALTO - btnReanudar.getHeight() * 2);
        btnMusica.setPosition(ANCHO/3,ALTO - btnReanudar.getHeight() * 3.5f);


        // Se anexan los Actores a la Escena.
        stagePausa.addActor(btnMenu);
        stagePausa.addActor(btnMusica);
        stagePausa.addActor(btnReanudar);


    }

    private void setStageJuego() {
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
                puntosJugador += 10; // incrementa los puntos del jugador conforme pasa el tiempo;
                fondo.render(batch);


                //Marcador
                texto.mostrarMensaje(batch,letras,ANCHO - ANCHO/8, ALTO);
                puntos.mostrarMensaje(batch, Integer.toString(puntosJugador), ANCHO - ANCHO/8, ALTO-50);

                for (Proyectil p: listaProyectil) {
                    //fuego.play();
                    p.render(batch);
                }

                for (Enemigos e: listaFlechas) {
                    //flecha_s.play();
                    e.render(batch);
                }
                batch.end();
                stageJuego.draw();

                if (btnPausa.isPressed()) {
                    pausa.play();
                    estado = Estado.PAUSA;
                }
                if (btnMenu.isPressed()){
                    juego.setScreen(new PantallaMenuPrincipal(juego));}
                break;
            case PAUSA:
                batch.begin();
                fondoPausa.render(batch);
                batch.end();
                stagePausa.draw();
                if (btnReanudar.isPressed()) {
                    reanudar.play();
                    estado = Estado.JUGANDO;
                }
                if (btnMenu.isPressed()){
                    juego.setScreen(new PantallaMenuPrincipal(juego));

                }

                break;
        }
    }

    private void actualizarCamara() {
        // Depende de la posición del personaje. Siempre sigue al personaje
        float y = dragon.getImageY();
        // Primera mitad de la pantalla.
        if (y < ALTO/2 ) {
            camara.position.set(ANCHO / 2, ALTO / 2, 0);
        } else if (y > ALTO_MAPA - ANCHO / 2) {   // Última mitad de la pantalla
            camara.position.set(camara.position.x,ALTO_MAPA - ANCHO/2,0);
        }
        camara.update();
    }

    private void actualizarObjetos(float delta) {
        actualizarFondo(delta);
        actualizarProyectiles(delta);
        actualizarEnemigos(delta);
        actualizarColisiones(delta);
    }

    private void actualizarFondo(float delta) {
        fondo.mover(delta);
    }

    private void actualizarColisiones(float delta) {
        for (int i = 0; i < listaProyectil.size(); i++) {
            Proyectil proyectil = listaProyectil.get(i);
            for (int j = 0; j < listaFlechas.size(); j++) {
                Enemigos flechas = listaFlechas.get(j);
                Rectangle rectProyectil = proyectil.getSprite().getBoundingRectangle();
                Rectangle rectFlechas = flechas.getSprite().getBoundingRectangle();
                if (rectProyectil.overlaps(rectFlechas)) {
                    colision.play();
                    listaProyectil.remove(proyectil);
                    listaFlechas.remove(flechas);
                    break;
                }
            }
        }
        for (int i = 0; i < listaFlechas.size(); i++) {
            Enemigos flechas = listaFlechas.get(i);
            Rectangle rectDragon = new Rectangle(dragon.getX(),dragon.getY(),dragon.getWidth(),dragon.getHeight());
            Rectangle rectFlechas = flechas.getSprite().getBoundingRectangle();
            if (rectDragon.overlaps(rectFlechas)) {
                impacto.play();
                listaFlechas.remove(i);
                break;
            }
        }
    }

    private void actualizarEnemigos(float delta) {
        timerFlecha += delta;
        int randomX = random.nextInt((int) ANCHO - flecha.getWidth());

        if (timerFlecha >= .75f) {
            listaFlechas.add(new Enemigos(flecha, randomX, ALTO));
            timerFlecha = 0;
        }

        for (int i = 0; i < listaFlechas.size(); i++) {
            listaFlechas.get(i).mover();
        }

        for (int i = 0; i < listaFlechas.size(); i++) {
            if (listaFlechas.get(i).getSprite().getHeight() <= 0) {
                listaFlechas.remove(i);
            }
        }
    }

    private void actualizarProyectiles(float delta) {
        timerProyectil += delta;
        if (timerProyectil >= .150f){
            listaProyectil.add(new Proyectil(proyectil, dragon.getX() + dragon.getWidth() / 2 - proyectil.getWidth() / 2, dragon.getY() + dragon.getHeight()));
            timerProyectil = 0;
        }
        for (int i = 0; i < listaProyectil.size(); i++) {
            listaProyectil.get(i).mover();
        }
        for (int i = 0; i < listaProyectil.size(); i++) {
            if (listaProyectil.get(i).getSprite().getY() >= ALTO) {
                listaProyectil.remove(i);
            }
        }
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
        musica_f.dispose();
        flecha_s.dispose();
        colision.dispose();
        fuego.dispose();
        pausa.dispose();
        reanudar.dispose();
        impacto.dispose();

    }
}