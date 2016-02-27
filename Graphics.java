import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Graphics extends JFrame implements KeyListener {
    Graphics() {
        this.addKeyListener(this);
        this.setSize(800,600);
        this.setVisible(true);
    }

    public void keyPressed(KeyEvent e) {
        // Prints the key code to the console.
        //System.out.println(e.getKeyCode());
    }

    public void keyReleased(KeyEvent e) {
        // Do stuff.
    }

    public void keyTyped(KeyEvent e) {
        // Do stuff.
    }

    public void Init() {

    }

    public void Quit() {

    }

    public void DrawSquare() {

    }
}