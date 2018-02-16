package mx.itesm.dragon;

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
 * Created by jorge on 09/02/2018.
 */

class AcercaDe implements Screen {

    private final Juego juego;

    // Cámara.
    private OrthographicCamera camera;
    private Viewport viewport;

    // Batch.
    private SpriteBatch batch;

    // Texturas.
    private Texture texturaAcercaDe;
    private Texture texturaNombreLuis;
    private Texture texturaNombreCharlotte;
    private Texture texturaNombreJorge;
    private Texture texturaNombreMarco;
    private Texture texturaNombreAna;


    public AcercaDe(Juego juego) {
        this.juego = juego;

    }

    @Override
    public void show() {
        crearCamara();
        batch = new SpriteBatch();
        // Cargar toda las texturas
        cargarTexturas();

        Gdx.input.setInputProcessor(new ProcesadorEntrada());


    }

    private void crearCamara() {
        camera = new OrthographicCamera(MenuPrincipal.ANCHO, MenuPrincipal.ALTO);
        camera.position.set(MenuPrincipal.ANCHO/2,MenuPrincipal.ALTO/2,0);
        camera.update();
        viewport = new StretchViewport(MenuPrincipal.ANCHO,MenuPrincipal.ALTO,camera);
    }

    private void cargarTexturas() {
        texturaAcercaDe = new Texture("FondoAcercaDe.png");
        texturaNombreLuis = new Texture("Boton Acerca de Luis.png");
        texturaNombreCharlotte = new Texture("Boton Acerca de Charlotte.png");
        texturaNombreJorge = new Texture("Boton Acerca de Jorge.png");
        texturaNombreMarco = new Texture("Boton Acerca de Marco.png");
        texturaNombreAna = new Texture("Boton Acerca de Ana.png");
    }

    @Override
    public void render(float delta) {
        /*
        Gdx.gl.glClearColor(1,0.4f,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        */
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        // Dibujar elementos del juego.
        batch.draw(texturaAcercaDe,0 ,0);
        batch.draw(texturaNombreLuis, 120,1000);
        batch.draw(texturaNombreCharlotte,220,800);
        batch.draw(texturaNombreMarco,120, 600);
        batch.draw(texturaNombreAna,220, 400);
        batch.draw(texturaNombreJorge,120,200);
        batch.end();

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
            juego.setScreen(new MenuPrincipal(juego));
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
