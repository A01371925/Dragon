package mx.itesm.dragon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by LEDNR on 09/02/18.
 */

public class MenuPrincipal implements Screen {
    private final Juego juego;

    private Texture texturaFondo;

    // Cámara.
    private OrthographicCamera camera;
    private Viewport viewport;

    // Escena para el menu.
    private Stage stageMenu;

    // Batch.
    private SpriteBatch batch;

    // Mundo.
    public static final float ANCHO = 720;
    public static final float ALTO = 1280;

    public MenuPrincipal(Juego juego) {
        this.juego = juego;
    }


    public void show() {
        crearCamara();
        crearMenu();
        batch = new SpriteBatch();
    }

    private void crearMenu() {
        texturaFondo = new Texture("FondoMenuPrincipal.png");
        stageMenu = new Stage(viewport);


        TextureRegionDrawable imagenDragon = new TextureRegionDrawable(
                new TextureRegion(
                        new Texture("BotonPlayDragon1.png")));

        TextureRegionDrawable trdPlay = new TextureRegionDrawable(
                new TextureRegion(
                        new Texture("BotonPlay1.png")));
        TextureRegionDrawable trdPlayTouch = new TextureRegionDrawable(
                new TextureRegion(
                        new Texture("BotonPlay2.png")));

        TextureRegionDrawable trdInfo = new TextureRegionDrawable(
                new TextureRegion(
                        new Texture("BotonAcerca de1.png")));
        /*TextureRegionDrawable trdInfoTouch = new TextureRegionDrawable(
                new TextureRegion(
                        new Texture("Boton Acerca de 2.png")));
                        */

        TextureRegionDrawable trdConfig = new TextureRegionDrawable(
                new TextureRegion(
                        new Texture("BotonConfiguracion2.png")));
        TextureRegionDrawable trdConfigTouch = new TextureRegionDrawable(
                new TextureRegion(
                        new Texture("BotonConfiguracion1.png")));

        ImageButton botonDragon = new ImageButton(imagenDragon);

        ImageButton btnPlay = new ImageButton(trdPlay,trdPlayTouch);

        ImageButton btnInfo = new ImageButton(trdInfo/*,trdInfoTouch*/);

        ImageButton btnConfig = new ImageButton(trdConfig,trdConfigTouch);

        // Posición de los botones.
        botonDragon.setPosition((ANCHO / 2) - botonDragon.getWidth() / 2,ALTO * 0.15F);

        btnPlay.setPosition((ANCHO / 2) - btnPlay.getWidth() / 2,botonDragon.getHeight() * 0.53f );

        btnInfo.setPosition(ANCHO - btnInfo.getWidth()-10,ALTO - btnInfo.getHeight()-20);

        btnConfig.setPosition(ANCHO - btnConfig.getWidth()-20,btnConfig.getHeight()/4);


        btnPlay.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                // Gdx.app.log("ClickedListener","Hizo click el usuario");
                // Cambia de pantalla, solo lo puede hacerlo 'juego'.
                juego.setScreen(new PantallaJugar(juego)); //
            }
        });

        btnInfo.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                // Gdx.app.log("ClickedListener","Hizo click el usuario");
                // Cambia de pantalla, solo lo puede hacerlo 'juego'.
                juego.setScreen(new AcercaDe(juego));
            }
        });

        btnConfig.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                juego.setScreen(new PantallaConfiguracion(juego));
            }
        });


        stageMenu.addActor(botonDragon);

        stageMenu.addActor(btnPlay);

        stageMenu.addActor(btnInfo);

        stageMenu.addActor(btnConfig);

        Gdx.input.setInputProcessor(stageMenu);
    }

    private void crearCamara() {
        camera = new OrthographicCamera(ANCHO, ALTO);
        camera.position.set(ANCHO/2,ALTO/2,0);
        camera.update();
        viewport = new StretchViewport(ANCHO,ALTO,camera);
    }

    @Override
    public void render(float delta) {


        batch.begin();
        batch.draw(texturaFondo,0,0);
        batch.end();


        batch.setProjectionMatrix(camera.combined);
        stageMenu.draw();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        texturaFondo.dispose();

    }
}

