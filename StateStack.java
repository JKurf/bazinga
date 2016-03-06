import java.util.*;

public class StateStack {
    Stack<IState> mStack = new Stack<>();
    IState mCurrentState = new EmptyState();

    public String Update(double elapsedTime)
    {
        mStack.peek().Update(elapsedTime);
        String act = mStack.peek().check();
        if (act != null) {
            return act;
        }

        return null;
    }

    public void Render(GraphicsClass graphics)
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

    /*
    public void Change(String stateName)
    {
        //mStack.peek().OnExit();
        //mCurrentState = mStates.get(stateName);
        //mCurrentStateID = stateName;
        //mCurrentState.OnEnter(new String[] {""});
    }

    public void Change(String stateName, String[] params)
    {
        mCurrentState.OnExit();
        mCurrentState = mStates.get(stateName);
        mCurrentStateID = stateName;
        mCurrentState.OnEnter(params);
    }

    public void Add(String name, IState state)
    {
        mStates.put(name, state);
        mStates.get(name).Init();
    }

    public void List() {
        System.out.println("Current States:");

        for (Map.Entry<String,IState> entry : mStates.entrySet()) {
            String key = entry.getKey();

            System.out.println(key);
        }

        System.out.println();
    }
    */
}
