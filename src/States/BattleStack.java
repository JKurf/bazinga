package src.States;

import src.Ent.Entity;
import src.Graphics.Renderer;

import java.util.*;

public class BattleStack {
    public Stack<IBattleState> mStack = new Stack<>();

    public BattleStack() {
        mStack.push(new EmptyBattleState());
    }

    public String Update(double elapsedTime)
    {
        if(!mStack.empty()) {
            mStack.peek().Update(elapsedTime);
            String act = mStack.peek().check();

            if (act != null) {
                return act;
            }
        }

        return null;
    }

    public void Render(Renderer graphics)
    {
        if(!mStack.empty()) {
            for(int n = 0; n < mStack.size(); n ++) {
                mStack.peek().Render(graphics, n);
            }
        }
    }

    public IBattleState Pop() {
        IBattleState top = mStack.pop();
        top.Execute();
        return top;
    }

    public void Push(IBattleState state, Entity A, Entity B) {
        state.Init(A, B);
        mStack.push(state);
    }
}
