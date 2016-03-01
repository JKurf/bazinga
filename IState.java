import java.awt.event.KeyEvent;

interface IState {
    void Init(GraphicsClass graphics);
    void Update(double elapsedTime);
    void Render(GraphicsClass graphics);
    void OnEnter(String[] params);
    void OnExit();
    void ProcessInput(KeyEvent e);
}