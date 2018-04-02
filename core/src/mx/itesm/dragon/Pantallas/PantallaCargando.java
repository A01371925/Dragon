package mx.itesm.dragon.Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import mx.itesm.dragon.Juego;
import mx.itesm.dragon.Objetos.Pantalla;

/**
 * Muestra una pantalla inicial durante cierto tiempo.
 */

class PantallaCargando extends Pantalla
{
    private Juego juego;
    private float tiempo;   // Tiempo transcurrido
    private Texture texturaReloj;   // Imagen que se muestra

    public PantallaCargando(Juego juego) {
        this.juego = juego;
    }

    // Se ejecuta cuando esta pantalla es la principal del juego
    @Override
    public void show() {
        tiempo = 0;
        texturaReloj = new Texture("reloj.png");
    }

    @Override
    public void render(float delta) {
        borrarPantalla(0.6f, 0.7f, 0.3f);
        // Dibuja
        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        batch.draw(texturaReloj, Pantalla.ANCHO/2-texturaReloj.getWidth()/2,
                Pantalla.ALTO/2-texturaReloj.getHeight()/2);
        batch.end();
        // Actualiza
        tiempo += Gdx.graphics.getDeltaTime();  // Acumula tiempo
        if (tiempo>=1) {
            juego.setScreen(new PJP(juego));
        }
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
}