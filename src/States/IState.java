package src.States;

import src.Graphics.Renderer;

interface IState {
    void Init();
    void Update(double elapsedTime);
    void Render(Renderer graphics);
    void OnEnter(String[] params);
    void OnExit();
    String check();
}