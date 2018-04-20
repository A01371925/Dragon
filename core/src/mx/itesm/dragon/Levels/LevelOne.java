package mx.itesm.dragon.Levels;

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

import mx.itesm.dragon.Main;
import mx.itesm.dragon.Screens.LoadingScreen;
import mx.itesm.dragon.States.GameState;
import mx.itesm.dragon.Screens.MenuScreen;
import mx.itesm.dragon.States.ScreenState;
import mx.itesm.dragon.Utils.AnimatedImage;
import mx.itesm.dragon.Objects.Dragon;
import mx.itesm.dragon.Objects.Enemigos;
import mx.itesm.dragon.Utils.BackGround;
import mx.itesm.dragon.Objects.JefeFinal;
import mx.itesm.dragon.Screens.GenericScreen;
import mx.itesm.dragon.Objects.Proyectil;
import mx.itesm.dragon.Utils.Text;
import mx.itesm.dragon.Objects.Vida;

public class LevelOne extends GenericScreen {

    private static final float ALTO_MAPA = 2560;

    private float timerProyectil;
    private float timerFlecha;
    private float timerJefeFinal;

    // determinan el movimiemto del jefe final
    private boolean jefePos = false;
    private int direccion = 1;

    private float xDragon;
    private float yDragon;
    private boolean pause = false;

    private Random random;

    private Stage stageJuego;
    private Stage stagePausa;
    private Stage stageGanar;
    private Stage stagePerder;

    private Texture proyectil;
    private Texture proyectilJefeFinal;
    private Texture flecha;
    private Texture pocima;

    private ArrayList<Proyectil> listaProyectil;
    private ArrayList<Proyectil> listaProyectilJefe;
    private ArrayList<Enemigos> listaFlechas;
    private ArrayList<Vida> listaVidas;

    private BackGround backGround;
    private BackGround backGroundPausa;
    private BackGround backGroundGanar;
    private BackGround backGroundPerder;

    private Vida vida;
    private JefeFinal framesJefeFinal;
    private Dragon framesDragon;

    private AnimatedImage dragon;
    private AnimatedImage jefeFinal;
    private Image barraVida;
    private Image v1,v2,v3,v4;
    private ImageButton btnPausa;
    private ImageButton btnReanudar;
    private ImageButton btnMusica;
    private ImageButton btnMenu;
    private ImageButton btnSFX;
    //private ImageButton btnReiniciar;
    private ImageButton btnMenuPerder;
    private ImageButton btnMenuGanar;
    private ImageButton btnSigNivel;

    private GameState gameState;

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
    private Text puntos;  // Muestra los valores en pantalla
    private Text text;
    private float timerVida;
    private float timerProyectilJefeFinal;


    public LevelOne(Main game) {
        super(game);
        gameState = GameState.JUGANDO;
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
        musica_f = Gdx.audio.newMusic(Gdx.files.internal("music/Hyrule Field - The Legend of Zelda Twilight Princess.mp3"));
        flecha_s = Gdx.audio.newSound(Gdx.files.internal("music/flecha.wav"));
        colision = Gdx.audio.newSound(Gdx.files.internal("music/colision.wav"));
        fuego = Gdx.audio.newSound(Gdx.files.internal("music/fuego.wav"));
        pausa = Gdx.audio.newSound(Gdx.files.internal("music/pausa.wav"));
        reanudar = Gdx.audio.newSound(Gdx.files.internal("music/reanudar.wav"));
        impacto = Gdx.audio.newSound(Gdx.files.internal("music/impacto.wav"));

        // Reproducir música de backGround
        musica_f.setVolume(.5f);
        musica_f.play();
        musica_f.setLooping(true);
    }

    private void initPausa() {
        stagePausa = new Stage(vista);
        backGroundPausa = new BackGround(new Texture("backgrounds/pause.png"));

        btnReanudar = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(
                        new Texture("buttons/resume.png"))),
                new TextureRegionDrawable(new TextureRegion(
                        new Texture("buttons/resumePressed.png"))));
        btnMusica = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(
                        new Texture("buttons/music.png"))),
                new TextureRegionDrawable(new TextureRegion(
                        new Texture("buttons/musicPressed.png"))));
        btnSFX = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("buttons/sfx.png"))),
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("buttons/sfxPressed.png"))));
        btnMenu = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(
                        new Texture("buttons/mainMenu.png"))),
                new TextureRegionDrawable(new TextureRegion(
                        new Texture("buttons/mainMenuPressed.png"))));

        // Se anexan las Escenas al Multiplexor.
        multiplexer.addProcessor(stagePausa);
    }

    private void initJuego() {
        stageJuego = new Stage(vista);
        timerProyectil = 0;
        timerFlecha = 0;
        timerVida = 0;
        timerJefeFinal = 0;
        timerProyectilJefeFinal = 0;
        multiplexer = new InputMultiplexer();
        listaProyectil = new ArrayList<Proyectil>();
        listaFlechas = new ArrayList<Enemigos>();
        listaVidas = new ArrayList<Vida>();
        listaProyectilJefe = new ArrayList<Proyectil>();
        random = new Random();

        // Objeto que dibuja al text
        letras = "Score";
        puntos = new Text();
        text = new Text();

        barraVida = new Image(new Texture("textures/healthBar.png"));
        v1 = new Image(new Texture("textures/heart.png"));
        v2 = new Image(new Texture("textures/heart.png"));
        v3 = new Image(new Texture("textures/heart.png"));
        v4 = new Image(new Texture("textures/heart.png"));
        btnPausa = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(
                        new Texture("buttons/pause.png"))));

        framesDragon = new Dragon("frames/dragon.png");
        dragon = new AnimatedImage(framesDragon.animacion());
        framesJefeFinal = new JefeFinal("frames/finalBoss1.png");
        jefeFinal = new AnimatedImage(framesJefeFinal.animacion());

        backGround = new BackGround(new Texture("backgrounds/level1.png"));

        proyectil = new Texture("textures/fireBall.png");
        proyectilJefeFinal = new Texture("textures/rock.png");
        flecha = new Texture("textures/arrow.png");
        pocima = new Texture("textures/potion.png");
        vida = new Vida(pocima,0,0);

        // Se anexan las Escenas al Multiplexor.
        multiplexer.addProcessor(stageJuego);
    }

    private void initGanar(){
        stageGanar = new Stage(vista);
        backGroundGanar = new BackGround(new Texture("backgrounds/win.png"));
        /*btnSigNivel = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("BotonReset.png"))),
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("BotonResetPresionado.png"))));*/
        btnMenuGanar = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(
                        new Texture("buttons/mainMenu.png"))),
                new TextureRegionDrawable(new TextureRegion(
                        new Texture("buttons/mainMenuPressed.png"))));

        multiplexer.addProcessor(stageGanar);
    }

    private void initPerder(){
        stagePerder = new Stage(vista);
        backGroundPerder = new BackGround(new Texture("backgrounds/gameOver.png"));

        /*btnReiniciar = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("buttons/reset.png"))),
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("buttons/resetPressed.png"))));
        */
        btnMenuPerder = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(
                        new Texture("buttons/mainMenu.png"))),
                new TextureRegionDrawable(new TextureRegion(
                        new Texture("buttons/mainMenuPressed.png"))));

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
                game.setScreen(new LoadingScreen(game, ScreenState.MENU));
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
        dragon.setPosition(ANCHO / 2 - dragon.getWidth() / 2, dragon.getY() + dragon.getHeight());
        jefeFinal.setPosition(0 - jefeFinal.getWidth(), ALTO);
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
        stageJuego.addActor(jefeFinal);
    }


    private void setStageGanar(){
        //btnSigNivel.setPosition();
        btnMenuGanar.setPosition(-1000,-1000);
        btnMenuGanar.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LoadingScreen(game, ScreenState.MENU));
            }
        });
        stageGanar.addActor(btnMenuGanar);
    }

    private void setStagePerder(){
        //btnReiniciar.setPosition(ANCHO / 3,ALTO - btnReanudar.getHeight() * 2.3f);

        btnMenuPerder.setPosition(-1000,-1000);

        btnMenuPerder.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LoadingScreen(game, ScreenState.MENU));
            }
        });



        stagePerder.addActor(btnMenuPerder);
        //stagePerder.addActor(btnReiniciar);

    }

    @Override
    public void render(float delta) {
        switch (gameState) {
            case JUGANDO:
                actualizarObjetos(delta);
                stagePausa.unfocusAll();
                batch.begin();
                puntosJugador += 10; // incrementa los puntos del jugador conforme pasa el tiempo;
                backGround.render(batch);
                //Marcador
                text.mostrarMensaje(batch,letras,ANCHO - ANCHO/8, ALTO);
                puntos.mostrarMensaje(batch, Integer.toString(puntosJugador), ANCHO - ANCHO/8, ALTO-50);

                for (Proyectil p: listaProyectil) {
                    //fuego.play();
                    p.render(batch);
                }
                for (Proyectil pjf: listaProyectilJefe) {
                    pjf.render(batch);
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
                    xDragon = dragon.getX();
                    yDragon = dragon.getY();
                    dragon.setPosition(-1000, -1000);
                    pausa.play();
                    gameState = GameState.PAUSA;
                }
                stageJuego.draw();
                if(vida.getVidas() == 0){
                    gameState = GameState.PERDER;
                }

                if(framesJefeFinal.getVida() == 0){
                    gameState = GameState.GANAR;
                }
                break;
            case PAUSA:
                batch.begin();
                backGroundPausa.render(batch);
                batch.end();
                if (btnReanudar.isPressed()) {
                    pause = false;
                    reanudar.play();
                    dragon.setPosition(xDragon, yDragon);
                    gameState = GameState.JUGANDO;
                }
                stagePausa.draw();
                break;
            case PERDER:
                dragon.setPosition(-1000,-1000);
                btnMenuPerder.setPosition(ANCHO / 3,ALTO/2 - 100 - (btnMenuPerder.getHeight()/2));
                batch.begin();
                backGroundPerder.render(batch);
                text.mostrarMensaje(batch,letras,ANCHO / 2, ALTO - ALTO / 4-50);
                puntos.mostrarMensaje(batch, Integer.toString(puntosJugador), ANCHO / 2, ALTO - ALTO /4 - 100);
                batch.end();

                stagePerder.draw();
                break;
            case GANAR:
                dragon.setPosition(-1000,-1000);
                btnMenuGanar.setPosition(ANCHO / 3,ALTO/2 - 100 - (btnMenuPerder.getHeight()/2));
                batch.begin();
                backGroundGanar.render(batch);
                text.mostrarMensaje(batch,letras,ANCHO / 2, ALTO - ALTO / 4 - 50);
                puntos.mostrarMensaje(batch, Integer.toString(puntosJugador), ANCHO / 2, ALTO - ALTO / 4 - 100);
                batch.end();
                stageGanar.draw();
                break;
        }
        // Gdx.app.log("render", "fps="+Gdx.graphics.getFramesPerSecond());
    }

    private void actualizarObjetos(float delta) {
        actualizarFondo(delta * 5);
        actualizarProyectiles(delta);
        actualizarEnemigos(delta);
        actualizarColisiones(delta);
        actualizarPersonaje(delta);
        actualizarJefeFinal(delta);
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

    private void actualizarJefeFinal(float delta) {
        timerJefeFinal += delta;
        jefeFinal.act(delta);
        if (timerJefeFinal >= 30){
            if (jefePos) {
                jefeFinal.setPosition(jefeFinal.getX() + (3 * direccion), jefeFinal.getY());

                if (jefeFinal.getX() + jefeFinal.getWidth() >= ANCHO) {
                    direccion = -1;
                }
                if (jefeFinal.getX() <= 0) {
                    direccion = 1;
                }
            }
            else {
                if (jefeFinal.getX() >= ANCHO / 2 - jefeFinal.getImageWidth() / 2) {
                    //jefeFinal.setPosition(jefeFinal.getX(), jefeFinal.getY());
                    jefePos = true;
                } else {
                    jefeFinal.setPosition(jefeFinal.getX() + 3, jefeFinal.getY() - 3);
                }
            }
        }


    }

    private void actualizarFondo(float delta) {
        backGround.mover(delta);
    }

    private void actualizarColisiones(float delta) {
        for (int i = 0; i < listaProyectil.size(); i++) {
            Proyectil proyectil = listaProyectil.get(i);
            Rectangle rectProyectil = proyectil.getSprite().getBoundingRectangle();
            Rectangle rectJefeFinal = new Rectangle(jefeFinal.getX(), jefeFinal.getY(), jefeFinal.getImageWidth(), jefeFinal.getImageHeight());
            if (rectJefeFinal.overlaps(rectProyectil)) {
                colision.play();
                listaProyectil.remove(i);
                framesJefeFinal.setVida(framesJefeFinal.getVida() - 1);
            }
         }

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
            for (int j = 0; j < listaProyectilJefe.size(); j++) {
                Proyectil proyectilJefe = listaProyectilJefe.get(j);
                Rectangle rectProyectil = proyectil.getSprite().getBoundingRectangle();
                Rectangle rectProyectilJefe = proyectilJefe.getSprite().getBoundingRectangle();
                if (rectProyectil.overlaps(rectProyectilJefe)) {
                    colision.play();
                    listaProyectil.remove(proyectil);
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
            Rectangle rectJefeFinal = new Rectangle(jefeFinal.getX(), jefeFinal.getY(), jefeFinal.getImageWidth(), jefeFinal.getImageHeight());
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
        for (int i = 0; i < listaProyectilJefe.size(); i++) {
            Proyectil proyectilJefe = listaProyectilJefe.get(i);
            Rectangle rectDragon = new Rectangle(dragon.getX() + 151,dragon.getY(),151,dragon.getHeight() / 2);
            Rectangle rectJefeFinal = proyectilJefe.getSprite().getBoundingRectangle();
            if (rectDragon.overlaps(rectJefeFinal)) {
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
                listaProyectilJefe.remove(i);
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
        timerProyectilJefeFinal += delta;
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
        if (timerProyectilJefeFinal >= 1f) {
            listaProyectilJefe.add(new Proyectil(proyectilJefeFinal,jefeFinal.getX() + jefeFinal.getWidth() / 2 - proyectilJefeFinal.getWidth() / 2, jefeFinal.getY()));
            timerProyectilJefeFinal = 0;
        }
        for (int i = 0; i < listaProyectilJefe.size(); i++) {
            listaProyectilJefe.get(i).moverAbajo();
        }
        for (int i = 0; i < listaProyectilJefe.size(); i++) {
            if (listaProyectilJefe.get(i).getSprite().getY() <= 0) {
                listaProyectilJefe.remove(i);
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
        if (!pause) {
            xDragon = dragon.getX();
            yDragon = dragon.getY();
            pause = true;
        }
        dragon.setPosition(-1000, -1000);
        gameState = GameState.PAUSA;
    }

    @Override
    public void resume() {
        //dragon.setPosition(xDragon,yDragon);
        gameState = GameState.PAUSA;
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