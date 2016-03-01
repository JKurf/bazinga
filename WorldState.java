import java.awt.event.KeyEvent;

public class WorldState implements IState{
    World world;

    @Override
    public void Init(GraphicsClass graphics) {
        world = new World("TestMap");
        //graphics.setWorld(world);
    }

    @Override
    public void Update(float elapsedTime) {

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
