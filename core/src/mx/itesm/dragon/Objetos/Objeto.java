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
    private float x;
    private float y;

    public Objeto() {
    }

    public Objeto(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
