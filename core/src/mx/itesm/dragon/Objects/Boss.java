package mx.itesm.dragon.Objects;

import com.badlogic.gdx.graphics.Texture;

public class Boss {

    private static final int TILEWIDTH = 331;
    private static final int TILEHEIGHT = 366;
    private static final int TEXTURE_REGION = 3;
    private static final float SPEED = 0.125f;
    private int vida = 15;

    private Texture jefeFinal;
    private AnimationGame animationGame;

    public Boss(String textura) {
        jefeFinal = new Texture(textura);
        animationGame = new AnimationGame(jefeFinal, TILEWIDTH, TILEHEIGHT, TEXTURE_REGION, SPEED);
    }

    public com.badlogic.gdx.graphics.g2d.Animation animacion() {
        return animationGame.animacionVertical();
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
}
