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
    private GlyphLayout glyph;
    private String mensaje;

    public Texto(){
        font = new BitmapFont(Gdx.files.internal("fuenteScore.fnt"));
        glyph = new GlyphLayout();
    }

    public void mostrarMensaje(SpriteBatch batch, String mensaje, float x, float y){
        glyph.setText(font, mensaje);
        this.mensaje = mensaje;
        font.draw(batch, glyph, x - glyph.width / 2, y);

    }

    public BitmapFont getFont() {
        return font;
    }

    public GlyphLayout getGlyph() {
        return glyph;
    }


}
