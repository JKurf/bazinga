import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputClass implements KeyListener {
    boolean keyStates[];

    public InputClass() {
        keyStates = new boolean[255];
        for(int i = 0; i < 255; i++) {
            keyStates[i] = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyStates[e.getKeyCode()] = true;

    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyStates[e.getKeyCode()] = false;
    }
}
