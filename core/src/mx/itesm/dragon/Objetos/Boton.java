package mx.itesm.dragon.Objetos;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Boton extends Dibujo {


    public Boton(Texture textura, float x, float y) {
        super(textura, x, y);
    }

    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }
}
