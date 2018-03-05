package mx.itesm.dragon.Objetos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Representa un objeto que forma parte del juego (pelota, raqueta, etc.)
 *
 * Created by jorge on 13/02/2018.
 */

public class Objeto {
    // Sprite que representa gráficamente el objeto
    public Sprite sprite;

    public Objeto() {
    }
    // Constructor para crear el objeto con cierta textura y posición
    public Objeto(Texture textura, float x, float y) {
        sprite = new Sprite(textura);
        sprite.setPosition(x, y);
    }
}
