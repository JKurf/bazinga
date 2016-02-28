//#bangpoundben

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JFrame implements KeyListener {

    GraphicsClass graphics = new GraphicsClass(this);
    StateMachine SM = new StateMachine();

    MenuState menu = new MenuState();

    World mainWorld = new World("TestMap");

    boolean finished = false;

    public Game() {
        super("Awesome Title");
        addKeyListener(this);
    }

    public void paint(Graphics g) {
        graphics.paint(g);
    }

    public void Init() {
        SM.Add("Menu", menu);
        SM.Change("Menu", new String[]{"A", "B"});
        graphics.Init();
        graphics.setWorld(mainWorld);
    }

    public Boolean Update() {
        SM.Update(0.0f);
        return finished;
    }

    public void Render() {
        SM.Render(graphics);
        graphics.Render();
    }

    public void Quit() {
        SM.Quit();
        graphics.Quit();
        System.out.println("Quitso");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == 'q') {
            finished = true;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
