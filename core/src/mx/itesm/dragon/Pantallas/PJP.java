package mx.itesm.dragon.Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.ArrayList;
import java.util.Random;

import mx.itesm.dragon.Estados.Estado;
import mx.itesm.dragon.Juego;
import mx.itesm.dragon.Objetos.AnimatedImage;
import mx.itesm.dragon.Objetos.Dragon;
import mx.itesm.dragon.Objetos.Enemigos;
import mx.itesm.dragon.Objetos.Fondo;
import mx.itesm.dragon.Objetos.Pantalla;
import mx.itesm.dragon.Objetos.Proyectil;
import mx.itesm.dragon.Objetos.Texto;
import mx.itesm.dragon.Objetos.Vida;

public class PJP extends Pantalla {

    private final Juego juego;

    private static final float ALTO_MAPA = 2560;

    private float timerProyectil;
    private float timerFlecha;

    private Random random;

    private Stage stageJuego;
    private Stage stagePausa;
    private Stage stageGanar;
    private Stage stagePerder;

    private Texture proyectil;
    private Texture flecha;
    private Texture pocima;

    private ArrayList<Proyectil> listaProyectil;
    private ArrayList<Enemigos> listaFlechas;
    private ArrayList<Vida> listaVidas;

    private Fondo fondo;
    private Fondo fondoPausa;
    private Fondo fondoGanar;
    private Fondo fondoPerder;

    private Vida vida;

    private Dragon dragonAnimado;

    private AnimatedImage dragon;
    private Image barraVida;
    private Image v1,v2,v3,v4;
    private ImageButton btnPausa;
    private ImageButton btnReanudar;
    private ImageButton btnMusica;
    private ImageButton btnMenu;
    private ImageButton btnSFX;
    private ImageButton btnReiniciar;
    private ImageButton btnMenuPerder;
    private ImageButton btnMenuGanar;
    private ImageButton btnSigNivel;

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
    private String letras;
    private Texto puntos;  // Muestra los valores en pantalla
    private Texto texto;
    private float timerVida;


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
        setStagePerder();
        setStageGanar();
    }

    private void inicializacion() {
        // INICIALIZACIÓN DE COMPONENTES.
        initJuego();
        initPausa();
        initMusica();
        initPerder();
        initGanar();
        Gdx.input.setInputProcessor(multiplexer);
    }

    private void initMusica() {
        // Música y SFX
        musica_f = Gdx.audio.newMusic(Gdx.files.internal("Hyrule Field - The Legend of Zelda Twilight Princess.mp3"));
        flecha_s = Gdx.audio.newSound(Gdx.files.internal("flecha.wav"));
        colision = Gdx.audio.newSound(Gdx.files.internal("colision.wav"));
        fuego = Gdx.audio.newSound(Gdx.files.internal("fuego.wav"));
        pausa = Gdx.audio.newSound(Gdx.files.internal("pausa.wav"));
        reanudar = Gdx.audio.newSound(Gdx.files.internal("reanudar.wav"));
        impacto = Gdx.audio.newSound(Gdx.files.internal("impacto.wav"));

        // Reproducir música de fondo
        musica_f.setVolume(.5f);
        musica_f.play();
        musica_f.setLooping(true);
    }

    private void initPausa() {
        stagePausa = new Stage(vista);
        fondoPausa = new Fondo(new Texture("FondoPausa.png"));

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
        btnSFX = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("Boton SFX.png"))),
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("Boton SFX Presionado.png"))));
        btnMenu = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(
                        new Texture("BotonMenu1.png"))),
                new TextureRegionDrawable(new TextureRegion(
                        new Texture("BotonMenu2.png"))));

        // Se anexan las Escenas al Multiplexor.
        multiplexer.addProcessor(stagePausa);
    }

    private void initJuego() {
        stageJuego = new Stage(vista);
        timerProyectil = 0;
        timerFlecha = 0;
        timerVida = 0;
        multiplexer = new InputMultiplexer();
        listaProyectil = new ArrayList<Proyectil>();
        listaFlechas = new ArrayList<Enemigos>();
        listaVidas = new ArrayList<Vida>();
        random = new Random();

        // Objeto que dibuja al texto
        letras = "Score";
        puntos = new Texto();
        texto = new Texto();

        barraVida = new Image(new Texture("barraVida.png"));
        v1 = new Image(new Texture("Vida.png"));
        v2 = new Image(new Texture("Vida.png"));
        v3 = new Image(new Texture("Vida.png"));
        v4 = new Image(new Texture("Vida.png"));
        btnPausa = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(
                        new Texture("botonPausa.png"))));

        dragonAnimado = new Dragon("framesDragon.png");
        dragon = new AnimatedImage(dragonAnimado.animacion());

        fondo = new Fondo(new Texture("fondoNivel1.png"));
        proyectil = new Texture("BolaFuego.png");
        flecha = new Texture("Flecha.png");
        pocima = new Texture("Pocima.png");
        vida = new Vida(pocima,0,0);

        // Se anexan las Escenas al Multiplexor.
        multiplexer.addProcessor(stageJuego);
    }

    private void initGanar(){
        stageGanar = new Stage(vista);
        fondoGanar = new Fondo(new Texture("fondoNegro.jpg"));
        /*btnSigNivel = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("BotonReset.png"))),
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("BotonResetPresionado.png"))));*/
        btnMenuGanar = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(
                        new Texture("BotonMenu1.png"))),
                new TextureRegionDrawable(new TextureRegion(
                        new Texture("BotonMenu2.png"))));

        multiplexer.addProcessor(stageGanar);

    }

    private void initPerder(){
        stagePerder = new Stage(vista);
        fondoPerder = new Fondo(new Texture("fondoNegro.jpg"));
        btnReiniciar = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("BotonReset.png"))),
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("BotonResetPresionado.png"))));
        btnMenuPerder = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(
                        new Texture("BotonMenu1.png"))),
                new TextureRegionDrawable(new TextureRegion(
                        new Texture("BotonMenu2.png"))));

        multiplexer.addProcessor(stagePerder);



    }

    private void setStagePausa() {
        // Posisción inicial de los elementos
        btnReanudar.setPosition(ANCHO / 3,ALTO - btnReanudar.getHeight() * 2.3f);
        btnMusica.setPosition(ANCHO / 8,btnReanudar.getY() - 300);
        btnSFX.setPosition(btnMusica.getX() + 330, btnMusica.getY());
        btnMenu.setPosition(ANCHO / 3,ALTO / 6);

        btnMenu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                juego.setScreen(new PantallaMenuPrincipal(juego));
            }
        });
        stagePausa.addActor(btnReanudar);
        stagePausa.addActor(btnMusica);
        stagePausa.addActor(btnSFX);
        stagePausa.addActor(btnMenu);


    }

    private void setStageJuego() {
        // Posisción inicial de los elementos
        btnPausa.setPosition(0,ALTO - btnPausa.getHeight());
        barraVida.setPosition( ANCHO / 2 - barraVida.getWidth() / 2, ALTO - barraVida.getHeight() - barraVida.getHeight() / 2);
        dragon.setPosition(ANCHO / 2 - dragon.getWidth() / 2, 0);
        v1.setPosition(barraVida.getX(),barraVida.getY());
        v2.setPosition(barraVida.getX() + v1.getWidth() + 8, barraVida.getY());
        v3.setPosition(v2.getX() + v2.getWidth() + 8, barraVida.getY());
        v4.setPosition(v3.getX() + v3.getWidth() + 8, barraVida.getY());

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
        stageJuego.addActor(barraVida);
        stageJuego.addActor(v1);
        stageJuego.addActor(v2);
        stageJuego.addActor(v3);
        stageJuego.addActor(v4);
        stageJuego.addActor(dragon);



    }

    private void setStageGanar(){
        //btnSigNivel.setPosition();
        btnMenuGanar.setPosition(ANCHO / 3,ALTO - btnReanudar.getHeight() * 2.3f - btnReiniciar.getHeight());
        btnMenuGanar.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                juego.setScreen(new PantallaMenuPrincipal(juego));
            }
        });


        stageGanar.addActor(btnMenuGanar);

    }

    private void setStagePerder(){
        //btnReiniciar.setPosition(ANCHO / 3,ALTO - btnReanudar.getHeight() * 2.3f);

        btnMenuPerder.setPosition(ANCHO / 3,ALTO - btnReanudar.getHeight() * 2.3f - btnReiniciar.getHeight());

        btnMenuPerder.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                juego.setScreen(new PantallaMenuPrincipal(juego));
            }
        });


        stagePerder.addActor(btnMenuPerder);
        //stagePerder.addActor(btnReiniciar);




    }


    @Override
    public void render(float delta) {
        switch (estado) {
            case JUGANDO:
                actualizarObjetos(delta);
                stagePausa.unfocusAll();
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
                for (Vida v : listaVidas){
                    v.render(batch);
                }

                for (Enemigos e: listaFlechas) {
                    //flecha_s.play();
                    e.render(batch);
                }
                batch.end();
                if (btnPausa.isPressed()) {
                    pausa.play();
                    estado = Estado.PAUSA;
                }
                stageJuego.draw();
                if(vida.getVidas() == 0){
                    estado = Estado.PERDER;
                }
                /*
                if(vidaBoss == 0){
                    estado = Estado.GANAR;

                }*/
                break;
            case PAUSA:
                batch.begin();
                fondoPausa.render(batch);
                batch.end();
                if (btnReanudar.isPressed()) {
                    reanudar.play();
                    estado = Estado.JUGANDO;
                }
                stagePausa.draw();
                break;
            case PERDER:
                batch.begin();
                fondoPerder.render(batch);
                texto.mostrarMensaje(batch,letras,ANCHO / 2, ALTO - ALTO / 4);
                puntos.mostrarMensaje(batch, Integer.toString(puntosJugador), ANCHO / 2, ALTO - ALTO /4 - 50);
                batch.end();

                stagePerder.draw();
                break;
            case GANAR:
                batch.begin();
                fondoGanar.render(batch);
                texto.mostrarMensaje(batch,letras,ANCHO / 2, ALTO - ALTO / 4);
                puntos.mostrarMensaje(batch, Integer.toString(puntosJugador), ANCHO / 2, ALTO - ALTO /4 - 50);
                batch.end();

                stageGanar.draw();
                break;
        }
    }

    private void actualizarObjetos(float delta) {
        actualizarFondo(delta * 5);
        actualizarProyectiles(delta);
        actualizarEnemigos(delta);
        actualizarColisiones(delta);
        actualizarPersonaje(delta);
        actualizarCamara();
        actualizarVida(delta);
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
        moverCamara();
    }

    private void actualizarPersonaje(float delta) {
        dragon.act(delta);
    }

    private void actualizarFondo(float delta) {
        fondo.mover(delta);
    }

    private void actualizarColisiones(float delta) {
        for (int i = 0; i < listaVidas.size(); i++) {
            Vida pocima = listaVidas.get(i);
            Rectangle rectDragon = new Rectangle(dragon.getX() + 151,dragon.getY(),151,dragon.getHeight() / 2);
            Rectangle rectPocima = pocima.getSprite().getBoundingRectangle();

            if (rectDragon.overlaps(rectPocima)) {
                listaVidas.remove(pocima);

                switch (vida.getVidas()){
                    case 1:
                        vida.setVidas(vida.getVidas()+1);
                        v2.setVisible(true);
                        break;
                    case 2:
                        vida.setVidas(vida.getVidas()+1);
                        v3.setVisible(true);
                        break;
                    case 3:
                        vida.setVidas(vida.getVidas()+1);
                        v4.setVisible(true);
                        break;
                    default:
                        break;
                }


            }
        }
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
            Rectangle rectDragon = new Rectangle(dragon.getX() + 151,dragon.getY(),151,dragon.getHeight() / 2);
            Rectangle rectFlechas = flechas.getSprite().getBoundingRectangle();

            if (rectDragon.overlaps(rectFlechas)) {
                impacto.play();
                switch (vida.getVidas()) {
                    case 1:
                        vida.setVidas(vida.getVidas() - 1);
                        v1.setVisible(false);
                        break;
                    case 2:
                        vida.setVidas(vida.getVidas() - 1);
                        v2.setVisible(false);
                        break;
                    case 3:
                        vida.setVidas(vida.getVidas() - 1);
                        v3.setVisible(false);
                        break;
                    case 4:
                        vida.setVidas(vida.getVidas() - 1);
                        v4.setVisible(false);
                        break;
                }
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
        if (timerProyectil >= .350f){
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

    private void actualizarVida(float delta){
        timerVida += delta;
        int randomX = random.nextInt((int) ANCHO - pocima.getWidth());
        if (timerVida >= 5){
            listaVidas.add(new Vida(pocima,  randomX, ALTO));
            timerVida = 0;
        }
        for (int i = 0; i < listaVidas.size(); i++) {
            listaVidas.get(i).mover();
        }
        for (int i = 0; i < listaVidas.size(); i++) {
            if (listaVidas.get(i).getSprite().getY() >= ALTO) {
                listaVidas.remove(i);
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
        stagePerder.dispose();
        stageGanar.dispose();
        proyectil.dispose();
        flecha.dispose();
        musica_f.dispose();
        flecha_s.dispose();
        colision.dispose();
        fuego.dispose();
        pausa.dispose();
        reanudar.dispose();
        impacto.dispose();


    }
}