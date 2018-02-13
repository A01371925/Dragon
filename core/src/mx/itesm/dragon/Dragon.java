package mx.itesm.dragon;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by jorge on 13/02/2018.
 */

public class Dragon extends Objeto {
    public Dragon(Texture textura, float x, float y) {
        super(textura, x, y);
    }

    public void dibujar(SpriteBatch batch){
        sprite.draw(batch);
    }

}
