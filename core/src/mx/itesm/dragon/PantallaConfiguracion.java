package mx.itesm.dragon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by LEDNR on 30/01/18.
 */

class PantallaConfiguracion implements Screen {

    private final MenuPrincipal menuPrincipal;

    // Cámara.
    private OrthographicCamera camera;
    private Viewport viewport;

    // Batch.
    private SpriteBatch batch;

    public PantallaConfiguracion(MenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;

    }

    @Override
    public void show() {
        crearCamara();
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(new ProcesadorEntrada());


    }

    private void crearCamara() {
        camera = new OrthographicCamera(PantallaMenu.ANCHO, PantallaMenu.ALTO);
        camera.position.set(PantallaMenu.ANCHO/2,PantallaMenu.ALTO/2,0);
        camera.update();
        viewport = new StretchViewport(PantallaMenu.ANCHO,PantallaMenu.ALTO,camera);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);

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

    private class ProcesadorEntrada implements InputProcessor {

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
            menuPrincipal.setScreen(new MenuPrincipal(menuPrincipal));
            return true; // Ya prosesamos el evento.
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
