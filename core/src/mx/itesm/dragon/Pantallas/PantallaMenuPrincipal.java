package mx.itesm.dragon.Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import mx.itesm.dragon.Juego;
import mx.itesm.dragon.Objetos.Fondo;
import mx.itesm.dragon.Objetos.Pantalla;

public class PantallaMenuPrincipal extends Pantalla {
    private final Juego juego;

    // Escena para el menu.
    private Stage stageMenu;

    // Fondo.
    private Fondo fondo;

    // Musica de fondo
    private Music musica_f;

    // Efectos de sonido
    private Sound jugar;
    private Sound config;
    private Sound acerca;

    public PantallaMenuPrincipal(Juego juego) {
        this.juego = juego;
    }

    public void show() {
        crearMenu();
    }

    private void crearMenu() {
        // Creación escena menú.
        stageMenu = new Stage(vista);

        // Creacion del fondo.
        fondo = new Fondo(new Texture("FondoMenuPrincipal.jpg"));

        // Creacion de la musica de fondo y efectos de sonido
        musica_f = Gdx.audio.newMusic(Gdx.files.internal("premenu.mp3"));
        jugar = Gdx.audio.newSound(Gdx.files.internal("jugar.wav"));
        config = Gdx.audio.newSound(Gdx.files.internal("config.wav"));
        acerca = Gdx.audio.newSound(Gdx.files.internal("config.wav"));

        // Reproduccion de la musica de fondo
        musica_f.setVolume(1);
        musica_f.play();
        musica_f.setLooping(true);

        // Creación de los botones del menú.
        ImageButton imgDragon = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("BotonPlayDragon1.png"))));

        ImageButton btnPlay = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("BotonPlay1.png"))),
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("BotonPlay2.png"))));

        ImageButton btnInfo = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("Boton Acerca de 1.png"))),
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("Boton Acerca de 2.png"))));

        ImageButton btnConfig = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("BotonConfiguracion2.png"))),
                new TextureRegionDrawable(
                        new TextureRegion(
                                new Texture("BotonConfiguracion1.png"))));

        // Posición de los botones.
        imgDragon.setPosition((ANCHO / 2) - imgDragon.getWidth() / 2,ALTO * 0.15F);

        btnPlay.setPosition((ANCHO / 2) - btnPlay.getWidth() / 2,imgDragon.getHeight() * 0.53f );

        btnInfo.setPosition(ANCHO - btnInfo.getWidth()-10,ALTO - btnInfo.getHeight()-20);

        btnConfig.setPosition(ANCHO - btnConfig.getWidth()-20,btnConfig.getHeight()/4);

        // Detecta si el usuario hace click en algún actor.
        btnPlay.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                jugar.play();
                //jugar.stop();
                // Gdx.app.log("ClickedListener","Hizo click el usuario");
                // Cambia de pantalla, solo lo puede hacerlo 'juego'.
                juego.setScreen(new PantallaCargando(juego));
            }
        });

        btnInfo.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                acerca.play();
                //acerca.stop();
                // Gdx.app.log("ClickedListener","Hizo click el usuario");
                // Cambia de pantalla, solo lo puede hacerlo 'juego'.
                juego.setScreen(new PantallaAcercaDe(juego));
            }
        });

        btnConfig.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                config.play();
                //config.stop();
                juego.setScreen(new PantallaConfiguracion(juego));
            }
        });

        // Se agregan elementos al menú.
        stageMenu.addActor(imgDragon);
        stageMenu.addActor(btnPlay);
        stageMenu.addActor(btnInfo);
        stageMenu.addActor(btnConfig);

        // Indica quién escucha y atiende eventos.
        Gdx.input.setInputProcessor(stageMenu);
    }

    @Override
    public void render(float delta) {
        // DIBUJAR.
        borrarPantalla();
        batch.begin();
            // Dibujar elementos de la pantalla.
            fondo.render(batch);
        batch.end();
        stageMenu.draw();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        stageMenu.dispose();
        batch.dispose();
        musica_f.dispose();
        acerca.dispose();
        jugar.dispose();
        config.dispose();
    }
}

