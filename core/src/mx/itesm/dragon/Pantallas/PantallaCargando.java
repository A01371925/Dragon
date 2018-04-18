package mx.itesm.dragon.Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;

import mx.itesm.dragon.Juego;
import mx.itesm.dragon.Objetos.AnimatedImage;
import mx.itesm.dragon.Objetos.Dragon;
import mx.itesm.dragon.Objetos.Fondo;
import mx.itesm.dragon.Objetos.Pantalla;

/**
 * Muestra una pantalla inicial durante cierto tiempo.
 */

class PantallaCargando extends Pantalla
{
    private Juego juego;
    private float tiempo;   // Tiempo transcurrido

    private Stage stage;
    private Dragon framesDragon;
    private Fondo fondo;
    private AnimatedImage dragon;

    public PantallaCargando(Juego juego) {
        this.juego = juego;
    }

    // Se ejecuta cuando esta pantalla es la principal del juego
    @Override
    public void show() {
        stage = new Stage(vista);
        fondo = new Fondo(new Texture("backgrounds/loading.jpg"));
        framesDragon = new Dragon("frames/loading.png", 448, 179, 6, 0.2f);
        dragon = new AnimatedImage(framesDragon.animacion());
        tiempo = 0;
        dragon.setPosition(ANCHO / 2 - dragon.getWidth() / 2, 50);
        stage.addActor(dragon);
    }

    @Override
    public void render(float delta) {
        borrarPantalla();
        dragon.act(delta);
        batch.begin();
            fondo.render(batch);
        batch.end();
        // TTL loading screen
        tiempo += delta;  // Acumula tiempo
        if (tiempo>=3) {
            juego.setScreen(new PJP(juego));
        }
        stage.draw();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}