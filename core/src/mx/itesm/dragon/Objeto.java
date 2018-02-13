package mx.itesm.dragon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Representa un objeto que forma parte del juego (pelota, raqueta, etc.)
 *
 * Created by jorge on 13/02/2018.
 */

public class Objeto {
    // Sprite que representa gráficamente el objeto
    protected Sprite sprite;

    // Constructor para crear el objeto con cierta textura y posición
    public Objeto(Texture textura, float x, float y){
        sprite = new Sprite(textura);
        sprite.setPosition(x, y);
    }

}
