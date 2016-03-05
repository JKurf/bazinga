import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by graf on 3/5/2016.
 */
public class BattleState implements IState {
    boolean Initialized = false;
    String action = null;

    Mob e;

    public BattleState(Player P, Mob E) {
        //System.out.println(ID);
        System.out.println("Battling ");
        e = E;
        System.out.println("Enemy Health: " + e.getHealth());
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
                e.damage(15);
                System.out.println("Enemy Health: " + e.getHealth());

                if(e.getHealth() <= 0) {
                    e.location.setX(-10);
                    action = "resume";
                    System.out.println("dead");
            }
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
