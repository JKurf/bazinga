package src.States;

import src.Graphics.Renderer;

import java.util.*;

public class StateStack {
    public Stack<IState> mStack = new Stack<>();
    public IState mCurrentState = new EmptyState();

    public String Update(double elapsedTime)
    {
        mStack.peek().Update(elapsedTime);
        String act = mStack.peek().check();
        if (act != null) {
            return act;
        }

        return null;
    }

    public void Render(Renderer graphics)
    {
        mStack.peek().Render(graphics);
    }

    public IState Pop() {
        return mStack.pop();
    }

    public void Push(IState state) {
        state.Init();
        mStack.push(state);
    }

    public void Quit() {
        mCurrentState.OnExit();
    }
}
