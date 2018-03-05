package mx.itesm.dragon.Pantallas;

import com.badlogic.gdx.Game;

public class Juego extends Game{

    @Override
    public void create() {
        // Pone la pantalla inicial.
        setScreen(new PantallaMenuPrincipal(this));
    }
}
