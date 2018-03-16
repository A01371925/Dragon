package mx.itesm.dragon.Objetos;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Actualizar {

    public void render(SpriteBatch batch);
    public void mover(float dy);
}
