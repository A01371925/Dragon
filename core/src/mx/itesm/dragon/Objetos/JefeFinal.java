package mx.itesm.dragon.Objetos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;

public class JefeFinal {

    private static final int TILEWIDTH = 331;
    private static final int TILEHEIGHT = 366;
    private static final int TEXTURE_REGION = 3;
    private static final float SPEED = 0.125f;
    private int vida = 5;

    private Texture jefeFinal;
    private Animacion animacion;

    public JefeFinal(String textura) {
        jefeFinal = new Texture(textura);
        animacion = new Animacion(jefeFinal, TILEWIDTH, TILEHEIGHT, TEXTURE_REGION, SPEED);
    }

    public Animation animacion() {
        return animacion.animacionVertical();
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
}
