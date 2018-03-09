package mx.itesm.dragon.Objetos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by LEDNR on 09/03/18.
 */

public class Boton extends Dibujo {

    private boolean active;

    public Boton(Texture textura, float x, float y) {
        super(textura, x, y);
        this.active = false;
    }

    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
