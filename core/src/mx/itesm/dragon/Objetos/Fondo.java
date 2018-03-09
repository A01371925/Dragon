package mx.itesm.dragon.Objetos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Fondo extends Dibujo{
    private Sprite imagenA;
    private Sprite imagenB;

    public Fondo() {
    }

    public Fondo(Texture texture) {
        imagenA = new Sprite(texture);
        imagenA.setPosition(0,0);
        imagenB = new Sprite(texture);
        imagenB.setPosition(0,imagenA.getHeight());
    }

    public Fondo(Texture texturaA, Texture texturaB) {
        imagenA = new Sprite(texturaA);
        imagenA.setPosition(0,0);
        imagenB = new Sprite(texturaB);
        imagenB.setPosition(0,imagenA.getHeight());
    }

    public void mover(float dy) {
        imagenA.setY(imagenA.getY() - dy);
        imagenB.setY(imagenB.getY() - dy);

        // Actualizar posiciones.
        if (imagenA.getY() <= -imagenA.getHeight()) {
            imagenA.setY(imagenB.getY() + imagenB.getHeight());
        } else if (imagenB.getY() <= -imagenB.getHeight()) {
            imagenB.setY(imagenA.getY() + imagenA.getHeight());
        }
    }

    public void render(SpriteBatch batch) {
        imagenA.draw(batch);
        imagenB.draw(batch);
    }
}
