package mx.itesm.dragon.Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import mx.itesm.dragon.Main;
import mx.itesm.dragon.Objects.Character;
import mx.itesm.dragon.Objects.LifeCharacter;
import mx.itesm.dragon.Screens.GenericScreen;
import mx.itesm.dragon.Screens.LoadingScreen;
import mx.itesm.dragon.States.GameState;
import mx.itesm.dragon.States.ScreenState;
import mx.itesm.dragon.Utils.AnimatedImage;
import mx.itesm.dragon.Utils.BackGround;
import mx.itesm.dragon.Utils.Text;

public abstract class GenericLevel extends GenericScreen {

    protected InputMultiplexer multiplexer;

    protected static final float ALTO_MAPA = 2560;

    protected float timerVida;

    protected Stage stageJuego;
    protected Stage stagePausa;
    protected Stage stageGanar;
    protected Stage stagePerder;

    protected GameState gameState;
    protected ScreenState screenState;

    protected BackGround backGround;
    protected BackGround backGroundPausa;
    protected BackGround backGroundGanar;
    protected BackGround backGroundPerder;

    protected LifeCharacter lifeCharacter;
    protected Character framesCharacter;

    protected AnimatedImage dragon;
    protected AnimatedImage boss;
    protected Image barraVida;
    protected Image v1,v2,v3,v4;
    protected ImageButton btnPausa;
    protected ImageButton btnReanudar;
    protected ImageButton btnMusica;
    protected ImageButton btnNoMusic;
    protected ImageButton btnMenu;
    protected ImageButton btnSFX;
    protected ImageButton btnNoSFX;
    protected ImageButton btnReiniciar;
    protected ImageButton btnMenuPerder;
    protected ImageButton btnMenuGanar;
    protected ImageButton btnSigNivel;

    //Sonido y musica
    protected Music music;
    protected Sound collision;
    protected Sound impact;
    protected Sound fire;
    protected Sound arrow;
    protected Sound resume;
    protected Sound pause;

    //Preferencias
    protected Preferences sonido = Gdx.app.getPreferences("preferenceS");
    protected Preferences musica = Gdx.app.getPreferences("preferenceM");

    // Marcador.//
    protected int puntosJugador = 0;
    protected String letras;
    protected Text puntos;  // Muestra los valores en pantalla
    protected Text text;

    protected Texture textureLife;
    protected Texture textureBarLife;
    protected Texture textureBtnPause;
    protected Texture textureFramesDragon;
    protected Texture texturePotion;
    protected Texture textureBackgroundPause;
    protected Texture textureBtnResume;
    protected Texture textureBtnPressResume;
    protected Texture textureBtnMusic;
    protected Texture textureBtnPressMusic;
    protected Texture textureBtnSFX;
    protected Texture textureBtnPressSFX;
    protected Texture textureBtnMenu;
    protected Texture textureBtnPressMenu;
    protected Texture textureBackgroundWin;
    protected Texture textureBackgroundLose;
    protected Texture textureBtnReset;
    protected Texture textureBtnPressReset;


    public GenericLevel(Main game) {
        super(game);
        gameState = GameState.JUGANDO;
    }

    protected void initialization() {
        initGame();
        initPause();
        initMusic();
        initLose();
        initWin();
        Gdx.input.setInputProcessor(multiplexer);
    }

    protected void loadResources() {

        textureBarLife = assetManager.get("textures/healthBar.png");
        textureLife = assetManager.get("textures/heart.png");
        textureBtnPause = assetManager.get("buttons/pause.png");
        textureFramesDragon = assetManager.get("frames/dragon.png");
        texturePotion = assetManager.get("textures/potion.png");
        textureBackgroundPause = assetManager.get("backgrounds/pause.png");
        textureBtnResume = assetManager.get("buttons/resume.png");
        textureBtnPressResume = assetManager.get("buttons/resumePressed.png");
        textureBtnMusic = assetManager.get("buttons/music.png");
        textureBtnPressMusic = assetManager.get("buttons/musicPressed.png");
        textureBtnSFX = assetManager.get("buttons/sfx.png");
        textureBtnPressSFX = assetManager.get("buttons/sfxPressed.png");
        textureBtnMenu = assetManager.get("buttons/mainMenu.png");
        textureBtnPressMenu = assetManager.get("buttons/mainMenuPressed.png");
        textureBackgroundWin = assetManager.get("backgrounds/win.png");
        textureBackgroundLose = assetManager.get("backgrounds/gameOver.png");
        textureBtnReset = assetManager.get("buttons/reset.png");
        textureBtnPressReset = assetManager.get("buttons/resetPressed.png");

        // Música y SFX
        music = assetManager.get("music/Hyrule Field - The Legend of Zelda Twilight Princess.mp3");
        arrow = assetManager.get("music/flecha.wav");
        collision = assetManager.get("music/colision.wav");
        fire = assetManager.get("music/fuego.wav");
        pause = assetManager.get("music/pausa.wav");
        resume = assetManager.get("music/reanudar.wav");
        impact = assetManager.get("music/impacto.wav");
    }

    private void initGame() {
        stageJuego = new Stage(vista);
        multiplexer = new InputMultiplexer();

        timerVida = 0;

        letras = "Score";
        puntos = new Text();
        text = new Text();

        barraVida = new Image(textureBarLife);
        v1 = new Image(textureLife);
        v2 = new Image(textureLife);
        v3 = new Image(textureLife);
        v4 = new Image(textureLife);

        btnPausa = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPause)));

        framesCharacter = new Character(textureFramesDragon);
        dragon = new AnimatedImage(framesCharacter.animacion());

        lifeCharacter = new LifeCharacter(texturePotion);

        // Posisción inicial de los elementos
        btnPausa.setPosition(0,ALTO - btnPausa.getHeight());
        barraVida.setPosition( ANCHO / 2 - barraVida.getWidth() / 2, ALTO - barraVida.getHeight() - barraVida.getHeight() / 2);
        dragon.setPosition(ANCHO / 2 - dragon.getWidth() / 2, dragon.getY() + dragon.getHeight());

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
        stageJuego.addActor(boss);
        // Se anexan las Escenas al Multiplexor.
        multiplexer.addProcessor(stageJuego);
    }

    private void initPause() {
        stagePausa = new Stage(vista);
        backGroundPausa = new BackGround(textureBackgroundPause);

        btnReanudar = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnResume)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPressResume)));
        btnMusica = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnMusic)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPressMusic)));
        btnNoMusic = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPressMusic)));
        btnSFX = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnSFX)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPressSFX)));
        btnNoSFX = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPressSFX)));
        btnMenu = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnMenu)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPressMenu)));

        // Posisción inicial de los elementos
        btnReanudar.setPosition(ANCHO / 3,ALTO - btnReanudar.getHeight() * 2.3f);
        btnMusica.setPosition(ANCHO / 8,btnReanudar.getY() - 300);
        btnNoMusic.setPosition(ANCHO / 8,btnReanudar.getY() - 300);
        btnSFX.setPosition(btnMusica.getX() + 330, btnMusica.getY());
        btnNoSFX.setPosition(btnMusica.getX() + 330, btnMusica.getY());
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

        // Se anexan las Escenas al Multiplexor.
        //multiplexer.addProcessor(stagePausa);
    }

    private void initMusic() {
        // Reproducir música de backGround
        boolean musicaActiva = musica.getBoolean("onMusic");
        if (musicaActiva){
            music.setVolume(1);
            music.play();
            music.setLooping(true);
        }

        final boolean sonidoactivo = sonido.getBoolean("onSound");
    }

    private void initWin() {
        stageGanar = new Stage(vista);
        backGroundGanar = new BackGround(textureBackgroundWin);
        /*btnSigNivel = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("BotonReset.png"))),
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("BotonResetPresionado.png"))));*/
        btnMenuGanar = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(
                        textureBtnMenu)),
                new TextureRegionDrawable(new TextureRegion(
                        textureBtnPressMenu)));

        btnMenuGanar.setPosition(ANCHO / 3,ALTO - btnReanudar.getHeight() * 2.3f - btnReiniciar.getHeight() -50);
        btnMenuGanar.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LoadingScreen(game, ScreenState.MENU));
            }
        });
        stageGanar.addActor(btnMenuGanar);

        //multiplexer.addProcessor(stageGanar);
    }

    private void initLose() {
        stagePerder = new Stage(vista);
        backGroundPerder = new BackGround(textureBackgroundLose);

        btnReiniciar = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnReset)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPressReset)));

        btnMenuPerder = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnMenu)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPressMenu)));


        btnMenuPerder.setPosition(ANCHO / 3,ALTO - btnReanudar.getHeight() * 2.3f - btnReiniciar.getHeight() -50);

        btnMenuPerder.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LoadingScreen(game, ScreenState.MENU));
            }
        });

        btnReiniciar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new LoadingScreen(game, screenState));
            }
        });

        stagePerder.addActor(btnMenuPerder);
        stagePerder.addActor(btnReiniciar);

        //multiplexer.addProcessor(stagePerder);
    }

    @Override
    public void pause() {
        //gameState = GameState.PAUSA;
    }

    @Override
    public void resume() {
        //gameState = GameState.JUGANDO;
    }

    @Override
    public void dispose() {

    }
}
