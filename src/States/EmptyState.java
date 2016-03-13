package src.States;

import src.Graphics.Renderer;

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
    public void Render(Renderer graphics) {

    }

    @Override
    public void OnEnter(String[] params) {

    }

    @Override
    public void OnExit() {

    }

    @Override
    public String check() {
        return null;
    }
}