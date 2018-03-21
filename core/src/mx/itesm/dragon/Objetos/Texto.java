package mx.itesm.dragon.Objetos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by jorge on 10/02/2018.
 */

public class Texto {
    private BitmapFont font;

    public Texto(){
        font = new BitmapFont(Gdx.files.internal("fuenteScore.fnt"));
    }

    public void mostrarMensaje(SpriteBatch batch, String mensaje, float x, float y){
        GlyphLayout glyph = new GlyphLayout();
        glyph.setText(font, mensaje);
        float anchoTexto =  glyph.width;
        font.draw(batch, glyph, x-anchoTexto/2, y);
    }
}
