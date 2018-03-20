package mx.itesm.dragon.Objetos;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Actualizar {

    void render(SpriteBatch batch);
    void mover(float dy);
}
