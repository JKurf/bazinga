import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by graf on 3/5/2016.
 */
public class BattleState implements IState {
    boolean Initialized = false;
    String action = null;

    Entity e;

    public BattleState(Player P, Entity E) {
        //System.out.println(ID);
        System.out.println(P.getID() + " Battling " + E.getID());
        e = E;
    }

    @Override
    public void Init() {
        if(!Initialized) {


            Initialized = true;
        }
    }

    @Override
    public void Update(double elapsedTime) {
        if(InputClass.keyPress(GLFW_KEY_SPACE)) {
            e.location.setX(-20);
            action = "resume";
        }
    }

    @Override
    public void Render(GraphicsClass graphics) {
        graphics.drawTextMenu("press space to win!", GraphicsClass.WIDTH/2, GraphicsClass.HEIGHT/4, false);
    }

    @Override
    public void OnEnter(String[] params) {

    }

    @Override
    public void OnExit() {

    }

    @Override
    public String check() {
        if(action != null) {
            return action;
        }

        return null;
    }
}
