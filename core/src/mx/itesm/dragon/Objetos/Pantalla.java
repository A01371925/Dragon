package mx.itesm.dragon.Objetos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class Pantalla extends Objeto implements Screen{

    // Dimensiones.
    public static final float ANCHO = 720;
    public static final float ALTO = 1280;

    // Atributos disponibles solo en las subclases.
    // Todas las pantallas tienen una cámara y una vista.
    protected OrthographicCamera camara;
    protected Viewport vista;

    // Todas las pantallas dibujan.
    protected SpriteBatch batch;

    public Pantalla() {
        // Crea la cámara con las dimensiones del mundo.
        camara = new OrthographicCamera(ANCHO, ALTO);
        camara.position.set(ANCHO / 2, ALTO /2,0);
        camara.update();
        // Crea la vista que escala los elementos gráficos.
        vista = new StretchViewport(ANCHO, ALTO, camara);
        // Crea el objeto que administra los trazos gráficos
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camara.combined);
    }

    // Borra la pantalla con fondo negro.
    protected void borrarPantalla() {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    protected void moverCamara() {
        camara.translate(0,1);
        camara.update();
    }

    @Override
    public void resize(int width, int height) {
        vista.update(width,height);
    }

    @Override
    public void hide() {
        // Libera los recursos asignados por cada pantalla.
        // Las subclases están obligadas a sobrescribir el método dispose().
        dispose();
    }
}
