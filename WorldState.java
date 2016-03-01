import java.awt.event.KeyEvent;

public class WorldState implements IState{
    World world;

    public WorldState(String worldName) {
        world = new World(worldName);
    }

    @Override
    public void Init(GraphicsClass graphics) {
        //world = new World("TestMap");
        //graphics.setWorld(world);
    }

    @Override
    public void Update(double elapsedTime) {

    }

    @Override
    public void Render(GraphicsClass graphics) {
        graphics.drawWorld(world);
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
