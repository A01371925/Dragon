package mx.itesm.dragon.Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

import mx.itesm.dragon.Objetos.Fondo;
import mx.itesm.dragon.Objetos.Personaje;

public class PantallaJugar extends Pantalla {

    private static final float ALTO_MAPA = 2560;
    private final Juego juego;

    // Fondo.
    private Fondo fondo;

    // Objetos.
    private Personaje personaje;

    public PantallaJugar(Juego juego) {
        this.juego = juego;
    }

    @Override
    public void show() {
        fondo = new Fondo(new Texture("fondoNivel1.png"));
        personaje = new Personaje(new Texture("Dragon.png"),ANCHO * 3f,0);
        personaje.sprite.setPosition(ANCHO / 2 - personaje.sprite.getWidth() / 2, 0);
        // Indica quién escucha y atiende eventos.
        Gdx.input.setInputProcessor(new ProcesadorEntreada());
    }

    @Override
    public void render(float delta) {
        // ACTUALIZAR.
        actualizarObjetos(delta);
        actualizarCamara();
        moverCamara();

        // DIBUJAR.
        borrarPantalla();
        batch.begin();
            // Dibujar elementos de la pantalla.
            fondo.render(batch);
            personaje.render(batch);
        batch.end();
    }

    private void actualizarCamara() {
        // Depende de la posición del personaje. Siempre sigue al personaje
        float posY = personaje.sprite.getY();
        // Primera mitad de la pantalla
        if (posY < ALTO/2 ) {
            camara.position.set(ANCHO/2, ALTO/2, 0);
        } else if (posY > ALTO_MAPA - ANCHO/2) {   // Última mitad de la pantalla
            camara.position.set(camara.position.x,ALTO_MAPA-ANCHO/2,0);
        } else {    // En 'medio' del mapa
            camara.position.set(camara.position.x,posY,0);
        }
        camara.update();
    }

    private void actualizarObjetos(float delta) {
        fondo.mover(delta * 100);
        personaje.sprite.setY(personaje.sprite.getY());
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    class ProcesadorEntreada implements InputProcessor {

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
            return false;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            Vector3 v = new Vector3(screenX, screenY, 0);
            camara.unproject(v);
            personaje.sprite.setX(v.x);
            Gdx.app.log("touchDra", "x=" + v.x);
            return true; // Ya se procesó el evento
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
