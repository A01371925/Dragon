package mx.itesm.dragon.Objetos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Dibujo extends Objeto {

    public Sprite sprite;

    public Dibujo () {
    }

    public Dibujo(Texture textura, float x, float y) {
        super(x, y);
        sprite = new Sprite(textura);
        sprite.setPosition(x,y);
    }
}
