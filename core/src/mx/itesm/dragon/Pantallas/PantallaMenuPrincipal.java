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

    // Escena para el menu.
    private Stage stageMenu;

    // Fondo.
    private Fondo fondo;

    public PantallaMenuPrincipal(Juego juego) {
        super(juego);
    }

    public void show() {
        crearMenu();
    }

    private void crearMenu() {
        // Creación escena menú.
        stageMenu = new Stage(vista);

        // Creacion del fondo.
        Texture mainMenu = assetManager.get("backgrounds/mainMenu.jpg");
        fondo = new Fondo(mainMenu);

        // Creacion de la musica de fondo.
        Music musicMenu = assetManager.get("music/premenu.mp3");

        // Reproduccion de la musica de fondo
        musicMenu.setVolume(1);
        musicMenu.play();
        musicMenu.setLooping(true);

        // Creación de los botones del menú.
        Texture textureDragon = assetManager.get("textures/dragonPlay1.png");
        Texture textureBtnPlay = assetManager.get("buttons/play.png");
        Texture textureBtnPressPlay = assetManager.get("buttons/playPressed.png");
        Texture textureBtnAU = assetManager.get("buttons/about.png");
        Texture textureBtnPressAU = assetManager.get("buttons/aboutPressed.png");
        Texture textureBtnSettings = assetManager.get("buttons/settings.png");
        Texture textureBtnPressSettings = assetManager.get("buttons/settingsPressed.png");
        ImageButton imgDragon = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(textureDragon)));

        ImageButton btnPlay = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPlay)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPressPlay)));

        ImageButton btnAU = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnAU)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPressAU)));

        ImageButton btnConfig = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnSettings)),
                new TextureRegionDrawable(
                        new TextureRegion(
                                textureBtnPressSettings)));

        // Posición de los botones.
        imgDragon.setPosition((ANCHO / 2) - imgDragon.getWidth() / 2,ALTO * 0.15F);

        btnPlay.setPosition((ANCHO / 2) - btnPlay.getWidth() / 2,imgDragon.getHeight() * 0.53f );

        btnAU.setPosition(ANCHO - btnAU.getWidth()-10,ALTO - btnAU.getHeight()-20);

        btnConfig.setPosition(ANCHO - btnConfig.getWidth()-20,btnConfig.getHeight()/4);

        // Detecta si el usuario hace click en algún actor.
        btnPlay.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Sound soundPlay = assetManager.get("music/jugar.wav");
                soundPlay.play();
                // Cambia de pantalla, solo lo puede hacerlo 'juego'.
                juego.setScreen(new PantallaCargando(juego));
            }
        });

        btnAU.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Sound soundAU = assetManager.get("music/config.wav");
                soundAU.play();
                // Cambia de pantalla, solo lo puede hacerlo 'juego'.
                juego.setScreen(new PantallaAcercaDe(juego));
            }
        });

        btnConfig.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Sound soundSettings = assetManager.get("music/config.wav");
                soundSettings.play();
                juego.setScreen(new PantallaConfiguracion(juego));
            }
        });

        // Se agregan elementos al menú.
        stageMenu.addActor(imgDragon);
        stageMenu.addActor(btnPlay);
        stageMenu.addActor(btnAU);
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
        assetManager.unload("backgrounds/mainMenu.jpg");
        assetManager.unload("textures/dragonPlay1.png");
        assetManager.unload("buttons/play.png");
        assetManager.unload("buttons/playPressed.png");
        assetManager.unload("buttons/about.png");
        assetManager.unload("buttons/aboutPressed.png");
        assetManager.unload("buttons/settings.png");
        assetManager.unload("buttons/settingsPressed.png");
        assetManager.unload("music/premenu.mp3");
        assetManager.unload("music/jugar.wav");
        assetManager.unload("music/config.wav");
    }
}

