import java.util.HashMap;
import java.util.Map;

public class StateMachine {
    Map<String, IState> mStates = new HashMap();
    IState mCurrentState = new EmptyState();
    String mCurrentStateID;

    public String Update(double elapsedTime)
    {

        mCurrentState.Update(elapsedTime);
        String act = mCurrentState.check();
        if (act != null) {
            return act;
        }

        return null;
    }

    public void Render(GraphicsClass graphics)
    {
        mCurrentState.Render(graphics);
    }

    public void Change(String stateName)
    {
        mCurrentState.OnExit();
        mCurrentState = mStates.get(stateName);
        mCurrentStateID = stateName;
        mCurrentState.OnEnter(new String[] {""});
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

    public void Quit() {
        mCurrentState.OnExit();
    }

    public void List() {
        System.out.println("Current States:");

        for (Map.Entry<String,IState> entry : mStates.entrySet()) {
            String key = entry.getKey();

            System.out.println(key);
        }

        System.out.println();
    }
}
