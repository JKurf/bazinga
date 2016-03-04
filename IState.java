interface IState {
    void Init();
    void Update(double elapsedTime);
    void Render(GraphicsClass graphics);
    void OnEnter(String[] params);
    void OnExit();
    String check();
}