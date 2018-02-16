package mx.itesm.dragon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.Menu;

/**
 * Created by LEDNR on 09/02/18.
 */

public class PantallaJugar implements Screen {

    private final Juego juego;

    // Cámara.
    private OrthographicCamera camera;
    private Viewport viewport;


    // Batch.
    private SpriteBatch batch;

    // Texturas.
    private Texture texturaNivel;
    // private Texture texturaDragon;

    public PantallaJugar(Juego juego) {
        this.juego = juego;
    }

    @Override
    public void show() {
        // Crear la cámara.
        crearCamara();

        batch = new SpriteBatch();

        // Carga todas las texturas.
        cargarTexturas();

        // Indica quién escucha y atiende eventos
        Gdx.input.setInputProcessor(new ProcesadorEntreada());

    }

    private void cargarTexturas() {
        texturaNivel = new Texture("fondoNivel1.png");
        // texturaDragon = new Texture("dragonN.png");
    }

    private void crearCamara() {
        camera = new OrthographicCamera(MenuPrincipal.ANCHO, MenuPrincipal.ALTO);
        camera.position.set(MenuPrincipal.ANCHO / 2, MenuPrincipal.ALTO / 2, 0);
        camera.update();
        viewport = new StretchViewport(MenuPrincipal.ANCHO, MenuPrincipal.ALTO, camera);
    }

    @Override
    public void render(float delta) {
        //Gdx.gl.glClearColor(1,0,0,1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

            // Dibujar elementos del juego.
            batch.draw(texturaNivel,0 ,0);
            // batch.draw(texturaDragon, MenuPrincipal.ANCHO / 2, 0);
        batch.end();


    }

    @Override
    public void resize(int width, int height) {

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

    private class ProcesadorEntreada implements InputProcessor {

        @Override
        public boolean keyDown(int keycode) {
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            return false;
        }

        @Override
        public boolean keyTyped(char character) {
            return false;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            juego.setScreen(new MenuPrincipal(juego));
            return true;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            return false;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(int amount) {
            return false;
        }
    }
}
