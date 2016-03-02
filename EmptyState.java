import java.awt.event.KeyEvent;

public class EmptyState implements IState {
    private boolean initialized = false;

    @Override
    public void Init() {
        initialized = true;
    }

    @Override
    public void Update(double elapsedTime) {

    }

    @Override
    public void Render(GraphicsClass graphics) {

    }

    @Override
    public void OnEnter(String[] params) {

    }

    @Override
    public void OnExit() {

    }

    @Override
    public void ProcessInput(KeyEvent e) {

    }
}