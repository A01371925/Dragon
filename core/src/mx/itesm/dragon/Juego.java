package mx.itesm.dragon;

import com.badlogic.gdx.Game;

import mx.itesm.dragon.Pantallas.PantallaMenuPrincipal;

public class Juego extends Game{

    @Override
    public void create() {
        // Pone la pantalla inicial.
        setScreen(new PantallaMenuPrincipal(this));
    }
}
