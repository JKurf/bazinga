import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class InputClass implements KeyListener {
    boolean[] keyStates = new boolean[255];

    public InputClass() {
        Arrays.fill(keyStates, Boolean.FALSE);
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