package mx.itesm.dragon;

import com.badlogic.gdx.Game;
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

    // Cámara.
    private OrthographicCamera camera;
    private Viewport viewport;

    // Escena para el menu.
    private Stage stageMenu;

    // Batch.
    private SpriteBatch batch;

    // MUNDO.
    public static final float ANCHO = 1280;
    public static final float ALTO = 720;

    public MenuPrincipal(Juego juego) {
        this.juego = juego;
    }


    public void show() {
        crearCamara();
        crearMenu();
        batch = new SpriteBatch();
    }

    private void crearMenu() {
        stageMenu = new Stage(viewport);

        TextureRegionDrawable trdPlay = new TextureRegionDrawable(
                new TextureRegion(
                        new Texture("play.png")));
        TextureRegionDrawable trdPlayTouch = new TextureRegionDrawable(
                new TextureRegion(
                        new Texture("playTouch.png")));

        ImageButton btnPlay = new ImageButton(trdPlay,trdPlayTouch);

        btnPlay.setPosition(ANCHO/2-btnPlay.getWidth()/2,ALTO/2-btnPlay.getHeight()/2);

        btnPlay.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                // Gdx.app.log("ClickedListener","Hizo click el usuario");
                // Cambia de pantalla, solo lo puede hacerlo 'juego'.
                juego.setScreen(new PantallaJuego(juego));
            }
        });

        stageMenu.addActor(btnPlay);
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
        Gdx.gl.glClearColor(25/255f,158/255f,218/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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

    }
}

