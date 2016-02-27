import java.awt.event.KeyEvent;

interface IState {
    void Update(float elapsedTime);
    void Render(GraphicsClass graphics);
    void OnEnter(String[] params);
    void OnExit();
    void ProcessInput(KeyEvent e);
}